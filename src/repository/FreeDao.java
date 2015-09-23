package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vo.FreeArticle;
import vo.User;

public class FreeDao{
	private Connection con;

	private FreeDao(){
	}
	private static FreeDao instance;
	public static FreeDao getInstance(){
		if(instance==null){
			instance = new FreeDao();
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
	
	
	public int insertFree(FreeArticle freeArticle){
		PreparedStatement pstmt = null;
		int result=0;
		try {
			String sql="insert into FREE_ARTICLE_TB( USER_ID, ARTICLE_TITLE, CONTENT,, YMD)"
					+"values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, freeArticle.getUserId());
			pstmt.setString(2, freeArticle.getArticleTitle());
			pstmt.setString(3, freeArticle.getContent());
			pstmt.setTimestamp(4,new Timestamp(freeArticle.getYmd().getTime()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FreeDao insertFree error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public FreeArticle selectFree(int articleNo){
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		FreeArticle result=new FreeArticle();
		try {
			String sql="select * from FREE_ARTICLE_TB where ARTICLE_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, articleNo);

			rs = pstmt.executeQuery();
			if(rs.next()){
				result.setArticleNo(rs.getInt(1));
				result.setUserId(rs.getString(2));
				result.setArticleTitle(rs.getString(3));
				result.setContent(rs.getString(4));
				result.setReadCount(rs.getInt(5));
				result.setYmd(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			System.out.println("FreeDao selectFree error");
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
			} catch (SQLException e) {}
		}
		return result;
	}
	
	public int updateFree(FreeArticle freeArticle){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update FREE_ARTICLE_TB set ARTICLE_TITLE=?, CONTENT=? where ARTICLE_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, freeArticle.getArticleTitle());
			pstmt.setString(2, freeArticle.getContent());
			pstmt.setInt(3, freeArticle.getArticleNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FreeDao updateFree error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int deleteFree(int articleNO){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="delete from FREE_ARTICLE_TB where ARTICLE_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("FreeDao deleteFree error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
}
