package org.tnt.scp.data.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.tnt.scp.data.domain.Message;
import org.tnt.scp.data.services.SimpleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class RepositoryFactory extends JpaRepositoryFactoryBean<Repository<Message,Long>,Message,Long> {

    @PersistenceContext
    private EntityManager em;


    @Bean(name = "simpleService")
    private SimpleService getSimpleService() {
        return new SimpleServiceImpl(Message.class, em);
    }

}
