from intro_to_flask import app
from flask import render_template, request, flash, url_for, redirect, session
from models import client, db, User
from forms import RegisterForm, SigninForm

import datetime

@app.route('/')
def home():
    return render_template('home.html')
    
@app.route('/hello_world')
def hello_world():
    return "Hello World!"

@app.route('/challenges')
def challenges():
    if 'screenname' in session:
        return render_template('challenges.html')
    else:
        return render_template('challenges.html')
    
@app.route('/register', methods=['GET', 'POST'])
def register():
    form = RegisterForm()
    
    if request.method == 'POST':
        if form.validate() == False:
            return render_template('register.html', form=form)
        else:
            newuser = User(form.firstname.data, form.lastname.data, form.screenname.data, form.email.data, form.password.data)
            session['screenname'] = newuser.user['screenname']
            #session['screenname']['user'] = newuser.user
            db.Users.insert(newuser.user)
            return redirect(url_for('profile'))
    elif request.method == 'GET':
        return render_template('register.html', form=form)
        
@app.route('/profile')
def profile():
    if 'screenname' not in session:
        return redirect(url_for('signin'))
     
    user = db.Users.find({'screenname':session['screenname']})
 
    if user is None:
        return redirect(url_for('signin'))
    else:
        return render_template('profile.html')
    
@app.route('/signin', methods=['GET', 'POST'])
def signin():
    form = SigninForm()
   
    if request.method == 'POST':
        if form.validate() == False:
            return render_template('signin.html', form=form)
        else:
            session['screenname'] = form.screenname.data
            user = db.Users.find({'screenname':session['screenname']})
            return redirect(url_for('profile'))
                 
    elif request.method == 'GET':
        return render_template('signin.html', form=form)
        
@app.route('/signout')
def signout():
 
    if 'screenname' not in session:
        return redirect(url_for('signin'))
     
    session.pop('screenname', None)
    return redirect(url_for('home'))
  
@app.route('/testdb')
def testdb():
    if 'Users' in db.collection_names():
        return 'It works! '
    else:
        return 'Something is broken.'

 
