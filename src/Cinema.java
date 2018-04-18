import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Cinema implements Serializable {
	private static final long serialVersionUID = 5720628715892982579L;
	transient Scanner scan = new Scanner(System.in);
	private Set<String> films;
	private Set<Integer> halls;
	private List<Session> sessions;

	public Cinema() {
		films = new LinkedHashSet<>();
		halls = new LinkedHashSet<>();
		sessions = new ArrayList<>();
	}

	public Cinema(Set<String> films, Set<Integer> halls, List<Session> sessions) {
		this.films = films;
		this.halls = halls;
		this.sessions = sessions;
	}

	public Set<String> getFilms() {
		return films;
	}

	public void setFilms(Set<String> films) {
		this.films = films;
	}

	public Set<Integer> getHalls() {
		return halls;
	}

	public void setHalls(Set<Integer> halls) {
		this.halls = halls;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public void addFilm() {
		System.out.print("New film: ");
		String newFilm = scan.next();
		films.add(newFilm);
	}

	public boolean deleteFilm() {
		boolean found = false;
		System.out.println("Films: " + films);
		System.out.print("Film to delete: ");
		String delFilm = scan.next();

		Iterator<String> iterFilms = films.iterator();
		while (iterFilms.hasNext()) {
			if (delFilm.equals(iterFilms.next())) {
				System.out.println(delFilm + " - DELETED");
				iterFilms.remove();
				found = true;
			}
		}
		if (found) {
			Iterator<Session> iterSession = sessions.iterator();
			while (iterSession.hasNext()) {
				if (delFilm.equals(iterSession.next().getFilm()))
					iterSession.remove();
			}
			return true;
		}
		System.out.println("Wrong film name");
		return false;
	}

	public void viewFilm() {
		if (films.isEmpty())
			System.out.println("No films");
		else {
			System.out.println("Films:");
			films.forEach(System.out::println);
		}
	}

	public void addHall() {
		System.out.print("New hall(number): ");
		if (scan.hasNextInt())
			halls.add(scan.nextInt());
		else {
			String wrong = scan.next();
			System.out.println("Wrong input");
		}
	}

	public boolean deleteHall() {
		boolean found = false;
		System.out.println("Halls: " + halls);
		System.out.print("Hall to delete: ");
		int delHall;
		if (scan.hasNextInt())
			delHall = scan.nextInt();
		else {
			String wrong = scan.next();
			System.out.println("Wrong input");
			return false;
		}

		Iterator<Integer> iterHalls = halls.iterator();
		while (iterHalls.hasNext()) {
			if (delHall == iterHalls.next()) {
				System.out.println(delHall + " - DELETED");
				iterHalls.remove();
				found = true;
			}
		}
		if (found) {
			Iterator<Session> iterSession = sessions.iterator();
			while (iterSession.hasNext()) {
				if (delHall == iterSession.next().getHall())
					iterSession.remove();
			}
			return true;
		}
		System.out.println("Wrong holl");
		return false;
	}

	public void viewHall() {
		if (halls.isEmpty())
			System.out.println("No halls");
		else {
			System.out.println("Halls:");
			halls.forEach(System.out::println);
		}
	}

	public void addSession() {
		boolean filmOk = false;
		boolean hallOk = false;
		Iterator<String> iterFilms = films.iterator();
		Iterator<Integer> iterHalls = halls.iterator();

		System.out.print("Film: ");
		String selectedFilm = scan.next();
		while (iterFilms.hasNext()) {
			if (selectedFilm.equals(iterFilms.next())) {
				filmOk = true;
			}
		}
		if (!filmOk) {
			System.out.println("Wrong film");
			return;
		}

		System.out.print("Hall: ");
		int selectedHall = scan.nextInt();
		while (iterHalls.hasNext()) {
			if (selectedHall == iterHalls.next()) {
				hallOk = true;
			}
		}
		if (!hallOk) {
			System.out.println("Wrong hall");
			return;
		}
		try {
			System.out.print("Enter day(from 1 to 31): ");
			int selectedDay = scan.nextInt();
			System.out.print("Enter hour(from 0 to 23): ");
			int selectedHour = scan.nextInt();
			System.out.print("Enter minute(from 0 to 59): ");
			int selectedMinute = scan.nextInt();
			if (selectedDay < 1 || selectedDay > 31 || selectedHour < 0 || selectedHour > 23 || selectedMinute < 0
					|| selectedMinute > 59) {
				System.out.println("Wrong date input");
				return;
			}
			Date selectedDate = new Date(selectedDay, selectedHour, selectedMinute);

			Session newSession = new Session(selectedDate, selectedFilm, selectedHall);
			sessions.add(newSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteSession() {
		System.out.println(sessions);
		System.out.print("Session to delete (enter id): ");
		while (!scan.hasNextInt()) {
			String wrong = scan.next();
			System.out.println("Wrong input! Enter a number: ");
		}
		int sessionId = scan.nextInt();
		Iterator<Session> iterSession = sessions.iterator();
		while (iterSession.hasNext()) {
			if (iterSession.next().getId() == sessionId) {
				iterSession.remove();
				return true;
			}
		}
		System.out.println("Wrong ID");
		return false;
	}

	public void viewSessions() {
		if (sessions.isEmpty())
			System.out.println("No sessions");
		else {
			System.out.println("Sessions:");
			sessions.forEach(System.out::println);
		}
	}

	public void sessionByDay() {
		boolean found = false;
		System.out.println("Day(from 1 to 31): ");
		int selectedDay = 0;
		if (scan.hasNextInt()) {
			selectedDay = scan.nextInt();
			if (selectedDay < 1 || selectedDay > 31) {
				System.out.println("Wrong number");
				return;
			}
		} else {
			String wrong = scan.next();
			System.out.println("Wrong input");
			return;
		}
		Iterator<Session> iterSession = sessions.iterator();
		while (iterSession.hasNext()) {
			Session selectedSession = iterSession.next();
			if (selectedDay == selectedSession.getDate().getDayOfMonth()) {
				System.out.println("Film: " + selectedSession.getFilm() + ", hall: " + selectedSession.getHall()
						+ ", date: " + selectedSession.getDate());
				found = true;
			}
		}
		if (!found)
			System.out.println("No session");
	}

	public void sessionByFilm() {
		System.out.println("Films: " + films);
		boolean found = false;
		System.out.print("Choose film: ");
		String selectedFilm = scan.next();
		Iterator<String> iterFilms = films.iterator();
		while (iterFilms.hasNext()) {
			if (selectedFilm.equals(iterFilms.next())) {
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Wrong film");
			return;
		}
		found = false;

		Iterator<Session> iterSession = sessions.iterator();
		while (iterSession.hasNext()) {
			Session selectedSession = iterSession.next();
			if (selectedFilm.equals(selectedSession.getFilm())) {
				System.out.println("Film: " + selectedSession.getFilm() + ", hall: " + selectedSession.getHall()
						+ ", date: " + selectedSession.getDate());
				found = true;
			}
		}
		if (!found)
			System.out.println("There is no session");
	}
	
	public String toString() {
		return "Cinema [films=" + films + ", halls=" + halls + ", sessions=" + sessions + "]";
	}
}
