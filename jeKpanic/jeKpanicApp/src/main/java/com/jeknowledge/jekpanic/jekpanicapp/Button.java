package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.*;

public class Button extends Activity
{
    protected Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

        ParseObject.registerSubclass(Request.class);
    }

    public void panic(View view)
    {
        request = new Request();
        request.setOwner(ParseUser.getCurrentUser());

        request.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                {
                    Toast.makeText(getApplicationContext(), "Senta-te, o cartão já vem ;)", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Deu merda!", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
