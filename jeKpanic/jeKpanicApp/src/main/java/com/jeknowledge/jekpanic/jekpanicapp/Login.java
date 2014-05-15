package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Permite efectuar o login do utilizador, e fornece registo à área de registo de um novo utilizador
 */
public class Login extends Activity {

    protected EditText usernameField;
    protected EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ParseAnalytics.trackAppOpened(getIntent());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameField = (EditText)findViewById(R.id.username_field);
        passwordField = (EditText)findViewById(R.id.password_field);
    }

    public void attemptLogin(View view)
    {
        if(requiredFields(getUsername(), getPassword()))
        {
            ParseUser.logInInBackground(getUsername(), getPassword(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if(e == null)
                        loginSuccessful();
                    else
                        loginUnSuccessful(e.getMessage());
                }
            });
        }
    }

    private boolean requiredFields(String username, String password)
    {
        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please, check the required field.", Toast.LENGTH_SHORT).show();
            //Fecha a janela do teclado
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(usernameField.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(passwordField.getWindowToken(), 0);

            if(username.isEmpty())
                usernameField.setError("Required");
            else if(password.isEmpty())
                passwordField.setError("Required");
            return false;
        }
        else
            return true;
    }

    public void loginUnSuccessful(String error)
    {
        Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
    }

    public void loginSuccessful()
    {
        startActivity(new Intent(getBaseContext(), Button.class));
    }

    public void sign_up(View view)
    {
        String username = getUsername();
        String password = getPassword();

        Intent intent = new Intent(getBaseContext(), Sign_up.class);
        Bundle args = new Bundle();
        args.putCharSequence("username", username);
        args.putCharSequence("password", password);
        intent.putExtras(args);
        startActivity(intent);
    }

    public String getUsername()
    {
        return this.usernameField.getText().toString();
    }

    public String getPassword()
    {
        return this.passwordField.getText().toString();
    }
}
