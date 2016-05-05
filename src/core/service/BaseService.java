package core.service;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseService<T, ID extends Serializable> implements IBaseCRUDService<T, ID> {
	protected static Logger log = LogManager.getLogger(BaseService.class);
}
