package lucene.integrationSearch;

import org.apache.lucene.document.Document;

public class IntegrationIndexerDoc {	
	private long id;
	private String className;
	
	public IntegrationIndexerDoc(Document d) {
		this(d.get(IntegrationIndexer.FIELD_ID), d.get(IntegrationIndexer.FIELD_CLASS_NAME));
	}
	
	public IntegrationIndexerDoc(long id, String className) {
		this.id = id;
		this.className = className;
	}

	public IntegrationIndexerDoc(String id, String className) {
		this.id = Long.valueOf(id);
		this.className = className;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
	
}
