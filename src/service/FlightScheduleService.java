package service;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import controller.Controllers;
import model.FlightScheduleDAO;
import view.FlightScheduleView;
import vo.AirportVO;
import vo.FlightScheduleVO;
import vo.LineVO;
import vo.MemberVO;

public class FlightScheduleService {
	
	private FlightScheduleDAO flightScheduleDAO = new FlightScheduleDAO();

	

	public FlightScheduleVO getFlightScheduleByNo(int scheduleNo) {
		
		return flightScheduleDAO.getFlightScheduleByNo(scheduleNo);
	}
	
	public void searchFlights(MemberVO member, Scanner sc) {
		System.out.println("-----출발지 국가-----");
		List<AirportVO> airports = Controllers.AIRPORT_CONTROLLER.selectAllAirports();
		List<String> countries = Controllers.AIRPORT_CONTROLLER.getCountries(airports);
		
		AirportVO departureAirport = Controllers.AIRPORT_CONTROLLER.selectAirport(airports, countries, "출발지", sc);
		System.out.println("출발 공항: " + departureAirport.getAirportName());
		
		airports = Controllers.AIRPORT_CONTROLLER.getPossibleDestinations(departureAirport.getAirportCode());
		countries = Controllers.AIRPORT_CONTROLLER.getCountries(airports);
		AirportVO destinationAirport = Controllers.AIRPORT_CONTROLLER.selectAirport(airports, countries, "도착지", sc);
		System.out.println("도착 공항: " + destinationAirport.getAirportName());
		Date departureDate = getDateTime(sc);
		
		List<FlightScheduleVO> selectedFlightSchedules = getFlightSchedules(departureAirport, destinationAirport, departureDate);
		if (selectedFlightSchedules.size() == 0) {
			System.out.println("선택된 날에 비행 스케쥴이 없습니다. 메인으로 돌아갑니다.");
			return;
		}
		
		FlightScheduleView.printFlightScheduleOnSelectedDate(selectedFlightSchedules);
		int reserveNum = selectItinerary(sc, selectedFlightSchedules.size());
		
		if (reserveNum == -1) {
			return;
		}
		
		if (member == null) {
			member = Controllers.MEMBER_CONTROLLER.login(sc);
		}
		
		Controllers.BOADING_CONTROLLER.makeReservation(member, selectedFlightSchedules.get(reserveNum), sc);
	}
	
	private List<FlightScheduleVO> getFlightSchedules(AirportVO departureAirport,
			AirportVO destinationVO, Date departureDate) {
		LineVO line = Controllers.LINE_CONTROLLER.selectLineByDepartureAirportAndDestinationAirport(departureAirport, destinationVO);
		
		return flightScheduleDAO.getFlightSchedules(line, departureDate);
	}
	
	private static Date getDateTime(Scanner sc) {
		System.out.print("여행 날짜 입력(YYYY-MM-DD): ");
		String dateStr = sc.next();
		return Date.valueOf(dateStr);
	}
	

	/**
	 * 출력된 여정의 번호를 선택(0 ~ length - 1)
	 * @param sc
	 * @param length
	 * @return itineryNum(예약 번호) or -1(예약 안함)
	 */
	private static int selectItinerary(Scanner sc, int length) {
		while (true) {
			System.out.println("1) 예약하기");
			System.out.println("2) 메인으로 돌아가기");
			System.out.print("명령 입력: ");
			String command = sc.next();
			switch (command) {
			case "1":
				System.out.print("여정 번호를 선택하세요: ");
				while (true) {
					int itineryNum = sc.nextInt();
					if (itineryNum >= 0 && itineryNum < length) {
						return itineryNum;
					}
				}
			case "2":
				return -1;
			default:
				System.out.println("올바른 옵션을 선택하세요");
			}
		}
	}
}
