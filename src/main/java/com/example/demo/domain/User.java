package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String ime;

	@Column(name = "prezime")
	private String prezime;

	@Column(name = "pbr")
	private int pbr;

	@Column(name = "grad")
	private String grad;

	@Column(name = "telefon")
	private String telefon;

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getPbr() {
		return pbr;
	}

	public void setPbr(int pbr) {
		this.pbr = pbr;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@JsonCreator
	public User(@JsonProperty("ime") String ime, @JsonProperty("prezime") String prezime, @JsonProperty("pbr") int pbr,
			@JsonProperty("grad") String grad, @JsonProperty("telefon") String telefon) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pbr = pbr;
		this.grad = grad;
		this.telefon = telefon;
	}
}
