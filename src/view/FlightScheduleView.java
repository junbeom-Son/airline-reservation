package view;

import java.util.List;

import vo.FlightScheduleVO;
import vo.LineVO;

public class FlightScheduleView {
	
	public static void printFlightScheduleOnSelectedDate(List<FlightScheduleVO> flightSchedules) {
		System.out.println("\n------------------------------");
		System.out.println("-----선택된 출발지, 목적지, 날짜의 비행 스케쥴표 입니다.---");
		System.out.println("번호\t출발시간\t\t\t출발지\t\t목적지\t\t예상 소요시간");
		for (int i = 0; i < flightSchedules.size(); ++i ) {
			System.out.print(i + ")\t");
			FlightScheduleVO flightSchedule = flightSchedules.get(i);
			LineVO line = flightSchedule.getLine();
			System.out.println(flightSchedule.getDeparture_time() + "\t" +
					line.getDepartureAirport().getAirportName() + "\t" +
					line.getDestinationAirport().getAirportName() + "\t" +
					line.getEstimatedTime());
		}
	}
}
