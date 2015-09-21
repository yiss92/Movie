package vo;

import java.util.Date;

public class MovieScore {
	private int commentNo;
	private String userId;
	private String movieTitle;
	private String comment;
	private int score;
	private Date ymd;

	public MovieScore(){
	}
	public MovieScore(int commentNo, String userId, String movieTitle, String comment, int score, Date ymd) {
		this.commentNo = commentNo;
		this.userId = userId;
		this.movieTitle = movieTitle;
		this.comment = comment;
		this.score = score;
		this.ymd = ymd;
	}
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getYmd() {
		return ymd;
	}
	public void setYmd(Date ymd) {
		this.ymd = ymd;
	}
}
