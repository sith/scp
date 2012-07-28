package org.tnt.scp.uiservices.service;


import org.tnt.scp.uiservices.events.AddScriptEvent;

public interface EventSystemService {

    public static interface SubscriberService {
        public void subscribeAddScriptEvent(SystemEventListener<AddScriptEvent> listener);
    }

    public static interface ProducerService {
        public void addScriptEvent(AddScriptEvent addScriptEvent);
    }
}
