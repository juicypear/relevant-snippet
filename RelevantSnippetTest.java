
import org.junit.*;
import static org.junit.Assert.*;


import java.util.List;
import java.util.ArrayList;

public class RelevantSnippetTest 
{
	public static final String doc1 = "Service here was great. We had a fabulous and friendly waiter. We tried the Classic, Mediterranean Chicken, Vegetable, and Little Star deep dish pizzas. The Little Star was the best followed by the Classic. The pizza was good but I honestly preferred the BBQ Chicken deep dish pizza I had at Patxi's in Hayes Valley. However the atmosphere and service at Little Star's was much better. Also the ice cream from the local creamery in the Mission (I wish we had heard which one it was) was delicious.";

	public static final String doc2 = "This is by far the best Pizza I ever had. There is a variety of gourmet beer and fine wine for a reasonable pizza. Try the deep Dish pizza and customize it with your toppings of choice. Bon Apetite.";

	public static final String doc3 = "The deep dish pizza here is amazing! When I order deep dish pizza at other places I usually have to wait 30-45 mins... But no here we got our pizza in about 20. How awesome!";

	public static final String doc4 = "A great place to go for deep dish pizza.";

	public static final String doc5 = "Best Deep Dish pizza I will ever try in my life.  The cornmeal crust was AMAZING.  For every 2 people I would get a 12\".  Expect a line.";

	public static final String doc6 = "When I have a (rare) craving for deep dish pizza, Little Steezy is it.  Love that cornmeal-dusted crust and juicy sausage filling. Wings are yummy too. In the great deep dish pizza rivalry between Zachary's and Little Star, I go with the upstart.";

	public static final String doc7 = "Best deep dish pizza and probably my favorite pizza in SF next to Golden Boy. In my opinion I think that little star even has zach's in Berkeley beat. Delicious!";

	public static final String doc8 = "Awesome deep dish pizza.  The crust is the best.  I'm usually a fan of the thin crust, but if I am in the mood for deep dish, this is where it is.  A must visit Mission spot.";

	public static final String doc9 = "I must say, I was never a fan of the thick or deep dish pizzas...HOWEVER, after my sister took me to Little Star, I changed my mind completely. I LOVE, absolutely LOVE their deep dish pizzas. I tried all the vegetable deep dish specialties (my sister is a vegetarian) and I was amazed! The crust is so flaky and is juicy in its buttery goodness and I REALLY RECOMMEND this place for all you pizza lovers as well as hesitant deep dish pizza eaters out there. lesson learned: DON'T HATE IT UNTIL YOU TRY IT! =) Note: The cheesecake is delicious too!";

	public static final String doc10 = "Yup, this beats Zachary's. I was quite doubtful and thought it would be overrated, but it is indeed wonderful. I bought take-out, but definitely want to eat in the restaurant some time. The ambience is super nice, kinda romantic and dimly lit. I ordered the \"little star pizza\" and split it with my friend for $20. Here's how it differs from Zachary's. 1) The crust here is more like pizza crust, less like flaky pie crust. It tastes better. 2) Zachary's pizzas are very tomato-heavy, which is great if you love tomato, but I prefer to have more other toppings on my pizza. Little Star puts a LOT of toppings but still gives that right amount of tomatoes for a deep dish pizza.";

	public static final String doc11 = "I was at my aunt's place which was a few blocks away so my dad and I decided to walk here for dinner on a Friday night.  I didn't see a whole lot of parking on the street so that could be an issue for anybody not walking.  It took a couple of minutes before anybody noticed us at the door.  There were a lot of people here and at the bar.  The lighting gradually got darker was we approached the back where our table was.  There were candles at each table so at least there was some kind of lighting to read the menu. Our waiter told us a 9 inch pizza would be enough for two people and if it were two hungry people, then an appetizer would be enough in addition to the 9 inch deep dish pizza.  We ordered two Blue Paddle Pilsner ($4.75 each), the Spicy Chicken Wings ($11), and a 9 inch pizza that had half with the Mediterranean Chicken ($19.75 for 9 inch and $25.50 for 12 inch) and the other half with the Classic ($19.50 for 9 inch and $25 for 12 inch).  We felt that should be enough for both of us. The Spicy Chicken Wings came out first and there were 10 pieces.  It was pretty tasty and good.  I didn't think it was spicy, but it was pretty hot temperature-wise.  I nearly burned my fingers trying to hold it, but I also don't have callus on my fingertips since I haven't played guitar for a long time.  The pizza came later (maybe 30 minutes after we ordered) and it looked like one topping for the whole pizza but it wasn't.  The side with the Mediterranean Chicken was good as well as the side with the Classic.  When the pizza was hot, then it tasted good; however, as it got cooler, the pizza's taste went down as well.  The Blue Paddle Pilsner was not bad and I wouldn't have known what else to order since the selection on tap was limited to beer I've never drank before. Overall, pretty good food and I would recommend trying their deep dish pizzas.";

	public static final String doc12 = "There's something profoundly special about a pizza you have to eat with a knife and fork. I've had a few of these Chicago deep-dish types now, from different places, several times each, and Little Star is just the best. I almost always get the small Little Star with artichoke hearts as an add-on. Lemme tell you bout this pizza. Usually I think tomato sauce completely take-it-or-leave-it, but this one really tastes like crushed tomatoes and is never too acidic. The spinach is fresh, the mushrooms savory, the top layer of ricotta is pleasantly creamy while the bottom layer of mozzarella is satisfyingly stretchy. I love that the artichoke hearts they use aren't over-marinated and tangy, and preserve the real flavor of the vegetable. And the crust. I always save it for last, savoring it like a dessert. This rich cornmeal crust is perfectly crispy like a cornbread muffin top. Eating it is like visiting the southern grandmother I don't have, going back to some roots that aren't mine, but still being enveloped in the warm embrace of home cooking. If it seems like I don't want my crusts, you are wrong. I want those to be the last flavor I enjoy so I can feel that sheer happiness as long as possible.";

	public static final String doc13 = "I dream about the day I am united with Little Star Pizza once again. If I were Superman, Little Star Pizza would be my kryptonite. I would live out my last days in the Fortress of Solitude happily munching on their deep dish pizza and guzzling some beer and that would be my life. Luckily, I live across the coast so my road to obesity has been side tracked...for now. I could wax poetic all day about their thick buttery crust, robust and zesty tomato sauce, mozzarella cheese, and Italian sausage. A few tips: + The deep dish will fill you up pretty quickly! + Come early, the place can fill up quick! + Wear a cave hat with a light attached to it, the atmosphere is pretty dark. + Don't plan on going out afterwards.. A siesta should be the only thing listed in your social agenda after eating here! Ugh, I've done it again. I've made myself hungry writing a Yelp review. Now to satisfy my pangs with lackluster grocery store purchased frozen \"Pizzeria Uno\" brand pizza...";

	public static final String doc14 = "the pizza taste good.. but the crust is way better at Paxti's.  The crust over here is tough and dry.";

	public static final String doc15 = " ";

	public static final String doc16 =  "What a lovely day margaret";

	public static final String query1 = "deep dish pizza";

	public static final String query2 = "taste";

	public static final String query3 = "";


	public static void main(String[] args)
	{
		testHighlight1();
		testHighlight2();
		testHighlight3();
	}


	@Test //Test with query1
	public static void testHighlight1()
	{
		int counter = 1;

		String query = query1;
		List<String> docs = new ArrayList<String>(); 

		docs.add(doc1);
		docs.add(doc2);
		docs.add(doc3);
		docs.add(doc4);
		docs.add(doc5);
		docs.add(doc6);
		docs.add(doc7);
		docs.add(doc8);
		docs.add(doc9);
		docs.add(doc10);
		docs.add(doc11);
		docs.add(doc12);
		docs.add(doc13);
		docs.add(doc14);

		System.out.println("\nTesting Highlight Doc with Query 1");

		for(String doc: docs)
		{
			System.out.println("\ndoc: " + doc);
			System.out.println("query: " + query + "\n");

			String snippet = RelevantSnippet.highlight_doc(doc, query);

			System.out.println("snippet " + counter++ + " is: " + snippet);

			assertNotNull("Assert error, snippet is null", snippet);
			assertNotSame("Assert error, snippet shouldn't be empty string", "", snippet);
		}

		docs.clear();

		docs.add(doc15);
		docs.add(doc16);

		for(String doc: docs)
		{
			System.out.println("\ndoc: " + doc);
			System.out.println("query: " + query + "\n");

			String snippet = RelevantSnippet.highlight_doc(doc, query);

			System.out.println("snippet " + counter++ + " is: " + snippet);

			assertNotNull("Assert error, snippet is null", snippet);
			assertSame("Assert error, snippet shouldn't be non-empty string", "", snippet);
		}

	}

	@Test // Test with query2
	public static void testHighlight2()
	{
		String query = query2;
		List<String> docs = new ArrayList<String>(); 

		int counter = 1;

		//Testing the docs that don't have the word taste in them, the snippet
		//should be an empty string
		docs.add(doc1);
		docs.add(doc2);
		docs.add(doc3);
		docs.add(doc4);
		docs.add(doc5);
		docs.add(doc6);
		docs.add(doc7);
		docs.add(doc8);
		docs.add(doc9);
		docs.add(doc13);
		docs.add(doc15);
		docs.add(doc16);
		
		System.out.println("\nTesting Highlight Doc with Query 2");


		for(String doc: docs)
		{
			System.out.println("\ndoc: " + doc);
			System.out.println("query: " + query + "\n");

			String snippet = RelevantSnippet.highlight_doc(doc, query);

			System.out.println("snippet " + counter++ + " is: " + snippet);

			assertNotNull("Assert error, snippet is null", snippet);
			assertSame("Assert error, snippet should be empty string", "", snippet);
		}

		docs.clear();

		//Testing the docs that do have the word taste in them, the snippet
		//shouldn't be an empty string
		docs.add(doc10);
		docs.add(doc11);
		docs.add(doc12);
		docs.add(doc14);
		
		for(String doc: docs)
		{
			System.out.println("\ndoc: " + doc);
			System.out.println("query: " + query + "\n");

			String snippet = RelevantSnippet.highlight_doc(doc, query);

			System.out.println("snippet " + counter++ + " is: " + snippet);

			assertNotNull("Assert error, snippet is null", snippet);
			assertNotSame("Assert error, snippet should not be empty string", "", snippet);
		}
	}

		

	@Test // Test with query3
	public static void testHighlight3()
	{
		String query = query3;
		List<String> docs = new ArrayList<String>(); 

		int counter = 1;

		//Testing that an empty query string will give an empty snippet string
		//and assuming it will fail because query length too small
		docs.add(doc12);
		docs.add(doc13);
		docs.add(doc14);
		docs.add(doc15);
		docs.add(doc16);

		System.out.println("\nTesting Highlight Doc with Query 3");

		for(String doc: docs)
		{
			System.out.println("\ndoc: " + doc);
			System.out.println("query: " + query + "\n");

			String snippet = RelevantSnippet.highlight_doc(doc, query);

			System.out.println("snippet " + counter++ + " is: " + snippet);

			assertNotNull("Assert error, snippet is null", snippet);
			assertSame("Assert error, snippet should be empty string", "", snippet);
		}

	}

}