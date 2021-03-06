package vo;

import java.util.Date;

public class MovieArticle {
	private String movieTitle;
	private String movieImage;
	private String genre1;
	private String genre2;
	private String director;
	private String star;
	private String production;
	private String story;
	private int readCount;
	private int ymd;
	private int openCheck;
	private double score;

	public MovieArticle(){
	}
	public MovieArticle(String movieTitle, String movieImage, String genre1, String genre2, String director,
			String star, String production, String story, int readCount, int ymd, int openCheck) {
		this.movieTitle = movieTitle;
		this.movieImage = movieImage;
		this.genre1 = genre1;
		this.genre2 = genre2;
		this.director = director;
		this.star = star;
		this.production = production;
		this.story = story;
		this.readCount = readCount;
		this.ymd = ymd;
		this.openCheck = openCheck;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieImage() {
		return movieImage;
	}
	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}
	public String getGenre1() {
		return genre1;
	}
	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}
	public String getGenre2() {
		return genre2;
	}
	public void setGenre2(String genre2) {
		this.genre2 = genre2;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getYmd() {
		return ymd;
	}
	public void setYmd(int ymd) {
		this.ymd = ymd;
	}
	public int getOpenCheck() {
		return openCheck;
	}
	public void setOpenCheck(int openCheck) {
		this.openCheck = openCheck;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
