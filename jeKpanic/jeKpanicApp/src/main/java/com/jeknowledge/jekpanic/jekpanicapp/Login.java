package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void login(View view) {
        EditText usernameField = (EditText)findViewById(R.id.username_field);
        username = usernameField.getText().toString();
        EditText passwordField = (EditText)findViewById(R.id.password_field);
        password = passwordField.getText().toString();

        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
        }
        else
        {
            startActivity(new Intent(this, Button.class));
        }
    }
}
