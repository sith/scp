package org.tnt.scp.uiservices.service;

import org.openide.util.Lookup;
import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.Script;

import java.util.Collection;

public interface GlobalContext {


    public static interface UpdateService {
        public void openScript(Script scriptType);

        public void closeScript(Script scriptType);

        void updateScript(Script selectedScript);
    }

    public static interface AwareSerice {

        public Script findScriptById(Id id);
        public Collection<? extends Script> openedScripts();
    }

}
