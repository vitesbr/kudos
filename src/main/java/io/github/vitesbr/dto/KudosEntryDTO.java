package io.github.vitesbr.dto;

import io.github.vitesbr.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class KudosEntryDTO {
	private Integer id;
	private User userGiven;
	private User userReceived;
	private LocalDate dateTime;
	private List<KudosEntryDetailDTO> details;
	private BigDecimal rewards;
	private String kudos;

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

	public List<KudosEntryDetailDTO> getDetails() {
		return this.details;
	}

	public void setDetails(List<KudosEntryDetailDTO> details) {
		this.details = details;
	}

	public BigDecimal getRewards() {
		return this.rewards;
	}

	public void setRewards(BigDecimal rewards) {
		this.rewards = rewards;
	}

	public void addRewards(BigDecimal rewards) {
		if (this.rewards == null) {
			this.rewards = new BigDecimal("0.00");
		}
		this.rewards = this.rewards.add(rewards);
	}

	public String getKudos() {
		return this.kudos;
	}

	public void setKudos(String kudos) {
		this.kudos = kudos;
	}

	public void addKudos(String kudos) {
		if (this.kudos == null) {
			this.kudos = "";
		} else {
			this.kudos += ", ";
		}

		this.kudos += kudos;
	}

	@Override
	public String toString() {
		StringBuilder dumpDetails = new StringBuilder("");
		this.details.forEach(d -> dumpDetails.append(d.toString() + ", "));

		return "id [" + this.id.toString()
				+ "] userGiven [" + this.userGiven.toString()
				+ "] userReceived [" + this.userReceived.toString()
				+ "] dateTime [" + this.dateTime.toString()
				+ "] details [" + dumpDetails.toString()
				+ "] rewards [" + this.rewards.toString()
				+ "] kudos [" + this.kudos
				+ "]";
	}

}
