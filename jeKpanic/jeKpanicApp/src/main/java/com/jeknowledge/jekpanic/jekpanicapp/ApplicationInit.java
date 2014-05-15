package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.PushService;

/**
 * Created by Tomas on 15/5/2014.
 */
public class ApplicationInit extends android.app.Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "hlYxbJG5mtCVV2dH7Ku70fEvq42V0S0C1kCO7sof", "r4M51AV8vgl7lIwrH81UY7pLLZNcBs3w15Eg6ab4");
        PushService.setDefaultPushCallback(this, Login.class);
        PushService.subscribe(this, "Everyone", Login.class);
    }
}
