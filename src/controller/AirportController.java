package controller;

import java.util.List;
import java.util.Scanner;

import service.Services;
import vo.AirportVO;

public class AirportController {

	
	public List<AirportVO> selectAllAirports() {
		return Services.AIRPORT_SERVICE.selectAllAirports();
	}
	
	public List<String> getCountries(List<AirportVO> airports) {
		return Services.AIRPORT_SERVICE.getCountries(airports);
	}
	
	public List<AirportVO> getAirportsByCountry(List<AirportVO> airports, String countryName) {
		return Services.AIRPORT_SERVICE.getAirportsByCountry(airports, countryName);
	}

	public List<AirportVO> getPossibleDestinations(String departureAirportCode) {
		List<String> possibleDestinationAirportCodes = Controllers.LINE_CONTROLLER.selectDestinationAirportCodeByDepartureAirportCode(departureAirportCode);
		return Services.AIRPORT_SERVICE.getPossibleDestinations(possibleDestinationAirportCodes);
	}
	
	public AirportVO selectAirport(List<AirportVO> airports, List<String> countries, String source, Scanner sc) {
		return Services.AIRPORT_SERVICE.selectAirport(airports, countries, source, sc);
	}

	public AirportVO selectAirportByCode(String airportCode) {
		
		return Services.AIRPORT_SERVICE.selectAirportByCode(airportCode);
	}
}
