package org.tnt.scp.uiservices.service;

import org.tnt.scp.uiservices.events.AbstractEvent;


public interface SystemEventListener<T extends AbstractEvent> {

    public void onEvent(T event);
}
