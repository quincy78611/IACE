package lucene.integrationSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;

public class TestMultithreadWrite implements Runnable {
	public static Logger log = LogManager.getLogger(Test.class);
	public static String luceneIndexFolder;

	int threadId;

	static {
		setLuceneIndexFolder();
	}

	public TestMultithreadWrite(int id) {
		this.threadId = id;
	}

	public static void main(String[] args) throws IOException, ParseException {
		new Thread(new TestMultithreadWrite(1)).start();
		new Thread(new TestMultithreadWrite(2)).start();
//		new Thread(new TestMultithreadWrite(3)).start();
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

	@Override
	public void run() {
//		test1();
		test2();
	}

	public void test1() {
		Directory indexDirectory = null;
		IndexWriter writer = null;
		try {
			indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
			writer = IntegrationIndexer.createIndexWriter(indexDirectory);
			for (int i = 0; i < 1000; i++) {
				IntegrationIndexer.addDoc(writer, i * 10 + this.threadId, "TestModel", "(threadId=" + this.threadId + ")TestData測試資 料kkk k[" + i + "]");
				System.out.println(this.threadId + ". (i=" + i + ") ");
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			try {
				writer.close();
				indexDirectory.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void test2() {
		for (int i = 0; i < 100; i++) {
			synchronized (IntegrationIndexer.lock) {
				Directory indexDirectory = null;
				IndexWriter writer = null;
				try {
					indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
					writer = IntegrationIndexer.createIndexWriter(indexDirectory);

					IntegrationIndexer.addDoc(writer, i * 10 + this.threadId, "TestModel", "(threadId=" + this.threadId + ")TestData測試資 料kkk k[" + i + "]");
					System.out.println(this.threadId + ". (i=" + i + ") ");

				} catch (IOException e2) {
					e2.printStackTrace();
				} finally {
					try {
						writer.close();
						indexDirectory.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
