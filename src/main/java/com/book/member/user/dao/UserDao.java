package com.book.member.user.dao;

import static com.book.common.sql.JDBCTemplate.close;
import static com.book.common.sql.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.book.member.user.vo.User;



public class UserDao {
	
	public int getEmailCount(String email) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS count FROM `users` WHERE `user_email` = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return count;
    }
	
	public int createUser(User u) {
        PreparedStatement pstmt = null;
        Connection conn = getConnection();
        int result = 0;
        try {
            // 이메일당 계정 수 확인
            int emailCount = getEmailCount(u.getUser_email());
            if (emailCount >= 3) {
                return -1; 
            }
            String sql = "INSERT INTO `users`(user_name, user_id, user_pw, user_email, user_nickname) VALUES(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, u.getUser_name());
            pstmt.setString(2, u.getUser_id());
            pstmt.setString(3, u.getUser_pw());
            pstmt.setString(4, u.getUser_email());
            pstmt.setString(5, u.getUser_nickname());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(pstmt);
        }
        return result;
    }
	
	public User checkid(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		User u= null;
		try {
			String sql = "SELECT * FROM `users` WHERE user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				u = new User(
						rs.getInt("user_no"),
						rs.getString("user_name"),
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_email"),
						rs.getString("user_nickname"),
						rs.getInt("user_active"),
						rs.getTimestamp("user_create").toLocalDateTime());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
			close(rs);
			close(pstmt);
		}
		return u;
	}
	
	public User checknickname(String nickname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		User u= null;
		try {
			String sql = "SELECT * FROM `users` WHERE user_nickname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				u = new User(
						rs.getInt("user_no"),
						rs.getString("user_name"),
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_email"),
						rs.getString("user_nickname"),
						rs.getInt("user_active"),
						rs.getTimestamp("user_create").toLocalDateTime());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return u;
	}
	
	public User loginUser(String id, String pw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =getConnection();
		User u=null;
		try {
			String sql = "SELECT * FROM `users` WHERE user_id=? AND user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new User(
						rs.getInt("user_no"),
						rs.getString("user_name"),
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_email"),
						rs.getString("user_nickname"),
						rs.getInt("user_active"),
						rs.getTimestamp("user_create").toLocalDateTime());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return u;
	}
	
	public User checkpw(String pw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =getConnection();
		User u=null;
		try {
			String sql = "SELECT * FROM `users` WHERE user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new User(
						rs.getInt("user_no"),
						rs.getString("user_name"),
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_email"),
						rs.getString("user_nickname"),
						rs.getInt("user_active"),
						rs.getTimestamp("user_create").toLocalDateTime());	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return u;
	}
	
	public User findpw(String id,String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn =getConnection();
		User u=null;
		try {
			String sql = "SELECT * FROM `users` WHERE `user_id`=? AND `user_email` = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new User(
						rs.getInt("user_no"),
						rs.getString("user_name"),
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_email"),
						rs.getString("user_nickname"),
						rs.getInt("user_active"),
						rs.getTimestamp("user_create").toLocalDateTime());
				System.out.println("dao");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return u;
	}
	
	public int editUser(int no,String pw, String email, String name, String nickname) {
		PreparedStatement pstmt = null;
		Connection conn =getConnection();
		System.out.println(email);
		int result = 0;
		try {
			String sql = "UPDATE `users` set user_pw=?,user_email=?, user_name=?,user_nickname=? WHERE user_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			pstmt.setString(2,email);
			pstmt.setString(3, name);
			pstmt.setString(4, nickname);
			pstmt.setInt(5, no);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	public int changepw(String id,String pw) {
		PreparedStatement pstmt = null;
		Connection conn =getConnection();
		int result = 0;
		try {
			String sql = "UPDATE `users` SET user_pw=? WHERE user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	

	public List<User> findid(String name, String email) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<User> resultList =new ArrayList<User>();
        try {
        String sql = "SELECT * FROM `users` WHERE `user_name` = ? AND `user_email` = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        rs = pstmt.executeQuery();
        while(rs.next()) {
			User u = new User (
					rs.getInt("user_no"),
					rs.getString("user_name"),
					rs.getString("user_id"),
					rs.getString("user_pw"),
					rs.getString("user_email"),
					rs.getString("user_nickname"),
					rs.getInt("user_active"),
					rs.getTimestamp("user_create").toLocalDateTime());
			resultList.add(u);
			}
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	close(conn);
        	close(rs);
        	close(pstmt);
        }
        return resultList;
    }
	 public String getRandomAdjective() {
		 Connection conn = getConnection();
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     String ad = null;
	     try {
	    	 String sql = "SELECT adjective FROM adjective ORDER BY RAND() LIMIT 1";
	    	 pstmt = conn.prepareStatement(sql);
	    	 rs = pstmt.executeQuery();
	    	 if (rs.next()) {
	                ad = rs.getString("adjective");
	            }
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }finally{
	    	close(conn);
	        close(rs);
	        close(pstmt);
	     }
	     return ad;
	 }
	public List<String> getRandomNouns(int count){
		Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> nouns = new ArrayList<>();
        try {
        	String sql = "SELECT noun FROM noun ORDER BY RAND() LIMIT ?";
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, count);
	    	rs = pstmt.executeQuery();
	    	while (rs.next()) {
                nouns.add(rs.getString("noun"));
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	close(rs);
        	close(pstmt);
        	close(conn);
        }
        return nouns;
	}
	
	public int deleteUser(String pw,int no) {
		PreparedStatement pstmt = null;
		Connection conn =getConnection();
		int result = 0;
		try {
			String sql = "UPDATE `users` set user_active=0 WHERE user_no=? AND user_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, pw);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	public List<Map<String, Object>> getAllUsers() {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> users = new ArrayList<>();
        try {
        	String sql = "SELECT * FROM `users`";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("user_no", rs.getInt("user_no"));
                userMap.put("user_name", rs.getString("user_name"));
                userMap.put("user_id", rs.getString("user_id"));
                userMap.put("user_pw", rs.getString("user_pw"));
                userMap.put("user_email", rs.getString("user_email"));
                userMap.put("user_nickname", rs.getString("user_nickname"));
                userMap.put("user_active", rs.getInt("user_active"));
                userMap.put("user_create", rs.getTimestamp("user_create").toLocalDateTime());
                users.add(userMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	close(rs);
        	close(pstmt);
            close(conn);
        }
        return users;
    }
}