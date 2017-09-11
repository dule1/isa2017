package com.isa.pomocni;

public class PomocniPice {
	private Long id;
	private Long pic;

	public PomocniPice() {
		// TODO Auto-generated constructor stub
	}

	public PomocniPice(Long id, Long pic) {
		super();
		this.id = id;
		this.pic = pic;
	}

	public Long getPic() {
		return pic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPic(Long pic) {
		this.pic = pic;
	}
}
