var express = require('express');
var router = express.Router();

/* POST CHAT */
router.post('/', function(req, res, next) {

    console.log('POST REQUEST send');

    // MENAMBAHKAN DATA CHAT KE DATABASE MONGODB

    // MENGIRIM CHAT KE FIREBASE

});

router.get('/', function(req, res, next) {

    console.log('GET REQUEST send');

    // MENAMBAHKAN DATA CHAT KE DATABASE MONGODB

    // MENGIRIM CHAT KE FIREBASE

});

module.exports = router;