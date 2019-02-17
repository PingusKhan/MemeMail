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
    Subscriptions[recipient] = Subscriber(Genre = genre,
                                        Interval = interval,
                                        Sender = sender,
                                        Date = datetime.utcnow())


def is_it_meme_time(subscriptions: dict) -> None:
    for subscriber in subscriptions:
        print(subscriber)
        meme_time = subscriptions[subscriber].Date + subscriptions[subscriber].Interval
        print(meme_time)
        print(meme_time < datetime.utcnow())
        if meme_time <= datetime.utcnow():  
            it_be_meme_time(subscriptions[subscriber], subscriber)
    

def it_be_meme_time(subscriber: Subscriber, recipient: str) -> None:
    sg = sendgrid.SendGridAPIClient(APIKEY)

    from_email = Email(subscriber.Sender)
    to_email = Email(recipient)
    subject = "Meme Mail brought straight to YOU by Team TBD"
    content = Content("text/html", "<h1>You like memes?</h1><img src={} alt=dank_meme.jpg style=width:auto;height:auto></img>".format(get_meme(subscriber.Genre)))

    mail = Mail(from_email, subject, to_email, content)
    response = sg.client.mail.send.post(request_body=mail.get())

    print(response.status_code)
    print(response.body)
    print(response.headers)


def get_meme(genre: str) -> str:
     return "https://i.ytimg.com/vi/p-Ww_oTfLcA/hqdefault.jpg"


if __name__ == "__main__":
    Subscriptions["pinguskhan@gmail.com"] = Subscriber(Genre = "genre",
                                        Interval = timedelta(hours=6),
                                        Sender = "kgdinh@uci.edu",
                                        Date = datetime.today())
    print(datetime.today())
    is_it_meme_time(Subscriptions)
