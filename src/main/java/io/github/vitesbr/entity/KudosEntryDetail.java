package io.github.vitesbr.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="kudos_entry_detail")
public class KudosEntryDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_kudos_entry")
	private KudosEntry kudosEntry;

	@ManyToOne
	@JoinColumn(name="id_kudos_conversion")
	private KudosConversion kudosConversion;

	@Column(name="points")
	private Integer points;

	@Column(name="reward")
	private BigDecimal reward;

	public KudosEntryDetail() {
	}

	public KudosEntryDetail(
			KudosEntry kudosEntry,
			KudosConversion kudosConversion,
			Integer points,
			BigDecimal rewards) {

		this.kudosEntry = kudosEntry;
		this.kudosConversion = kudosConversion;
		this.points = points;
		this.reward = rewards;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KudosEntry getKudosEntry() {
		return this.kudosEntry;
	}

	public void setKudosEntry(KudosEntry kudosEntry) {
		this.kudosEntry = kudosEntry;
	}

	public KudosConversion getKudosConversion() {
		return this.kudosConversion;
	}

	public void setKudosConversion(KudosConversion kudosConversion) {
		this.kudosConversion = kudosConversion;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public BigDecimal getReward() {
		return this.reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		return "id [" + this.id.toString()
				+ "] kudosEntry [" + this.kudosEntry.toString()
				+ "] kudosConversion [" + this.kudosConversion.toString()
				+ "] points [" + this.points.toString()
				+ "] reward [" + this.reward.toString()
				+ "]";
	}
}
