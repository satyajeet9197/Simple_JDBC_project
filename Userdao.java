package edu.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import edu.jsp.entity.User;

public interface Userdao {
	
	int addUser (User us) throws SQLException;
	
	//to fetch department based on id
	User getUserById(int id)throws SQLException;
		
	//to fetch all departments
	List<User> getAllUsers() throws SQLException;
	
	//to delete data by id
	int deleteById(int id) throws SQLException;
		
	//update department details
	int updateUser(User user)throws SQLException;

}
