package org.tnt.scp.uiservices.events;


public abstract class AbstractEvent<T> {

    private final T target;


    public AbstractEvent(T target) {
        this.target = target;
    }

    public T getTarget() {
        return target;
    }
}
