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
import vo.LineVO;

public class LineDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst; // ?지원
	CallableStatement cst; // SP 지원
	ResultSet rs;
	int resultCount;
	
	public List<String> selectDestinationAirportCodeByDepartureAirportCode(String departureAirportCode) {
		List<String> destinationAirportCodes = new ArrayList<>();
		String sql = """
				select destination_airport_code
				from line
				where departure_airport_code = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, departureAirportCode);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				destinationAirportCodes.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return destinationAirportCodes;
	}
	
	public LineVO selectLineByDepartureAirportAndDestinationAirport(AirportVO departureAirport,
			AirportVO destinationAirport) {
		LineVO line = new LineVO();
		String sql = """
				select *
				from line 
				where departure_airport_code = ? and destination_airport_code = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, departureAirport.getAirportCode());
			pst.setString(2, destinationAirport.getAirportCode());
			rs = pst.executeQuery();
			
			if (rs.next()) {
				line = makeLine(rs);
				line.setDepartureAirport(departureAirport);
				line.setDestinationAirport(destinationAirport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return line;
	}
	
	private LineVO makeLine(ResultSet rs) throws SQLException {
		LineVO line = new LineVO();
		line.setLineNo(rs.getInt("line_no"));
		line.setEconomyPrice(rs.getInt("economy_price"));
		line.setEstimatedTime(rs.getString("estimated_time"));
		return line;
	}

	public LineVO selectLineByNo(int lineNo) {
		LineVO line = new LineVO();
		String sql = """
				select *
				from line
				where line_no = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, lineNo);
			rs = pst.executeQuery();
			rs.next();
			line.setLineNo(lineNo);
			AirportVO departureAirport = new AirportVO();
			AirportVO destinationAirport = new AirportVO();
			
			departureAirport.setAirportCode(rs.getString("departure_airport_code"));
			destinationAirport.setAirportCode(rs.getString("destination_airport_code"));
			line.setDepartureAirport(departureAirport);
			line.setDestinationAirport(destinationAirport);
			line.setEconomyPrice(rs.getInt("economy_price"));
			line.setEstimatedTime(rs.getString("estimated_time"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return line;
	}
}
