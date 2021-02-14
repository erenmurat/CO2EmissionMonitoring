package com.emission.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SENSOR")
public class Sensor implements Serializable {

	private static final long serialVersionUID = -3925263982164352842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	// add one info
	@Column
	private String sensorname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "DIST_ID", nullable = false)
	private District district;

	@OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CO2Emission> co2EmissionList = new ArrayList<CO2Emission>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSensorname() {
		return sensorname;
	}

	public void setSensorname(String sensorname) {
		this.sensorname = sensorname;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<CO2Emission> getCo2EmissionList() {
		return co2EmissionList;
	}

	public void setCo2EmissionList(List<CO2Emission> co2EmissionList) {
		this.co2EmissionList = co2EmissionList;
	}
	
	
	@Override
	public String toString() {
		return "Sensor{" + "id=" + id + ", name='" + sensorname + '\'' +

				", district=" + district.getName()+ '}';
	}
}
