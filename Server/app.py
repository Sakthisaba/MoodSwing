import base64
import io
from PIL import Image
from io import BytesIO
import base64
import flask
import imageio.v2
import requests
from PIL.Image import Image
from  deepface import DeepFace
import cv2
import  numpy as np
from flask import Flask, request, jsonify, Response
from keras.preprocessing import image
import scipy.misc
import werkzeug
import keras.models
from pandas.io.sas.sas_constants import magic
from urllib3.util import url

app = Flask(__name__)

@app.route('/')
def index():
    return "Hello world"

@app.route('/predict',methods=["POST"])
def predict():
    encodedimg = flask.request.form.get("image");
    print(encodedimg)
    # imagefile = flask.request.files['image']
    #imagefile = base64.b64decode(encodedimg)
    # with open("encode.bin", "wb") as fh:
    #     fh.write(base64.decodebytes(imagefile))
    # with open("encode.bin", "rb") as fh:
    #     byte = fh.read()
    #     fh.close()
    with open("detect.png", "wb") as fh:
        fh.write(base64.b64decode(encodedimg))
        fh.close()
    
    # filename = werkzeug.utils.secure_filename(imagefile.filename)
    # print("\nReceived image File name : " + imagefile.filename)
    # imagefile.save(filename)
    detect = DeepFace.analyze("detect.png",actions = ['emotion'],enforce_detection='false')
    return detect["dominant_emotion"]

    # img = 'img.png'
    # loaded_model = keras.models.load_model('emotion_classifier.h5')
    # detect = loaded_model.predict(np.array([img]))[0]
    # r = request.files.get('image')
    # # # convert string of image data to uint8
    # # nparr = np.fromstring(r.data, np.uint8)
    # # # decode image
    # # img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    # # # file = request.files['image']
    # # # fil1 = file.rea

    # # return detect
    # # convert string of image data to uint8
    # # nparr = np.fromstring(r.data, np.uint8)
    # # # decode image
    # # img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    #xf
    # # do some fancy processing here....
    #
    # # build a response dict to send back to client
    # if request.method == 'POST':
    #     # check if the post request has the file part
    #     if ('image' in request.files):
    #         file1 = request.files.get('image')
    #
    #         file_like_object = io.BytesIO()
    #         res = file1.raw
    #         # move to the beginning of file
    #         # res = requests.post(url, file1)
    #         # resp_data = {"match": "File1"}  # convert numpy._bool to bool for json.dumps
    #         # detect = DeepFace.analyze(res, actions=['emotion'], enforce_detection='false')
    #         return "f"
    #
    #
    # # return Response(response=r, status=200, mimetype="application/json")

if __name__ == '__main__':
    app.run(host="192.168.8.175",debug=True)
