(function(window, cordova) {

  // Define the PushNotification class.
  var PushNotification = function() {
    // Plugin name.
    this.name = 'PushNotification';
  };
  PushNotification.prototype = {
    // Call this to retrieve the device unique identifier.
    getDeviceUniqueIdentifier: function(callback) {
      cordova.exec(callback, callback, this.name, 'getDeviceUniqueIdentifier', []);
    },

    // Call this to check whether the device is registered for push notifications.
    isRegisteredDevice: function(callback) {
      cordova.exec(callback, callback, this.name, 'isRegisteredDevice', []);
    },

    // Call this to register for push notifications.
    registerDevice: function(callback) {
      cordova.exec(callback, callback, this.name, 'registerDevice', []);
    },

    // Call this to unregister for push notifications.
    unregisterDevice: function(callback) {
      cordova.exec(callback, callback, this.name, 'unregisterDevice', []);
    },

    // Spawns an event when a notification is received.
    notify: function(notification) {
      var e = document.createEvent('HTMLEvents');
      e.initEvent('notification', true, true, arguments);
      e.data = { notification: notification };
      document.dispatchEvent(e);
    }
  };

  // Export.
  cordova.addConstructor(function() {
    window.plugins || (window.plugins = {});
    window.plugins.pushNotification = new PushNotification();
  });

}(window, window.cordova || window.Cordova || window.PhoneGap));