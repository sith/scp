package org.tnt.scp.uiservices.service.impl;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.tnt.scp.common.generated.CharacterType;
import org.tnt.scp.common.generated.Characters;
import org.tnt.scp.common.generated.ObjectFactory;
import org.tnt.scp.common.generated.Script;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class ScriptServiceImplTest {
    @Test
    public void testFake() throws Exception {

        ClassPathResource resource = new ClassPathResource("script_sample.xml");

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:services-module-context.xml");

        Jaxb2Marshaller marshaller = context.getBean("jaxb2Marshaller", Jaxb2Marshaller.class);


        JAXBElement<Script> unmarshal = (JAXBElement<Script>) marshaller.unmarshal(new StreamSource(resource.getInputStream()));
        Script scriptType = unmarshal.getValue();
        System.out.println(scriptType);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(150);
        ObjectFactory objectFactory = new ObjectFactory();
        Characters value = new Characters();

        CharacterType characterType = new CharacterType();
        characterType.setName("Sith");
        value.getCharacters().add(characterType);
        scriptType.setCharacters(value);
        marshaller.marshal(objectFactory.createScript(scriptType), new StreamResult(outputStream));

        System.out.println(new String(outputStream.toByteArray()));


    }
}

