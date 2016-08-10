package iace.entity.qnrCooperateWay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;

@Entity
@Table(name = "QNR_COOP_WAY_MERIT")
public class QnrCooperateWayMerit extends BaseEntity {

	private static final long serialVersionUID = 2150548989097055716L;
	
	public static final int[] YEARS = {2013, 2014, 2015};

	private long id;
	private QnrCooperateWay qnrCooperateWay;
	private int year;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String c6;
	private String c7;
	private String c8;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_QUESTIONNAIRE_ID")
	@SequenceGenerator(name = "SEQUENCE_QUESTIONNAIRE_ID", sequenceName = "SEQUENCE_QUESTIONNAIRE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QNR_COOP_WAY_ID", nullable = false, updatable = false)
	public QnrCooperateWay getQnrCooperateWay() {
		return qnrCooperateWay;
	}

	public void setQnrCooperateWay(QnrCooperateWay qnrCooperateWay) {
		this.qnrCooperateWay = qnrCooperateWay;
	}

	@Column(name = "YEAR", nullable = false, length = 4)
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "C1", nullable = false, length = 20)
	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	@Column(name = "C2", nullable = false, length = 20)
	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	@Column(name = "C3", nullable = false, length = 20)
	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	@Column(name = "C4", nullable = false, length = 20)
	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	@Column(name = "C5", nullable = false, length = 20)
	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	@Column(name = "C6", nullable = false, length = 20)
	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	@Column(name = "C7", nullable = false, length = 20)
	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	@Column(name = "C8", nullable = false, length = 20)
	public String getC8() {
		return c8;
	}

	public void setC8(String c8) {
		this.c8 = c8;
	}

	
	

}
