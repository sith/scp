package org.tnt.scp.uiservices.service.impl;


import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.tnt.scp.common.generated.*;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Ignore
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


    @Test
    public void test() throws Exception {

        Id id1 = new Id();
        id1.setValue("1");

        Id id2 = new Id();
        id2.setValue("2");


        Object o = id1.mergeFrom(id2, id1);
        System.out.println(o);

    }
}

