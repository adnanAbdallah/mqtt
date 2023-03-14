package newProjectMqtt;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
public class MyDbConnection {
	public boolean connectInsertDb(String xamppUser,String pass,
			String temp,String hum,String cons,String status,String topic) {
		
		
		//first we should split the topic 
		String[] list = topic.split("/");
		String a ,b,c,d,e;
		a=list[0];
		b=list[1];
		c=list[2];
		d=list[3];
		e=list[4];
		
		//setup the date
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String DateTimeString = date.format(formatter);
//		System.out.println(DateTimeString);
		
		
		try{
			//create instance of the connection to connect to local database  using the DriverManager class
			Connection connection= DriverManager.getConnection
					("jdbc:mysql://localhost:3306/test",xamppUser,pass);
//			we are gonna prepare a sql statement to be executed
			PreparedStatement preparedStatement =
					connection.prepareStatement("INSERT INTO info "
							+ "(state,district,locality,"
							+ "building,department,temperature,"
							+ " humidity,consumption,status,date_time)"
							+ " VALUES (?, ?,?,?,?,?,?,?,?,?);");
							
							
			//here indicate the variable of "?"
			preparedStatement.setNString(1,a);
			preparedStatement.setNString(2,b);
			preparedStatement.setNString(3,c);
			preparedStatement.setNString(4,d);
			preparedStatement.setNString(5,e);
			preparedStatement.setNString(6,temp);
			preparedStatement.setNString(7,hum);
			preparedStatement.setNString(8,cons);
			preparedStatement.setNString(9,status);
			preparedStatement.setNString(10,DateTimeString);



			//execute this sql statement
			preparedStatement.executeUpdate();
			
			return true;
	}catch(Exception error){
		return false;
		}
	}
	

	public static void main(String[] args) {


	}

}
