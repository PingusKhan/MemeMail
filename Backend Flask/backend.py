import sendgrid
from sendgrid.helpers.mail import *
from collections import namedtuple
from datetime import *
import Giphy

APIKEY = 'MY_API_KEY'
# ^^^ cant display api key cuz it gets my accound suspended lmao
sg = sendgrid.SendGridAPIClient(APIKEY)

Subscriptions = {}
Subscriber = namedtuple('Subscription', ['Genre', 'Interval', 'Sender', 'Date'])


def add_new_subscription(recipient: str, genre: str, interval: timedelta, 
                        sender: str) -> None:
    # Takes in the input of recipient of the subscription, genre of memes, interval in which they receive
    # the memes, and who is the sender; Organizes multiple subscriptions as a dictionary of Subscriber
    # namedtuples, with the recipient as the key
    Subscriptions[recipient] = Subscriber(Genre = genre, Interval = interval, Sender = sender, Date = datetime.utcnow())
    # _first_email(recipient, sender, interval)
    

def remove_old_subscription(recipient: str) -> None:
    try:
        del Subscriptions[recipient]
    except KeyError:
        pass


def is_it_meme_time(subscriptions: dict) -> None:
    # Checks if it is time for memes to be sent to the recipient based on the interval that was selected
    # Note: this method will probably be run hourly in the app to constantly check all subscriptions 
    for subscriber in subscriptions:
        meme_time = subscriptions[subscriber].Date + subscriptions[subscriber].Interval
        if meme_time <= datetime.utcnow():  
            it_be_meme_time(subscriptions[subscriber], subscriber)
            subscriptions[subscriber] = subscriptions[subscriber]._replace(Date = datetime.utcnow())    


def it_be_meme_time(subscriber: Subscriber, recipient: str) -> None:
    # The method that sends the actual meme! Uses the Sendgrid API
    sg = sendgrid.SendGridAPIClient(APIKEY)

    from_email = Email(subscriber.Sender)
    to_email = Email(recipient)
    subject = "Meme Mail brought straight to YOU by Team TBD"
    # vvvv this line is the one responsible for the body of the meme mail!!!
    memes = get_meme(subscriber.Genre)
    content = Content("text/html", "<h1>You like memes?</h1><img src={} alt=dank_meme0.jpg style=width:auto;height:auto></img><img src={} alt=dank_meme1.jpg style=width:auto;height:auto></img><img src={} alt=dank_meme2.jpg style=width:auto;height:auto></img><img src={} alt=dank_meme3.jpg style=width:auto;height:auto></img><img src={} alt=dank_meme4.jpg style=width:auto;height:auto></img>".format(memes[0], memes[1], memes[2], memes[3], memes[4]))

    mail = Mail(from_email, subject, to_email, content)
    response = sg.client.mail.send.post(request_body=mail.get())

    print(response.status_code)
    print(response.body)
    print(response.headers)


def get_meme(genre: str) -> list:
    # Gets the url of the meme to display within the email
    return Giphy.give_url(genre)


# Private Functions Below

'''
def _first_email(recipient:str, sender:str, interval: timedelta) -> bool:
    sg = sendgrid.SendGridAPIClient(APIKEY)

    from_email = Email(sender)
    to_email = Email(recipient)
    subject = "Meme Mail brought straight to YOU by Team TBD"
    content = Content("text/html", "<h1>Hello! This is Meme Mail!</h1><body>Don't want memes on a {} basis? Click the button below!<body>".format(interval))
    
    mail = Mail(from_email, subject, to_email, content)
    response = sg.client.mail.send.post(request_body=mail.get())

    data = {
        "enabled": True,
        "html_content": "<button type=button>Unsubscribe</button>",
    }

    response = sg.client.tracking_settings.subscription.patch(request_body=data)
    print(response.status_code)
    print(response.body)
    print(response.headers)


def check_subscription():
    pass
'''

if __name__ == "__main__":
    add_new_subscription("pinguskhan@gmail.com", "jojo", timedelta(hours=-1), "pinguskhan@gmail.com")
    print(Subscriptions)
    is_it_meme_time(Subscriptions)
    remove_old_subscription("pinguskhan@gmail.com")
    print(Subscriptions)
