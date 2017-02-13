package iace.entity.activity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import iace.entity.DbFile;

@Entity
@DiscriminatorValue("Activity")
public class ActivityAttach extends DbFile {
	private static final long serialVersionUID = -7983528801058556631L;
	
	private transient Activity activity;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}
