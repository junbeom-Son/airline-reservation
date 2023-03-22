package controller;

import java.util.List;

import service.Services;
import vo.AirportVO;
import vo.LineVO;

public class LineController {
	
//	private LineService lineService = Services.LINE_SERVICE;

	public List<String> selectDestinationAirportCodeByDepartureAirportCode(String departureAirportCode) {
		
		return Services.LINE_SERVICE.selectDestinationAirportCodeByDepartureAirportCode(departureAirportCode);
	}
	
	public LineVO selectLineByDepartureAirportAndDestinationAirport(AirportVO departureAirport,
			AirportVO destinationAirport) {
		return Services.LINE_SERVICE.selectLineByDepartureAirportAndDestinationAirport(departureAirport, destinationAirport);
	}

	public LineVO selectLineByNo(int lineNo) {
		return Services.LINE_SERVICE.selectLineByNo(lineNo);
	}
}
