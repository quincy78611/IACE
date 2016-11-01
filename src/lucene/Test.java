package lucene;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import iace.entity.patent.Patent;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.Technology;

public class Test {
	protected static Logger log = LogManager.getLogger(Test.class);
	public static String luceneIndexFolder;
	
	
//	public static String searchText = Indexer.FIELD_CONTENT + ":PACKET";
//	public static String searchText = Indexer.FIELD_CLASS_NAME + ":" + Technology.class.getSimpleName();
	public static String searchText = Indexer.FIELD_CONTENT + ":北市Operating";
//	public static String searchText = Indexer.FIELD_CLASS_NAME + ":" + Technology.class.getSimpleName() + "  " + Indexer.FIELD_CONTENT + ":METHOD AND PACKET";
	public static int hitsPerPage = 10;

	public static void main(String[] args) throws IOException, ParseException {
		setLuceneIndexFolder();
		Indexer indexer = new Indexer(luceneIndexFolder);
		
		createIndex(indexer);
		search(indexer);
//		indexer.deleteDoc(2392);
//		update(indexer);
		
		
//		deleteAll(indexer);
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

	public static void createIndex(Indexer indexer) throws IOException {
		indexer.addDoc(2390, Technology.class.getSimpleName(), "METHOD OF CONFIGURING MULTI-LEVEL PACKET TRANSMISSION PATHS IN A WIRELESS SENSOR NETWORK");
		indexer.addDoc(2391, Patent.class.getSimpleName(), "METHOD OF CONFIGURING MULTI-LEVEL PACKET TRANSMISSION PATHS IN A WIRELESS SENSOR NETWORK");
		indexer.addDoc(2392, Patent.class.getSimpleName(), "今天台北市政府發布一個訊息Operating method of low-power-consumption wireless sensor network system");
		indexer.addDoc(2393, Patent.class.getSimpleName(), "中文測試Operating method of low-power-consumption wireless sensor network system");
		indexer.close();
	}
	
	public static void search(Indexer indexer) throws ParseException, IOException {
		// 2. query
		String[] queryFields = { Indexer.FIELD_CLASS_NAME, Indexer.FIELD_CONTENT };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(queryFields, Indexer.ANALYZER);
		parser.setDefaultOperator(QueryParser.Operator.OR);
		Query query = parser.parse(searchText);
		log.info(searchText);

		// 3. search
		IndexReader reader = DirectoryReader.open(indexer.getIndexDirectory());
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs = searcher.search(query, hitsPerPage);
		ScoreDoc[] hits = docs.scoreDocs;

		// 4. display results
		log.info("Found " + docs.totalHits + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			log.info(docId + ". " + d.get(Indexer.FIELD_ID) + "\t" + d.get(Indexer.FIELD_CLASS_NAME));
		}
		
		reader.close();
	}


	public static void update(Indexer indexer) throws IOException, ParseException {
		indexer.updateDoc(2392, Patent.class.getSimpleName(), "嗨Operating method of low-power-consumption wireless sensor network system");
		indexer.close();
	}

	public static void deleteAll(Indexer indexer) throws IOException {
		IndexWriter writer = indexer.getWriter();
		writer.deleteAll();
		writer.close();
	}
}
