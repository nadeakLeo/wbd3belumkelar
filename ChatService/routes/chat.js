var express = require('express');
var router = express.Router();

/* GET CHAT */
router.get('/', function(req, res, next) {

    console.log('test!');

    res.send("ting!");

    // AMBIL CHAT DARI MONGODB
});

/* POST CHAT */
router.post('/', function(req, res, next) {

    // MENAMBAHKAN DATA CHAT KE DATABASE MONGODB
    res.send('<p>sukses!</p>');
});

module.exports = router;