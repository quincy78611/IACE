package iace.service.rdFocus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.util.PagedList;
import iace.dao.rdFocus.IRdFocusDao;
import iace.entity.rdFocus.RdFocus;
import iace.entity.rdFocus.RdFocusAttach;
import iace.entity.rdFocus.RdFocusSearchModel;
import iace.service.BaseIaceService;
import iace.service.ServiceFactory;

public class RdFocusService extends BaseIaceService<RdFocus> {
	private RdFocusAttachService rdFocusAttachService = ServiceFactory.getRdFocusAttachService();
	private IRdFocusDao dao;

	public RdFocusService(IRdFocusDao dao) {
		super(dao);
		this.dao = dao;
	}

	public PagedList<RdFocus> searchBy(RdFocusSearchModel arg) {
		return this.dao.searchBy(arg);
	}
	
	public List<RdFocus> sampleForHomePage() {
		return this.dao.sampleForHomePage();
	}
	
	public List<RdFocus> sampleForEpaper() {
		return this.dao.sampleForEpaper();
	}

	@Override
	public RdFocus get(Long id) {
		RdFocus entity = this.dao.get(id);
		for (RdFocusAttach attach : entity.getAttachs()) {
			attach.setFileFolder(this.rdFocusAttachService.getRdFocusAttachFolder());
			try {
				attach.loadFileContentFromDisk();
			} catch (Exception e) {
				log.warn("Load attach file fail!");
			}
		}
		return entity;
	}

	@Override
	public void create(RdFocus entity) throws IOException, SQLException {
		// remove empty file
		List<RdFocusAttach> attachList = new ArrayList<RdFocusAttach>();
		for (RdFocusAttach attach : entity.getAttachs()) {
			if (attach.hasUpload()) {
				attach.setRdFocus(entity);
				attachList.add(attach);
			}
		}
		
		// create news data to DB
		entity.setAttachs(null);
		super.create(entity);
		
		// save attach files and create attach data to DB
		for (RdFocusAttach attach : attachList) {
			this.rdFocusAttachService.create(attach);
		}
		entity.setAttachs(attachList);
	}

	@Override
	public void update(RdFocus entity) throws IOException, SQLException {
		// remove empty from attachList
		List<RdFocusAttach> attachList = new ArrayList<RdFocusAttach>();
		for (RdFocusAttach attach : entity.getAttachs()) {
			if (attach.hasUpload() || attach.getId() > 0) {
				attach.setRdFocus(entity);
				attachList.add(attach);  // keep new upload files or old files  
			}
		}
		entity.setAttachs(attachList);
		
		{// must UPDATE [old attach files] before CREATE [new upload files] otherwise after create, attach's id will > 0 and will delete the file just create 
			// update old attach files
			for (RdFocusAttach attach : entity.getAttachs()) {
				if (attach.getId() > 0) {
					this.rdFocusAttachService.update(attach);
				}
			}
			// create new upload files
			for (RdFocusAttach attach : entity.getAttachs()) {
				if (attach.getId() <= 0) {
					this.rdFocusAttachService.create(attach);
				}
			}
		}
		
		// delete file which is not in current attaches anymore
		Set<Long> currentAttachIdSet = new HashSet<Long>();
		attachList.forEach(e -> currentAttachIdSet.add(e.getId()));
		RdFocus newsO = get(entity.getId());
		for (RdFocusAttach attach : newsO.getAttachs()) {
			if(currentAttachIdSet.contains(attach.getId()) == false) {
				this.rdFocusAttachService.delete(attach);
			}
		}
		
		super.update(entity);
	}

	@Override
	public void delete(RdFocus entity) throws IOException, SQLException {
		for (RdFocusAttach attach : entity.getAttachs()) {
			this.rdFocusAttachService.delete(attach);
		}
		entity.setAttachs(null);
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		RdFocus entity = this.dao.get(id);
		delete(entity);
	}
	
	
}
