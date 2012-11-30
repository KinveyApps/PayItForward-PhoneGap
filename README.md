# PayItForward-PhoneGap

The Pay It Forward sample application is a simplified version of the [#RubyRiot app](http://www.kinvey.com/blog/70/observing-rubyriot-mobile-app-usage-was-fascinating) built by Kinvey in January 2012. The app makes it easy for conference attendees make introductions on behalf of others. This application shows you how to login via Facebook, store data, implement offline saving for your app, and implement push notifications using Kinvey.

## Run It
After downloading or cloning the repository:

* Replace `<your-app-key>` and `<your-app-secret>` (lines 7–8 in `assets/www/scripts/app.js`) with your application credentials
* Replace `<your-facebook-app-id>` (line 11 in `index.html`) with your Facebook App ID/Api Key.
* Replace `<your-push-key>` and `<your-push-secret>` (in `assets/airshipconfig.properties`) with your push credentials. You can find these on the Application Settings page in the console.
* [Set-up](https://developer.android.com/tools/devices/emulator.html) a virtual Android device. Then, deploy the project to this device.

## Functionality
This application demonstrates:

* Data Storage
* Login with Facebook
* Caching
* Offline Saving
* Push

## Architecture
The Pay It Forward app is built using PhoneGap. This allowed the app to support push notifications.

The app is a single-page application. All HTML code is contained in `index.html`.

jQuery and jQuery Mobile are used for handling all routes and displaying the appropriate pages. Also, a few other third party libraries are used to enhance the app. These resources are all contained in the `vendor` directory.

The `scripts` directory contains application-specific files. These are:

* `init.js` configures jQuery and jQuery Mobile.
* `app.js` initializes Kinvey’s JavaScript library for use in your app. In addition, the application domain is specified.
* `ui.js` connects the application-domain with the user interface. Most of this file consists of event handlers which are executed when a page is requested.

### Push
The app uses Urban Airship to enable push notifications. The code connecting JavaScript with the native platform resides in `src/com/kinvey/push`. `IntentReceiver.java` gets called whenever a push notification is received. The receiver then invokes `PushNotification.java`, which translates the message into a JavaScript call. Also, this class handles enabling and/or disabling push for the device.

This bridging technique is described in more detail in the [PhoneGap Plugins Wiki](http://docs.phonegap.com/en/2.0.0/guide_plugin-development_index.md.html).

![Receiving a push message](https://github.com/KinveyApps/PayItForward-PhoneGap/blob/master/screenshot-push.png)