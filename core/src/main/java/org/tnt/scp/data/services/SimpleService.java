package org.tnt.scp.data.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tnt.scp.data.domain.Message;


public interface SimpleService extends CrudRepository<Message, Long>
//        extends CrudRepository<Message, Long>
{


}
