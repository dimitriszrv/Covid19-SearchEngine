import csv
import sys 
import re 
import os 

# get path where 'covid19_articles_20201231.csv' is located
path = "/home/ze/Desktop/corona_scripts"

# create directory to store the selected articles
# define the name of the directory to be created
create_folder_path = path+"/covid19_articles/"
try:
    os.mkdir(create_folder_path)
except OSError:
    print ("")
else:
    print ("\nSuccessfully created the directory %s " % create_folder_path)


# max limit
csv.field_size_limit(sys.maxsize)

# some keywords
matches=["covid","sars","coronavirus","virus","pneumonia","flu","epidemic"]

# counter to gather 1000 documents
counter = 0

with open(path+"/covid19_articles_20201231.csv", "r") as file:
    reader = csv.reader(file)

    # skip first line 
    next(reader)

    # for each line, get data
    for row in reader:
        
        author = row[0]
        date = row[1]
        domain = row[2]
        title = row[3]
        url = row[4]
        topic_area = row[6]
        content = row[5]
        
        # check if any keyword is in title
        if any(x in title.lower() for x in matches):
            
            # remove special characters from title, because each txt is stored as 'title name'.txt
            t = re.sub("(\\W)+"," ",title)
   
            # check if the file is already there
            if not(os.path.exists(create_folder_path+t+".txt")):

                # open each txt
                covid19_articles = open(create_folder_path+t+".txt","w")
                
                # remove from content some unnecessary data
                content = re.sub(r'document.write(.*\));','',content)
                content = re.sub(r"\n|\xa0|\'|”|“",'',content)

                # write and close
                covid19_articles.write(author+"\n"+date+"\n"+domain+"\n"+title+"\n"+url+"\n"+topic_area+"\n"+content)
                covid19_articles.close()

                counter += 1
                if counter == 1000:
                    break
        
print("Found {} articles related to 'Covid' and moved to folder path: '{}' \n".format(counter,create_folder_path))  
