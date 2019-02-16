import json
import urllib.request


#contents = urllib.request.urlopen("http://api.giphy.com/v1/gifs/search?q=quora&api_key=dc6zaTOxFJmzC").read()
#jsonContent = json.loads(contents)

#print(jsonContent['data'][0]['images']['fixed_height']['url'])

data = json.loads(urllib.request.urlopen("http://api.giphy.com/v1/gifs/search?q=ryan+gosling&api_key=Oroe1G0gBq2z6n2lD0fA5r2UC3SNA9u6&limit=1").read())
gifs = json.dumps(data, sort_keys=True, indent=4)
out = data['data']
print(data)
