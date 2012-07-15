package org.tnt.scp.services;

import org.springframework.data.repository.CrudRepository;
import org.tnt.scp.domain.Project;
import org.tnt.scp.domain.Script;

public interface ScriptService extends CrudRepository<Script, Long> {
}
