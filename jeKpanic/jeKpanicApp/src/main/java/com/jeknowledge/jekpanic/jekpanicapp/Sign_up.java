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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Sign_up extends Activity
{
    private String name;
    private String email;
    private String username;
    private String password;
    private String retype_password;
    private EditText new_name;
    private EditText new_email;
    private EditText new_username;
    private EditText new_password;
    private EditText new_retype_password;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Recebe os dados colocados no login
        Bundle args = getIntent().getExtras();
        username = args.getString("username");
        password = args.getString("password");

        new_name = (EditText)findViewById(R.id.new_name);
        new_email = (EditText)findViewById(R.id.new_email);

        new_username = (EditText)findViewById(R.id.new_username);
        new_username.setText(username);

        new_password = (EditText)findViewById(R.id.new_password);
        new_password.setText(password);

        new_retype_password = (EditText)findViewById(R.id.new_password_retype);
    }


    public void sign_up(View view)
    {
        username = getUsername();
        password = getPassword();

        name = getName();
        email = getEmail();
        retype_password = getRetypePassword();

        if(requiredFields(name, email, username, password, retype_password))
        {
            ParseUser user = new ParseUser();

            user.put("name", getName());
            user.setEmail(getEmail());
            user.setUsername(getUsername());
            user.setPassword(getPassword());

            user.signUpInBackground(new SignUpCallback(){
                @Override
                public void done(ParseException e) {
                    if (e == null)
                        signUpSuccessful();
                    else
                        signUpUnSuccessful(e.getMessage());
                }
            });
        }
    }


    public void loginUnSuccessful(String error)
    {
        Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
    }

    public void loginSuccessful()
    {
        startActivity(new Intent(getBaseContext(), Button.class));
    }

    private void signUpSuccessful()
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

    private void signUpUnSuccessful(String error)
    {
        Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
    }

    private boolean requiredFields(String name, String email, String username, String password, String retype_password)
    {
        if(name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || retype_password.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please, check the required field.", Toast.LENGTH_SHORT).show();

            //Fecha a janela do teclado
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(new_name.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(new_email.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(new_username.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(new_password.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(new_retype_password.getWindowToken(), 0);

            if(name.isEmpty())
                new_name.setError("Required");
            else if(email.isEmpty())
                new_email.setError("Required");
            else if(username.isEmpty())
                new_username.setError("Required");
            else if(password.isEmpty())
                new_password.setError("Required");
            else if(retype_password.isEmpty())
                new_retype_password.setError("Required");
            return false;
        }
        else
            return true;
    }

    private String getUsername()
    {
        return new_username.getText().toString();
    }

    private String getPassword()
    {
        return new_password.getText().toString();
    }

    private String getEmail()
    {
        return new_email.getText().toString();
    }

    private String getName()
    {
        return new_name.getText().toString();
    }

    private String getRetypePassword()
    {
        return new_retype_password.getText().toString();
    }
}
