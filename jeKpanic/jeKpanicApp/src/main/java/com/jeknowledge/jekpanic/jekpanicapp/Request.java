package com.jeknowledge.jekpanic.jekpanicapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Tomas on 15/5/2014.
 */
@ParseClassName("Request")
public class Request extends ParseObject
{
    public String getObjectId()
    {
        return getString("objectId");
    }

    public void setOwner(ParseUser owner)
    {
        put("owner", owner.getObjectId());
    }
}
