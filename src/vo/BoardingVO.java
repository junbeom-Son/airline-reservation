package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardingVO {
	
	private FlightScheduleVO schedule;
	private MemberVO member;
	private char flightClass;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("출발일: ").append(schedule.getDeparture_time().toLocalDate())
		.append("\n출발시각: ").append(schedule.getDeparture_time().toLocalTime())
		.append("\n편명: ").append(schedule.getFlightName())
		.append("\n출발지: ").append(schedule.getLine().getDepartureAirport().getAirportName())
		.append("\n도착지: ").append(schedule.getLine().getDestinationAirport().getAirportName())
		.append("예상 소요시간: ").append(schedule.getLine().getEstimatedTime())
		.append("\n이름: ").append(member.getMemberName())
		.append("\n전화번호: ").append(member.getPhone())
		.append("\n좌석등급: ");
		if (flightClass == 'F') {
			builder.append("First class");
		} else if (flightClass == 'B') {
			builder.append("Business class");
		} else if (flightClass == 'E') {
			builder.append("Economy class");
		}
		return builder.toString();
	}
	
}
