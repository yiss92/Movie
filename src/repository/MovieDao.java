package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vo.MovieArticle;

public class MovieDao{
	private Connection con;

	private MovieDao(){
	}
	private static MovieDao instance;
	public static MovieDao getInstance(){
		if(instance==null){
			instance = new MovieDao();
		}
		return instance;
	}
	
	public void startCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(con==null){
				String url = "jdbc:mysql://203.236.209.87:3306/board_db";
				con = DriverManager.getConnection(url,"root","hanbit");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����");
		}
	}
	public void closeCon(){
		if(con!=null){
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				System.out.println("�����ͺ��̽� ���� ����");
			}
		}
	}
	

}
