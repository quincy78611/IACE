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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import core.util.AESEncrypter;
import iace.entity.BaseEntity;
import iace.entity.option.OptionSchool;

@Entity
@Table(name = "QNR_COOP_WAY")
public class QnrCooperateWay extends BaseEntity {

	private static final long serialVersionUID = 9162172455947173462L;

	private long id;
	private OptionSchool school;
	
	private String q0_1;
	private Integer q0_2;
	private Integer q0_3;
	private Integer q1_1;
	private Integer q1_2;
	private Integer q1_3;
	private Integer q1_4;
	private Integer q1_5;
	private Integer q1_6;
	private Integer q1_7;
	private Integer q1_8;
	private Integer q1_9;
	private Integer q1_10;
	private Integer q1_11;
	private Integer q1_12;
	private Integer q1_13;
	private Integer q1_14;
	private Integer q1_15;
	private Integer q2_1;
	private Integer q2_2;
	private Integer q2_3;
	private Integer q2_4;
	private Integer q2_5;
	private Integer q2_6;
	private Integer q2_7;
	private Integer q2_8;
	private Integer q2_9;
	private Integer q2_10;
	private Integer q3_1;
	private Integer q3_2;
	private Integer q3_3;
	private Integer q3_4;
	private Integer q3_5;
	private Integer q3_6;
	private Integer q3_7;
	private Integer q3_8;
	private Integer q3_9;
	private Integer q3_10;
	private Integer q3_11;
	private Integer q3_12;
	private Integer q3_13;
	private Integer q3_14;
	private Integer q4_1;
	private Integer q4_2;
	private Integer q4_3;
	private Integer q4_4;
	private Integer q4_5;
	private Integer q4_6;
	private Integer q4_7;
	private Integer q4_8;
	private Integer q4_9;
	
	private Integer attendCourseWill;
	private String attendCourseDesc;
	
	private Boolean aggreePDPL;
	private transient String name;
	private String encryptedName;
	private transient String email;
	private String encryptedEmail;
	private transient String address;
	private String encryptedAddress;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_ID", referencedColumnName = "ID", nullable = false, updatable = false)
	public OptionSchool getSchool() {
		return school;
	}

	public void setSchool(OptionSchool school) {
		this.school = school;
	}

	@Column(name = "Q0_1", nullable = true, length = 20)
	public String getQ0_1() {
		return q0_1;
	}

	public void setQ0_1(String q0_1) {
		this.q0_1 = q0_1;
	}

	@Column(name = "Q0_2", nullable = true, length = 1)
	public Integer getQ0_2() {
		return q0_2;
	}

	public void setQ0_2(Integer q0_2) {
		this.q0_2 = q0_2;
	}

	@Column(name = "Q0_3", nullable = true, length = 1)
	public Integer getQ0_3() {
		return q0_3;
	}

	public void setQ0_3(Integer q0_3) {
		this.q0_3 = q0_3;
	}

	@Column(name = "Q1_1", nullable = true, length = 1)
	public Integer getQ1_1() {
		return q1_1;
	}

	public void setQ1_1(Integer q1_1) {
		this.q1_1 = q1_1;
	}

	@Column(name = "Q1_2", nullable = true, length = 1)
	public Integer getQ1_2() {
		return q1_2;
	}

	public void setQ1_2(Integer q1_2) {
		this.q1_2 = q1_2;
	}

	@Column(name = "Q1_3", nullable = true, length = 1)
	public Integer getQ1_3() {
		return q1_3;
	}

	public void setQ1_3(Integer q1_3) {
		this.q1_3 = q1_3;
	}

	@Column(name = "Q1_4", nullable = true, length = 1)
	public Integer getQ1_4() {
		return q1_4;
	}

	public void setQ1_4(Integer q1_4) {
		this.q1_4 = q1_4;
	}

	@Column(name = "Q1_5", nullable = true, length = 1)
	public Integer getQ1_5() {
		return q1_5;
	}

	public void setQ1_5(Integer q1_5) {
		this.q1_5 = q1_5;
	}

	@Column(name = "Q1_6", nullable = true, length = 1)
	public Integer getQ1_6() {
		return q1_6;
	}

	public void setQ1_6(Integer q1_6) {
		this.q1_6 = q1_6;
	}

	@Column(name = "Q1_7", nullable = true, length = 1)
	public Integer getQ1_7() {
		return q1_7;
	}

	public void setQ1_7(Integer q1_7) {
		this.q1_7 = q1_7;
	}

	@Column(name = "Q1_8", nullable = true, length = 1)
	public Integer getQ1_8() {
		return q1_8;
	}

	public void setQ1_8(Integer q1_8) {
		this.q1_8 = q1_8;
	}

	@Column(name = "Q1_9", nullable = true, length = 1)
	public Integer getQ1_9() {
		return q1_9;
	}

	public void setQ1_9(Integer q1_9) {
		this.q1_9 = q1_9;
	}

	@Column(name = "Q1_10", nullable = true, length = 1)
	public Integer getQ1_10() {
		return q1_10;
	}

	public void setQ1_10(Integer q1_10) {
		this.q1_10 = q1_10;
	}

	@Column(name = "Q1_11", nullable = true, length = 1)
	public Integer getQ1_11() {
		return q1_11;
	}

	public void setQ1_11(Integer q1_11) {
		this.q1_11 = q1_11;
	}

	@Column(name = "Q1_12", nullable = true, length = 1)
	public Integer getQ1_12() {
		return q1_12;
	}

	public void setQ1_12(Integer q1_12) {
		this.q1_12 = q1_12;
	}

	@Column(name = "Q1_13", nullable = true, length = 1)
	public Integer getQ1_13() {
		return q1_13;
	}

	public void setQ1_13(Integer q1_13) {
		this.q1_13 = q1_13;
	}

	@Column(name = "Q1_14", nullable = true, length = 1)
	public Integer getQ1_14() {
		return q1_14;
	}

	public void setQ1_14(Integer q1_14) {
		this.q1_14 = q1_14;
	}

	@Column(name = "Q1_15", nullable = true, length = 1)
	public Integer getQ1_15() {
		return q1_15;
	}

	public void setQ1_15(Integer q1_15) {
		this.q1_15 = q1_15;
	}

	@Column(name = "Q2_1", nullable = true, length = 1)
	public Integer getQ2_1() {
		return q2_1;
	}

	public void setQ2_1(Integer q2_1) {
		this.q2_1 = q2_1;
	}

	@Column(name = "Q2_2", nullable = true, length = 1)
	public Integer getQ2_2() {
		return q2_2;
	}

	public void setQ2_2(Integer q2_2) {
		this.q2_2 = q2_2;
	}

	@Column(name = "Q2_3", nullable = true, length = 1)
	public Integer getQ2_3() {
		return q2_3;
	}

	public void setQ2_3(Integer q2_3) {
		this.q2_3 = q2_3;
	}

	@Column(name = "Q2_4", nullable = true, length = 1)
	public Integer getQ2_4() {
		return q2_4;
	}

	public void setQ2_4(Integer q2_4) {
		this.q2_4 = q2_4;
	}

	@Column(name = "Q2_5", nullable = true, length = 1)
	public Integer getQ2_5() {
		return q2_5;
	}

	public void setQ2_5(Integer q2_5) {
		this.q2_5 = q2_5;
	}

	@Column(name = "Q2_6", nullable = true, length = 1)
	public Integer getQ2_6() {
		return q2_6;
	}

	public void setQ2_6(Integer q2_6) {
		this.q2_6 = q2_6;
	}

	@Column(name = "Q2_7", nullable = true, length = 1)
	public Integer getQ2_7() {
		return q2_7;
	}

	public void setQ2_7(Integer q2_7) {
		this.q2_7 = q2_7;
	}

	@Column(name = "Q2_8", nullable = true, length = 1)
	public Integer getQ2_8() {
		return q2_8;
	}

	public void setQ2_8(Integer q2_8) {
		this.q2_8 = q2_8;
	}

	@Column(name = "Q2_9", nullable = true, length = 1)
	public Integer getQ2_9() {
		return q2_9;
	}

	public void setQ2_9(Integer q2_9) {
		this.q2_9 = q2_9;
	}

	@Column(name = "Q2_10", nullable = true, length = 1)
	public Integer getQ2_10() {
		return q2_10;
	}

	public void setQ2_10(Integer q2_10) {
		this.q2_10 = q2_10;
	}

	@Column(name = "Q3_1", nullable = true, length = 1)
	public Integer getQ3_1() {
		return q3_1;
	}

	public void setQ3_1(Integer q3_1) {
		this.q3_1 = q3_1;
	}

	@Column(name = "Q3_2", nullable = true, length = 1)
	public Integer getQ3_2() {
		return q3_2;
	}

	public void setQ3_2(Integer q3_2) {
		this.q3_2 = q3_2;
	}

	@Column(name = "Q3_3", nullable = true, length = 1)
	public Integer getQ3_3() {
		return q3_3;
	}

	public void setQ3_3(Integer q3_3) {
		this.q3_3 = q3_3;
	}

	@Column(name = "Q3_4", nullable = true, length = 1)
	public Integer getQ3_4() {
		return q3_4;
	}

	public void setQ3_4(Integer q3_4) {
		this.q3_4 = q3_4;
	}

	@Column(name = "Q3_5", nullable = true, length = 1)
	public Integer getQ3_5() {
		return q3_5;
	}

	public void setQ3_5(Integer q3_5) {
		this.q3_5 = q3_5;
	}

	@Column(name = "Q3_6", nullable = true, length = 1)
	public Integer getQ3_6() {
		return q3_6;
	}

	public void setQ3_6(Integer q3_6) {
		this.q3_6 = q3_6;
	}

	@Column(name = "Q3_7", nullable = true, length = 1)
	public Integer getQ3_7() {
		return q3_7;
	}

	public void setQ3_7(Integer q3_7) {
		this.q3_7 = q3_7;
	}

	@Column(name = "Q3_8", nullable = true, length = 1)
	public Integer getQ3_8() {
		return q3_8;
	}

	public void setQ3_8(Integer q3_8) {
		this.q3_8 = q3_8;
	}

	@Column(name = "Q3_9", nullable = true, length = 1)
	public Integer getQ3_9() {
		return q3_9;
	}

	public void setQ3_9(Integer q3_9) {
		this.q3_9 = q3_9;
	}

	@Column(name = "Q3_10", nullable = true, length = 1)
	public Integer getQ3_10() {
		return q3_10;
	}

	public void setQ3_10(Integer q3_10) {
		this.q3_10 = q3_10;
	}

	@Column(name = "Q3_11", nullable = true, length = 1)
	public Integer getQ3_11() {
		return q3_11;
	}

	public void setQ3_11(Integer q3_11) {
		this.q3_11 = q3_11;
	}

	@Column(name = "Q3_12", nullable = true, length = 1)
	public Integer getQ3_12() {
		return q3_12;
	}

	public void setQ3_12(Integer q3_12) {
		this.q3_12 = q3_12;
	}

	@Column(name = "Q3_13", nullable = true, length = 1)
	public Integer getQ3_13() {
		return q3_13;
	}

	public void setQ3_13(Integer q3_13) {
		this.q3_13 = q3_13;
	}

	@Column(name = "Q3_14", nullable = true, length = 1)
	public Integer getQ3_14() {
		return q3_14;
	}

	public void setQ3_14(Integer q3_14) {
		this.q3_14 = q3_14;
	}
	
	@Column(name = "Q4_1", nullable = true, length = 1)
	public Integer getQ4_1() {
		return q4_1;
	}

	public void setQ4_1(Integer q4_1) {
		this.q4_1 = q4_1;
	}

	@Column(name = "Q4_2", nullable = true, length = 1)
	public Integer getQ4_2() {
		return q4_2;
	}

	public void setQ4_2(Integer q4_2) {
		this.q4_2 = q4_2;
	}

	@Column(name = "Q4_3", nullable = true, length = 1)
	public Integer getQ4_3() {
		return q4_3;
	}

	public void setQ4_3(Integer q4_3) {
		this.q4_3 = q4_3;
	}

	@Column(name = "Q4_4", nullable = true, length = 1)
	public Integer getQ4_4() {
		return q4_4;
	}

	public void setQ4_4(Integer q4_4) {
		this.q4_4 = q4_4;
	}

	@Column(name = "Q4_5", nullable = true, length = 1)
	public Integer getQ4_5() {
		return q4_5;
	}

	public void setQ4_5(Integer q4_5) {
		this.q4_5 = q4_5;
	}

	@Column(name = "Q4_6", nullable = true, length = 1)
	public Integer getQ4_6() {
		return q4_6;
	}

	public void setQ4_6(Integer q4_6) {
		this.q4_6 = q4_6;
	}

	@Column(name = "Q4_7", nullable = true, length = 1)
	public Integer getQ4_7() {
		return q4_7;
	}

	public void setQ4_7(Integer q4_7) {
		this.q4_7 = q4_7;
	}

	@Column(name = "Q4_8", nullable = true, length = 1)
	public Integer getQ4_8() {
		return q4_8;
	}

	public void setQ4_8(Integer q4_8) {
		this.q4_8 = q4_8;
	}

	@Column(name = "Q4_9", nullable = true, length = 1)
	public Integer getQ4_9() {
		return q4_9;
	}

	public void setQ4_9(Integer q4_9) {
		this.q4_9 = q4_9;
	}
	

	@Column(name = "ATTEND_COURSE_WILL", nullable = true, length = 1)
	public Integer getAttendCourseWill() {
		return attendCourseWill;
	}

	public void setAttendCourseWill(Integer attendCourseWill) {
		this.attendCourseWill = attendCourseWill;
	}

	@Column(name = "ATTEND_COURSE_DESC", nullable = true, length = 1000)
	public String getAttendCourseDesc() {
		return attendCourseDesc;
	}

	public void setAttendCourseDesc(String attendCourseDesc) {
		this.attendCourseDesc = attendCourseDesc;
	}

	@Column(name = "AGREE_PDPL")
	@Type(type = "true_false")
	public Boolean getAggreePDPL() {
		return aggreePDPL;
	}

	public void setAggreePDPL(Boolean aggreePDPL) {
		this.aggreePDPL = aggreePDPL;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.encryptedName = AESEncrypter.encrypt(AESEncrypter.KEY, name);
	}

	@Column(name = "APPLICANT_NAME")
	public String getEncryptedName() {
		return encryptedName;
	}

	public void setEncryptedName(String encryptedName) {
		this.encryptedName = encryptedName;
		this.name = AESEncrypter.decrypt(AESEncrypter.KEY, encryptedName);
	}

	@Transient
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.encryptedEmail = AESEncrypter.encrypt(AESEncrypter.KEY, email);
	}

	@Column(name = "APPLICANT_EMAIL")
	public String getEncryptedEmail() {
		return encryptedEmail;
	}

	public void setEncryptedEmail(String encryptedEmail) {
		this.encryptedEmail = encryptedEmail;
		this.email = AESEncrypter.decrypt(AESEncrypter.KEY, encryptedEmail);
	}

	@Transient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		this.encryptedAddress = AESEncrypter.encrypt(AESEncrypter.KEY, address);
	}

	@Column(name = "APPLICANT_ADDRESS")
	public String getEncryptedAddress() {
		return encryptedAddress;
	}

	public void setEncryptedAddress(String encryptedAddress) {
		this.encryptedAddress = encryptedAddress;
		this.address = AESEncrypter.decrypt(AESEncrypter.KEY, encryptedAddress);
	}


}
