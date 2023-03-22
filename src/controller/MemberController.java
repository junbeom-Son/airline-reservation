package controller;

import java.util.Scanner;

import service.Services;
import view.MemberView;
import vo.MemberVO;

public class MemberController {
		
	public MemberVO login(Scanner sc) {
		return Services.MEMBER_SERVICE.login(sc);
	}
	
	public void registerMember(Scanner sc) {
		Services.MEMBER_SERVICE.registerMember(sc);
	}
}
