package org.tnt.scp.uiservices.service;

import org.openide.util.Lookup;
import org.tnt.scp.common.generated.ScriptType;

import java.util.List;

public interface ScriptService extends Lookup.Provider{

    public List<ScriptType> loadScripts();

    public void addScript(ScriptType script);

    public void removeScript(ScriptType script);

}
