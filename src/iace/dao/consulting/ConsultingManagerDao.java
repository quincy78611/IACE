package iace.dao.consulting;

import iace.dao.BaseIaceDao;
import iace.entity.consulting.ConsultingManager;

public class ConsultingManagerDao extends BaseIaceDao<ConsultingManager> implements IConsultingManagerDao {

	public ConsultingManagerDao() {
		super(ConsultingManager.class);
	}

}
