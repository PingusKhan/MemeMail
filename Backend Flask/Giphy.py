import json
import urllib.request

#take user input for topic and how many results they want
topic = input("Please enter gif search: \n").rstrip().lower().replace(" ","+")
n = input("How many results would you like? \n")

urlgen = "http://api.giphy.com/v1/gifs/search?q="+str(topic)+"+meme&api_key=Oroe1G0gBq2z6n2lD0fA5r2UC3SNA9u6&limit="+str(n)

#json format of GIF Object
data = json.loads(urllib.request.urlopen(urlgen).read())

#formatted to look nice
gifs = json.dumps(data, sort_keys=True, indent=4)

#adding all the generated urls to a list and printing
urls = list()
for i in range(len(data['data'])):
    urls.append(data['data'][i]['images']['fixed_height']['url'])
print(urls)

out1 = data['data'][0]['images']['fixed_height']['url']
