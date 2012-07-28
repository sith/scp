package org.tnt.scp.uiservices.service;

import org.openide.util.Lookup;
import org.tnt.scp.common.generated.Script;

public interface EntryStatisticsService {


    public static interface UpdateService {
        public void openScript(Script scriptType);

        public void closeScript(Script scriptType);
    }

    public static interface AwareSerice {

        public Lookup openedScriptsLookup();
    }

}
