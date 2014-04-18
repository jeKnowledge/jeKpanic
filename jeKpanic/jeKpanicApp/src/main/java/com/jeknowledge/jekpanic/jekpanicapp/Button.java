package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Button extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);
    }

    public void panic(View view)
    {
        Toast.makeText(getApplicationContext(), "Senta-te, o cartão já vem ;)", Toast.LENGTH_SHORT).show();
    }
}
