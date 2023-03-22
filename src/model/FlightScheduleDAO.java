package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import vo.FlightScheduleVO;
import vo.LineVO;

public class FlightScheduleDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst; // ?지원
	CallableStatement cst; // SP 지원
	ResultSet rs;
	int resultCount;
	
	
	public List<FlightScheduleVO> getFlightSchedules(LineVO line, Date departureDate) {
		String sql = """
				select departure_time, flight_name, schedule_no
				from flight_schedule
				where line_no = ?
				""";
		List<FlightScheduleVO> flightSchedules = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, line.getLineNo());
			rs = pst.executeQuery();
			while (rs.next()) {
				FlightScheduleVO flightSchedule = makeFlightSchedule(rs);
				
				flightSchedule.setLine(line);
				flightSchedules.add(flightSchedule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return flightSchedules;
	}
	
	private FlightScheduleVO makeFlightSchedule(ResultSet rs) throws SQLException {
		FlightScheduleVO flightSchedule = new FlightScheduleVO();
		flightSchedule.setFlightName(rs.getString("flight_name"));
		flightSchedule.setScheduleNo(rs.getInt("schedule_no"));
		String dateStr = rs.getString("departure_time");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		flightSchedule.setDeparture_time(LocalDateTime.parse(dateStr, dtf));
		return flightSchedule;
	}

	public FlightScheduleVO getFlightScheduleByNo(int scheduleNo) {
		FlightScheduleVO schedule = new FlightScheduleVO();
		String sql = """
				select departure_time, flight_name, line_no
				from flight_schedule
				where schedule_no = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, scheduleNo);
			rs = pst.executeQuery();
			rs.next();
			
			LineVO line = new LineVO();
			line.setLineNo(rs.getInt("line_no"));
			
			String dateStr = rs.getString("departure_time");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			schedule.setDeparture_time(LocalDateTime.parse(dateStr, dtf));
			schedule.setScheduleNo(scheduleNo);
			schedule.setFlightName(rs.getString("flight_name"));
			schedule.setLine(line);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return schedule;
	}
}
