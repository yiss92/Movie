package vo;

import java.util.Date;

public class MovieReview {
	private int aticleNo;
	private String userId;
	private String movieTitle;
	private String articleTitle;
	private String review;
	private int readCount;
	private Date ymd;
	
	public MovieReview(){
	}
	public MovieReview(int aticleNo, String userId, String movieTitle, String articleTitle, String review,
			int readCount, Date ymd) {
		this.aticleNo = aticleNo;
		this.userId = userId;
		this.movieTitle = movieTitle;
		this.articleTitle = articleTitle;
		this.review = review;
		this.readCount = readCount;
		this.ymd = ymd;
	}
	
	public int getAticleNo() {
		return aticleNo;
	}
	public void setAticleNo(int aticleNo) {
		this.aticleNo = aticleNo;
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
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Date getYmd() {
		return ymd;
	}
	public void setYmd(Date ymd) {
		this.ymd = ymd;
	}
}
