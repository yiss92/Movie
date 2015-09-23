package service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import repository.MovieDao;
import vo.MovieArticle;

public class NowPlayingService {
	
	private NowPlayingService() {
	}

	private static NowPlayingService instance = new NowPlayingService();

	public static NowPlayingService getInstance() {
		return instance;
	}
	
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
	
	public void modifyMovie(HttpServletRequest request) throws Exception {
//		pstmt.setString(1, movieArticle.getGenre1());
//		pstmt.setString(2, movieArticle.getGenre2());
//		pstmt.setString(3, movieArticle.getDirector());
//		pstmt.setString(4, movieArticle.getStar());
//		pstmt.setString(5, movieArticle.getProduction());
//		pstmt.setString(6, movieArticle.getStory());
//		pstmt.setInt(7, movieArticle.getYmd());
//		pstmt.setString(8, movieArticle.getMovieTitle());
		//영화제목은 못 바꾸므로 session 필요
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
		
		
		// article은 수정희망자가 입력한 내용이 들어있고
		// original은 원본글을 db에서 꺼낸 내용이 들어있음.
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();
//		
//		HttpSession session = request.getSession();
//		String temp = String.valueOf(session.getAttribute("user"));
//		String member_id = temp;

		MovieArticle original = dao.selectMovie(temp);

		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 영화입니다. 수정 불가!");
		}

		if (movieArticle.getMovieTitle() != null && movieArticle.getMovieTitle().equals(original.getMovieTitle())) {
			dao.updateMovie(movieArticle); // 비밀번호 검사 통과하면 수정사항 db에 저장함.
			dao.closeCon();
		} else {
			// 비밀번호가 틀린 경우 예외객체 던짐.
			dao.closeCon();
			throw new Exception("비밀번호 오류!");
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
			throw new Exception("존재하지 않는 ID 수정 불가!");
		}				

	//	if (member_id.equals(temp)) {  //session id와 비교해서 삭제시킴
			dao.deleteMovie(title); // deleteUser(member_id); // articleId db에 있는 거 삭제시킴.
			dao.closeCon();
//		} else {
//			// 비밀번호가 틀린 경우 예외객체 던짐.
//			dao.closeCon();
//			throw new Exception("ID 없음!");
//		}
	
	}	

}
