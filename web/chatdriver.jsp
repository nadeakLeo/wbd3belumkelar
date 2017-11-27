<html>
<head>
    <title>Make A Order</title>
    <link rel="stylesheet" type="text/css" href="makeorder.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
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
      <label class = "floatright"> Hi, <b>snorlax!</b> </label>
      <br>
     <a class="floatright" href="">logout</a>
  </div>
</div>

<div class="tab">
  <button class="tablinks active" onclick="">ORDER</button>
  <button class="tablinks" onclick="">HISTORY</button>
  <button class="tablinks" onclick="">MY PROFILE</button>
</div>

<div>

  <div class="title1">LOOKING FOR AN ORDER</div>

      <div class="steplabeltab">
        <div class="title2">
          Got an Order!
        </div>
        <div class="userojekname">
          pikapikapikachu
        </div>
      </div>

      <div ng-app="chatting" ng-controller="chatCtrl">
        <div class="chatoutput" id="scrollable">
          <div ng-repeat="chat in history">
            <span ng-if="(chat.from == 1) && (chat.to == 2)" class="sender">{{ chat.message }}</span>
            <span ng-if="(chat.from == 2) && (chat.to == 1)" class="receiver">{{ chat.message }}</span>
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
        <button class="close" type="submit">CLOSE</button>

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
  app.controller('chatCtrl', function($scope, $timeout, $http){
    
    $http({
        method : "GET",
        url : "http://localhost:3000/chat/1/2"
    }).then(function mySuccess(response) {
        $scope.history = response.data;
        console.log('success');
    }, function myError(response) {
        alert('error');
    });

    $scope.messages = [];

    $scope.submitChat = function() {
      if ($scope.textSent != "" && $scope.textSent != null && $scope.textSent != undefined) {
        $scope.messages.push({sentAs: 'sender', content: $scope.textSent});
        $timeout(function() {
          var element = document.getElementById("scrollable");
          element.scrollTop = element.scrollHeight;
        }, 0, false);
      }
    }
    $scope.receiveChat = function() {
      if ($scope.textSent != "" && $scope.textSent != null && $scope.textSent != undefined) {
        $scope.messages.push({sentAs: 'receiver', content: $scope.textReceived});
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
