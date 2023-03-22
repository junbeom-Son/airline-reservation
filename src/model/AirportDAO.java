package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import vo.AirportVO;

public class AirportDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst; // ?지원
	CallableStatement cst; // SP 지원
	ResultSet rs;
	int resultCount;
	
	public List<AirportVO> selectAllAirports() {
		String sql = """
				select * from airport;
				""";
		conn = DBUtil.getConnection();
		List<AirportVO> airports = new ArrayList<>();
		try {
			st =  conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				airports.add(makeAirport(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, st, conn);
		}
		return airports;
	}

	public List<AirportVO> getPossibleDestinations(
			List<String> possibleDestinationAirportCodes) {
		String sql = """
				select *
				from airport
				where airport_code = ?
				""";
		conn = DBUtil.getConnection();
		List<AirportVO> airports = new ArrayList<>();
		try {
			pst = conn.prepareStatement(sql);
			for (String destinationAirportCode : possibleDestinationAirportCodes) {
				pst.setString(1, destinationAirportCode);
				rs = pst.executeQuery();
				if (rs.next()) {
					airports.add(makeAirport(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, st, conn);
		}
		return airports;
	}
	
	private AirportVO makeAirport(ResultSet rs) throws SQLException {
		AirportVO airport = new AirportVO();
		airport.setAirportCode(rs.getString("airport_code"));
		airport.setAirportName(rs.getString("airport_name"));
		airport.setCountryName(rs.getString("country_name"));		
		return airport;
	}

	public AirportVO selectAirportByCode(String airportCode) {
		AirportVO airport = new AirportVO();
		String sql = """
				select *
				from airport
				where airport_code = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, airportCode);
			rs = pst.executeQuery();
			if (rs.next()) {
				airport.setAirportCode(airportCode);
				airport.setAirportName(rs.getString("airport_name"));
				airport.setCountryName(rs.getString("country_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return airport;
	}
}
