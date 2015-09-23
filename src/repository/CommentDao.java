package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vo.Comment;

public class CommentDao {
	private Connection con;

	private CommentDao(){
	}
	private static CommentDao instance;
	public static CommentDao getInstance(){
		if(instance==null){
			instance = new CommentDao();
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
	
	public int insertComment(Comment comment, String dbName){
		if( dbName=="free" ){
			dbName="FREE_COMMENT_TB";
		}else if( dbName=="review"){
			dbName="REVIEW_COMMENT_TB";
		}
		PreparedStatement pstmt = null;
		int result=0;
		try {
			String sql="insert into ?( ARTICLE_NO, COMMENT_REPLY_NO, USER_ID, COMMENT, YMD)"
					+"values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comment.getArticleNo());
			pstmt.setInt(2, comment.getComment_reply_no());
			pstmt.setString(3, comment.getUserId());
			pstmt.setString(4, comment.getComment());
			pstmt.setTimestamp(5,new Timestamp(comment.getYmd().getTime()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CommentDao insertComment error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int selectCommentCount(String dbName){
		if( dbName=="free" ){
			dbName="FREE_COMMENT_TB";
		}else if( dbName=="review"){
			dbName="REVIEW_COMMENT_TB";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try{
			String sql = "select count(*) from ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dbName);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);			
		}catch(SQLException ex){
			System.out.println("CommentDao selectCommentCount error");
			ex.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();} 
			}catch (SQLException e){e.printStackTrace();}
		}
		return result;
	}
	
	public List<Comment> selectCommentList(int articleNo, int startRow, int count, String dbName){
		if( dbName=="free" ){
			dbName="FREE_COMMENT_TB";
		}else if( dbName=="review"){
			dbName="REVIEW_COMMENT_TB";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> result = new ArrayList<>();
		try{
			String sql = 
				"select * from ? where=? order by COMMENT_NO desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dbName);
			pstmt.setInt(2, articleNo);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, count);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Comment comment = new Comment();
				comment.setArticleNo(rs.getInt(1));
				comment.setCommentNo(rs.getInt(2));
				comment.setComment_reply_no(rs.getInt(3));
				comment.setUserId(rs.getString(4));
				comment.setComment(rs.getString(5));
				comment.setYmd(rs.getTimestamp(6));
				result.add(comment);
			}
		}catch(SQLException ex){
			System.out.println("CommentDao selectCommentList error");
			ex.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();} 
				if(pstmt != null){pstmt.close();} 
			}catch (SQLException e){e.printStackTrace();}
		}
		return result;
	}
	
	public Comment selectComment(int CommentNo, String dbName){
		if( dbName=="free" ){
			dbName="FREE_COMMENT_TB";
		}else if( dbName=="review"){
			dbName="REVIEW_COMMENT_TB";
		}
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Comment result=new Comment();
		try {
			String sql="select * from ? where COMMENT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dbName);
			pstmt.setInt(2, CommentNo);

			rs = pstmt.executeQuery();
			if(rs.next()){
				result.setArticleNo(rs.getInt(1));
				result.setCommentNo(rs.getInt(2));
				result.setComment_reply_no(rs.getInt(3));
				result.setUserId(rs.getString(4));
				result.setComment(rs.getString(5));
				result.setYmd(rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			System.out.println("CommentDao selectComment error");
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
			} catch (SQLException e) {}
		}
		return result;
	}
	
	
	public int updateComment(Comment comment, String dbName){
		if( dbName=="free" ){
			dbName="FREE_COMMENT_TB";
		}else if( dbName=="review"){
			dbName="REVIEW_COMMENT_TB";
		}
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update ? set COMMENT=? where COMMENT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dbName);
			pstmt.setString(2, comment.getComment());
			pstmt.setInt(3, comment.getCommentNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CommentDao updateComment error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int deleteComment(int commentNo, String dbName){
		if( dbName=="free" ){
			dbName="FREE_COMMENT_TB";
		}else if( dbName=="review"){
			dbName="REVIEW_COMMENT_TB";
		}
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="delete from ? where COMMENT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dbName);
			pstmt.setInt(2, commentNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("CommentDao deleteComment error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
}
