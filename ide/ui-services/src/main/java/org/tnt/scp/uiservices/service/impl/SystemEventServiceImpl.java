package org.tnt.scp.uiservices.service.impl;

import com.google.common.collect.Lists;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.tnt.scp.uiservices.events.*;
import org.tnt.scp.uiservices.service.EventSystemService;
import org.tnt.scp.uiservices.service.SystemEventListener;

@ServiceProviders({
        @ServiceProvider(service = EventSystemService.ProducerService.class),
        @ServiceProvider(service = EventSystemService.SubscriberService.class)
})
public class SystemEventServiceImpl implements EventSystemService.ProducerService, EventSystemService.SubscriberService {

    private final InstanceContent addScriptsContent;
    private final InstanceContent addSceneContent;

    private final Lookup.Result<AddScriptEvent> addScriptEventResult;
    private final Lookup.Result<AddSceneEvent> addSceneEventResult;
    private Lookup.Result<ScriptSelectedEvent> selectScriptEventResult;
    private InstanceContent secletScriptContent;
    private InstanceContent addCharacterContent;
    private Lookup.Result<AddCharacterEvent> addCharacterEventResult;
    private InstanceContent removeCharacterContent;
    private Lookup.Result<AddCharacterEvent> removeCharacterEventResult;

    public SystemEventServiceImpl() {
        addScriptsContent = new InstanceContent();
        addScriptEventResult = new AbstractLookup(addScriptsContent).lookupResult(AddScriptEvent.class);

        addSceneContent = new InstanceContent();
        addSceneEventResult = new AbstractLookup(addSceneContent).lookupResult(AddSceneEvent.class);

        secletScriptContent = new InstanceContent();
        selectScriptEventResult = new AbstractLookup(secletScriptContent).lookupResult(ScriptSelectedEvent.class);

        addCharacterContent = new InstanceContent();
        addCharacterEventResult = new AbstractLookup(addCharacterContent).lookupResult(AddCharacterEvent.class);

        removeCharacterContent = new InstanceContent();
        removeCharacterEventResult = new AbstractLookup(removeCharacterContent).lookupResult(AddCharacterEvent.class);

    }

    @Override
    public void addScriptEvent(AddScriptEvent addScriptEvent) {
        addScriptsContent.set(Lists.newArrayList(addScriptEvent), null);
    }

    @Override
    public void addSceneEvent(AddSceneEvent addSceneEvent) {
        addSceneContent.set(Lists.newArrayList(addSceneEvent), null);

    }

    @Override
    public void subscribeAddScriptEvent(SystemEventListener<AddScriptEvent> listener) {
        addScriptEventResult.addLookupListener(new ProxyLookupListener<AddScriptEvent>(listener));
    }

    @Override
    public void subscribeAddSceneEvent(SystemEventListener<AddSceneEvent> listener) {
        addSceneEventResult.addLookupListener(new ProxyLookupListener<AddSceneEvent>(listener));
    }

    @Override
    public void subscribeScriptSelectedEvent(SystemEventListener<ScriptSelectedEvent> listener) {
        selectScriptEventResult.addLookupListener(new ProxyLookupListener<ScriptSelectedEvent>(listener));
    }

    @Override
    public void subscribeAddCharacterEvent(SystemEventListener<AddCharacterEvent> listener) {

        addCharacterEventResult.addLookupListener(new ProxyLookupListener<AddCharacterEvent>(listener));

    }

    @Override
    public void subscribeRemoveCharacterEvent(SystemEventListener<RemoveCharacterEvent> listener) {
        removeCharacterEventResult.addLookupListener(new ProxyLookupListener<RemoveCharacterEvent>(listener));
    }

    @Override
    public void selectScript(ScriptSelectedEvent scriptSelectedEvent) {
        secletScriptContent.set(Lists.newArrayList(scriptSelectedEvent), null);
    }

    @Override
    public void addCharacterEvent(AddCharacterEvent addCharacterEvent) {
        addCharacterContent.set(Lists.newArrayList(addCharacterEvent), null);

    }

    @Override
    public void removeCharacterEvent(RemoveCharacterEvent removeCharacterEvent) {
        removeCharacterContent.set(Lists.newArrayList(removeCharacterEvent), null);
    }

    private static class ProxyLookupListener<T extends AbstractEvent> implements LookupListener {
        private SystemEventListener<T> targetListener;

        public ProxyLookupListener(SystemEventListener<T> listener) {
            targetListener = listener;
        }

        @Override
        public void resultChanged(LookupEvent ev) {

            Lookup.Result<T> result = (Lookup.Result<T>) ev.getSource();
            targetListener.onEvent(result.allInstances().iterator().next());
        }
    }
}
