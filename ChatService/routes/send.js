var express = require('express');
var router = express.Router();
var http = require('http');
var querystring = require('querystring');
var request = require('request');

var key = 'AIzaSyDMn5W8Vk4dGewT4P_2sFnIIxiggpsfFJM';
var firebaseUrl = 'https://fcm.googleapis.com/fcm/send';
/*var messageData = {
    "notification": {
      "title": "hai!",
      "body": "body",
    },
    "data": {
      "message": "Hai!",
      "person": "Maizono",
    },
    "to": "cIGQhBnoD4U:APA91bFeDZNrCavASOLGzDq7uI9UNCUs6S1KeP3XdszFsgxyjHa9NS0nsE4vKXYJTwavMt8OYCe5fkVL9i9zIlZ8KqSOncgB3Qr8EfLNrYrbqnb6DmJLz-m-bRSPU8DoK-SOE7brkRyq"
};*/


/* POST CHAT */
router.post('/', function(req, res, next) {

    console.log('POST REQUEST send');

    var idFrom = req.body.from;
    var idTo = req.body.to;
    var message = req.body.message;
    var tokenDest = req.body.tokenDest;

    console.log(tokenDest);

    var messageData = {
        "notification": {
            "title": "Chat Masuk!",
            "body": message,
        },
        "data": {
            "fromPerson": idFrom,
            "toPerson": idTo,
            "message": message,
            "tokenDest": tokenDest
        },
        "to": tokenDest
    };

    /*var messageData = {
        "data": {
            "fromPerson": idFrom,
            "toPerson": idTo,
            "message": message,
            "tokenDest": tokenDest
        },
        "to": "cIGQhBnoD4U:APA91bFeDZNrCavASOLGzDq7uI9UNCUs6S1KeP3XdszFsgxyjHa9NS0nsE4vKXYJTwavMt8OYCe5fkVL9i9zIlZ8KqSOncgB3Qr8EfLNrYrbqnb6DmJLz-m-bRSPU8DoK-SOE7brkRyq"
    };*/

    /*var chat = querystring.stringify({
        from: idFrom,
        to: idTo,
        dateTime: Date.now(),
        message: message
    });*/

    /*request({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        uri: 'http://127.0.0.1:3000/chat',
        method: 'POST'
    }, function (err, response, body) {
        //res.send("berhasil!");
        console.log("Respon: "+response.body);
    });*/

    //request.write(chat);

    request({
        headers: {
            'Authorization' : 'key='+key,
            'Content-Type': 'application/json'
        },
        uri: firebaseUrl,
        body: JSON.stringify(messageData),
        method: 'POST'
    }, function (err, response, body) {
        //res.send("berhasil!");
        console.log("body "+response.body);
        console.log("response "+JSON.stringify(response.headers));
    });


});

router.get('/', function(req, res, next) {

    console.log('GET REQUEST send');

    request({
        headers: {
            'Authorization' : 'key='+key,
            'Content-Type': 'application/json'
        },
        uri: firebaseUrl,
        body: JSON.stringify(messageData),
        method: 'POST'
    }, function (err, response, body) {
        res.send("berhasil!");
        console.log("body "+response.body);
        console.log("response "+JSON.stringify(response.headers));
    });

});

module.exports = router;