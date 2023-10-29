package database;
import java.sql.*;
//import java.io.*;

// There is  no main method yet. in the main method, the connection would be made and the functions would be called as per need
// i guess. idk. also, no clue about the profile picture format. for now it is string. if its going to be a file,
// then can change it to file or whatever it is. Pretty sure, need more methods. please check. 

public class DatabaseHandling {
	private String userName;
	private String password;
	private int winCount;
	private String profilepicture;
	
	public DatabaseHandling(String userName,String password, int winCount, String profilepicture) {
		this.userName = userName;
		this.password = password;
		this.winCount = winCount;
		this.profilepicture = profilepicture;
	}
	
	public String getUsername() {
		return userName;
	}
	public String getpassword() {
		return password;
	}
	public int getwincount() {
		return winCount;
	}
	public String getpfp() {
		return profilepicture;
	}
	
	public static DatabaseHandling getUserInfo(String userName, Connection connection) throws SQLException{
		String query = "select * from gameInfo where userName = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, userName);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			String dbusername = result.getString("userName");
			String dbpassword = result.getString("password");
			int dbwincount = result.getInt("wins");
			String dbphoto = result.getString("profilepicture");
			return new DatabaseHandling(dbusername, dbpassword, dbwincount, dbphoto);
		}
		else {
			return null;
		}
	}
	
	public static void insertUser(String userName, String password, int winCount, String profilepicture, Connection connection) throws SQLException{
		String query = "insert into gameInfo(userName, password, wins, profilepicture) values(?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1,userName);
		statement.setString(2, password);
		statement.setInt(3, winCount);
		statement.setString(4, profilepicture);
		statement.executeUpdate();
		System.out.println("User data inserted");
	}
	
	public void checkExistingUser(String userName, String password, Connection connection) throws SQLException{
		DatabaseHandling existingUser = DatabaseHandling.getUserInfo(userName, connection);
		if(existingUser == null) {
			DatabaseHandling.insertUser(userName, password, 0, "", connection);
			System.out.println("New user added");
		}
		else {
			if(existingUser.getpassword().equals(password)) {
				System.out.println("user verified");
			}
			else {
				System.out.println("invalid.");
			}
		}
	}
		
	public void updateWinCount(DatabaseHandling user, Connection connection) throws SQLException{
		int winnings = user.getwincount();
		String query = "update gameInfo set wins = ? where userName = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, winnings+1);
		statement.setString(2, user.getUsername());
		statement.executeUpdate();
		System.out.println("win count update finish");
	}
	
//	public static void main(String[] args) {
//		
//	}
}	

