package org.tnt.scp.data.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tnt.scp.data.domain.Message;
import org.tnt.scp.data.services.SimpleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class SimpleServiceImpl extends SimpleJpaRepository<Message,Long> implements SimpleService {


    public SimpleServiceImpl(Class<Message> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
