# Covid19_SearchEngine

### Implementing a search engine of articles related to Covid-19. 

Using datasets from **Kaggle.com** related to Covid-19 (and in general health issues) and given some key-words, returning articles that match best with the search word/s  -words are located at title or at body text(content).

Datasets are found at url: 

https://www.kaggle.com/jannalipenkova/covid19-public-media-dataset

This file contains more than 380,000 online articles with full texts which were scraped from online media in the timespan January 1 - December 31, 2020 from 65 English-language websites.

So running the **get_articles.py** script, located at 'Covid19_SearchEngine/scripts/get_articles.py', getting those documents whose their titles are referenced to keywords like 'covid', 'sars', 'coronavirus', 'virus', 'pneumonia', 'flu', 'epidemic'. We are collecting 1000 documents and moving them to a new folder path to work with. Each document is being saved as txt file by its title   -> ex 10 year Treasury yield falls below 1 7 amid coronavirus fears.txt

Sample of documents are at 'Covid19_SearchEngine/articles' and the file covid19_articles.zip has all the documents collected.


### Steps

Each txt file has fields like author, date, domain, title, url, topic_area and content. But at the creation of the documents will be stored the fields author, title, content and date saved as TextFields and then adding the documents to **IndexWriter()** will be implemented once and will be stored at disk memory using inverted index scoring (an inverted index uses the tokens as the lookup key to find the documents which contains that token), and maps it. 

The Search User Interface is implemented with **GUI search box and JavaFX**. The given word/s are being searched at fields title and content(text). 
All queries are done by **StandardAnalyzer()** and **QueryBuiler()**.

The results will be show at GUI as 10 results per page.
