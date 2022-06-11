# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import base64
import io

from io import BytesIO
import base64
from tkinter import Image

import flask

import pickle

import  numpy as np
from flask import Flask, request, jsonify, Response
from keras.preprocessing import image


model = pickle.load(open("emotionfinal.pkl","rb"))

app = Flask(__name__)

@app.route('/')
def index():
    return "Hello world"

@app.route('/predict')
def predict():
 print("predict")

 image = Image.open("Screenshot 2022-06-08 210557.png")
 image = image.resize((48, 48))
 arr = np.array(image)
 x_data = [arr]
 x_data = np.array(x_data, dtype="float32")
 x_data = x_data.reshape((len(x_data), 48, 48, 1))
 x_data /= 255

 predict = model.predict(x_data);

 return  predict;

if __name__ == '__main__':
    app.run(host="172.16.5.164",debug=True)
