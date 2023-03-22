package view;

import vo.MemberVO;

public class ApplicationView {

	public static void printInit() {
		System.out.println("\n-----Welcome to XX Airline-----");
		System.out.println("1) 로그인");
		System.out.println("2) 회원가입");
		System.out.println("3) 항공기 조회");
		System.out.println("0) 종료");
		System.out.print("숫자 입럭: ");
	}
	
	public static void printInit(MemberVO member) {
		System.out.println("\n-----" + member.getMemberName() + "님 환영합니다-----");
		System.out.println("1) 항공기 조회");
		System.out.println("2) 예약 조회");
		System.out.println("3) 회원 정보 조회");
		System.out.println("4) 로그아웃");
		System.out.println("0) 프로그램 종료");
		System.out.print("옵션 입력: ");
	}
}
