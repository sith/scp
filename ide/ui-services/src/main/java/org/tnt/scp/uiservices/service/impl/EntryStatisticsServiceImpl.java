package org.tnt.scp.uiservices.service.impl;

import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.tnt.scp.uiservices.service.EntryStatisticsService;


public class EntryStatisticsServiceImpl implements EntryStatisticsService {


    private final InstanceContent openedScriptsContent;

    public EntryStatisticsServiceImpl() {
        openedScriptsContent = new InstanceContent();
        AbstractLookup openedScriptsLookup = new AbstractLookup(this.openedScriptsContent);

    }

    @Override
    public Lookup openedScriptsLookup() {
        return openedScriptsLookup();
    }
}
