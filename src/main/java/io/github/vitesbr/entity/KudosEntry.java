package io.github.vitesbr.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="kudos_entry")
public class KudosEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_user_given")
	private User userGiven;

	@ManyToOne
	@JoinColumn(name="id_user_received")
	private User userReceived;

	@Column(name = "date_time")
    private LocalDate dateTime;

	public KudosEntry() {
	}

	public KudosEntry(
			User userGiven,
			User userReceived,
			LocalDate dateTime) {

		this.userGiven = userGiven;
		this.userReceived = userReceived;
		this.dateTime = dateTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserGiven() {
		return this.userGiven;
	}

	public void setUserGiven(User userGiven) {
		this.userGiven = userGiven;
	}

	public User getUserReceived() {
		return this.userReceived;
	}

	public void setUserReceived(User userReceived) {
		this.userReceived = userReceived;
	}

	public LocalDate getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "id [" + this.id.toString()
				+ "] userGiven [" + this.userGiven.toString()
				+ "] userReceived [" + this.userReceived.toString()
				+ "] dateTime [" + this.dateTime.toString()
				+ "]";
	}
}
