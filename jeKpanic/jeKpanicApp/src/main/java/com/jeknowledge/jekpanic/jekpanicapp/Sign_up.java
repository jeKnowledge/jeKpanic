package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Tomas on 7/5/2014.
 */
public class Sign_up extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Bundle args = getIntent().getExtras();
        String username = args.getString("username");
        String password = args.getString("password");

        EditText new_username = (EditText)findViewById(R.id.new_username);
        new_username.setText(username);
        EditText new_password = (EditText)findViewById(R.id.new_password);
        new_password.setText(password);

        Parse.initialize(this, "hlYxbJG5mtCVV2dH7Ku70fEvq42V0S0C1kCO7sof", "r4M51AV8vgl7lIwrH81UY7pLLZNcBs3w15Eg6ab4");
    }


    public void sign_up(View view)
    {
        ParseUser user = new ParseUser();
        EditText username = (EditText)findViewById(R.id.new_username);
        user.setUsername(username.getText().toString());
        EditText password = (EditText)findViewById(R.id.new_password);
        user.setPassword(password.getText().toString());
        EditText email = (EditText)findViewById(R.id.new_email);
        user.setEmail(email.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e)
            {
                if(e == null)
                {
                    Toast.makeText(getApplicationContext(), "Parabéns, podes agora entrar em panico!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ups, algo de errado se passou!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
