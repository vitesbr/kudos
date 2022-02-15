package io.github.vitesbr.dto;

import io.github.vitesbr.entity.KudosConversion;

import java.math.BigDecimal;

public class KudosEntryDetailDTO {
	private Integer id;
	private KudosConversion kudosConversion;
	private Integer points;
	private BigDecimal reward;

	public KudosEntryDetailDTO() {
	}

	public KudosEntryDetailDTO(Integer id, KudosConversion kudosConversion, Integer points, BigDecimal reward) {
		this.id = id;
		this.kudosConversion = kudosConversion;
		this.points = points;
		this.reward = reward;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
				+ "] kudosConversion [" + this.kudosConversion.toString()
				+ "] points [" + this.points.toString()
				+ "] reward [" + this.reward.toString()
				+ "]";
	}
}
