from pymongo import MongoClient
from werkzeug import generate_password_hash, check_password_hash


client = MongoClient('mongodb://admin:password@ds053438.mongolab.com:53438/challenge_u_tester')
db = client['challenge_u_tester']

class User(object):
    def __init__(self, firstname, lastname, screenname, email, password):
        self.user = { 'screenname': screenname.title(),
                      'firstname': firstname.title(),
                      'lastname': lastname.title(),
                      'email': email.lower(),
                      'password': self.set_password(password),
                      'points': 0,
                      'user_challenges':
                        {
                            
                        }
                                            
                    }        
    
    def set_challenge(self, new_challenge):
        challenge_list = self.user['user_challenges']
        challenge_list.append(new_challenge)
        self.user['points'] += new_challenge['point_val']
        
    def get_points(self):
        return self.user['points']
        
    def set_password(self, password):
        self.pwdhash = generate_password_hash(password)
        
    def check_password(self, password):
        return check_password_hash(self.pwdhash, password)
        
        
class Challenge(object):
    def __init__(self, title, description, difficulty, point_val):
        self.challenge = { 'title': title.lower(),
                           'description': description.lower(),
                           'difficulty': difficulty, #int
                           'point_val': point_val
                         }
