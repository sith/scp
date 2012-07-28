package org.tnt.scp.uiservices.service.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.tnt.scp.common.generated.*;
import org.tnt.scp.uiservices.events.AddScriptEvent;
import org.tnt.scp.uiservices.installer.ContextHandler;
import org.tnt.scp.uiservices.service.EntryStatisticsService;
import org.tnt.scp.uiservices.service.EventSystemService;
import org.tnt.scp.uiservices.service.ScriptService;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@ServiceProvider(service = ScriptService.class)
public class ScriptServiceImpl implements ScriptService {

    private static final String SCRIPT_EXT = ".script";
    private InstanceContent content = new InstanceContent();
    private final AbstractLookup abstractLookup;
    private ObjectFactory objectFactory;
    private final EntryStatisticsService.UpdateService scriptsStatistics;
    private final EventSystemService.ProducerService eventProducerService;

    public ScriptServiceImpl() {
        abstractLookup = new AbstractLookup(content);
        objectFactory = new ObjectFactory();
        scriptsStatistics = Lookup.getDefault().lookup(EntryStatisticsService.UpdateService.class);
        eventProducerService = Lookup.getDefault().lookup(EventSystemService.ProducerService.class);
    }

    @Override
    public List<Script> loadScripts() {


        FileObject scriptsFolder = FileUtil.getConfigFile("Scripts");
        FileObject[] children = scriptsFolder.getChildren();
        ArrayList<Script> scripts = Lists.newArrayList();

        for (FileObject child : children) {
            if (!child.isFolder()) {
                continue;
            }

            String name = child.getName();

            FileObject scriptFile = FileUtil.getConfigFile(child.getPath() + "/" + name + SCRIPT_EXT);
            Jaxb2Marshaller marshaller = ContextHandler.getContext().getBean(Jaxb2Marshaller.class);
            try {
                InputStream inputStream = scriptFile.getInputStream();
                JAXBElement<Script> element = (JAXBElement<Script>) marshaller.unmarshal(new StreamSource(inputStream));
                Script scriptType = element.getValue();
                scripts.add(scriptType);

                AddScriptEvent addScriptEvent = new AddScriptEvent(scriptType);

                eventProducerService.addScriptEvent(addScriptEvent);
                scriptsStatistics.openScript(scriptType);

                inputStream.close();
            } catch (FileNotFoundException e) {
                throw new IllegalStateException(e);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }


        return scripts;
    }

    @Override
    public void openScript(Script script) {

    }

    @Override
    public void removeScript(Script script) {

    }


    @Override
    public void createScript(String scriptName) {

        UUID uuid = UUID.randomUUID();
        Script script = new Script();
        Author author = new Author();
        author.setName("tmpAuthor");
        script.setAuthor(author);
        script.setName(scriptName);
        Id id = new Id();
        id.setValue(uuid.toString());
        script.setId(id);
        script.setCharacters(new Characters());
        script.setScenesRef(new ScenesRef());
        Jaxb2Marshaller marshaller = ContextHandler.getContext().getBean(Jaxb2Marshaller.class);

        FileObject scriptsFolder = FileUtil.getConfigFile("Scripts");
        try {
            String folderName = uuid.toString();

            if (scriptsFolder.getFileObject(folderName) != null) {
                throw new IllegalStateException("Folder already exists");
            }

            FileObject folder = scriptsFolder.createFolder(folderName);
            FileObject scriptFile = folder.createData(uuid.toString() + SCRIPT_EXT);
            OutputStream outputStream = scriptFile.getOutputStream();
            marshaller.marshal(objectFactory.createScript(script), new StreamResult(outputStream));
            outputStream.close();


            AddScriptEvent event = new AddScriptEvent(script);

            eventProducerService.addScriptEvent(event);
            scriptsStatistics.openScript(script);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public Lookup getLookup() {
        return abstractLookup;
    }
}
