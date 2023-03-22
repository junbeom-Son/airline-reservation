package view;

import java.util.List;

import vo.AirportVO;

public class AirportView {
	
	public static void printAirportCountries(List<String> countries) {
		System.out.println("------------------------------");
		System.out.println("번호\t국가명");
		for (int i = 0; i < countries.size(); ++i ) {
			System.out.println(i + "\t" + countries.get(i));
		}
	}
	
	public static void printAirports(List<AirportVO> airports) {
		System.out.println("------------------------------");
		System.out.println("번호\t국가명\t공항명");
		for (int i = 0; i < airports.size(); ++i ) {
			System.out.println(i + "\t" + airports.get(i).getCountryName() + "\t" + airports.get(i).getAirportName());
		}
	}
}
