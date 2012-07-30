package org.tnt.scp.uiservices.service;

import org.openide.util.Lookup;
import org.tnt.scp.common.generated.Script;

import java.util.List;

public interface ScriptService extends Lookup.Provider{

    public List<Script> loadScripts();

    public void openScript(Script script);

    public void removeScript(Script script);

    void createScript(String scriptName);

    public void createScene(Script script, String sceneName);
}
