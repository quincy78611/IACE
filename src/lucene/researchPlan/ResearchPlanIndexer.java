package lucene.researchPlan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import lucene.integrationSearch.IntegrationIndexer;

public class ResearchPlanIndexer {
	public final static Object lock = new Object();
	public final static String FIELD_ID = "id";
	public final static String FIELD_MANAGER = "manager";
	public final static String FIELD_KEYWORD = "keyword";
	public final static Analyzer ANALYZER = new StandardAnalyzer();
	
	public static Directory openDirectory(String luceneIndexFolder) throws IOException {
		Directory indexDirectory = FSDirectory.open(new File(luceneIndexFolder).toPath());
		return indexDirectory;
	}
	
	public static IndexWriter createIndexWriter(Directory indexDirectory) throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(ANALYZER);
		IndexWriter writer = new IndexWriter(indexDirectory, config);
		return writer;
	}
	
	public static IndexReader createIndexReader(Directory indexDirectory) throws IOException {
		IndexReader reader = DirectoryReader.open(indexDirectory);
		return reader;
	}
	
	public static void addDoc(IndexWriter writer, long id, String manager, String[] keywords) throws IOException {
		for (String keyword : keywords) {
			Document doc = new Document();
			doc.add(new StringField(FIELD_ID, String.valueOf(id), Store.YES));
			doc.add(new StringField(FIELD_MANAGER, manager.trim(), Store.YES));
			doc.add(new StringField(FIELD_KEYWORD, keyword.trim(), Store.YES));
			writer.addDocument(doc);
		}
	}
	
	public static void deleteDoc(IndexWriter writer, long id) throws ParseException, IOException {
		String queryStr = FIELD_ID + ":" + id;
		String[] queryFields = { IntegrationIndexer.FIELD_ID };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(queryFields, ANALYZER);
		Query query = parser.parse(queryStr);
		writer.deleteDocuments(query);
	}
	
	public static void updateDoc(IndexWriter writer, long id, String manager, String[] keywords) throws ParseException, IOException {
		deleteDoc(writer, id);
		addDoc(writer, id, manager, keywords);
	}
	
	public static List<Document> search(IndexReader reader, String keyword) throws ParseException, IOException {
		Query query = createSearchQuery(keyword);
		IndexSearcher searcher = new IndexSearcher(reader);
		int topN = queryTotalRecordsCount(reader, query);
		TopDocs docs = searcher.search(query, topN > 0 ? topN : 1);
		ScoreDoc[] scoreDocs = docs.scoreDocs;

		List<Document> resultList = new ArrayList<Document>();
		for (int i=0; i < scoreDocs.length; i++) {
			int docId = scoreDocs[i].doc;
			Document d = searcher.doc(docId);
			resultList.add(d);
		}
		
		return resultList;
	}
	
	public static int queryTotalRecordsCount(IndexReader reader, Query query) throws ParseException, IOException {
		IndexSearcher searcher = new IndexSearcher(reader);
		int docCount = searcher.count(query);
		return docCount;
	}
	
	public static long queryTotalRecordsCount(IndexReader reader, String keyword) throws ParseException, IOException {
		Query query = createSearchQuery(keyword);
		return queryTotalRecordsCount(reader, query);
	}
	
	private static Query createSearchQuery(String keyword) throws ParseException {
		Term term = new Term(FIELD_KEYWORD, keyword);
		Query query = new TermQuery(term);
		return query;
	}
	

}
