var express = require('express');
var router = express.Router();
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/ojek_chat';

/* GET token */
router.get('/:id', function(req, res, next) {

    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var user_id = parseInt(req.params.id);

        var query = { "user_id":user_id };

        db.collection('user').findOne(query, function(err, result) {
            if (err) throw err;
            var token = result.token;
            res.end(JSON.stringify({"token": token}));
            db.close();
        });
    });

});

/* POST token */
router.post('/', function(req, res, next) {

    var insertToken = function(db, callback) {
        db.collection('user').insertOne( {
            "user_id" : 1,
            "token" : "aB123"
        }, function(err, result) {
            assert.equal(err, null);
            console.log("Inserted a token to user data.");
            callback();
        });
    };

    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        insertToken(db, function () {
            db.close();
        });
    });

});

module.exports = router;