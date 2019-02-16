import sendgrid
from sendgrid.helpers.mail import *


def create_user_subscription(user: str) -> None:
    subscriber_list = open('Subscriptions.txt', 'w+')
    subscriber_list.write('User: ' + user + '\n')
    subscriber_list.close()


def add_subscription(subscriptions: str, subscriber: str) -> None:
    if _check_if_already_subscriber(subscriptions, subscriber):
        pass
    else:
        subscriber_list = open(subscriptions, 'a')
        subscriber_list.write(subscriber + '\n')
        subscriber_list.close()

def remove_subscription(subscriptions: str, unsubscriber: str) -> None:
    if _check_if_already_subscriber(subscriptions, unsubscriber):
        subscriber_list = open(subscriptions, 'w')

        for line in _lines(subscriptions):
 
            if line != (unsubscriber + '\n'):
                subscriber_list.write(line + '\n')
        
        subscriber_list.close()
    else:
        pass
    

def send_memes_to_subscribers():
    pass


# Private Functions Below


def _lines(subscriptions: str) -> list:
    subscriber_list = open(subscriptions, 'r')
    lines = subscriber_list.readlines()
    subscriber_list.close()

    return lines


def _check_if_already_subscriber(subscriptions: str, email: str) -> bool:
    if (email + '\n') in _lines(subscriptions):
        return True
    else: 
        return False


if __name__ == "__main__":
    create_user_subscription("pinguskhan@gmail.com")
    add_subscription("Subscriptions.txt", "o")
    print(_check_if_already_subscriber("Subscriptions.txt", "o"))
    remove_subscription("Subscriptions.txt", "o")




