# Corona Search

### Implementing a search engine of articles related to Covid-19.
This project is part of the `Information Retrieval` course, CSE Dept. of University of Ioannina, Greece.

Contributors : 
<a href="https://github.com/vaggelisbarb">
    Evangelos Barbalias
</a> and <a href="https://github.com/dimitriszrv">
    Dimitrios Zervas
</a>
   
## Folder info
- `JavaFX/javafx-sdk-16` : JavaFx jars 
- `covid19_articles` : Contains the collected txt files with reference to covid19 
- `deliverables` : Contains the report and a demo video
- `indexes` : Folder in which the indexes are stored
- `lib`: Lucene jar files (lucene-core, lucene-queries, ...)
- `scripts`: Script for pre-processing and collection of datasets
- `src` : Contains the source code of the project
- `uml` : Umls

## About

Using datasets from **Kaggle.com** related to Covid-19 (and in general health issues) and given some key-words, returning articles that match best with the search word/s  -words are located at title or at body text(content).

Datasets are found at url: 

https://www.kaggle.com/jannalipenkova/covid19-public-media-dataset

_This file contains more than 380,000 online articles with full texts which were scraped from online media in the timespan January 1 - December 31, 2020 from 65 English-language websites._

So running the **get_articles.py** script, located at '**Covid19_SearchEngine/scripts/get_articles.py**', getting those documents whose their titles are referenced to keywords like 'covid', 'sars', 'coronavirus', 'virus', 'pneumonia', 'flu', 'epidemic'. We are collecting 1000 documents and moving them to a new folder path to work with. Each document is being saved as txt file by its title   
-> ex 10_year_Treasury_yield_falls_below_1_7_amid_coronavirus_fears.txt

Documents are found at '**Covid19_SearchEngine/articles**'.

## Demo

![l1](https://user-images.githubusercontent.com/17187213/119989894-63005100-bfd0-11eb-9f3c-31fdbf4b0870.png)

Given some words to search (ex sars), the results are shown as above, the user has the ability to choose the sort by date and the history searches are being saved also.

![l3](https://user-images.githubusercontent.com/17187213/119990724-5af4e100-bfd1-11eb-9e3b-774e719aae33.png)

### Steps ( check deliverables/2766_2894_report.pdf )

Each txt file has fields like author, date, domain, title, url, topic_area and content. But at the creation of the documents will be stored the fields author, title, content and date saved as TextFields and then adding the documents to **IndexWriter()** will be implemented once and will be stored at disk memory using inverted index scoring (an inverted index uses the tokens as the lookup key to find the documents which contains that token), and maps it. 

The Search User Interface is implemented with **GUI search box and JavaFX**. The given word/s are being searched at fields title and content(text). 
All queries are done by 'StandardAnalyzer()' and 'QueryBuiler()'.

The results will be show at GUI as 10 results per page.
