package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vo.User;

public class UserDao {
	private Connection con;
	
	// singleton
	private UserDao(){
	}
	private static UserDao instance;
	public static UserDao getInstance(){
		if(instance==null){
			instance = new UserDao();
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
			System.out.println("데이터베이스 연동 실패");
		}
	}
	public void closeCon(){
		if(con!=null){
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				System.out.println("데이터베이스 해제 실패");
			}
		}
	}
	
	public int insertUser(User user){
		PreparedStatement pstmt = null;
		int result=0;
		try {
			String sql="insert into USER_TB( USER_CD, USER_ID, PW, NICKNAME, EMAIL, YMD)"
					+"(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "user");
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getPw());
			pstmt.setString(4, user.getNickname());
			pstmt.setString(5, user.getEmail());
			pstmt.setTimestamp(6,new Timestamp(user.getYmd().getTime()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDao insertUser error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public User selectUser(String userId){
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		User result=new User();
		try {
			String sql="select * from USER_TB where USER_ID=?"
					+"(?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			if(rs.next()){
				result.setUserCd(rs.getString(1));
				result.setUserId(rs.getString(2));
				result.setPw(rs.getString(3));
				result.setNickname(rs.getString(4));
				result.setEmail(rs.getString(5));
				result.setYmd(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			System.out.println("UserDao insertUser error");
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
			} catch (SQLException e) {}
		}
		return result;
	}
	
	public int updateUser(User user){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update user_tb set NICKNAME=?, EMAIL=? where USER_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getEmail());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDao updateUser error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	public int deleteUser(String userId){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="delete from USER_TB where USER_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UserDao deleteUser error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
}
