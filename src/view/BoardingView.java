package view;

import java.util.List;

import model.RemainingSeatCount;
import vo.BoardingVO;

public class BoardingView {
	
	public static void printReservation(BoardingVO boarding) {
		System.out.println("\n------------------------------");
		System.out.println(boarding);
	}
	
	public static void printRemainingSeatCounts(int economyPrice, RemainingSeatCount remaining) {
		System.out.println("\n------------------------------");
		System.out.println("\tfirst class\tbusiness class\teconomoy class");
		System.out.println("잔여좌석수\t" + remaining.getFirstClass() + "\t\t" + remaining.getBusinessClass() + "\t\t" + remaining.getEconomyClass());
		System.out.println("금액\t" + economyPrice * 4 + "\t\t" + economyPrice * 2 + "\t\t" + economyPrice);
	}
	
	public static void printReservations(List<BoardingVO> boardings) {
		for (BoardingVO boarding : boardings) {
			printReservation(boarding);
		}
	}
}
