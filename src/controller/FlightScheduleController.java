package controller;

import java.util.Scanner;

import service.Services;
import vo.FlightScheduleVO;
import vo.MemberVO;

public class FlightScheduleController {
	
	
//	private FlightScheduleService flightScheduleService = Services.FLIGHT_SCHEDULE_SERVICE;
//	private LineController lineController = Controllers.LINE_CONTROLLER;

//	public List<FlightScheduleVO> getFlightSchedules(AirportVO departureAirport,
//			AirportVO destinationVO, Date departureDate) {
//		
//		
//		
//		return Services.FLIGHT_SCHEDULE_SERVICE.getFlightSchedules(departureAirport, destinationVO, departureDate);
//	}

	public FlightScheduleVO getFlightScheduleByNo(int scheduleNo) {
		FlightScheduleVO schedule = Services.FLIGHT_SCHEDULE_SERVICE.getFlightScheduleByNo(scheduleNo);
		schedule.setLine(Controllers.LINE_CONTROLLER.selectLineByNo(schedule.getLine().getLineNo()));
		return schedule;
	}
	
	public void searchFlights(MemberVO member, Scanner sc) {
		Services.FLIGHT_SCHEDULE_SERVICE.searchFlights(member, sc);
	}
}
