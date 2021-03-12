package entities;

import enums.TravelType;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name="trip")
public final class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@FutureOrPresent
	@Column(name = "date_begin")
	private Calendar dateBegin;

	@Future
	@Column(name = "date_end")
	private Calendar dateEnd;

	@NotNull
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;

	@NotNull
	@ManyToOne
	@JoinColumn(name="route_id")
	private Route route;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "trip_type")
	private TravelType type;

	public Trip(){ }

	//-------------------------------\\Getters//-------------------------------\\

	public String getDateBegin() {
		return dateBegin.get(Calendar.YEAR) + "-" + dateBegin.get(Calendar.MONTH) + "-" + dateBegin.get(Calendar.DAY_OF_MONTH);
	}

	public String getDateEnd() {
		return dateEnd.get(Calendar.YEAR) + "-" + dateEnd.get(Calendar.MONTH) + "-" + dateEnd.get(Calendar.DAY_OF_MONTH);
	}

	public int getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public Route getRoute() {
		return route;
	}

	public TravelType getType() {
		return type;
	}

	//-------------------------------------------------------------------------\\

	//-------------------------------\\Setters//-------------------------------\\
	
	public void setDateBegin(Calendar date) {
		this.dateBegin = date;
	}
	
	public void setDateEnd(Calendar date) {
		this.dateEnd = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(TravelType type) {
		this.type = type;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	//-------------------------------------------------------------------------\\
}
