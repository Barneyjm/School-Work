from flask.ext.wtf import Form
from wtforms import TextField, TextAreaField, SubmitField, PasswordField, RadioField, validators
from wtforms.validators import Required
from models import db, User
 
class RegisterForm(Form):
    firstname = TextField("first name", [validators.Required("Please enter your first name.")])
    lastname = TextField("last name", [validators.Required("Please enter your last name.")])
    screenname = TextField("screen name", [validators.Required("Please enter a screen name.")])
    email = TextField("email", [validators.Required("Please enter your email address.")])
    password = PasswordField("password", [validators.Required("Please enter a password.")])
    register = SubmitField("register")
    
    def __init__(self, *args, **kwargs):
        Form.__init__(self, *args, **kwargs)
 
    def validate(self):
        if not Form.validate(self):
          return False
         
        #~ user = User.query.filter_by(email = self.email.data.lower()).first()
        #~ if user:
          #~ self.email.errors.append("That email is already taken")
          #~ return False
        #~ else:
          #~ return True

class SigninForm(Form):
    screenname = TextField("screen name",  [validators.Required("Please enter your screen name.")])
    password = PasswordField("password", [validators.Required("Please enter a password.")])
    submit = SubmitField("sign in")

    def __init__(self, *args, **kwargs):
        Form.__init__(self, *args, **kwargs)
 
    def validate(self):
        if not Form.validate(self):
            return False
     
        login = db.Users.find({'screenname':self.screenname})
        
        if login and login['password'] is self.password:
          return True
        else:
          self.screenname.errors.append("Invalid screen name or password")
          return False
      
class ChallengeForm(Form):
    title = TextField("title",  [validators.Required("Please enter a title for the challenge.")])
    description = TextField("description", [validators.Required("Please enter a description.")])
    difficulty = RadioField(1, [1,2,3])
    submit = SubmitField("sign in")

    def __init__(self, *args, **kwargs):
        Form.__init__(self, *args, **kwargs)
 
    def validate(self):
        if not Form.validate(self):
            return False
          
                
#~ self.firstname = firstname.title()
        #~ self.lastname = lastname.title()
        #~ self.screenname = str(screenname)
        #~ self.email = email.lower()
        #~ self.set_password(password)
