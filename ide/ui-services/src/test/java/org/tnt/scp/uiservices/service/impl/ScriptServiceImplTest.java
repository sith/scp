package org.tnt.scp.uiservices.service.impl;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.tnt.scp.common.generated.CharacterType;
import org.tnt.scp.common.generated.CharactersType;
import org.tnt.scp.common.generated.ObjectFactory;
import org.tnt.scp.common.generated.ScriptType;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

public class ScriptServiceImplTest {
    @Test
    public void testFake() throws Exception {

        ClassPathResource resource = new ClassPathResource("sample.xml");

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:jaxb-context.xml");

        Jaxb2Marshaller marshaller = context.getBean("jaxb2Marshaller", Jaxb2Marshaller.class);


        try {
            JAXBElement<ScriptType> unmarshal = (JAXBElement<ScriptType>) marshaller.unmarshal(new StreamSource(resource.getInputStream()));
            ScriptType scriptType = unmarshal.getValue();
            System.out.println(scriptType);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(150);
            ObjectFactory objectFactory = new ObjectFactory();
            CharactersType value = new CharactersType();
            CharacterType characterType = new CharacterType();
            characterType.setName("Sith");
            value.getCharacter().add(characterType);
            scriptType.setCharacters(value);
            marshaller.marshal(objectFactory.createScript(scriptType), new StreamResult(outputStream));

            System.out.println(new String(outputStream.toByteArray()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

