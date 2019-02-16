import sendgrid
from sendgrid.helpers.mail import *
from collections import namedtuple
from datetime import *

APIKEY = 'MY_API_KEY'
sg = sendgrid.SendGridAPIClient(APIKEY)

Subscriptions = {}
Subscriber = namedtuple('Subscription', ['Genre', 'Interval',
                'Sender', 'Date'])

def add_new_subscription(recipient: str, genre: str, interval: timedelta, 
                        sender: str) -> None:
    Subscriptions[Email(recipient)] = Subscriber(Genre = genre,
                                        Interval = interval,
                                        Sender = Email(sender),
                                        Date = datetime.utcnow())

def is_it_meme_time(subscriptions: dict) -> None:
    for subscriber in subscriptions:
        meme_time = subscriber.Date + subscriber.Interval
        if meme_time >= datetime.utcnow():  
            it_be_meme_time(subscriber)
    
def it_be_meme_time(subscriber: Subscriber) -> None:
    pass

         

if __name__ == "__main__":
    pass
