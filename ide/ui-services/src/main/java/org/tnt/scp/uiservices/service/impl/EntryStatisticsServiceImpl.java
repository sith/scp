package org.tnt.scp.uiservices.service.impl;

import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.tnt.scp.common.generated.Script;
import org.tnt.scp.uiservices.service.EntryStatisticsService;


@ServiceProviders({
        @ServiceProvider(service = EntryStatisticsService.AwareSerice.class),
        @ServiceProvider(service = EntryStatisticsService.UpdateService.class)})
public class EntryStatisticsServiceImpl implements EntryStatisticsService.AwareSerice, EntryStatisticsService.UpdateService {


    private final InstanceContent openedScriptsContent;
    private final AbstractLookup openedScriptsLookup;

    public EntryStatisticsServiceImpl() {
        openedScriptsContent = new InstanceContent();
        openedScriptsLookup = new AbstractLookup(this.openedScriptsContent);

    }

    @Override
    public Lookup openedScriptsLookup() {
        return openedScriptsLookup;
    }

    @Override
    public void openScript(Script scriptType) {
        openedScriptsContent.add(scriptType);
    }

    @Override
    public void closeScript(Script scriptType) {
        openedScriptsContent.remove(scriptType);

    }
}
