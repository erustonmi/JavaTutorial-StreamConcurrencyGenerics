package timeywimeystuff;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TimeTinker {

	public static void main(String[] args) {
		ZonedDateTime takeOff = LocalDateTime.of(2017, Month.MARCH, 12, 1, 15, 0).atZone(ZoneId.systemDefault());
		System.out.println("takeoff at: " + takeOff);
		System.out.println("day of week is: ");
		ZonedDateTime landing = takeOff.plusHours(7).plusMinutes(23);
		System.out.println("Home time at landing: " + landing);
		
		landing = landing.withZoneSameInstant(ZoneId.of("UTC+3"));
		System.out.println("Local time at landing at: " + landing);
		
		System.out.println("flight duration is " + ChronoUnit.HOURS.between(takeOff, landing) + " hours");
		System.out.println("flight duration is " + ChronoUnit.MINUTES.between(takeOff, landing) + " minutes");
		System.out.println("flight duration is " + Duration.between(takeOff, landing));
		
		ZonedDateTime comeHome = landing.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
		System.out.println("go home next Thursday will be: " + comeHome);
		System.out.println("which is " + ChronoUnit.DAYS.between(landing, comeHome) + " days later");
		
	}
}
