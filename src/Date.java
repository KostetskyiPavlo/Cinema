import java.io.Serializable;

public class Date implements Serializable {
	private int day;
	private int hour;
	private int minute;
	

	public Date(int day, int hour, int minute) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

}
