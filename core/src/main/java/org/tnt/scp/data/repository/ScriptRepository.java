package org.tnt.scp.data.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tnt.scp.domain.Scene;
import org.tnt.scp.domain.Script;
import org.tnt.scp.services.SceneService;
import org.tnt.scp.services.ScriptService;

import javax.persistence.EntityManager;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ScriptRepository extends SimpleJpaRepository<Script, Long> implements ScriptService {
    public ScriptRepository(Class<Script> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
