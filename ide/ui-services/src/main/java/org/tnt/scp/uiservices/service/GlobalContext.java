package org.tnt.scp.uiservices.service;

import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.Scene;
import org.tnt.scp.common.generated.SceneRef;
import org.tnt.scp.common.generated.Script;
import org.tnt.scp.uiservices.context.ScriptContext;

import java.util.Collection;

public interface GlobalContext {


    public static interface UpdateService {
        public void openScript(Script scriptType);

        public void closeScript(Script scriptType);

        void updateScript(Script selectedScript);

        void updateScene(SceneRef sceneRef, Id scriptId);
    }

    public static interface AwareService {

        public ScriptContext findScriptContext(Id id);

        public Script findScriptById(Id id);

        public Collection<? extends Script> openedScripts();
    }

}
