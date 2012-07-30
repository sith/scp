package org.tnt.scp.uiservices.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.tnt.scp.common.generated.*;
import org.tnt.scp.uiservices.events.AddSceneEvent;
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
import java.util.List;
import java.util.UUID;


@ServiceProvider(service = ScriptService.class)
public class ScriptServiceImpl implements ScriptService {

    private static final String SCRIPT_EXT = ".script";
    public static final String SCENE_EXT = ".scene";
    private InstanceContent content = new InstanceContent();
    private final AbstractLookup abstractLookup;
    private ObjectFactory objectFactory;
    private final EntryStatisticsService.UpdateService scriptsStatistics;
    private final EventSystemService.ProducerService eventProducerService;
    private Jaxb2Marshaller marshaller;

    public ScriptServiceImpl() {
        abstractLookup = new AbstractLookup(content);
        objectFactory = new ObjectFactory();
        scriptsStatistics = Lookup.getDefault().lookup(EntryStatisticsService.UpdateService.class);
        eventProducerService = Lookup.getDefault().lookup(EventSystemService.ProducerService.class);
        marshaller = ContextHandler.getContext().getBean(Jaxb2Marshaller.class);
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


        FileObject scriptsFolder = FileUtil.getConfigFile("Scripts");

        OutputStream outputStream = null;
        try {
            String folderName = uuid.toString();

            FileObject folder = scriptsFolder.createFolder(folderName);

            FileObject scriptFile = folder.createData(uuid.toString() + SCRIPT_EXT);


            outputStream = scriptFile.getOutputStream();
            writeScript(script, marshaller, outputStream);


            AddScriptEvent event = new AddScriptEvent(script);

            eventProducerService.addScriptEvent(event);
            scriptsStatistics.openScript(script);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {

            IOUtils.closeQuietly(outputStream);

        }

    }

    private void writeScript(Script script, Jaxb2Marshaller marshaller, OutputStream outputStream) throws IOException {
        marshaller.marshal(objectFactory.createScript(script), new StreamResult(outputStream));
    }

    @Override
    public void createScene(Script script, String sceneName) {
        FileObject sceneFolder = getScriptFolder(script.getId());

        Scene scene = new Scene();
        String sceneIdString = UUID.randomUUID().toString();
        Id sceneId = new Id();
        sceneId.setValue(sceneIdString);
        scene.setId(sceneId);
        scene.setScriptRef(script.getId());

        SceneRef sceneRef = new SceneRef();
        sceneRef.setScenRef(sceneId);
        script.getScenesRef().getScenesRef().add(sceneRef);

        FileObject fileObject = sceneFolder.getFileObject(script.getId().getValue() + SCRIPT_EXT);

        FileLock lock = null;
        OutputStream scriptUpdateStream = null;
        OutputStream sceneUpdateStream = null;
        try {
            lock = fileObject.lock();
            scriptUpdateStream = fileObject.getOutputStream(lock);
            writeScript(script, marshaller, scriptUpdateStream);


            sceneUpdateStream = sceneFolder.createData(sceneIdString + SCENE_EXT).getOutputStream();
            writeScene(scene, marshaller, sceneUpdateStream);

            eventProducerService.addSceneEvent(new AddSceneEvent(scene));

        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            if (lock != null) {
                lock.releaseLock();
            }

            IOUtils.closeQuietly(sceneUpdateStream);
            IOUtils.closeQuietly(scriptUpdateStream);
        }
    }

    private void writeScene(Scene scene, Jaxb2Marshaller marshaller, OutputStream outputStream) throws IOException {
        marshaller.marshal(objectFactory.createScene(scene), new StreamResult(outputStream));

    }

    private FileObject getScriptFolder(Id id) {

        FileObject scriptsFolder = FileUtil.getConfigFile("Scripts");
        FileObject fileObject = scriptsFolder.getFileObject(id.getValue());
        if (fileObject == null) {
            throw new IllegalStateException("Script folder doesn't exist");
        }

        return fileObject;
    }

    @Override
    public Lookup getLookup() {
        return abstractLookup;
    }
}
