package com.javatechie.rabbitmq.demo.model;

public class Preference {

	private int id;
	private String preferenceKey;
	private String preferenceValue;
	private String preferenceType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreferenceKey() {
		return preferenceKey;
	}

	public void setPreferenceKey(String preferenceKey) {
		this.preferenceKey = preferenceKey;
	}

	public String getPreferenceValue() {
		return preferenceValue;
	}

	public void setPreferenceValue(String preferenceValue) {
		this.preferenceValue = preferenceValue;
	}

	public String getPreferenceType() {
		return preferenceType;
	}

	public void setPreferenceType(String preferenceType) {
		this.preferenceType = preferenceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preference other = (Preference) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Preference [id=" + id + ", preferenceKey=" + preferenceKey + ", preferenceValue=" + preferenceValue
				+ ", preferenceType=" + preferenceType + "]";
	}

}
