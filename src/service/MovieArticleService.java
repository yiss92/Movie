package service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import repository.MovieDao;
import vo.MovieArticle;
import vo.MovieArticlePage;

public class MovieArticleService {

	private MovieArticleService() {
	}

	private static MovieArticleService instance = new MovieArticleService();

	public static MovieArticleService getInstance() {
		return instance;
	}

	public static final int COUNT_PER_PAGE = 10;
	
	public int MovieArticlewrite(HttpServletRequest request) {

		String title = request.getParameter("title");
		String movieImg = request.getParameter("movieImg");
		String gen1 = request.getParameter("gen1");
		String gen2 = request.getParameter("gen2");
		String director = request.getParameter("director");
		String star = request.getParameter("star");
		String production = request.getParameter("production");

		// HttpSession session = request.getSession();
		// String temp = String.valueOf(session.getAttribute("user"));

		// 시간을 Date -> String -> int
		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);
		int time = Integer.parseInt(tempDate);

		MovieArticle article = new MovieArticle();
		article.setMovieTitle(title);
		article.setMovieImage(movieImg);
		article.setGenre1(gen1);
		article.setGenre2(gen2);
		article.setDirector(director);
		article.setStar(star);
		article.setProduction(production);
		article.setYmd(time);

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		int result = dao.insertMovie(article);
		dao.closeCon();

		return result;
	}

	public MovieArticle MovieArticleread(HttpServletRequest request) {

		String title = request.getParameter("title");

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle result = null;

		// if (dao.updatedReadCount(id) > 0) {
		// // 조회수 증가 실행 결과가 1 이상이면 해당글이 존재하므로 select 실행
		// result = dao.selectArticle(articleId);
		// }
		result = dao.selectMovie(title);

		return result;
	}

	public void MovieArticleUpdate(HttpServletRequest request) throws Exception {

		// pstmt.setString(1, movieArticle.getGenre1());
		// pstmt.setString(2, movieArticle.getGenre2());
		// pstmt.setString(3, movieArticle.getDirector());
		// pstmt.setString(4, movieArticle.getStar());
		// pstmt.setString(5, movieArticle.getProduction());
		// pstmt.setString(6, movieArticle.getStory());
		// pstmt.setInt(7, movieArticle.getYmd());
		// pstmt.setString(8, movieArticle.getMovieTitle());
		String gen1 = request.getParameter("gen1");
		String gen2 = request.getParameter("gen2");
		String director = request.getParameter("director");
		String star = request.getParameter("star");
		// 시간을 Date -> String -> int
		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);
		int time = Integer.parseInt(tempDate);

		String title = request.getParameter("title");

		MovieArticle article = new MovieArticle();
		article.setGenre1(gen1);
		article.setGenre2(gen2);
		article.setDirector(director);
		article.setStar(star);
		article.setYmd(time);
		article.setMovieTitle(title);

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle original = dao.selectMovie(title);
		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 영화입니다. 수정 불가!");
		}
		if (original.getMovieTitle().equals(title)) {
			dao.updateMovie(article);
			dao.closeCon();
		} else {
			dao.closeCon();
			throw new Exception("update 오류!");
		}
	}

	public void MovieArticledelete(HttpServletRequest request) throws Exception {
		String title = request.getParameter("title");

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle original = dao.selectMovie(title);

		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 영화입니다. 수정 불가!");
		}
		//
		if (original.getMovieTitle().equals(title)) {
			dao.deleteMovie(title); // articleNum db에 있는 거 삭제시킴.
			dao.closeCon();
		} else {
			// 비밀번호가 틀린 경우 예외객체 던짐.
			dao.closeCon();
			throw new Exception("영화없음 없음!");
		}
	}

	// 조회후 수정할때 count 값 올라가지 않을때.
	public MovieArticle readWithOutReadCount(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String title = request.getParameter("title");

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle result = dao.selectMovie(title);// selectArticle(articleId);

		dao.closeCon();
		return result;
	}
	
	public MovieArticlePage getArticlePage(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// 현재 요청하는 페이지 파라미터 int로 받아내기
		String pageStr = request.getParameter("page");
		String openCheck = request.getParameter("openCheck");
		int Check = Integer.parseInt(openCheck);
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		// 총 게시글의 갯수 조회
		int totalArticleCount = dao.selectMovieCount(Check); // dao.selectArticleCount();

		// 게시글이 없는 경우
		if (totalArticleCount == 0) {
			return new MovieArticlePage();
		}

		// 총 페이지수 계산
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;

		// 현재 페이지에 보여질 글들을 DB에서 조회하기
		List<MovieArticle> articleList = dao.selectMovieList(startRow, COUNT_PER_PAGE, Check); // dao.selectArticleList(startRow,
																						// COUNT_PER_PAGE);

		// 페이지 하단에 링크할 startPage, endPage 계산
		int startPage = requestPage - 5;
		if (startPage < 1) {
			startPage = 1;
		}
		int endPage = requestPage + 5;
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		dao.closeCon();// dao.closeConnection();
		return new MovieArticlePage(articleList, requestPage, totalPageCount, startPage, endPage);
	}

}
