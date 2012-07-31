package org.tnt.scp.uiservices.service.impl;

import com.google.common.collect.Lists;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.Script;
import org.tnt.scp.uiservices.service.GlobalContext;

import java.util.*;


@ServiceProviders({
        @ServiceProvider(service = GlobalContext.AwareSerice.class),
        @ServiceProvider(service = GlobalContext.UpdateService.class)})
public class GlobalContextImpl implements GlobalContext.AwareSerice, GlobalContext.UpdateService {


    private Map<Id, ScriptContext> scriptContexts;


    private final InstanceContent openedScriptsContent;
    private final AbstractLookup openedScriptsLookup;

    public GlobalContextImpl() {
        openedScriptsContent = new InstanceContent();
        openedScriptsLookup = new AbstractLookup(this.openedScriptsContent);

        scriptContexts = new HashMap<Id, ScriptContext>();

    }


    @Override
    public Script findScriptById(Id id) {
        return scriptContexts.get(id).getScript();
    }

    @Override
    public Collection<? extends Script> openedScripts() {
        return openedScriptsLookup.lookupAll(Script.class);
    }

    @Override
    public void openScript(Script scriptType) {
        scriptContexts.put(scriptType.getId(), new ScriptContext(scriptType, new ArrayList<SceneContext>()));
        openedScriptsContent.add(scriptType);
    }

    @Override
    public void closeScript(Script scriptType) {
        scriptContexts.remove(scriptType.getId());
        openedScriptsContent.remove(scriptType);

    }

    @Override
    public void updateScript(Script selectedScript) {
        scriptContexts.put(selectedScript.getId(), new ScriptContext(selectedScript, new ArrayList<SceneContext>()));
    }
}
