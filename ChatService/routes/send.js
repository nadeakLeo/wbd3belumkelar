var express = require('express');
var router = express.Router();
var http = require('http');
var querystring = require('querystring');
var request = require('request');

var key = 'AIzaSyDMn5W8Vk4dGewT4P_2sFnIIxiggpsfFJM';
var firebaseUrl = 'https://fcm.googleapis.com/fcm/send';
var messageData = {
    "notification": {
      "title": "hai!",
      "body": "body",
    },
    "data": {
      "message": "Hai!",
      "person": "Maizono",
    },
    "to": "cIGQhBnoD4U:APA91bFeDZNrCavASOLGzDq7uI9UNCUs6S1KeP3XdszFsgxyjHa9NS0nsE4vKXYJTwavMt8OYCe5fkVL9i9zIlZ8KqSOncgB3Qr8EfLNrYrbqnb6DmJLz-m-bRSPU8DoK-SOE7brkRyq"
};


/* POST CHAT */
router.post('/', function(req, res, next) {

    console.log('POST REQUEST send');

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

router.get('/', function(req, res, next) {

    console.log('GET REQUEST send');
    /*var post_data = querystring.stringify({
        'compilation_level' : 'ADVANCED_OPTIMIZATIONS',
        'output_format': 'json',
        'output_info': 'compiled_code',
        'warning_level' : 'QUIET',
        'js_code' : codestring
    });

    // An object of options to indicate where to post to
    var post_options = {
        host: 'closure-compiler.appspot.com',
        port: '80',
        path: '/compile',
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Content-Length': Buffer.byteLength(post_data)
        }
    };

    // Set up the request
    var post_req = http.request(post_options, function(res) {
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            console.log('Response: ' + chunk);
        });
    });

    // post the data
    post_req.write(post_data);
    post_req.end();*/

    var form = {
        username: 'usr',
        password: 'pwd',
        opaque: 'opaque',
        logintype: '1'
    };

    var formData = querystring.stringify(form);
    var contentLength = formData.length;

    //console.log('key'+key);

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