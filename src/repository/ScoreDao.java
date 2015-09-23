package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vo.MovieScore;

public class ScoreDao {
	private Connection con;

	private ScoreDao(){
	}
	private static ScoreDao instance;
	public static ScoreDao getInstance(){
		if(instance==null){
			instance = new ScoreDao();
		}
		return instance;
	}
	
	public void startCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(con==null){
				String url = "jdbc:mysql://203.236.209.87:3306/movie_db";
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
	
	public int insertScore(MovieScore movieScore){
		PreparedStatement pstmt = null;
		int result=0;
		try {
			String sql="insert into MOVIE_COMMENT_TB( MOVIE_TITLE, USER_ID, COMMENT, SCORE, YMD)"
					+"values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieScore.getMovieTitle());
			pstmt.setString(2, movieScore.getUserId());
			pstmt.setString(3, movieScore.getComment());
			pstmt.setInt(4, movieScore.getScore());
			pstmt.setTimestamp(5,new Timestamp(movieScore.getYmd().getTime()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ScoreDao insertScore error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int selectScoreCount(){
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try{
			String sql = "select count(*) from MOVIE_COMMENT_TB";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);			
		}catch(SQLException ex){
			System.out.println("ScoreDao selectScoreCount error");
			ex.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(stmt != null){stmt.close();} 
			}catch (SQLException e){e.printStackTrace();}
		}
		return result;
	}
	
	public int selectAvgScore(String movieTitle){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try{
			String sql = "select avg(SCORE) from MOVIE_COMMENT_TB where=?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);			
		}catch(SQLException ex){
			System.out.println("ScoreDao selectAvgScore error");
			ex.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();} 
			}catch (SQLException e){e.printStackTrace();}
		}
		return result;
	}
	
	public List<MovieScore> selectScoreList(int startRow, int count, String movieTitle){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieScore> result = new ArrayList<>();
		try{
			String sql = 
				"select * from MOVIE_COMMENT_TB where=? order by COMMENT desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieTitle);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, count);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MovieScore score = new MovieScore();
				score.setMovieTitle(rs.getString(1));
				score.setComment(rs.getString(2));
				score.setUserId(rs.getString(3));
				score.setComment(rs.getString(4));
				score.setScore(rs.getInt(5));
				score.setYmd(rs.getTimestamp(6));
				result.add(score);
			}
		}catch(SQLException ex){
			System.out.println("ScoreDao selectFreeList error");
			ex.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();} 
				if(pstmt != null){pstmt.close();} 
			}catch (SQLException e){e.printStackTrace();}
		}
		return result;
	}
	
	public MovieScore selectScore(int commentNo){
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		MovieScore result=new MovieScore();
		try {
			String sql="select * from MOVIE_COMMENT_TB where COMMENT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);

			rs = pstmt.executeQuery();
			if(rs.next()){
				result.setMovieTitle(rs.getString(1));
				result.setComment(rs.getString(2));
				result.setUserId(rs.getString(3));
				result.setComment(rs.getString(4));
				result.setScore(rs.getInt(5));
				result.setYmd(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			System.out.println("ScoreDao selectScore error");
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
			} catch (SQLException e) {}
		}
		return result;
	}
	

	public int updateScore(MovieScore movieScore){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update MOVIE_COMMENT_TB set COMMENT=?, SCORE=? where COMMENT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieScore.getComment());
			pstmt.setInt(2, movieScore.getScore());
			pstmt.setInt(3, movieScore.getCommentNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ScoreDao updateScore error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int deleteScore(int commentNo){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="delete from MOVIE_COMMENT_TB where COMMENT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ScoreDao deleteScore error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
}
