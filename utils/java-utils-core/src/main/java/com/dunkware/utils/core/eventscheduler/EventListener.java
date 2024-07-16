package com.dunkware.utils.core.eventscheduler;

import com.dunkware.utils.core.eventscheduler.model.Event;

public interface EventListener {
    void onEventStarted(Event event);
    void onEventEnded(Event event);
}