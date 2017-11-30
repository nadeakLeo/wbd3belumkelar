var config = {
  apiKey: "AIzaSyBf2W-srwcUrbvD0TY5bl8dpTgXRzRViic",
  authDomain: "pro-jek-2a08a.firebaseapp.com",
  databaseURL: "https://pro-jek-2a08a.firebaseio.com",
  storageBucket: "pro-jek-2a08a.appspot.com",
  messagingSenderId: "978916192990"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

messaging.requestPermission()
.then(function() {
  console.log('Notification permission granted.');
  // TODO(developer): Retrieve an Instance ID token for use with FCM.
  // ...
})
.catch(function(err) {
  console.log('Unable to get permission to notify.', err);
});

messaging.getToken()
.then(function(currentToken) {
  if (currentToken) {
    console.log("Token ini:");
    console.log(currentToken);
    /*sendTokenToServer(currentToken);
    updateUIForPushEnabled(currentToken);*/
  } else {
    // Show permission request.
    console.log('No Instance ID token available. Request permission to generate one.');
    // Show permission UI.
    /*updateUIForPushPermissionRequired();
    setTokenSentToServer(false);*/
  }
})
.catch(function(err) {
  console.log('An error occurred while retrieving token. ', err);
  /*showToken('Error retrieving Instance ID token. ', err);
  setTokenSentToServer(false);*/
});

messaging.onTokenRefresh(function() {
  messaging.getToken()
  .then(function(refreshedToken) {
    console.log('Token refreshed.');
    // Indicate that the new Instance ID token has not yet been sent to the
    // app server.
    //setTokenSentToServer(false);
    // Send Instance ID token to app server.
    //sendTokenToServer(refreshedToken);
    // ...
  })
  .catch(function(err) {
    console.log('Unable to retrieve refreshed token ', err);
    //showToken('Unable to retrieve refreshed token ', err);
  });
});
// [END refresh_token]
// [START receive_message]
// Handle incoming messages. Called when:
// - a message is received while the app has focus
// - the user clicks on an app notification created by a sevice worker
//   `messaging.setBackgroundMessageHandler` handler.
messaging.onMessage(function(payload) {
  console.log("Message received. ", payload.data.message);
  var scope = angular.element(document.getElementById("chatter")).scope();
    scope.$apply(function(){
        scope.messages.push({sentAs: 'receiver', content: payload.data.message});
    });
  //console.log("Message received. ", payload);
  // [START_EXCLUDE]
  // Update the UI to include the received message.
  // [END_EXCLUDE]
});
