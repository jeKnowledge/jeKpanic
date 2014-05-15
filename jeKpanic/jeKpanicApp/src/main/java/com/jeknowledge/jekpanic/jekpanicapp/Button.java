package com.jeknowledge.jekpanic.jekpanicapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.parse.SendCallback;

import org.json.*;

/**
 * Button Activity permite enviar notificações para a lista de clientes com posse do cartão
 */
public class Button extends Activity
{
    private ParseUser currentUser = ParseUser.getCurrentUser();
    private EditText msgField;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

        ParseObject.registerSubclass(Request.class);
        msgField = (EditText)findViewById(R.id.message_to_send);
        msgField.setHint("Hello, " + currentUser.getString("name") + " wants the card!");
    }

    public void sendPanic(View view)
    {
        message = msgField.getText().toString();
        if(message.isEmpty())
            sendRequest(createRequest());
        else
            sendRequest(createRequest(message));
    }

    private Request createRequest(){
        Request request = new Request();
        request.setOwner(currentUser);
        request.setMsg("Hello, " + currentUser.get("name") + " wants the card!");
        return request;
    }

    private Request createRequest(String msg){
        Request request = new Request();
        request.setOwner(currentUser);
        request.setMsg("" + currentUser.get("name") + " say: " + msg);
        return request;
    }

    public void sendRequest(final Request object)
    {
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                    sendRequest("Everyone", object.getMsg());
                else
                    Toast.makeText(getApplicationContext(), "An error occurred. Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendRequest(String channel, String text){
        ParsePush push = new ParsePush();
        push.setChannel(channel);
        push.setMessage(text);
        push.sendInBackground(new SendCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                    Toast.makeText(getApplicationContext(), "A jeKpanic Request was sent.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "An error occurred. Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
