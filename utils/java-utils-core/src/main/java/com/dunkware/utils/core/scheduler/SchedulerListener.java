package com.dunkware.utils.core.scheduler;

import com.dunkware.utils.core.scheduler.model.Event;

public interface SchedulerListener {
    void onEventStarted(Event event);
    void onEventEnded(Event event);
}