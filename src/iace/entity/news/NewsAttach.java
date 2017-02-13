package iace.entity.news;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import iace.entity.DbFile;

@Entity
@DiscriminatorValue("News")
public class NewsAttach extends DbFile {
	private static final long serialVersionUID = 3305612629891651456L;
	
	private transient News news;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
