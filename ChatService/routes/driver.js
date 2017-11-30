var express = require('express');
var router = express.Router();
var path = require('path');

/* GET users listing. */
router.get('/', function(req, res, next) {
    //console.log(req.query.id);
    res.sendFile(path.resolve('firebase/chatdriver.html'));
    //res.render('chatojekuser',{id:req.query.id});
});

module.exports = router;
