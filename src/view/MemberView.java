package view;

import vo.MemberVO;

public class MemberView {
	public static void printIsSuccessRegistration(int result, MemberVO member) {
		if (result == 0) {
			System.out.println("회원가입 실패");
		}
		else {
			System.out.println(member);
		}
	}
	
	public static void login(MemberVO member) {
		System.out.println("\n------------------------------");
		System.out.println(member.getMemberName() + "님 로그인 하셨습니다.");
	}
	
	public static void printInfo(MemberVO member) {
		System.out.println("\n------------------------------");
		System.out.println(member);
		
	}
}
