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
	private Integer p1_1_1;
	private Integer p1_1_2;
	private Integer p1_2_1;
	private Integer p1_2_2;
	private Integer p1_3_1;
	private Integer p1_3_2;
	private Integer p1_4_1;
	private Integer p1_4_2;
	private Integer p1_4_3;
	private Integer p2_1_1;
	private Integer p2_1_2;
	private Integer p2_2_1;
	private Integer p2_2_2;
	private Integer p2_3_1;
	private Integer p2_3_2;
	private Integer p2_4_1;
	private Integer p2_4_2;
	private Integer p2_4_3;
	private Integer p2_4_4;
	private Integer p2_4_5;

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

	@Column(name = "P1_1_1", nullable = true, length = 10)
	public Integer getP1_1_1() {
		return p1_1_1;
	}

	public void setP1_1_1(Integer p1_1_1) {
		this.p1_1_1 = p1_1_1;
	}

	@Column(name = "P1_1_2", nullable = true, length = 10)
	public Integer getP1_1_2() {
		return p1_1_2;
	}

	public void setP1_1_2(Integer p1_1_2) {
		this.p1_1_2 = p1_1_2;
	}

	@Column(name = "P1_2_1", nullable = true, length = 10)
	public Integer getP1_2_1() {
		return p1_2_1;
	}

	public void setP1_2_1(Integer p1_2_1) {
		this.p1_2_1 = p1_2_1;
	}

	@Column(name = "P1_2_2", nullable = true, length = 10)
	public Integer getP1_2_2() {
		return p1_2_2;
	}

	public void setP1_2_2(Integer p1_2_2) {
		this.p1_2_2 = p1_2_2;
	}

	@Column(name = "P1_3_1", nullable = true, length = 10)
	public Integer getP1_3_1() {
		return p1_3_1;
	}

	public void setP1_3_1(Integer p1_3_1) {
		this.p1_3_1 = p1_3_1;
	}

	@Column(name = "P1_3_2", nullable = true, length = 10)
	public Integer getP1_3_2() {
		return p1_3_2;
	}

	public void setP1_3_2(Integer p1_3_2) {
		this.p1_3_2 = p1_3_2;
	}

	@Column(name = "P1_4_1", nullable = true, length = 10)
	public Integer getP1_4_1() {
		return p1_4_1;
	}

	public void setP1_4_1(Integer p1_4_1) {
		this.p1_4_1 = p1_4_1;
	}

	@Column(name = "P1_4_2", nullable = true, length = 10)
	public Integer getP1_4_2() {
		return p1_4_2;
	}

	public void setP1_4_2(Integer p1_4_2) {
		this.p1_4_2 = p1_4_2;
	}

	@Column(name = "P1_4_3", nullable = true, length = 10)
	public Integer getP1_4_3() {
		return p1_4_3;
	}

	public void setP1_4_3(Integer p1_4_3) {
		this.p1_4_3 = p1_4_3;
	}

	@Column(name = "P2_1_1", nullable = true, length = 10)
	public Integer getP2_1_1() {
		return p2_1_1;
	}

	public void setP2_1_1(Integer p2_1_1) {
		this.p2_1_1 = p2_1_1;
	}

	@Column(name = "P2_1_2", nullable = true, length = 10)
	public Integer getP2_1_2() {
		return p2_1_2;
	}

	public void setP2_1_2(Integer p2_1_2) {
		this.p2_1_2 = p2_1_2;
	}

	@Column(name = "P2_2_1", nullable = true, length = 10)
	public Integer getP2_2_1() {
		return p2_2_1;
	}

	public void setP2_2_1(Integer p2_2_1) {
		this.p2_2_1 = p2_2_1;
	}

	@Column(name = "P2_2_2", nullable = true, length = 10)
	public Integer getP2_2_2() {
		return p2_2_2;
	}

	public void setP2_2_2(Integer p2_2_2) {
		this.p2_2_2 = p2_2_2;
	}

	@Column(name = "P2_3_1", nullable = true, length = 10)
	public Integer getP2_3_1() {
		return p2_3_1;
	}

	public void setP2_3_1(Integer p2_3_1) {
		this.p2_3_1 = p2_3_1;
	}

	@Column(name = "P2_3_2", nullable = true, length = 10)
	public Integer getP2_3_2() {
		return p2_3_2;
	}

	public void setP2_3_2(Integer p2_3_2) {
		this.p2_3_2 = p2_3_2;
	}

	@Column(name = "P2_4_1", nullable = true, length = 10)
	public Integer getP2_4_1() {
		return p2_4_1;
	}

	public void setP2_4_1(Integer p2_4_1) {
		this.p2_4_1 = p2_4_1;
	}

	@Column(name = "P2_4_2", nullable = true, length = 10)
	public Integer getP2_4_2() {
		return p2_4_2;
	}

	public void setP2_4_2(Integer p2_4_2) {
		this.p2_4_2 = p2_4_2;
	}

	@Column(name = "P2_4_3", nullable = true, length = 10)
	public Integer getP2_4_3() {
		return p2_4_3;
	}

	public void setP2_4_3(Integer p2_4_3) {
		this.p2_4_3 = p2_4_3;
	}

	@Column(name = "P2_4_4", nullable = true, length = 10)
	public Integer getP2_4_4() {
		return p2_4_4;
	}

	public void setP2_4_4(Integer p2_4_4) {
		this.p2_4_4 = p2_4_4;
	}

	@Column(name = "P2_4_5", nullable = true, length = 10)
	public Integer getP2_4_5() {
		return p2_4_5;
	}

	public void setP2_4_5(Integer p2_4_5) {
		this.p2_4_5 = p2_4_5;
	}

}
