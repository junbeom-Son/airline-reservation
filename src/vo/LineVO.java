package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LineVO {
	
	private int lineNo;
	private AirportVO departureAirport;
	private AirportVO destinationAirport;
	private int economyPrice;
	private String estimatedTime;
}
