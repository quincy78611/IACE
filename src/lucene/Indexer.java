package lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
	public final static String FIELD_ID = "id";
	public final static String FIELD_CLASS_NAME = "className";
	public final static String FIELD_CONTENT = "content";
	public final static Analyzer ANALYZER = new StandardAnalyzer();

	private Directory indexDirectory;
	private IndexWriter writer;

	public Indexer(String luceneIndexFolder) throws IOException {
		this.indexDirectory = FSDirectory.open(new File(luceneIndexFolder).toPath());
		IndexWriterConfig config = new IndexWriterConfig(ANALYZER);
		this.writer = new IndexWriter(this.indexDirectory, config);
	}
	
	public IndexWriter getWriter(String luceneIndexFolder) throws IOException {
		this.indexDirectory = FSDirectory.open(new File(luceneIndexFolder).toPath());
		IndexWriterConfig config = new IndexWriterConfig(ANALYZER);
		this.writer = new IndexWriter(this.indexDirectory, config);
		return writer;
	}
	
	public void addDoc(long id, String className, String content) throws IOException {
		Document doc = new Document();
		doc.add(new StringField(FIELD_ID, String.valueOf(id), Store.YES));
		doc.add(new TextField(FIELD_CLASS_NAME, className, Store.YES));
		doc.add(new TextField(FIELD_CONTENT, content, Store.NO));		
		this.writer.addDocument(doc);
	}
	
	public void addDoc(long id, Class<?> cls, String content) throws IOException {
		addDoc(id, cls.getSimpleName(), content);
	}
	
	public void deleteDoc(long id) throws ParseException, IOException {
		Query q = new QueryParser(FIELD_ID, ANALYZER).parse(String.valueOf(id));
		this.writer.deleteDocuments(q);
	}
	
	public void updateDoc(long id, String className, String content) throws IOException, ParseException {
		deleteDoc(id);
		addDoc(id, className, content);
	}

	public void close() throws CorruptIndexException, IOException {
		this.writer.close();
	}

	public Directory getIndexDirectory() {
		return indexDirectory;
	}

	public IndexWriter getWriter() {
		return writer;
	}
	
	 
}
