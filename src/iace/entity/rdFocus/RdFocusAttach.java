package iace.entity.rdFocus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import iace.entity.DbFile;

@Entity
@DiscriminatorValue("RdFocus")
public class RdFocusAttach extends DbFile {
	private static final long serialVersionUID = -1593732662749081604L;

	private transient RdFocus rdFocus;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
	public RdFocus getRdFocus() {
		return rdFocus;
	}

	public void setRdFocus(RdFocus rdFocus) {
		this.rdFocus = rdFocus;
	}
	
	
}
