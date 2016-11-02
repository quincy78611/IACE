package iace.service.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;

import core.util.PagedList;
import iace.dao.coopExample.ICoopExDao;
import iace.dao.incubationCenter.IIncubationCenterDao;
import iace.dao.literature.ILiteratureDao;
import iace.dao.patent.IPatentDao;
import iace.dao.researchPlan.IResearchPlanDao;
import iace.dao.researchPlan.ITechnologyDao;
import iace.dao.talentedPeople.ITalentedPeopleDao;
import iace.entity.BaseSearchModel;
import iace.entity.coopExample.CoopEx;
import iace.entity.coopExample.CoopExSearchModel;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.incubationCenter.IncubationCenterSearchModel;
import iace.entity.literature.Literature;
import iace.entity.literature.LiteratureSearchModel;
import iace.entity.patent.Patent;
import iace.entity.patent.PatentSearchModel;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.ResearchPlanSearchModel;
import iace.entity.researchPlan.Technology;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;
import lucene.integrationSearch.IntegrationIndexer;
import lucene.integrationSearch.IntegrationSearchModel;
import lucene.integrationSearch.IntegrationSearchResult;

public class LuceneIndexService {
	protected static Logger log = LogManager.getLogger(LuceneIndexService.class);

	private IResearchPlanDao researchPlanDao;
	private ITechnologyDao technologyDao;
	private IPatentDao patentDao;
	private ITalentedPeopleDao talentedPeopleDao;
	private ICoopExDao coopExDao;
	private ILiteratureDao literautureDao;
	private IIncubationCenterDao incubationCenterDao;

	private String luceneIndexFolder;

	public LuceneIndexService(
			IResearchPlanDao researchPlanDao, 
			ITechnologyDao technologyDao, 
			IPatentDao patentDao, 
			ITalentedPeopleDao talentedPeopleDao,
			ICoopExDao coopExDao,
			ILiteratureDao literautureDao,
			IIncubationCenterDao incubationCenterDao) {
		this.researchPlanDao = researchPlanDao;
		this.technologyDao = technologyDao;
		this.patentDao = patentDao;
		this.talentedPeopleDao = talentedPeopleDao;
		this.coopExDao = coopExDao;
		this.literautureDao = literautureDao;
		this.incubationCenterDao = incubationCenterDao;

		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.luceneIndexFolder = prop.getProperty("luceneIndexFolder");
		} catch (IOException e) {
			log.fatal("", e);
		}
	}

	// rebuild the whole index
	public void rebuildIndex() throws IOException {
		synchronized (IntegrationIndexer.lock) {
			Directory indexDirectory = IntegrationIndexer.openDirectory(this.luceneIndexFolder);
			IndexWriter writer = IntegrationIndexer.createIndexWriter(indexDirectory);
			try {
				writer.deleteAll(); writer.commit();
//				createResearchPlanIndex(writer);
				createTechnologyIndex(writer);
				createPatentIndex(writer);
				createTalentedPeopleIndex(writer);
				createCoopExIndex(writer);
				createLiteratureIndex(writer);
				createIncubationCenterIndex(writer);
			} catch (IOException e) {
				throw e;
			} finally {
				writer.close();
				indexDirectory.close();
			}
		}
	}

	private void createResearchPlanIndex(IndexWriter writer) throws IOException {
		ResearchPlanSearchModel arg = new ResearchPlanSearchModel();
		long totalRecordCount = this.researchPlanDao.queryTotalRecordsCount(arg);
		int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
		for (int i = 0; i < pageCount; i++) {
			arg.setPageIndex(i);
			PagedList<ResearchPlan> pagedList = this.researchPlanDao.searchBy(arg);
			for (ResearchPlan entity : pagedList.getList()) {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			}
		}
	}

	private void createTechnologyIndex(IndexWriter writer) throws IOException {
		BaseSearchModel arg = new BaseSearchModel();
		long totalRecordCount = this.technologyDao.queryTotalRecordsCount();
		int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
		for (int i = 0; i < pageCount; i++) {
			arg.setPageIndex(i);
			PagedList<Technology> pagedList = this.technologyDao.searchBy(arg);
			for (Technology entity : pagedList.getList()) {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			}
		}
	}
	
	private void createPatentIndex(IndexWriter writer) throws IOException {
		PatentSearchModel arg = new PatentSearchModel();
		long totalRecordCount = this.patentDao.queryTotalRecordsCount(arg);
		int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
		for (int i = 0; i < pageCount; i++) {
			arg.setPageIndex(i);
			PagedList<Patent> pagedList = this.patentDao.searchBy(arg);
			for (Patent entity : pagedList.getList()) {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			}
		}
	}
	
	private void createTalentedPeopleIndex(IndexWriter writer) throws IOException {
		TalentedPeopleSearchModel arg = new TalentedPeopleSearchModel();
		long totalRecordCount = this.talentedPeopleDao.queryTotalRecordsCount(arg);
		int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
		for (int i = 0; i < pageCount; i++) {
			arg.setPageIndex(i);
			PagedList<TalentedPeople> pagedList = this.talentedPeopleDao.searchBy(arg);
			for (TalentedPeople entity : pagedList.getList()) {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			}
		}		
	}
	
	private void createCoopExIndex(IndexWriter writer) throws IOException {
		CoopExSearchModel arg = new CoopExSearchModel();
		long totalRecordCount = this.coopExDao.queryTotalRecordsCount(arg);
		int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
		for (int i = 0; i < pageCount; i++) {
			arg.setPageIndex(i);
			PagedList<CoopEx> pagedList = this.coopExDao.searchBy(arg);
			for (CoopEx entity : pagedList.getList()) {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			}
		}
	}
	
	private void createLiteratureIndex(IndexWriter writer) throws IOException {
		{
			LiteratureSearchModel arg = new LiteratureSearchModel();
			arg.setCategory("文獻");
			long totalRecordCount = this.literautureDao.queryTotalRecordsCount(arg);
			int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
			for (int i = 0; i < pageCount; i++) {
				arg.setPageIndex(i);
				PagedList<Literature> pagedList = this.literautureDao.searchBy(arg);
				for (Literature entity : pagedList.getList()) {
					IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
				}
			}
		}
		{
			LiteratureSearchModel arg = new LiteratureSearchModel();
			arg.setCategory("法規政策");
			long totalRecordCount = this.literautureDao.queryTotalRecordsCount(arg);
			int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
			for (int i = 0; i < pageCount; i++) {
				arg.setPageIndex(i);
				PagedList<Literature> pagedList = this.literautureDao.searchBy(arg);
				for (Literature entity : pagedList.getList()) {
					IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
				}
			}
		}
	}
	
	private void createIncubationCenterIndex(IndexWriter writer) throws IOException {
		IncubationCenterSearchModel arg = new IncubationCenterSearchModel();
		long totalRecordCount = this.incubationCenterDao.queryTotalRecordsCount(arg);
		int pageCount = (int) Math.ceil(totalRecordCount / (double) arg.getPageSize());
		for (int i = 0; i < pageCount; i++) {
			arg.setPageIndex(i);
			PagedList<IncubationCenter> pagedList = this.incubationCenterDao.searchBy(arg);
			for (IncubationCenter entity : pagedList.getList()) {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			}
		}
	}

	public PagedList<IntegrationSearchResult> integrationSearch(IntegrationSearchModel arg) throws IOException, ParseException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexReader reader = IntegrationIndexer.createIndexReader(indexDirectory);
		try {
			PagedList<Document> docs = IntegrationIndexer.searchBy(reader, arg); 
			List<IntegrationSearchResult> list = new ArrayList<IntegrationSearchResult>();
			for (Document doc : docs.getList()) {
				String className = doc.get(IntegrationIndexer.FIELD_CLASS_NAME);
				long id = Long.valueOf(doc.get(IntegrationIndexer.FIELD_ID));
				log.debug(className + "\t" + id);
				
				IntegrationSearchResult r = new IntegrationSearchResult();
				r.setType(className);
				if (ResearchPlan.class.getName().equals(className)) {
					r.setResearchPlan(this.researchPlanDao.get(id));
				} else if (Technology.class.getName().equals(className)) {
					r.setTechnology(this.technologyDao.get(id));
				} else if (Patent.class.getName().equals(className)) {
					r.setPatent(this.patentDao.get(id));
				} else if (TalentedPeople.class.getName().equals(className)) {
					r.setTalentedPeople(this.talentedPeopleDao.get(id));
				} else if (CoopEx.class.getName().equals(className)) {
					r.setCoopEx(this.coopExDao.get(id));
				} else if (Literature.class.getName().equals(className)) {
					r.setLiterature(this.literautureDao.get(id));
				} else if (IncubationCenter.class.getName().equals(className)) {
					r.setIncubationCenter(this.incubationCenterDao.get(id));
				} else {
					throw new IllegalArgumentException("No such type!");
				}				
				list.add(r);
			}
			
			PagedList<IntegrationSearchResult> resultList = 
					new PagedList<IntegrationSearchResult>(list, docs.getTotatlItemCount(), arg.getPageSize(), arg.getPageIndex());
			return resultList;			
		} catch (Exception e) {
			throw e;
		} finally {
			reader.close();
			indexDirectory.close();
		}
	}
}
