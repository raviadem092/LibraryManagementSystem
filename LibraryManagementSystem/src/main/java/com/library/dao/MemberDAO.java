package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.models.Member;
import com.library.utils.DatabaseManager;

public class MemberDAO {

    // Add new member
	 public void addMember(Member member) {
	        String sql = "INSERT INTO members(member_id, name, contact_no) VALUES (member_id_seq.nextval, ?, ?)" ;
	        try (Connection con = DatabaseManager.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)){
	            pstmt.setString(1, member.getName());
	            pstmt.setString(2, member.getPhoneNumber());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.getMessage();
	        }
	    }
    
    // Remove a member 
    public void removeMember(int memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try(Connection con = DatabaseManager.getConnection();
        	PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, memberId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    // Find a member by his/her ID
    public Member findMemberById(int memberId) {
    	String sql = "SELECT * FROM members WHERE member_id = ?";
        try(Connection con = DatabaseManager.getConnection();
        	PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, memberId);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Member m = new Member();
				m.setMemberId(rs.getInt("member_id"));
				m.setName(rs.getString("name"));
				m.setPhoneNumber(rs.getString("contact_no"));
				return m;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    // Get all members from the database
    public List<Member> getAllMembers() {
        List<Member> list1 = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setPhoneNumber(rs.getString("contact_no"));
                list1.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list1;
    }
    
}

