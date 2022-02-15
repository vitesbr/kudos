package io.github.vitesbr.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@Column(name="name", length=100)
	private String name;

	@Column(name="url_photo", length=100)
	private String urlPhoto;

	public User() {
	}

	public User(String name, String urlPhoto) {
		this.name = name;
		this.urlPhoto = urlPhoto;
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

	public String getUrlPhoto() {
		return this.urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	@Override
	public String toString() {
		return "id [" + this.id.toString()
				+ "] name [" + this.name
				+ "] urlPhoto [" + this.urlPhoto
				+ "]";
	}
}
