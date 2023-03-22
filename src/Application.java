import java.util.Scanner;

import controller.Controllers;
import view.ApplicationView;
import view.BoardingView;
import view.MemberView;
import vo.MemberVO;

public class Application {
	
	private static MemberVO member = null;
	
	public static void main(String[] args) {
		init();
	}
	
	// 로그인 전 메인화면
	private static void init() {
		Scanner sc = new Scanner(System.in);
		int exitCode = 0;
		while (true) {
			if (exitCode == -1) {
				break;
			}
			if (member != null) {
				exitCode = initAfterLogin(sc);
				if (exitCode == -1) {
					break;
				}
			}
			ApplicationView.printInit();
			String command = sc.next();
			if (command.equals("0")) {
				break;
			}
			switch (command) {
			case "1":
				member = Controllers.MEMBER_CONTROLLER.login(sc); // 로그인을 하면 로그인한 member
				exitCode = initAfterLogin(sc);
				break;
			case "2":
				Controllers.MEMBER_CONTROLLER.registerMember(sc);
				break;
			case "3":
				Controllers.FLIGHT_SCHEDULE_CONTROLLER.searchFlights(member, sc);
				break;
			}
			
		}
		sc.close();
		System.out.println("프로그램을 종료합니다.");
	}
	
	/**
	 * 로그인 후 이용하는 메인 페이지
	 */
	private static int initAfterLogin(Scanner sc) {
		int exitCode = 0;
		while (true) {
			if (exitCode == -1 || exitCode == 4) {
				break;
			}
			ApplicationView.printInit(member);
			String command = sc.next();
			switch (command) {
			case "0":
				exitCode = -1;
				break;
			case "1":
				Controllers.FLIGHT_SCHEDULE_CONTROLLER.searchFlights(member, sc);
				break;
			case "2":
				BoardingView.printReservations(Controllers.BOADING_CONTROLLER.selectReservationByMember(member));
				break;
			case "3":
				MemberView.printInfo(member);
				break;
			case "4":
				member = null;
				System.out.println("로그아웃 되었습니다.");
				exitCode = 4;
			}
		}
		return exitCode;
	}
}
