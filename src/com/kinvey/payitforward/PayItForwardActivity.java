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
package com.kinvey.payitforward;

import android.os.Bundle;

import com.kinvey.push.IntentReceiver;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

import org.apache.cordova.DroidGap;

public class PayItForwardActivity extends DroidGap {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");

        // Start UrbanAirship.
        UAirship.takeOff(super.getApplication());
        PushManager.shared().setIntentReceiver(IntentReceiver.class);
    }
}