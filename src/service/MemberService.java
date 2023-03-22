package service;

import java.util.Scanner;

import model.MemberDAO;
import view.MemberView;
import vo.MemberVO;

public class MemberService {
	MemberDAO memberDAO = new MemberDAO();
	
	public void registerMember(Scanner sc) {
		System.out.println("\n-----회원가입-----");
		MemberVO member = new MemberVO();
		while(true) {
			System.out.print("이름 입력: ");
			member.setMemberName(sc.next());;
			System.out.print("전화번호 입력: ");
			member.setPhone(sc.next());
			MemberVO registeredMember = selectMemberByNameAndPhone(member.getMemberName(), member.getPhone());
			if (!member.equals(registeredMember)) {
				break;
			}
			System.out.println("입력하신 정보로 가입된 내역이 있습니다. 다시 입력해 주십시오");
		}
		int result = memberDAO.registerMember(member);
		MemberView.printIsSuccessRegistration(result, member);
	}
	
	private MemberVO selectMemberByNameAndPhone(String name, String phone) {
		return memberDAO.selectMemberByNameAndPhone(name, phone);
	}
	
	public MemberVO login(Scanner sc) {
		MemberVO member = null;		
		System.out.println("\n-----로그인-----");
		while (true) {
			System.out.print("이름 입력: ");
			String name = sc.next();
			System.out.print("전화번호 입력: ");
			String phone = sc.next();
			member = Services.MEMBER_SERVICE.selectMemberByNameAndPhone(name, phone);
			if (member != null) {
				break;
			}
			System.out.println("입력하신 정보로 가입된 내역이 없습니다. 다시 입력해 주십시오");
		}
		MemberView.login(member);
		return member;
	}
}
