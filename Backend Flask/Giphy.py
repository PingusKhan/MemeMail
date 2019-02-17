import json
import urllib.request
import urllib

import requests


###---------GIPHY----------

#take user input for topic and how many results they want
#topic = input("Please enter gif search: \n").rstrip().lower().replace(" ","+")
#n = input("How many results would you like? \n")

def give_url(topic:str) -> list:
    urlgen = "http://api.giphy.com/v1/gifs/search?q="+str(topic).rstrip().lower().replace(" ","+")+"+memes&api_key=Oroe1G0gBq2z6n2lD0fA5r2UC3SNA9u6&limit=5"

    #json format of GIF Object
    data = json.loads(urllib.request.urlopen(urlgen).read())

    #formatted to look nice
    gifs = json.dumps(data, sort_keys=True, indent=4)

    #adding all the generated urls to a list and printing
    urls = list()
    for i in range(len(data['data'])):
        urls.append(data['data'][i]['images']['fixed_height']['url'])
    
    return urls

#out1 = data['data'][0]['images']['fixed_height']['url']
'''
###-------Meme generator--------

hotmeme = "https://api.imgflip.com/get_memes"
r = requests.get(hotmeme).text ##string
j = json.loads(r)
#print(j['data'])

#Get a list of 20 hot meme template names
i = 0
top = list()
while i <20:
    top.append(j['data']['memes'][i]['name'])
    i+=1
print(top)


#https://imgflip.com/meme/Surprised-Pikachu
template = requests.get("https://imgflip.com/meme/Surprised-Pikachu")
#print(template.text)

###-------Bing image--------------

url ="https://www.bing.com/images/search?q="+top[6].lower().replace(" ","%20")+"%20memes&FORM=OIIARP"
#"https://www.bing.com/images/search?q=mocking spongebob meme funny&FORM=OIIARP"
print(url)
'''

'''
if __name__=="__main__":
    give_url("surprised pikachu",5)
'''