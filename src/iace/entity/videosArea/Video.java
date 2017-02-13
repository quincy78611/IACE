package iace.entity.videosArea;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import iace.entity.DbFile;

@Entity
@DiscriminatorValue("VideosArea")
public class Video extends DbFile {
	private static final long serialVersionUID = 4222738120436895884L;
	
	private transient VideosArea videosArea;
	
	public Video() {
		super.setFileType(FILE_TYPE_FILE);
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	public VideosArea getVideosArea() {
		return videosArea;
	}

	public void setVideosArea(VideosArea videosArea) {
		this.videosArea = videosArea;
	}

}
