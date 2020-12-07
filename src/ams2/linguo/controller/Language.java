package ams2.linguo.controller;

public class Language {

	private String name;
	private String isoCode;

	public Language() {}

	public Language(String name, String isoCode) {
		this.name = name;
		this.isoCode = isoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

}
