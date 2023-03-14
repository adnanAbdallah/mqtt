package newProjectMqtt;
import java.sql.DriverManager;
import java.sql.*;
public class testconnection {


	public static void main(String[] args) {
		String user="root";
		String password="";
		String url="localhost";
		String port="3306";
		String db="test";
//		user="admin";password="Mark71479953*";
//		url="demo-db.cstexhe2nsvj.us-east-1.rds.amazonaws.com";db="sample";

		try{
			//create instance of the connection to connect to local database  using the DriverManager class
			Connection connection= DriverManager.getConnection
					("jdbc:mysql://"+url+":"+port+"/"+db,user,password);
			System.out.println("connection successfully");
			
	}catch(Exception e){
		//alter error if the connection not successfully
		System.out.println("error!");
		}
	
	}

}
