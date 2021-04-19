import json
import os
import shutil

# where extracted json files are located
path_to_json = '/home/pc/Desktop/document_parses/pdf_json/'

# keywords to search in title
matches=["Covid","covid","COVID","SARS-CoV-2","sars-cov-2","coronavirus","Coronavirus","CORONAVIRUS"]

# get all files ending with '.json' format
json_files = [pos_json for pos_json in os.listdir(path_to_json) if pos_json.endswith('.json')]

# counter for articles
total_articles = 0

# store the '.json' files related with Covid
covid_json =[]

# for each json file, we search at title  
for each_json in json_files:
   
    with open(path_to_json+each_json) as json_file:
        # load json file
        data = json.load(json_file)
        
        # get title
        get_title = data["metadata"]["title"]
        
        # check if title matches with any keyword like
        # "Covid","covid","COVID","SARS-CoV-2","sars-cov-2","coronavirus","Coronavirus" or "CORONAVIRUS"
        # and append the json file to covid_json[] 
        if any(x in get_title for x in matches):
            print("Title:",get_title)
            print("Found at ",each_json,"\n")
            total_articles+=1
            covid_json.append(each_json)

    # if covid articles are 1000, then stop collecting  
    if total_articles == 1001:
        break

# path where extracted json files are located
original_path = "/home/pc/Desktop/document_parses/pdf_json/"

# new path where covid articles will be stored
target_path = "/home/pc/Desktop/covid_articles/"

for article in covid_json:
    original = original_path + article
    target = target_path + article
    
    # move the articles to new path
    shutil.move(original,target)
    
print("\n\nFound {} articles related to 'Covid' and moved to folder path: '{}' \n".format(total_articles-1,target_path))   
