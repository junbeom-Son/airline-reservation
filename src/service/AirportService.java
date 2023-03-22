package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.AirportDAO;
import view.AirportView;
import vo.AirportVO;

public class AirportService {
	
	private AirportDAO airportDAO = new AirportDAO();
	
	public List<AirportVO> selectAllAirports() {
		return airportDAO.selectAllAirports();
	}
	
	public List<String> getCountries(List<AirportVO> airports) {
		List<String> countries = new ArrayList<>();
		for (AirportVO airport : airports) {
			if (!countries.contains(airport.getCountryName())) {
				countries.add(airport.getCountryName());
			}
		}
		return countries;
	}
	
	public List<AirportVO> getAirportsByCountry(List<AirportVO> airports, String countryName) {
		List<AirportVO> filteredAirports = new ArrayList<>();
		for (AirportVO airport : airports) {
			if (airport.getCountryName().equals(countryName)) {
				filteredAirports.add(airport);
			}
		}
		return filteredAirports;
	}
	
	public List<AirportVO> getPossibleDestinations(List<String> possibleDestinationAirportCodes) {
		return airportDAO.getPossibleDestinations(possibleDestinationAirportCodes);
	}
	
	public AirportVO selectAirport(List<AirportVO> airports, List<String> countries, String source, Scanner sc) {
		AirportView.printAirportCountries(countries);
		System.out.print(source + " 국가 번호 입력: ");
		String countryName = countries.get(getOptionNumber(sc, countries.size()));
		List<AirportVO> airportsByCountry = getAirportsByCountry(airports, countryName);
		AirportView.printAirports(airportsByCountry);
		System.out.print(source + " 공항 번호 입력: ");
		AirportVO airport = airportsByCountry.get(getOptionNumber(sc, airportsByCountry.size()));
		return airport;
	}
	
	private int getOptionNumber(Scanner sc, int length) {
		int countryNo = -1;
		while (true) {
			countryNo = sc.nextInt();
			if (countryNo >= 0 && countryNo < length) {
				break;				
			}
			System.out.println("유효한 번호를 입력하세요(0 ~ " + (length - 1) + ")");
		}
		return countryNo;
	}

	public AirportVO selectAirportByCode(String airportCode) {
		
		return airportDAO.selectAirportByCode(airportCode);
	}
}