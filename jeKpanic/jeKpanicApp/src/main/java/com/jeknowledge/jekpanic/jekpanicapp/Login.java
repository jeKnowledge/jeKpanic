package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Login extends Activity {

    protected EditText usernameField;
    protected String username;
    protected EditText passwordField;
    protected String password;
    protected Button login_btn = null;
    protected Button sign_up_btn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ParseAnalytics.trackAppOpened(getIntent());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void attemptLogin(View view)
    {
        usernameField = (EditText)findViewById(R.id.username_field);
        username = usernameField.getText().toString();
        passwordField = (EditText)findViewById(R.id.password_field);
        password = passwordField.getText().toString();

        if(username.isEmpty())
            usernameField.setError(getString(R.string.error_field_required));
        else if(password.isEmpty())
            passwordField.setError(getString(R.string.error_field_required));
        else
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(usernameField.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);

            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if(e == null)
                        loginSuccessful();
                    else
                        loginUnSuccessful();
                }
            });
        }
    }

    public void loginUnSuccessful()
    {
        Toast.makeText(getApplicationContext(), "Username or Password is invalid.", Toast.LENGTH_SHORT).show();
    }

    public void loginSuccessful()
    {
        startActivity(new Intent(getBaseContext(), Button.class));
    }

    public void sign_up(View view)
    {
        EditText usernameField = (EditText)findViewById(R.id.username_field);
        username = usernameField.getText().toString();
        EditText passwordField = (EditText)findViewById(R.id.password_field);
        password = passwordField.getText().toString();

        Intent intent = new Intent(getBaseContext(), Sign_up.class);
        Bundle args = new Bundle();
        args.putCharSequence("username", username);
        args.putCharSequence("password", password);
        intent.putExtras(args);
        startActivity(intent);
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }
}
