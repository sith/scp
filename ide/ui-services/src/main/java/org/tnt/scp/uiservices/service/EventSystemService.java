package org.tnt.scp.uiservices.service;


import org.tnt.scp.uiservices.events.*;

public interface EventSystemService {

    public static interface SubscriberService {
        public void subscribeAddScriptEvent(SystemEventListener<AddScriptEvent> listener);

        public void subscribeAddSceneEvent(SystemEventListener<AddSceneEvent> listener);

        public void subscribeScriptSelectedEvent(SystemEventListener<ScriptSelectedEvent> listener);

        public void subscribeAddCharacterEvent(SystemEventListener<AddCharacterEvent> listener);

        public void subscribeRemoveCharacterEvent(SystemEventListener<RemoveCharacterEvent> listener);
    }

    public static interface ProducerService {
        public void addScriptEvent(AddScriptEvent addScriptEvent);

        void addSceneEvent(AddSceneEvent addSceneEvent);

        void selectScript(ScriptSelectedEvent scriptSelectedEvent);

        void addCharacterEvent(AddCharacterEvent addCharacterEvent);

        void removeCharacterEvent(RemoveCharacterEvent removeCharacterEvent);
    }
}
