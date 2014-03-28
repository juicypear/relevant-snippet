RelevantSnippet
===============

Finds the most relevant snippet for a document and highlights all the query terms that appear in the snippet (like highlights on a search page)


One highlighting example is:
>>> highlight_doc( “I like fish. Little star’s deep dish
pizza sure is fantastic. Dogs are funny.”, “deep dish
pizza”)

“Little star’s [[HIGHLIGHT]]deep dish pizza[[ENDHIGHLIGHT]]
sure is fantastic.”

def highlight_doc(doc, query): """

Args: doc - Document to be highlighted (string) query - The
search query (string)

Returns: The the most relevant snippet with the query terms
highlighted (string)
"""
