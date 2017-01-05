package iace.dao.relatedWebsite;

import iace.dao.BaseIaceDao;
import iace.entity.relatedWebsite.RelatedWebsite;

public class RelatedWebsiteDao extends BaseIaceDao<RelatedWebsite> implements IRelatedWebsiteDao {

	public RelatedWebsiteDao() {
		super(RelatedWebsite.class);
	}

}
