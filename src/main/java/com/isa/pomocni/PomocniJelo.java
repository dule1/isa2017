package com.isa.pomocni;

public class PomocniJelo {
	private Long id;
	private Long jel;

	public PomocniJelo() {
		// TODO Auto-generated constructor stub
	}

	public PomocniJelo(Long id, Long jel) {
		super();
		this.id = id;
		this.jel = jel;
	}

	public Long getJel() {
		return jel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJel(Long jel) {
		this.jel = jel;
	}
}
