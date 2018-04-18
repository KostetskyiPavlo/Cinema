import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		try {
			File file = new File("backup");
			if (!file.exists())
				file.createNewFile();

			Scanner scan = new Scanner(System.in);
			Cinema planetaKino = new Cinema();
			while (true) {
				System.out.print("Enter 1 - for administrator, 2 - for customer,\n"
						+ "3 - write ot file, 4 - read from file, q - quit: ");
				if (scan.hasNextInt()) {
					switch (scan.nextInt()) {
					case 1:
						while (true) {
							System.out.print("Administrator (choose action):\n"
									+ "1 - add film, 2 - delete film, 3 - film list,\n"
									+ "4 - add hall, 5 - delete hall, 6 - view hall,\n"
									+ "7 - add session, 8 - delete session, 9 - view session,\n" + "q - quit: ");
							if (scan.hasNextInt()) {
								switch (scan.nextInt()) {
								case 1:
									planetaKino.addFilm();
									break;
								case 2:
									planetaKino.deleteFilm();
									break;
								case 3:
									planetaKino.viewFilm();
									break;
								case 4:
									planetaKino.addHall();
									break;
								case 5:
									planetaKino.deleteHall();
									break;
								case 6:
									planetaKino.viewHall();
									break;
								case 7:
									planetaKino.addSession();
									break;
								case 8:
									planetaKino.deleteSession();
									break;
								case 9:
									planetaKino.viewSessions();
									break;
								default:
									System.out.println("Wrong input");
									break;
								}
							} else {
								String answer = scan.next();
								if (answer.equalsIgnoreCase("q"))
									break;
								System.out.println("Wrong enter");
							}
						}
						break;
					case 2:
						while (true) {
							System.out.print("Customer (choose action):\n"
									+ "1 - session by day, 2 - session by film, 3 - film list, " + "q - quit: ");
							if (scan.hasNextInt()) {
								switch (scan.nextInt()) {
								case 1:
									planetaKino.sessionByDay();
									break;
								case 2:
									planetaKino.sessionByFilm();
									break;
								case 3:
									planetaKino.viewFilm();
									break;
								default:
									System.out.println("Wrong input");
									break;
								}
							} else {
								String answer = scan.next();
								if (answer.equalsIgnoreCase("q"))
									break;
								System.out.println("Wrong enter");
							}
						}
						break;
					case 3:
						try {
							FileOutputStream fos = new FileOutputStream("backup");
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.writeObject(planetaKino);
							oos.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 4:
						try {
							FileInputStream fis = new FileInputStream("backup");
							ObjectInputStream ois = new ObjectInputStream(fis);
							Object o = ois.readObject();
							System.out.println(o);
							ois.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					default:
						System.out.println("Wrong enter");
						break;
					}
				} else {
					String answer = scan.next();
					if (answer.equalsIgnoreCase("q"))
						break;
					System.out.println("Wrong enter");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
