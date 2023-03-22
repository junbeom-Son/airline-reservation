package service;

import java.util.List;
import java.util.Scanner;

import controller.Controllers;
import model.BoardingDAO;
import model.RemainingSeatCount;
import view.BoardingView;
import vo.BoardingVO;
import vo.FlightScheduleVO;
import vo.MemberVO;

public class BoardingService {
		
	private BoardingDAO boardingDAO = new BoardingDAO();
	
	/**
	 * 선택된 일정에 따라 예약을 진행한다.
	 * 해당 일정정에 이미 되어있는 경우 예약을 할 수 없다.
	 */
	public void makeReservation(MemberVO member, FlightScheduleVO schedule, Scanner sc) {
		BoardingVO boarding = new BoardingVO();
		boarding.setSchedule(schedule);
		boarding.setMember(member);
		boolean reserved = hasReserved(boarding);
		if (reserved) {
			System.out.println("선택된 일정에 예약이 되어 있습니다. 예약 정보는 다음과 같습니다.");
			BoardingView.printReservation(boarding);
			return;
		}
		RemainingSeatCount remainingSeatCount = getRemainingSeatCount(boarding);
		BoardingView.printRemainingSeatCounts(boarding.getSchedule().getLine().getEconomyPrice(),
				remainingSeatCount);
		String flightClass = selectFlightClass(remainingSeatCount, sc);
		while (true) {
			int result = boardingDAO.makeReservation(schedule, member, flightClass);
			if (result == 1) {
				boarding.setFlightClass(flightClass.charAt(0));
				break;
			}			
		}
		BoardingVO reservedBoarding = new BoardingVO();
		reservedBoarding.setSchedule(boarding.getSchedule());
		reservedBoarding.setMember(boarding.getMember());
		reservedBoarding.setFlightClass(flightClass.charAt(0));
		BoardingView.printReservation(reservedBoarding);
		System.out.println("예약되었습니다.\n");
	}
	
	private boolean hasReserved(BoardingVO boarding) {
		return boardingDAO.hasReserved(boarding);
	}
	
	private RemainingSeatCount getRemainingSeatCount(BoardingVO boarding) {
		return boardingDAO.getRemainingSeatCount(boarding);
	}
	
	private static String selectFlightClass(RemainingSeatCount remainingSeatCount, Scanner sc) {
		while (true) {
			System.out.print("원하는 좌석의 등급을 선택하세요(F(first class), B(business class), E(economy class): ");
			String flightClass = sc.next();
			if (flightClass.equals("F") || flightClass.equals("B") || flightClass.equals("E")) {
				if (remainingSeatCount.isRemaining(flightClass.charAt(0))) {
					return flightClass;
				} else {
					System.out.println("선택하신 등급의 좌석은 매진 되었습니다.");
				}
			}	
			else {
				System.out.println("유효한 값을 입력하세요");
			}
		}
	}


	public List<BoardingVO> selectReservationByMember(MemberVO member) {
		List<BoardingVO> boardings = boardingDAO.selectReservationByMember(member);
		for (BoardingVO boarding : boardings) {
			boarding.setSchedule(Controllers.FLIGHT_SCHEDULE_CONTROLLER.getFlightScheduleByNo(boarding.getSchedule().getScheduleNo()));
		}
		
				
		return boardings;
	}
}
