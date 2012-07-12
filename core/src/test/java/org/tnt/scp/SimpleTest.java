package org.tnt.scp;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tnt.scp.data.domain.Message;
import org.tnt.scp.data.services.SimpleService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleTest {


    @Test
    public void testName() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:scp-context.xml");
        SimpleService bean = context.getBean(SimpleService.class);
        Message message = new Message();
        message.setMessage("Hello world");
        bean.save(message);
        Message beanById = bean.findOne(message.getId());

        assertTrue(beanById.equals(message));

    }
}
