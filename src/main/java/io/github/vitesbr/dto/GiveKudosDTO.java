package io.github.vitesbr.dto;

public class GiveKudosDTO {

	private Integer idUserGiven;
	private Integer idUserReceived;
	private Integer points;

	public Integer getIdUserGiven() {
		return this.idUserGiven;
	}

	public void setIdUserGiven(Integer idUserGiven) {
		this.idUserGiven = idUserGiven;
	}

	public Integer getIdUserReceived() {
		return this.idUserReceived;
	}

	public void setIdUserReceived(Integer idUserReceived) {
		this.idUserReceived = idUserReceived;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
