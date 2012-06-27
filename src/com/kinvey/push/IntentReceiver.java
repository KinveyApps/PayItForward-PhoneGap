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
 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
 
import com.urbanairship.push.PushManager;
 
public class IntentReceiver extends BroadcastReceiver {
 
	// Forwards intent to PushNotification plugin.
    @Override
    public void onReceive(Context _, Intent intent) {
    	// Obtain a reference to the plugin.
    	PushNotification plugin = PushNotification.getInstance();
    	if(null == plugin) {// Plugin may not have been initialized yet.
    		return;
    	}
    	String action = intent.getAction();

    	// Forwards push notification to plugin.
    	if(action.equals(PushManager.ACTION_PUSH_RECEIVED)) {
    		plugin.send(intent.getStringExtra(PushManager.EXTRA_ALERT));
    	}

    	// Forwards registration intent to plugin.
    	else if(action.equals(PushManager.ACTION_REGISTRATION_FINISHED)) {
        	plugin.onRegistrationComplete();
    	}
    }
}