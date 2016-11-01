package lucene.integrationSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;

import iace.entity.researchPlan.Technology;

public class TestMultithreadSearch implements Runnable {
	public static Logger log = LogManager.getLogger(Test.class);
	public static String luceneIndexFolder;
	
	int threadId;
	
	static {
		setLuceneIndexFolder();
	}
	
	public TestMultithreadSearch(int id) {
		this.threadId = id;
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		new Thread(new TestMultithreadSearch(1)).start();
		new Thread(new TestMultithreadSearch(2)).start();
		new Thread(new TestMultithreadSearch(3)).start();
	}

	private static void setLuceneIndexFolder() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/configs/iace.properties"));
			luceneIndexFolder = prop.getProperty("luceneIndexFolder");
			log.debug(luceneIndexFolder);
		} catch (IOException e) {
			log.fatal("", e);
		}
	}

	public void run() {  
		for (int i=0; i<1000; i++) {
			try {
				testSearch(i);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public void testSearch(int i) throws IOException, ParseException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexReader reader = IntegrationIndexer.createIndexReader(indexDirectory);
		try {
			IntegrationSearchModel arg1 = new IntegrationSearchModel();
			arg1.setClassName(Technology.class.getName());
			arg1.setSearchText("SKD11");
			List<Document> docs = IntegrationIndexer.search(reader, arg1);
			for (Document doc : docs) {
				System.out.println(this.threadId+". (i="+i+") "+doc.get(IntegrationIndexer.FIELD_ID) + "\t" + doc.get(IntegrationIndexer.FIELD_CLASS_NAME));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			reader.close();
			indexDirectory.close();
		}
	}
}
