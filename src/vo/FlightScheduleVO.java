package vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightScheduleVO {
	
	private int scheduleNo;
	private LineVO line;
	private AirportVO departureAirport;
	private AirportVO destinationAirport;
	private String flightName;
	private LocalDateTime departure_time;
}
