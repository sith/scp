package org.tnt.scp.uiservices.service.impl;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.tnt.scp.common.generated.ScriptType;
import org.tnt.scp.uiservices.service.ScriptService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@ServiceProvider(service = ScriptService.class)
public class ScriptServiceImpl implements ScriptService {

    private InstanceContent content = new InstanceContent();
    private final AbstractLookup abstractLookup;

    public ScriptServiceImpl() {
        abstractLookup = new AbstractLookup(content);
    }

    @Override
    public List<ScriptType> loadScripts() {


        FileObject scriptsFolder = FileUtil.getConfigFile("Scripts");
        FileObject[] children = scriptsFolder.getChildren();
        List<ScriptType> scripts = new ArrayList<ScriptType>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("org.tnt.scp.common.generated");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            for (FileObject child : children) {
                scripts.add((ScriptType) unmarshaller.unmarshal(child.getInputStream()));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addScript(ScriptType script) {

    }

    @Override
    public void removeScript(ScriptType script) {

    }

    @Override
    public Lookup getLookup() {
        return abstractLookup;
    }
}
