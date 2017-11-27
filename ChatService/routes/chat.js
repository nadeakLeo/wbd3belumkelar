var express = require('express');
var router = express.Router();
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var link = 'mongodb://localhost:27017/ojek_chat';

/* GET CHAT */
router.get('/:sender/:receiver', function(req, res, next) {

    MongoClient.connect(link, function(err, db) {
        if (err) throw err;
        var sender = parseInt(req.params.sender);
        var receiver = parseInt(req.params.receiver);
        //console.log(sender);
        //console.log(receiver);
        var query = { $or: [ {"from":sender, "to":receiver}, {"from":receiver, "to":sender} ] };
        //console.log(query);
        db.collection('chat').find(query).toArray(function(err, result) {
            if (err) throw err;
            console.log(result);
            res.end(JSON.stringify(result));
            db.close();
        });
    });
});

/* POST CHAT */
router.post('/', function(req, res, next) {

    console.log("request POST chat");

    var idFrom = req.body.from;
    var idTo = req.body.to;
    var message = req.body.message;
    var dateTime = req.body.dateTime;
    var error = 0;

    var insertChat = function(db, callback) {
        db.collection('chat').insertOne( {
            "from" : idFrom,
            "to" : idTo,
            "dateTime" : dateTime,
            "message" : message
        }, function(err, result) {
            assert.equal(err, null);
            error = 1;
            //console.log("Inserted a chat to history.");
            callback();
        });
    };

    // AMBIL CHAT DARI MONGODB
    MongoClient.connect(link, function(err, db) {
        assert.equal(null, err);
        insertChat(db, function() {
            db.close();
        });
    });

    if (error !== 1) res.send("Chat inserted!");

    // MENAMBAHKAN DATA CHAT KE DATABASE MONGODB
});

module.exports = router;