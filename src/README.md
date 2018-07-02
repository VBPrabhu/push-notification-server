Push Notification Server
========================

This can be used to send Push Notification to iOS or Android Device.
  
### Android Push Notification

* ```SERVER_KEY``` - if you don't have SERVER_KEY, generate using [this tutorial](https://developers.google.com/cloud-messaging/android/client) 
* ```DEVICE_TOKEN``` - if you don't have device token, in case of Ionic 2, use [this tutorial](https://developer.apple.com/notifications/)
* ```FirebasePush.java``` - run this file to push notification to Android device

  ```
  $ javac FirebasePush.java
  $ java  FirebasePush
  ```

### iOS Push Notification

* ```APNS certificate(.p12)``` - if you don't have .p12 certificate
* ```DEVICE_TOKEN``` - if you don't have device token, please generate from Apple developer site and from local mac machine
* ```ApnsPush.java``` - run this file to push notification to iOS device

  ```
  $  javac -cp apns-1.0.0.Beta6.jar:. ApnsPush.java
  $  java  -cp apns-1.0.0.Beta6.jar:. ApnsPush 
  ```
