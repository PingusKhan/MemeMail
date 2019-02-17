import sendgrid
from sendgrid.helpers.mail import *
from collections import namedtuple
from datetime import *

APIKEY = 'MY_API_KEY'
# ^^^ cant display api key cuz it gets my accound suspended lmao
sg = sendgrid.SendGridAPIClient(APIKEY)

Subscriptions = {}
Subscriber = namedtuple('Subscription', ['Genre', 'Interval',
                'Sender', 'Date'])


def add_new_subscription(recipient: str, genre: str, interval: timedelta, 
                        sender: str) -> None:
    # Takes in the input of recipient of the subscription, genre of memes, interval in which they receive
    # the memes, and who is the sender; Organizes multiple subscriptions as a dictionary of Subscriber
    # namedtuples, with the recipient as the key
    Subscriptions[recipient] = Subscriber(Genre = genre,
                                        Interval = interval,
                                        Sender = sender,
                                        Date = datetime.utcnow())


def is_it_meme_time(subscriptions: dict) -> None:
    # Checks if it is time for memes to be sent to the recipient based on the interval that was selected
    # Note: this method will probably be run hourly in the app to constantly check all subscriptions 
    for subscriber in subscriptions:
        meme_time = subscriptions[subscriber].Date + subscriptions[subscriber].Interval
        if meme_time <= datetime.utcnow():  
            it_be_meme_time(subscriptions[subscriber], subscriber)
    

def it_be_meme_time(subscriber: Subscriber, recipient: str) -> None:
    # The method that sends the actual meme! Uses the Sendgrid API
    sg = sendgrid.SendGridAPIClient(APIKEY)

    from_email = Email(subscriber.Sender)
    to_email = Email(recipient)
    subject = "Meme Mail brought straight to YOU by Team TBD"
    # vvvv this line is the one responsible for the body of the meme mail!!!
    content = Content("text/html", "<h1>You like memes?</h1><img src={} alt=dank_meme.jpg style=width:auto;height:auto></img>".format(get_meme(subscriber.Genre)))

    mail = Mail(from_email, subject, to_email, content)
    response = sg.client.mail.send.post(request_body=mail.get())


def get_meme(genre: str) -> str:
    # Gets the url of the meme to display within the email
    # Note: atm it returns only a singular url because it needs Giphy implementation...
     return "https://i.ytimg.com/vi/p-Ww_oTfLcA/hqdefault.jpg"

