package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.DBUtil;
import vo.MemberVO;

public class MemberDAO {

	Connection conn;
	Statement st;
	PreparedStatement pst; // ?지원
	CallableStatement cst; // SP 지원
	ResultSet rs;
	int resultCount;
	
	public int registerMember(MemberVO member) {
		String sql = """
				insert into members(member_name, phone, registration_date)
				values (?, ?, now());
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, member.getMemberName());
			pst.setString(2,  member.getPhone());
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(null, pst, conn);
		}
		return resultCount;
	}
	
	public MemberVO selectMemberByNameAndPhone(String name, String phone) {
		MemberVO member = null;
		String sql = """
				select *
				from members
				where member_name = ? and phone = ?
				""";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, phone);
			rs = pst.executeQuery();
			if (rs.next()) {
				member = makeMember(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.disConnect(rs, pst, conn);
		}
		return member;
	}
	
	private MemberVO makeMember(ResultSet rs) throws SQLException {
		MemberVO member = new MemberVO();
		member.setMemberId(rs.getInt("member_id"));
		member.setMemberName(rs.getString("member_name"));
		member.setPhone(rs.getString("phone"));
		member.setRegistrationDate(rs.getDate("registration_date"));
		return member;
	}
}