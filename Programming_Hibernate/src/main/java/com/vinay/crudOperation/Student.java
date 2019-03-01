package com.vinay.crudOperation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Generating SQL statements takes time and Hibernate, therefore, uses one
 * cached SQL UPDATE statement per entity. It sets all database columns so that
 * it can be used for all update operations. You can change that with
 * the @DynamicUpdate annotation. It tells Hibernate to generate a new SQL
 * statement for each update operation. The only thing you need to do is to add
 * the @DynamicUpdate annotation to your entity class.
 */

@SuppressWarnings("serial")
@Entity
@DynamicUpdate
@Table(name = "Student_Dtls")
public class Student implements java.io.Serializable {
	private int rollNo;
	private String name;
	private Date createdDate;

	public Student() {
		super();
	}

	public Student(int rollNo, String name, Date createdDate) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.createdDate = createdDate;
	}

	@Id
	@Column(name = "STUD_ROLL_NO", unique = true, nullable = false, precision = 5, scale = 0)
	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	// @Column(name = "STUD_NAME", nullable = false, length = 20)
	@Column(name = "STUD_NAME", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This annotation must be specified for persistent fields or properties of type
	 * java.util.Date and java.util.Calendar. It may only be specified for fields or
	 * properties of these types. The Temporal annotation may be used in conjunction
	 * with the Basic annotation, the Id annotation, or the ElementCollection
	 * annotation (when the element collection value is of such a temporal type).
	 * 
	 * This annotation is available since the release of JPA 1.0. @Temporal solves
	 * the one of the major issue of converting the date and time values from Java
	 * object to compatible database type and retrieving back to the application.
	 * When Java class declares the fields java.sql.Date or java.sql.Time, then it
	 * is compatible with the database types and will not have any issues. But, when
	 * we are using the java.util.Date or java.util.Calendar, then we have to
	 * specify Temporal types to map the appropriate database types when persisting
	 * to the database.
	 * 
	 * Valid Values are:- TemporalType.DATE, TemporalType.TIME,
	 * TemporalType.TIMESTAMP
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "STUD_CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}

// Reference URL:-
// https://www.thoughts-on-java.org/hibernate-tips-exclude-unchanged-columns-generated-update-statements/