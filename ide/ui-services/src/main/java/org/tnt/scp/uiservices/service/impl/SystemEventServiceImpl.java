package org.tnt.scp.uiservices.service.impl;

import com.google.common.collect.Lists;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.tnt.scp.uiservices.events.AbstractEvent;
import org.tnt.scp.uiservices.events.AddScriptEvent;
import org.tnt.scp.uiservices.service.EventSystemService;
import org.tnt.scp.uiservices.service.SystemEventListener;

@ServiceProviders({
        @ServiceProvider(service = EventSystemService.ProducerService.class),
        @ServiceProvider(service = EventSystemService.SubscriberService.class)
})
public class SystemEventServiceImpl implements EventSystemService.ProducerService, EventSystemService.SubscriberService {

    private final InstanceContent addScriptsContent;

    private final Lookup.Result<AddScriptEvent> addScriptEventResult;

    public SystemEventServiceImpl() {
        addScriptsContent = new InstanceContent();
        addScriptEventResult = new AbstractLookup(addScriptsContent).lookupResult(AddScriptEvent.class);
    }

    @Override
    public void addScriptEvent(AddScriptEvent addScriptEvent) {
        addScriptsContent.set(Lists.newArrayList(addScriptEvent), null);
    }

    @Override
    public void subscribeAddScriptEvent(SystemEventListener<AddScriptEvent> listener) {
        addScriptEventResult.addLookupListener(new ProxyLookupListener(listener));
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
