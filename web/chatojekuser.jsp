<%@ page import="utility.CookieChecker" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = "";
    Cookie[] cookies = null;
    cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cooky : cookies) {
            String cookieName = cooky.getName();
            String cookieValue = cooky.getValue();
            if (cookieName.equals("uname")) {
                username = cookieValue;
            }
        }
    }

    int user_id = 1;
    int driver_id = 2;
%>
<html>
<head>
    <title>Make A Order</title>
    <link rel="stylesheet" type="text/css" href="makeorder.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://www.gstatic.com/firebasejs/4.6.2/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/4.6.2/firebase-messaging.js"></script>
    <script src="https://www.gstatic.com/firebasejs/4.6.2/firebase.js"></script>
    <script src="./firebase.js"></script>
</head>
<body>
<div class = "header">
    <div class="title">
        <div class="bigfont">
            <label class= "headcolor1">PR</label><label>-</label><label class="headcolor2">OJEK</label>
        </div>
        <div>
            <label class = "wushwush">wushh...wushh...ngeeeng</label>
        </div>
    </div>
    <div class="logininfo">
        <label class = "floatright"> Hi, <b><%=username%>!</b> </label>
        <br>
        <a class="floatright" href="/logout">logout</a>
    </div>
</div>

<div class="tab">
    <a class="tablinks active" href="order1">ORDER</a>
    <a class="tablinks" href="previousorder">HISTORY</a>
    <a class="tablinks" href="profile">MY PROFILE</a>
</div>

<div>

    <div class="makeorder">MAKE AN ORDER</div>

    <div class="steplabeltab">
        <div class = "steplabel">
            <div class= "steplabelnumber">
                <label >1</label>
            </div>
            <div class = "steplabeltext">
                <label >Select Destination</label>
            </div>
        </div>
        <div class = "steplabel">
            <div class= "steplabelnumber">
                <label >2</label>
            </div>
            <div class = "steplabeltext">
                <label >Select a Driver</label>
            </div>
        </div>
        <div class = "steplabelopen">
            <div class= "steplabelnumber">
                <label >3</label>
            </div>
            <div class = "steplabeltext">
                <label >Chat driver</label>
            </div>
        </div>
        <div class = "steplabelright">
            <div class= "steplabelnumber">
                <label >4</label>
            </div>
            <div class = "steplabeltext">
                <label >Complete your order</label>
            </div>
        </div>

    </div>

    <div id="chatter" ng-app="chatting" ng-controller="chatCtrl" ng-init="temp=1">
        <div class="chatoutput" id="scrollable">
            <div ng-repeat="chat in history">
                <span ng-if="(chat.from == <%=user_id%>) && (chat.to == <%=driver_id%>)" class="sender">{{ chat.message }}</span>
                <span ng-if="(chat.from == <%=driver_id%>) && (chat.to == <%=user_id%>)" class="receiver">{{ chat.message }}</span>
            </div>
            <div ng-repeat="message in messages">
                <span class="{{ message.sentAs }}">{{ message.content }}</span>
            </div>
        </div>
        <div class="chatinput">
            <form ng-submit="submitChat()">
                <div class="inputarea">
                    <input type="text" name="chat" placeholder="Say something..." ng-model="textSent">
                </div>
                <div class="send">
                    <button class="sendbutton" type="submit">Kirim</button>
                </div>
            </form>
        </div>
        <a class="close" href="/order3">CLOSE</a>

        <hr>
        <form ng-submit="receiveChat()">
            <input type="text" name="chatRcv" ng-model="textReceived"> <button type="submit">SEND</button>
        </form>
    </div>

</div>

<script>

    function validateForm() {
        var x = document.forms["pickdest"]["pickpoint"].value;
        var y = document.forms["pickdest"]["dest"].value;

        if (x == "") {
            alert("Picking Point must be filled out");
            return false;
        } else
        if (y == "") {
            alert("Destination must be filled out");
            return false;
        }
    }

    function resetForm(element) {
        element.reset();
    }

    var app = angular.module('chatting', []);
    app.controller('chatCtrl', function($scope, $timeout, $http, $location){

        console.log($location.search());

        /* add history chat*/
        $http({
            method : "GET",
            url : "http://localhost:3000/chat/" + <%=user_id%> + "/" + <%=driver_id%>
        }).then(function mySuccess(response) {
            $scope.history = response.data;
            console.log('success');
        }, function myError(response) {
            alert('error');
        });

        $scope.messages = [];
        var fromId = 100;
        var toId = 200;
        var tokenDest = "";

        tokenDest = "eT8dZ_4aL1Y:APA91bEZhzMxMEldfC-2N_cpR598SyBbliD8XsxmEoADBdKpuYTE16x2MSV4yk2Njg0P98qf_LB8drfLKFP9iiGKiQ9CIYQD62BeFHBOPvqGJj3RL_wlP6XkYfddCvfEoCx5g1f3c8w9";

        console.log("tokenDest");
        console.log(tokenDest);

        $scope.submitChat = function() {
            if ($scope.textSent != "" && $scope.textSent != null && $scope.textSent != undefined) {
                $scope.messages.push({sentAs: 'sender', content: $scope.textSent});
                $timeout(function() {
                    var element = document.getElementById("scrollable");
                    element.scrollTop = element.scrollHeight;
                }, 0, false);
            }

            var dataRawChat = {
                from: fromId,
                to: toId,
                tokenDest: tokenDest,
                message: $scope.textSent,
                date: Date.now().toString(),
            };

            var dataChat = JSON.stringify(dataRawChat);

            // HTTP POST REQUEST MENAMBAHKAN CHAT KE DATABASE
            $http({
                method : "POST",
                url : "http://127.0.0.1:3000/chat",
                data: dataChat
            }).then(function mySuccess(response) {
                console.log("success!");
                console.log("Respon: "+response.body);
            }, function myError(response) {
                console.log("error!");
                console.log("Header: "+JSON.stringify(response.headers));
            });

            // HTTP POST REQUEST MENGIRIM CHAT
            $http({
                method : "POST",
                url : "http://127.0.0.1:3000/send",
                data: dataChat
            }).then(function mySuccess(response) {
                console.log("success!");
            }, function myError(response) {
                console.log("error!");
                console.log("Header: "+JSON.stringify(response.headers));
            });
        }
        $scope.receiveChat = function() {
            if ($scope.textSent != "" && $scope.textSent != null && $scope.textSent != undefined) {
                console.log("tes!");
                $scope.messages.push({sentAs: 'receiver', content: $scope.textReceived});
                tokenDest = "cIGQhBnoD4U:APA91bFeDZNrCavASOLGzDq7uI9UNCUs6S1KeP3XdszFsgxyjHa9NS0nsE4vKXYJTwavMt8OYCe5fkVL9i9zIlZ8KqSOncgB3Qr8EfLNrYrbqnb6DmJLz-m-bRSPU8DoK-SOE7brkRyq";
                console.log("tokenDest");
                console.log(tokenDest);
                $timeout(function() {
                    var element = document.getElementById("scrollable");
                    element.scrollTop = element.scrollHeight;
                }, 0, false);
            }
        }

    });
</script>

</body>
</html>
