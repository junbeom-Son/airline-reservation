package controller;

import java.util.List;
import java.util.Scanner;

import service.Services;
import vo.BoardingVO;
import vo.FlightScheduleVO;
import vo.MemberVO;

public class BoardingController {
	
	public void makeReservation(MemberVO member, FlightScheduleVO schedule, Scanner sc) {
		Services.BOARDING_SERVICE.makeReservation(member, schedule, sc);
	}
	
	public List<BoardingVO> selectReservationByMember(MemberVO member) {
		return Services.BOARDING_SERVICE.selectReservationByMember(member);
	}
}
