package service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import repository.MovieDao;
import vo.MovieArticle;
import vo.MovieArticlePage;

public class NowPlayingService {
	
	private NowPlayingService() {
	}

	private static NowPlayingService instance = new NowPlayingService();

	public static NowPlayingService getInstance() {
		return instance;
	}
	
	public static final int COUNT_PER_PAGE = 10;
	
	public int writeMovie(HttpServletRequest request)
			throws ClassNotFoundException, SQLException, UnsupportedEncodingException {

		String title = request.getParameter("title");
		String moveImage = request.getParameter("moveImg");
		String gen1 = request.getParameter("gen1");
		String gen2 = request.getParameter("gen2");
		String director = request.getParameter("director");
		String actor = request.getParameter("actor");
		String production = request.getParameter("production");
		String story = request.getParameter("story");
		int open =  Integer.parseInt(request.getParameter("open"));

		// System.out.println("abcd");

//		Article article = new Article();
//		article.setTitle(title);
//		article.setWriterName(writerName);
//		article.setPassword(password);
//		article.setContent(content);
//		pstmt.setString(1, movieArticle.getMovieTitle());
//		pstmt.setString(1, movieArticle.getMovieImage());
//		pstmt.setString(3, movieArticle.getGenre1());
//		pstmt.setString(4, movieArticle.getGenre2());
//		pstmt.setString(5, movieArticle.getDirector());
//		pstmt.setString(6, movieArticle.getStar());
//		pstmt.setString(7, movieArticle.getProduction());
//		pstmt.setString(8, movieArticle.getStory());
//		pstmt.setInt(9,  movieArticle.getYmd());
//		pstmt.setInt(10,  movieArticle.getOpenCheck());
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String to = transFormat.format(date);
		int stoint =  Integer.parseInt(to);
		
		MovieArticle movieArticle = new MovieArticle();
		movieArticle.setMovieTitle(title);
		movieArticle.setMovieImage(moveImage);
		movieArticle.setGenre1(gen1);
		movieArticle.setGenre2(gen2);
		movieArticle.setDirector(director);
		movieArticle.setStar(actor);
		movieArticle.setProduction(production);
		movieArticle.setStory(story);
		movieArticle.setYmd(stoint);
		movieArticle.setOpenCheck(open);
//
//		article.setPostingDate(new Date());
//		article.setReadCount(0);

		MovieDao dao = MovieDao.getInstance();
		dao.startCon(); // startConnection();

		int result = dao.insertMovie(movieArticle);
		dao.closeCon();

		return result;
	}
	
	public MovieArticle getMovieArticle(HttpServletRequest request){
		String title = request.getParameter("movie_title");				
		
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle result = null;

		if (dao.selectMovie(title) != null) {
			// ��ȸ�� ���� ���� ����� 1 �̻��̸� �ش���� �����ϹǷ� select ����
			result = dao.selectMovie(title);
		}

		dao.closeCon();
		return result;
	}
	
	public MovieArticlePage gatMovieArticlePage(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// ���� ��û�ϴ� ������ �Ķ���� int�� �޾Ƴ���
		String pageStr = request.getParameter("page");
		String check = request.getParameter("openChek");
		int openCheck = Integer.parseInt(check);
		
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}
      
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		// �� �Խñ��� ���� ��ȸ
		int totalArticleCount = dao.selectMovieCount(openCheck);// selectArticleCount();

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
        int chack=1;
		// ���� �������� ������ �۵��� DB���� ��ȸ�ϱ�
		List<MovieArticle> articleList = dao.selectMovieList(startRow, COUNT_PER_PAGE,chack);

		// ������ �ϴܿ� ��ũ�� startPage, endPage ���
		int startPage = requestPage - 5;
		if (startPage < 1) {
			startPage = 1;
		}
		int endPage = requestPage + 5;
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		dao.closeCon(); // closeConnection();
		return new MovieArticlePage(articleList, requestPage, totalPageCount, startPage, endPage);
	}
	
	public void modifyMovie(HttpServletRequest request) throws Exception {
//		pstmt.setString(1, movieArticle.getGenre1());
//		pstmt.setString(2, movieArticle.getGenre2());
//		pstmt.setString(3, movieArticle.getDirector());
//		pstmt.setString(4, movieArticle.getStar());
//		pstmt.setString(5, movieArticle.getProduction());
//		pstmt.setString(6, movieArticle.getStory());
//		pstmt.setInt(7, movieArticle.getYmd());
//		pstmt.setString(8, movieArticle.getMovieTitle());
		//��ȭ������ �� �ٲٹǷ� session �ʿ�
		HttpSession session = request.getSession();
		String temp = String.valueOf(session.getAttribute("title"));
		
		String gen1 = request.getParameter("gen1");
		String gen2 = request.getParameter("gen2");
		String director = request.getParameter("director");
		String star = request.getParameter("star");
		String production = request.getParameter("production");
		String story = request.getParameter("story");
		//String title = request.getParameter("title");
		
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String to = transFormat.format(date);
		int stoint =  Integer.parseInt(to);
		
		MovieArticle movieArticle = new MovieArticle();
		movieArticle.setGenre1(gen1);
		movieArticle.setGenre2(gen2);
		movieArticle.setDirector(director);
		movieArticle.setStar(star);
		movieArticle.setProduction(production);		
		movieArticle.setStory(story);
		movieArticle.setYmd(stoint);
		movieArticle.setMovieTitle(temp);
		
		
		// article�� ��������ڰ� �Է��� ������ ����ְ�
		// original�� �������� db���� ���� ������ �������.
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();
//		
//		HttpSession session = request.getSession();
//		String temp = String.valueOf(session.getAttribute("user"));
//		String member_id = temp;

		MovieArticle original = dao.selectMovie(temp);

		if (original == null) {
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ��ȭ�Դϴ�. ���� �Ұ�!");
		}

		if (movieArticle.getMovieTitle() != null && movieArticle.getMovieTitle().equals(original.getMovieTitle())) {
			dao.updateMovie(movieArticle); // ��й�ȣ �˻� ����ϸ� �������� db�� ������.
			dao.closeCon();
		} else {
			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
			dao.closeCon();
			throw new Exception("��й�ȣ ����!");
		}

	}
	
	public void deleteMovie(HttpServletRequest request) throws Exception {
		//String sql="delete from MOVIE_TB where MOVIE_TITLE=?";
		
		String title = request.getParameter("title");
		
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieArticle original = dao.selectMovie(title); // selectUser(member_id);
		if (original == null) {
			dao.closeCon();
			throw new Exception("�������� �ʴ� ID ���� �Ұ�!");
		}				

	//	if (member_id.equals(temp)) {  //session id�� ���ؼ� ������Ŵ
			dao.deleteMovie(title); // deleteUser(member_id); // articleId db�� �ִ� �� ������Ŵ.
			dao.closeCon();
//		} else {
//			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
//			dao.closeCon();
//			throw new Exception("ID ����!");
//		}
	
	}	

}
