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
import vo.BoardingVO;
import vo.FlightScheduleVO;
import vo.MemberVO;

public class BoardingDAO {

	Connection conn;
	Statement st;
	PreparedStatement pst; // ?지원
	CallableStatement cst; // SP 지원
	ResultSet rs;
	int resultCount;
	
	public boolean hasReserved(BoardingVO boarding) {
		String sql = """
				select *
				from boarding
				where schedule_no = ? and member_id = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, boarding.getSchedule().getScheduleNo());
			pst.setInt(2, boarding.getMember().getMemberId());
			rs = pst.executeQuery();
			if (rs.next()) {
				String flightClass = rs.getString("class");
				boarding.setFlightClass(flightClass.charAt(0));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return false;
	}

	public RemainingSeatCount getRemainingSeatCount(BoardingVO boarding) { 
		RemainingSeatCount remainingSeatCount = new RemainingSeatCount();
		String sql = """
				select first_class - (select count(*) from boarding where schedule_no = ? and class = 'F') as first_class,
				    business_class - (select count(*) from boarding where schedule_no = ? and class = 'B') as business_class,
				    economy_class - (select count(*) from boarding where schedule_no = ? and class = 'E') as economy_class
				from flight
				where flight_name = ?
				""";
		conn = DBUtil.getConnection();
		try {
			FlightScheduleVO flightSchedule = boarding.getSchedule();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, flightSchedule.getScheduleNo());
			pst.setInt(2, flightSchedule.getScheduleNo());
			pst.setInt(3, flightSchedule.getScheduleNo());
			pst.setString(4, flightSchedule.getFlightName());
			rs = pst.executeQuery();
			rs.next();
			remainingSeatCount.setFirstClass(rs.getInt("first_class"));
			remainingSeatCount.setBusinessClass(rs.getInt("business_class"));
			remainingSeatCount.setEconomyClass(rs.getInt("economy_class"));			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return remainingSeatCount;
	}

	public int makeReservation(FlightScheduleVO schedule, MemberVO member, String flightClass) {
		String sql = """
				insert into boarding
				values (?, ?, ?)
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, schedule.getScheduleNo());
			pst.setInt(2, member.getMemberId());
			pst.setString(3, flightClass);
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return resultCount;
	}

	public List<BoardingVO> selectReservationByMember(MemberVO member) {
		List<BoardingVO> reservations = new ArrayList<>();
		String sql = """
				select schedule_no, class
				from boarding
				where member_id = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, member.getMemberId());
			rs = pst.executeQuery();
			while (rs.next()) {
				BoardingVO boarding = new BoardingVO();
				FlightScheduleVO schedule = new FlightScheduleVO();
				schedule.setScheduleNo(rs.getInt("schedule_no"));
				
				boarding.setSchedule(schedule);
				boarding.setMember(member);
				boarding.setFlightClass(rs.getString("class").charAt(0));
				reservations.add(boarding);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return reservations;
	}
}
