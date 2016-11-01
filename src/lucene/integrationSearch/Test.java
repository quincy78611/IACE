package lucene.integrationSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;

import iace.entity.researchPlan.Technology;

public class Test {
	public static Logger log = LogManager.getLogger(Test.class);
	public static String luceneIndexFolder;
	
	public static void main(String[] args) throws IOException, ParseException {
		setLuceneIndexFolder();
//		testCreate();
//		testDelete();
		testSearch();
//		testDocCount();
//		testMultithreadReadWrite();
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
	
	public static void testCreate() throws IOException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexWriter writer = IntegrationIndexer.createIndexWriter(indexDirectory);
		try {
			IntegrationIndexer.addDoc(writer, 19860905, "TestModel", "TestData測試資 料kkk k");
		} catch (Exception e) {
			throw e;
		} finally {
			writer.close();
			indexDirectory.close();
		}
	}
	
	public static void testDelete() throws IOException, ParseException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexWriter writer = IntegrationIndexer.createIndexWriter(indexDirectory);
		try {
			IntegrationIndexer.deleteDoc(writer, 89852, Technology.class);
		} catch (ParseException e) {
			throw e;
		} finally {
			writer.close();
			indexDirectory.close();
		}
	}
	
	public static void testSearch() throws IOException, ParseException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexReader reader = IntegrationIndexer.createIndexReader(indexDirectory);
		try {
			IntegrationSearchModel arg1 = new IntegrationSearchModel();
			arg1.setClassName(Technology.class.getName());
			arg1.setSearchText("SKD11的");
			List<Document> docs = IntegrationIndexer.search(reader, arg1);
			for (Document doc : docs) {
				log.debug(doc.get(IntegrationIndexer.FIELD_ID) + "\t" + doc.get(IntegrationIndexer.FIELD_CLASS_NAME));
			}			
		} catch (Exception e) {
			throw e;
		} finally {
			reader.close();
			indexDirectory.close();
		}
	}

	public static void testDocCount() throws IOException, ParseException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexReader reader = IntegrationIndexer.createIndexReader(indexDirectory);
		try {
			log.debug(IntegrationIndexer.existCount(reader, 89881, "iace.entity.researchPlan.Technology"));
		} catch (Exception e) {
			throw e;
		} finally {
			reader.close();
			indexDirectory.close();
		}
	}

	public static void testMultithreadReadWrite() {
		new Thread(new TestMultithreadWrite(1)).start();
		new Thread(new TestMultithreadSearch(2)).start();
	}

}


