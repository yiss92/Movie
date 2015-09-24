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

		// �ð��� Date -> String -> int
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
		// // ��ȸ�� ���� ���� ����� 1 �̻��̸� �ش���� �����ϹǷ� select ����
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
		// �ð��� Date -> String -> int
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
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ��ȭ�Դϴ�. ���� �Ұ�!");
		}
		if (original.getMovieTitle().equals(title)) {
			dao.updateMovie(article);
			dao.closeCon();
		} else {
			dao.closeCon();
			throw new Exception("update ����!");
		}
	}

	public void MovieArticledelete(HttpServletRequest request) throws Exception {
		String title = request.getParameter("title");

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle original = dao.selectMovie(title);

		if (original == null) {
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ��ȭ�Դϴ�. ���� �Ұ�!");
		}
		//
		if (original.getMovieTitle().equals(title)) {
			dao.deleteMovie(title); // articleNum db�� �ִ� �� ������Ŵ.
			dao.closeCon();
		} else {
			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
			dao.closeCon();
			throw new Exception("��ȭ���� ����!");
		}
	}

	// ��ȸ�� �����Ҷ� count �� �ö��� ������.
	public MovieArticle readWithOutReadCount(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String title = request.getParameter("title");

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle result = dao.selectMovie(title);// selectArticle(articleId);

		dao.closeCon();
		return result;
	}
	
	public MovieArticlePage getArticlePage(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// ���� ��û�ϴ� ������ �Ķ���� int�� �޾Ƴ���
		String pageStr = request.getParameter("page");
		String openCheck = request.getParameter("openCheck");
		int Check = Integer.parseInt(openCheck);
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		// �� �Խñ��� ���� ��ȸ
		int totalArticleCount = dao.selectMovieCount(Check); // dao.selectArticleCount();

		// �Խñ��� ���� ���
		if (totalArticleCount == 0) {
			return new MovieArticlePage();
		}

		// �� �������� ���
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;

		// ���� �������� ������ �۵��� DB���� ��ȸ�ϱ�
		List<MovieArticle> articleList = dao.selectMovieList(startRow, COUNT_PER_PAGE, Check); // dao.selectArticleList(startRow,
																						// COUNT_PER_PAGE);

		// ������ �ϴܿ� ��ũ�� startPage, endPage ���
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
