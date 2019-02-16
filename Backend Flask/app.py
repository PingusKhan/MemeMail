from flask import Flask

app = Flask(__name__)


@app.route('/hello_world')
def hello_world():
    return '<html><h1>cool!</h1></html>'


if __name__ == '__main__':
    app.run()
