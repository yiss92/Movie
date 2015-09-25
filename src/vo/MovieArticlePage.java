package vo;

import java.util.List;

public class MovieArticlePage {
	private List<MovieArticle> movieList;
	private int nowPage;
	private int perPage;
	private int totalPage;
	private int startArticleNo;
	private int endArticleNo;
	private int perList;
	private int startListNo;
	private int endListNo;
	
	public MovieArticlePage(){
	}
		
	
	public MovieArticlePage(List<MovieArticle> movieList) {
		super();
		this.movieList = movieList;
	}

	public MovieArticlePage(List<MovieArticle> movieList, int nowPage, int totalPage, int startArticleNo, int endArticleNo ){
		this.movieList = movieList;
		this.nowPage = nowPage;
		this.totalPage = totalPage;
		this.startArticleNo = startArticleNo;
		this.endArticleNo = endArticleNo;
	}
	public MovieArticlePage(List<MovieArticle> movieList, int nowPage, int perPage, int totalPage, int startArticleNo, int endArticleNo, int perList, int startListNo, int endListNo) {
		this.movieList = movieList;
		this.nowPage = nowPage;
		this.perPage = perPage;
		this.totalPage = totalPage;
		this.startArticleNo = startArticleNo;
		this.endArticleNo = endArticleNo;
		this.perList = perList;
		this.startListNo = startListNo;
		this.endListNo = endListNo;
	}
	
	public List<MovieArticle> getMovieList() {
		return movieList;
	}
	public void setMovieList(List<MovieArticle> movieList) {
		this.movieList = movieList;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartArticleNo() {
		return startArticleNo;
	}
	public void setStartArticleNo(int startArticleNo) {
		this.startArticleNo = startArticleNo;
	}
	public int getEndArticleNo() {
		return endArticleNo;
	}
	public void setEndArticleNo(int endArticleNo) {
		this.endArticleNo = endArticleNo;
	}
	public int getPerList() {
		return perList;
	}
	public void setPerList(int perList) {
		this.perList = perList;
	}
	public int getStartListNo() {
		return startListNo;
	}
	public void setStartListNo(int startListNo) {
		this.startListNo = startListNo;
	}
	public int getEndListNo() {
		return endListNo;
	}
	public void setEndListNo(int endListNo) {
		this.endListNo = endListNo;
	}
}
