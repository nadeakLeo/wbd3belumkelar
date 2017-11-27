importScripts("https://www.gstatic.com/firebasejs/4.6.2/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/4.6.2/firebase-messaging.js");
importScripts("https://www.gstatic.com/firebasejs/4.6.2/firebase.js");

var config = {
  apiKey: "AIzaSyBf2W-srwcUrbvD0TY5bl8dpTgXRzRViic",
  authDomain: "pro-jek-2a08a.firebaseapp.com",
  databaseURL: "https://pro-jek-2a08a.firebaseio.com",
  projectId: "pro-jek-2a08a",
  storageBucket: "pro-jek-2a08a.appspot.com",
  messagingSenderId: "978916192990"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };

    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});
