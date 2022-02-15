package io.github.vitesbr.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kudos_conversion")
public class KudosConversion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@Column(name="name", length=100)
	private String name;

	@Column(name="points")
	private Integer points;

	@Column(name="reward")
	private BigDecimal reward;

	public KudosConversion() {
	}

	public KudosConversion(String name, Integer points, BigDecimal reward) {
		this.name = name;
		this.points = points;
		this.reward = reward;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
				+ "] name [" + this.name
				+ "] points [" + this.points.toString()
				+ "] reward [" + this.reward.toString()
				+ "]";
	}
}
