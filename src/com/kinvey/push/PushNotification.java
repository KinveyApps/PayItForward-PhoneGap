/**
 * Copyright (c) 2012 Kinvey, Inc. All rights reserved.
 *
 * Licensed to Kinvey, Inc. under one or more contributor
 * license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership.  Kinvey, Inc. licenses this file to you under the
 * Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You
 * may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.kinvey.push;

import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;

public class PushNotification extends Plugin {

    static PushNotification instance;

    // Save the current instance, so it can be accessed from the IntentReceiver.
    public PushNotification() {
    	instance = this;
    }

    // Returns the PushNotification instance.
    public static PushNotification getInstance() {
        return instance;
    }

    // Sends notification to the JavaScript view.
    public void send(String msg) {
    	String notification = String.format("window.plugins.pushNotification.notify('%s');", msg);
    	sendJavascript(notification);
    }

    // Delegates requested action.
    @Override
    public PluginResult execute(String action, JSONArray data, String callbackId) {
    	if(action.equals("getDeviceUniqueIdentifier")) {
    		return getDeviceUniqueIdentifier(callbackId);
    	}
    	if(action.equals("isRegisteredDevice")) {
    		return isRegisteredDevice(callbackId);
    	}
    	if(action.equals("registerDevice")) {
    		return registerDevice(callbackId);
    	}
    	if(action.equals("unregisterDevice")) {
    		return unregisterDevice(callbackId);
    	}
        return new PluginResult(PluginResult.Status.INVALID_ACTION);
    }

    // Retrieves the Urban Airship ID of this device.
    public PluginResult getDeviceUniqueIdentifier(String callbackId) {
    	return new PluginResult(PluginResult.Status.OK, PushManager.shared().getAPID());
    }

    // Returns whether device is registered.
    public PluginResult isRegisteredDevice(String callbackId) {
    	PushPreferences preferences = PushManager.shared().getPreferences();
    	return new PluginResult(PluginResult.Status.OK, preferences.isPushEnabled());
    }

    // Enables push for this device.
    String callbackId = null;
    public PluginResult registerDevice(String callbackId) {
    	// Return instantly if already enabled.
    	PushPreferences preferences = PushManager.shared().getPreferences();
    	if(preferences.isPushEnabled()) {
    		return new PluginResult(PluginResult.Status.OK);
    	}

    	// Enable push. This action is asynchronous, so save the callback for
    	// later. onRegistrationComplete will be invoked when task completes.
    	this.callbackId = callbackId;

    	// Trigger task.
    	PushManager.enablePush();

    	// Return no result.
    	PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
    	result.setKeepCallback(true);
    	return result;
    }

    // Invokes JavaScript handler.
    public void onRegistrationComplete() {
    	PluginResult result = new PluginResult(PluginResult.Status.OK);
    	result.setKeepCallback(false);
    	success(result, callbackId);
    }

    // Disables push for this device.
    public PluginResult unregisterDevice(String callbackId) {
    	// Return instantly if already disabled.
    	PushPreferences preferences = PushManager.shared().getPreferences();
    	if(!preferences.isPushEnabled()) {
    		return new PluginResult(PluginResult.Status.OK);
    	}

    	// Disable push. There is no intent sent out, so complete immediately.
    	PushManager.disablePush();
    	PluginResult result = new PluginResult(PluginResult.Status.OK);
    	return result;
    }
}