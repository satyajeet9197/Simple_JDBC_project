package edu.jsp.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.jsp.entity.User;
public class Userdaoimpl implements Userdao {
	
	private Connection conn;
	private PreparedStatement ptmt;
	
	{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/j2ee?user=root&password=sql13";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			System.out.println("____connection establish______.");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public int addUser(User us) throws SQLException {
		String query = "insert into user values(?,?,?,?,?,?,?)";
		ptmt = conn.prepareStatement(query);
		ptmt.setInt(1, us.getUserid());
		ptmt.setString(2, us.getFirstname());
		ptmt.setString(3, us.getLastname());
		ptmt.setLong(4, us.getMobile());
		ptmt.setString(5, us.getEmail());
		ptmt.setString(6, us.getPassword());
		ptmt.setDate(7, us.getDob());
		
		
		return ptmt.executeUpdate();
	}

	@Override
	public User getUserById(int id) throws SQLException {
		String query = "select * from user where userid = ?";
		ptmt = conn.prepareStatement(query);
		ptmt.setInt(1, id);
		
		ResultSet ref = ptmt.executeQuery();
		
		if(ref.next()) {
			User u = new User();
	
			u.setUserid(ref.getInt("userid"));
			u.setFirstname(ref.getString("firstname"));
			u.setLastname(ref.getString("lastname"));
			u.setMobile(ref.getLong("mobile"));
			u.setEmail(ref.getString("email"));
			u.setPassword(ref.getString("password"));
			u.setDob(ref.getDate("dob"));
			
			return u;
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		String query ="select * from user";
		ptmt = conn.prepareStatement(query);
		
		ResultSet ref = ptmt.executeQuery();
		
		List<User> list = new ArrayList();
		if(ref.isBeforeFirst()) {
			
			//if present 
			while(ref.next()) {
				//update user and add in the list
				User u = new User();
				
				u.setUserid(ref.getInt("userid"));
				u.setFirstname(ref.getString("firstname"));
				u.setLastname(ref.getString("lastname"));
				u.setMobile(ref.getLong("mobile"));
				u.setEmail(ref.getString("email"));
				u.setPassword(ref.getString("password"));
				u.setDob(ref.getDate("dob"));
				
				list.add(u);
				
			}
			return list;
		}
		return null;
	}

	@Override
	public int deleteById(int id) throws SQLException {
		String query ="delete from user where userid = ? " ;
		ptmt = conn.prepareStatement(query);
		ptmt.setInt(1, id);
		
		return ptmt.executeUpdate();
	}

	@Override
	public int updateUser(User user) throws SQLException {
		String query ="update user set firstname = ? where userid = ? ";
		ptmt = conn.prepareStatement(query);
		
		ptmt.setString(1, user.getFirstname());
		ptmt.setInt(2 ,user.getUserid());
		
		return ptmt.executeUpdate();
	}
	
	

}
