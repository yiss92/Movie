package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vo.MovieArticle;
import vo.MovieReview;

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
	
	
	public int insertMovie(MovieArticle movieArticle){
		PreparedStatement pstmt = null;
		int result=0;
		try {
			String sql="insert into MOVIE_TB( MOVIE_TITLE, MOVIE_IMAGE, GENRE_1, GENRE_2, DIRECTOR, STAR, PRODUCTION, STORY, YMD, OPEN_CHECK)"
					+"values(?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieArticle.getMovieTitle());
			pstmt.setString(1, movieArticle.getMovieImage());
			pstmt.setString(3, movieArticle.getGenre1());
			pstmt.setString(4, movieArticle.getGenre2());
			pstmt.setString(5, movieArticle.getDirector());
			pstmt.setString(6, movieArticle.getStar());
			pstmt.setString(7, movieArticle.getProduction());
			pstmt.setString(8, movieArticle.getStory());
			pstmt.setInt(9,  movieArticle.getYmd());
			pstmt.setInt(10,  movieArticle.getOpenCheck());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao insertMovie error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public MovieArticle selectMovie(String movieTitle){
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		MovieArticle result=new MovieArticle();
		try {
			String sql="select * from MOVIE_TB where MOVIE_TITLE=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieTitle);

			rs = pstmt.executeQuery();
			if(rs.next()){
				result.setMovieTitle(rs.getString(1));
				result.setMovieImage(rs.getString(2));
				result.setGenre1(rs.getString(3));
				result.setGenre2(rs.getString(4));
				result.setDirector(rs.getString(5));
				result.setStar(rs.getString(6));
				result.setProduction(rs.getString(7));
				result.setStory(rs.getString(8));
				result.setReadCount(rs.getInt(9));
				result.setYmd(rs.getInt(10));
				result.setYmd(rs.getInt(11));
			}
		} catch (SQLException e) {
			System.out.println("MovieDao selectMovie error");
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
			} catch (SQLException e) {}
		}
		return result;
	}

	
	public int updateMovie(MovieArticle movieArticle){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update MOVIE_TB set GENRE_1=?, GENRE_2=?, DIRECTOR=?, STAR=?, PRODUCTION=?, STORY=?, YMD=? where MOVIE_TITLE=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieArticle.getGenre1());
			pstmt.setString(2, movieArticle.getGenre2());
			pstmt.setString(3, movieArticle.getDirector());
			pstmt.setString(4, movieArticle.getStar());
			pstmt.setString(5, movieArticle.getProduction());
			pstmt.setString(6, movieArticle.getStory());
			pstmt.setInt(7, movieArticle.getYmd());
			pstmt.setString(8, movieArticle.getMovieTitle());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao updateMovie error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int deleteMovie(String movieTitle){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="delete from MOVIE_TB where MOVIE_TITLE=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieTitle);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao deleteMovie error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	public int updateOpenChangeMovie(String movieTitle, int openCheck){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update MOVIE_TB set OPEN_CHECK=? where MOVIE_TITLE=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 1-openCheck);
			pstmt.setString(2, movieTitle);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao openChangeMovie error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	
	
	public int insertReview(MovieReview movieReview){
		PreparedStatement pstmt = null;
		int result=0;
		try {
			String sql="insert into MOVIE_REVIEW_TB( USER_ID, MOVIE_TITLE, ARTICLE_TITLE, REVIEW, YMD)"
					+"values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieReview.getUserId());
			pstmt.setString(2, movieReview.getMovieTitle());
			pstmt.setString(3, movieReview.getArticleTitle());
			pstmt.setString(4, movieReview.getReview());
			pstmt.setTimestamp(5,new Timestamp(movieReview.getYmd().getTime()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao insertReview error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public MovieReview selectReview(int articleNo){
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		MovieReview result=new MovieReview();
		try {
			String sql="select * from MOVIE_REVIEW_TB where ARTICLE_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, articleNo);

			rs = pstmt.executeQuery();
			if(rs.next()){
				result.setArticleNo(rs.getInt(1));
				result.setUserId(rs.getString(2));
				result.setMovieTitle(rs.getString(3));
				result.setArticleTitle(rs.getString(4));
				result.setReview(rs.getString(5));
				result.setReadCount(rs.getInt(6));
				result.setYmd(rs.getTimestamp(7));
				
			}
		} catch (SQLException e) {
			System.out.println("MovieDao selectReview error");
		}finally{
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
			} catch (SQLException e) {}
		}
		return result;
	}
	
	public int updateReview(MovieReview movieReview){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="update MOVIE_REVIEW_TB set ARTICLE_TITLE=?, CONTENT=? where ARTICLE_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, movieReview.getArticleTitle());
			pstmt.setString(2, movieReview.getReview());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao updateReview error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int deleteReview(int articleNO){
		PreparedStatement pstmt=null;
		int result=0;
		try {
			String sql="delete from MOVIE_REVIEW_TB where ARTICLE_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MovieDao deleteReview error");
		}finally{
			if(pstmt!=null){
				try {pstmt.close();} catch (SQLException e) {}
			}
		}
		return result;
	}
	
}
