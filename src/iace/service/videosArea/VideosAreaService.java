package iace.service.videosArea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.util.PagedList;
import core.util.ThumbnailUtil;
import iace.dao.videosArea.IVideosAreaDao;
import iace.entity.videosArea.Video;
import iace.entity.videosArea.VideosArea;
import iace.entity.videosArea.VideosAreaSearchModel;
import iace.service.BaseIaceService;
import iace.service.ServiceFactory;

public class VideosAreaService extends BaseIaceService<VideosArea> {
	private VideoService videoService = ServiceFactory.getVideoService();
	
	private IVideosAreaDao dao;
	
	public VideosAreaService(IVideosAreaDao dao) {
		super(dao);
		this.dao = dao;
	}

	public PagedList<VideosArea> searchBy(VideosAreaSearchModel arg) {
		return this.dao.searchBy(arg);
	}

	@Override
	public VideosArea get(Long id) {
		VideosArea entity = this.dao.get(id);
		for (Video video : entity.getVideoList()) {
			video.setFileFolder(this.videoService.getVideoFolder());
			try {
				video.loadFileContentFromDisk();
			} catch (Exception e) {
				log.warn("Load attach file fail!");
			}
		}

		return entity;
	}

	@Override
	public void create(VideosArea entity) throws IOException, SQLException {
		produceThumbnail(entity);
		
		//remove empty file
		List<Video> videoList = new ArrayList<Video>();
		for (Video video : entity.getVideoList()) {
			if (video.hasUpload()) {
				video.setVideosArea(entity);
				videoList.add(video);
			}
		}
		entity.setVideoList(null);
	
		// create videosArea data to DB
		super.create(entity);
		
		// save video files and create data to DB
		for (Video video : videoList) {
			this.videoService.create(video);
		}
		entity.setVideoList(videoList);
	}

	@Override
	public void update(VideosArea entity) throws IOException, SQLException {
		//remove empty file
		List<Video> videoList = new ArrayList<Video>();
		for (Video video : entity.getVideoList()) {
			if (video.hasUpload() || video.getId() > 0) {
				video.setVideosArea(entity);
				videoList.add(video);
			}
		}
		entity.setVideoList(videoList);
		
		// update old videos & create new upload videos
		for (Video video : entity.getVideoList()) {
			if (video.getId() > 0) { 
				this.videoService.update(video);
			} else {
				this.videoService.create(video);
			}
		}
		
		// delete video which is not exist anymore
		Set<Long> currentVideoIdSet = new HashSet<Long>();
		videoList.forEach(e -> currentVideoIdSet.add(e.getId()));
		VideosArea entityO = get(entity.getId());
		for (Video video : entityO.getVideoList()) {
			if (currentVideoIdSet.contains(video.getId()) == false) {
				this.videoService.delete(video);
			}
		}
		
		produceThumbnail(entity);
		super.update(entity);
	}

	@Override
	public void delete(VideosArea entity) throws IOException, SQLException {
		for (Video video : entity.getVideoList()) {
			this.videoService.delete(video);
		}
		entity.setVideoList(null);
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		VideosArea entity = this.dao.get(id);
		delete(entity);
	}
	
	private void produceThumbnail(VideosArea entity) throws IOException {
		if (entity.getUploadThumbnail() != null) {
			Path p = Paths.get(entity.getUploadThumbnail().getAbsolutePath());
			byte[] image = Files.readAllBytes(p);
			entity.setThumbnail(ThumbnailUtil.resize(image, 400, 300, true, 1f));
		}
	}
}
