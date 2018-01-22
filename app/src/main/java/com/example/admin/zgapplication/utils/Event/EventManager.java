package com.example.admin.zgapplication.utils.Event;

/**
 * Created by fushuang on 2018/1/16.
 */

public class EventManager {
    public static Event event;

    public static void setEvent(Event event) {
        EventManager.event = event;
    }

    public static void raiseEvent(){
        event.onSomethingHappend("1");
    }
}
