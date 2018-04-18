import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;

public class Session implements Serializable {
	static private int idCount = 0;
	private int id;
	private LocalDateTime date;
	private String film;
	private int hall;
		

	public Session() {
		this.id = idCount++;
	}
	
	public Session(Date date, String film, int hall) {
		this.id = idCount++;
		this.date = LocalDateTime.of(2018, Month.APRIL, date.getDay(), date.getHour(), date.getMinute());
		this.film = film;
		this.hall = hall;
	}

	public int getId() {
		return id;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDay(LocalDateTime date) {
		this.date = date;
	}
	public String getFilm() {
		return film;
	}
	public void setFilm(String film) {
		this.film = film;
	}
	public int getHall() {
		return hall;
	}
	public void setHall(int hall) {
		this.hall = hall;
	}
	
	@Override
	public String toString() {
		return "Session: [id=" + id + ", film=" + film + ", hall=" + hall + ", date=" + date + "]";
	}
	
	
}
