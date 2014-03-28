
import java.util.List;
import java.util.ArrayList;

import java.util.Set;
import java.util.HashSet;

import java.util.Queue;
import java.util.ArrayDeque;


public class RelevantSnippet
{

	public static final String doc1 = "This is by far the best Pizza I ever had. There is a variety of gourmet beer and fine wine for a reasonable pizza. Try the deep Dish pizza and customize it with your toppings of choice. Bon Apetite.";

	public static final String query1 = "deep dish pizza";

	private static boolean DEBUG = false;

	public static void main(String[] args)
	{
		if(args != null && args.length == 1 && (args[0]).equals("-debug"))
		{
			DEBUG = true;
		}
	
		String doc = doc1;
		String query = query1;

		debug("\ndoc: " + doc);
		debug("query: " + query + "\n");

		String snippet = highlight_doc(doc, query);

		debug("\n" + snippet);	
		
	}

	private static void debug(String msg)
	{
		if(DEBUG) System.out.println(msg);
	}



	/**
	 * @param doc, document to be highlighted
	 * @param query, string search query
	 * @return the most relevant snippet with the query terms
	 *  	   highlighted
	 */
	public static String highlight_doc(String doc, String query)
	{
		String snippet = "";

		int SMALLEST_QUERY_LENGTH = 3;
		int SMALLEST_DOC_LENGTH = 10;

		try
		{
			if(doc == null || query == null)
			{
				throw new IllegalArgumentException("input parameters can't be null");
			}

			if(query.length() < SMALLEST_QUERY_LENGTH  
					|| doc.length() < SMALLEST_DOC_LENGTH)
			{
				throw new IllegalArgumentException("input parameters too small");
			}

			//input params
			String[] sentences = doc.split("[.]");


			/*
			  Exclude these words from being individually scored
			  unless they are part of a larger phrase
			  the, is, at, which, and on

			  https://en.wikipedia.org/wiki/Stop_words
			*/
			Set<String> stopWords = new HashSet<String>();
			stopWords.add("the");
			stopWords.add("is");
			stopWords.add("at");
			stopWords.add("which");
			stopWords.add("on");

			//output params
			List<String> highlightedSentences = new ArrayList<String>();

			List<Integer> scores = new ArrayList<Integer>();
			
			//process relevance of sentences given query 
			//and stop words set
			process_relevance(sentences, query, stopWords, 
				scores, highlightedSentences);

			//find highest scoring relevant sequence of 
			//sentences
			snippet = find_largest_score_sequence(highlightedSentences, scores);
		}
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace(System.err);
		}

		return snippet;
	}

	/**
	 * This function scores each of the sentences in the doc by whether it contains
	 * the search query term or parts of the search query term.  The higher
	 * the score the higher the relevance.  None of these and it's scored a default -5
	 * @param String[] of doc sentences
	 * @param String query to search for
	 * @param Set<String> of words to exclude from the query token search set
	 * @returns two output parameters a list of scores and a list of the sentences
	 *			highlighted if query term(s) are found
	 */
	private static void process_relevance(String[] sentences, 
		String query, Set<String> exclusion, 
		List<Integer> scores, List<String> highlighted)
	{
		try
		{
			if(sentences == null || sentences.length == 0 
				|| query == null || exclusion == null 
				|| scores == null || highlighted == null)
			{
				throw new IllegalArgumentException("Input parameters can't be null");
			}

			int QUERY_PHRASE_SCORE_VALUE = 10;
			int QUERY_ATOM_SCORE_VALUE = 2;
			int NO_QUERY_VALUE = -5;

			//If the string query contains multiple words separated
			//by a space, fetch those
			String[] splits = query.split(" ");
			Set<String> querySet = new HashSet<String>();

			//Add the query atom terms to the querySet excluding 
			//relevant word
			//FUTURE:  Could add common synonyms of the terms
			
			for(String str: splits)
			{
				if(false == exclusion.contains(str)) 
				{
					querySet.add(str);
				}
			}

			String placeholder = "{~~~}";
			
			//loop through the sentences and assign scores
			//based on if they contain the query string or parts 
			//of the query string
			for(int i = 0; i < sentences.length; i++)
			{
				int score = 0;

				boolean queryFound = false, queryAtomFound = false;

				StringBuilder sentence = 
					new StringBuilder(sentences[i]);

				//save the state of the original query's letter case that is found in 
				//the sentence
				Queue<String> origTokensQueue = new ArrayDeque<String>();

				//we only match on lowercase version of sentence
				//which is exact clone of sentence
				String sentenceClone = (sentence.toString()).toLowerCase();

				//we only match on lowercase version of query
				//which is exact clone of query
				String queryClone = query.toLowerCase();
				
				int queryIndex = -1;

				//assign points if the sentence contains the
				//query phrase once or multiple times
				while(-1 != (queryIndex = 
					sentenceClone.indexOf(queryClone, queryIndex + 1)))
				{
					score += QUERY_PHRASE_SCORE_VALUE;

					//replace the query string with a placeholder 
					//so that we can do the second pass of 
					//individual query terms below, save original substring in queue

					String matchOrig = 
						sentence.substring(queryIndex, queryIndex + query.length());

					origTokensQueue.add(matchOrig);

					sentence.replace(queryIndex, 
						queryIndex + query.length(), 
						placeholder);

					sentenceClone = (sentence.toString()).toLowerCase();

					queryIndex += placeholder.length();
					
					if(false == queryFound) queryFound = true;
				}

				//see if the sentence (without the query string) 
				//contains any parts of the query
				for(String atom: querySet)
				{

					//we want to match only on lower case version of clone
					//but don't want to modify original clone's uppercase
					//or lowercase letters
					String atomClone = atom.toLowerCase();

					int queryAtomIndex = -1;

					while(-1 != (queryAtomIndex = 
						sentenceClone.indexOf(atomClone, queryAtomIndex + 1)))
					{
						score += QUERY_ATOM_SCORE_VALUE;

						if(false == queryAtomFound)
						{
							queryAtomFound = true;
						}

						String atomOriginal = sentence.substring(queryAtomIndex, 
												queryAtomIndex + atom.length());

						StringBuilder wrap = new StringBuilder();
						wrap.append("[[HIGHLIGHT]]");
						wrap.append(atomOriginal);
						wrap.append("[[ENDHIGHLIGHT]]");

						sentence.replace(queryAtomIndex, 
							queryAtomIndex + atom.length(), 
							wrap.toString());

						sentenceClone = (sentence.toString()).toLowerCase();

						queryAtomIndex += wrap.length();

						wrap = null;
						atomOriginal = null;
						
					}

					queryAtomIndex = -1;
					atom = null;
					atomClone = null;
				}

				//if the original query was found in the sentence, 
				//replace the placeholder(s) with the placeholder 
				//value(s) along with highlights

				if(true == queryFound && origTokensQueue.size() > 0)
				{
					int index = -1;

					while(-1 != (index = 
						sentence.indexOf(placeholder, index + 1)))
					{
						String queryProperCase = origTokensQueue.remove();

						StringBuilder wrap = new StringBuilder();
						wrap.append("[[HIGHLIGHT]]");
						wrap.append(queryProperCase);
						wrap.append("[[ENDHIGHLIGHT]]");

						sentence.replace(index, 
										index + placeholder.length(), 
										wrap.toString());

						index += wrap.length();

						wrap = null;
					}
				}

				//if neither the query string or elements of 
				//the query string were found
				//in the sentence, score a negative value
				if(false == queryAtomFound 
					&& false == queryFound)
				{
					score = NO_QUERY_VALUE;
				}

				//capture the score value back into the scores list
				scores.add(Integer.valueOf(score));

				//append sentences to list return parameter
				highlighted.add(sentence.toString());

				debug("Sentence " + i + " is: " 
					+ sentence + ", score is: " + score);

				queryFound = false;
				queryAtomFound = false;
				score = 0;
				sentence = null;
				sentenceClone = null;
				queryClone = null;
				origTokensQueue.clear();
				origTokensQueue = null;
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Return a sentence or sentences sequence that has the 
	 * largest contiguous score value given a sentence list 
	 * and score list which correspond to each other.
	 */
	private static String find_largest_score_sequence(List<String> sentences, 
					List<Integer> scores)
	{
		//If there is no large value, return the empty string
		String snippet = "";
		
		try
		{
			if(sentences == null || scores == null)
			{
				throw new IllegalArgumentException("Input parameters can't be null");
			}

			if(sentences.size() != scores.size())
			{
				throw new IllegalStateException("# sentences doesn't equal # scores");
			}

			debug("Scores are: " + scores + "\n");

			StringBuilder maxSequence = null;

			StringBuilder currentSequence = new StringBuilder();

			int i = 0;
			int maxSum = 0;
			int currentSum = 0;

			for(Integer val: scores)
			{
				if(val >= 0)
				{
					String sentence = sentences.get(i);

					if(false == sentence.equals(null))
					{
						currentSequence.append(sentence);
						currentSequence.append(". ");
						currentSum += val;
					}
				}
				else
				{
					//record the max sum value and max sequence
					if(currentSum > maxSum)
					{
						maxSum = currentSum;
						maxSequence = null;
						maxSequence = new StringBuilder(currentSequence);

				//		debug("New max sum: " + maxSum + 
				//			", New max sequence is: " + maxSequence);
					}

					currentSum = 0;

					if(currentSequence.length() > 0)
					{
						currentSequence = null;
						currentSequence = new StringBuilder();
					}
				}

				val = 0;
				i++;
			}


			//if the last sequence of the string is positive 
			//and greater than the max sum
			if(currentSum >  maxSum) 
			{
				maxSequence = null;
				maxSequence = currentSequence;
				currentSequence = null;
				maxSum = currentSum;
			}

			debug("Max sum: " + maxSum + 
					", Max sequence is: " + maxSequence + "\n");


			if(maxSequence != null) 
			{
				snippet = maxSequence.toString();
				maxSequence = null;
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace(System.err);
		}

		return snippet;
	}
}