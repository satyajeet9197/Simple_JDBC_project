package edu.jsp.app;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.jsp.dao.*;
import edu.jsp.entity.User;

public class Apllication {
	public static void main(String[] args) {
		
		
		Userdao ud = new Userdaoimpl();
		
		System.out.println("Enter 1 to add User");
		System.out.println("Enter 2 to search User by Userid");
		System.out.println("Enter 3 to get all User details");
		System.out.println("Enter 4 to delete User by Userid");
		System.out.println("Enter 5 to update User by Userid");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter choice");
		int choice = sc.nextInt();
		
		
		switch (choice) {
		case 1:{
			User us = new User();
			System.out.println("Enter user id :");
			us.setUserid(sc.nextInt());
			System.out.println("enter first name :");
			us.setFirstname(sc.next());
			System.out.println("enter last name :");
			us.setLastname(sc.next());
			System.out.println("enter mobile number :");
			us.setMobile(sc.nextLong());
			System.out.println("enter email add :");
			us.setEmail(sc.next());
			System.out.println("enter password :");
			us.setPassword(sc.next());
			System.out.println("enter date of birth (yyyy-mm-dd) :");
			String date = sc.next();
			Date dob = Date.valueOf(date);
			us.setDob(dob);
			
			try {
				int n =ud.addUser(us);
				
					System.out.println(n+" User Added Sucessfully....");
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}break;
		
		case 2:{
			System.out.println("enter user id :");
			int id = sc.nextInt();
			
			try {
				User u = ud.getUserById(id);
				
				if(u != null) {
					System.out.println(u);
				}
				else {
					System.err.println("user not found...");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}break;
		
		case 3:{
			try {
				List<User> li = ud.getAllUsers();
				if(li != null) {
					for(User u : li) {
                         System.out.println(u);
					}
				}
				else {
					System.err.println("No Data Found...");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}break;
		case 4 :{
			
			System.out.println("Enter the user id :");
			int id = sc.nextInt();
			
			try {
				int n = ud.deleteById(id);
				if(n != 0) {
					System.out.println("records deleted :"+n);
				}
				else {
					System.err.println("No Data Found...");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}break;
		
		case 5 :{
			
			User u = new User();
			System.out.println("enter id where u want to change fname :");
			u.setUserid(sc.nextInt());
			System.out.println("enter the name :");
			u.setFirstname(sc.next());
			
			try {
				int n = ud.updateUser(u);
				if(n != 0) {
					System.out.println("name is updated...");
				}
				else {
					System.err.println("Invalid user id...");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}break;
		

		default:
			break;
		}
	}

}
