from flask import Flask

app = Flask(__name__)

app.secret_key = 'development key'


from models import client

import intro_to_flask.routes
