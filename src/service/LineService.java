package service;

import java.util.List;

import controller.Controllers;
import model.LineDAO;
import vo.AirportVO;
import vo.LineVO;

public class LineService {
	
//	private AirportController airportController = Controllers.AIRPORT_CONTROLLER;
	
	private LineDAO lineDAO = new LineDAO();

	public List<String> selectDestinationAirportCodeByDepartureAirportCode(String departureAirportCode) {
		
		return lineDAO.selectDestinationAirportCodeByDepartureAirportCode(departureAirportCode);
	}
	
	public LineVO selectLineByDepartureAirportAndDestinationAirport(AirportVO departureAirport,
			AirportVO destinationAirport) {
		return lineDAO.selectLineByDepartureAirportAndDestinationAirport(departureAirport, destinationAirport);
	}

	public LineVO selectLineByNo(int lineNo) {
		LineVO line = lineDAO.selectLineByNo(lineNo);
		line.setDepartureAirport(Controllers.AIRPORT_CONTROLLER.selectAirportByCode(line.getDepartureAirport().getAirportCode()));
		line.setDestinationAirport(Controllers.AIRPORT_CONTROLLER.selectAirportByCode(line.getDestinationAirport().getAirportCode()));
		return line;
	}
}
