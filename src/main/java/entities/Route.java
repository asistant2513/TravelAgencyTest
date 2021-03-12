package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import enums.Country;

@Entity
@Table(name="route")
public final class Route {

	@Id
	@Basic(optional = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name= "pfrom")
	@Enumerated(EnumType.STRING)
	private Country pointBegin;

	@Column(name= "pto")
	@Enumerated(EnumType.STRING)
	private Country pointEnd;

	@Column(name="cost")
	private int cost;

	@OneToMany(mappedBy = "route",  fetch = FetchType.EAGER)
	private Set<Trip> trips = new HashSet<>();

	public Route(){ }

	//-------------------------------\\Getters//-------------------------------\\

	public int getId() {
		return id;
	}

	public int getCost() {
		return this.cost;
	}

	public Country getPointBegin() {
		return pointBegin;
	}

	public Country getPointEnd() {
		return pointEnd;
	}

	public Set<Trip> getTrips() {
		return trips;
	}

	//-------------------------------------------------------------------------\\

	//-------------------------------\\Setters//-------------------------------\\

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPointBegin(Country pointBegin) {
		this.pointBegin = pointBegin;
	}

	public void setPointEnd(Country pointEnd) {
		this.pointEnd = pointEnd;
	}

	//-------------------------------------------------------------------------\\
}
