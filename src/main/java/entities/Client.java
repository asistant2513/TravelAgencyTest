package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import enums.Country;

@Entity
@Table(name="clients")
public class Client{
	@Id
	@Basic(optional = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "age")
	private int age;

	@Enumerated(EnumType.STRING)
	@Column(name="homeland")
	private Country country;

	@OneToOne(mappedBy = "client")
	private User user;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<Trip> trips = new HashSet<>();

	public Client(){ }

	//-------------------------------\\Getters//-------------------------------\\

	public int getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}

	public Country getCountry() {
		return country;
	}

	public User getUser() {
		return user;
	}

	public Set<Trip> getTrips() {
		return trips;
	}
	//-------------------------------------------------------------------------\\

	//-------------------------------\\Setters//-------------------------------\\

	public void setId(int id) {
		Id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//-------------------------------------------------------------------------\\
}
