# Covid19_SearchEngine

### Implementing a search engine of articles related to Covid-19. 

Using datasets from **COVID-19 Open Research Dataset(CORD-19)** related to Covid-19 (and in general health issues) and given some key-words, returning aticles that match best with the search word/s. 

Datasets are found at url: 

https://www.semanticscholar.org/cord19 > Download CORD-19 > All releases.

or direct link: 
https://ai2-semanticscholar-cord-19.s3-us-west-2.amazonaws.com/historical_releases.html

We have collected datasets at .json format from date: 2021-03-29.

### Steps

We break the data to title and text-body, so the **BuildDocument()** will get json documents datasets and each document will be splitted into fields, as title and text. Then using **StopAnalyzer()** to filter- clean data and convert the text into token than can be added to the index. Then adding the documents to **IndexWriter()** will be implemented once and will be stored at disk memory using inverted index scoring (an inverted index uses the tokens as the lookup key to find the documents which contains that token), and maps it.


The Search User Interface will be implemented with **GUI search box**. At searching the user will have the option if he wants to search by the title of the document or at all document at once.  
All queries of the index are done through the **IndexSearcher()**, returns documents ranked by either the relevance to the query.
**QueryParser()** parses a textual representation of a query and searches the index for results. **Query()** has many types of queries (ex WildcardQuery, BooleanQuery etc) and each type of query provides a unique way of searching the index and contains the results.

The results will be show at GUI as 10 results per page.
