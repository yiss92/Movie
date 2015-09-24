package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import repository.MovieDao;
import vo.MovieArticle;
import vo.MovieReview;

public class PremiereService {
	
	private PremiereService() {
	}

	private static PremiereService instance = new PremiereService();

	public static PremiereService getInstance() {
		return instance;
	}
	
	public int PremiereMovieInsert(HttpServletRequest request){
//		`ARTICLE_NO` INT(20) NOT NULL AUTO_INCREMENT,
//		`USER_ID` VARCHAR(15) NULL DEFAULT NULL,
//		`MOVIE_TITLE` VARCHAR(30) NULL DEFAULT NULL,
//		`ARTICLE_TITLE` VARCHAR(100) NULL DEFAULT NULL,
//		`REVIEW` TEXT NULL,
//		`READ_COUNT` INT(20) UNSIGNED NOT NULL,
//		`YMD` DATETIME NULL DEFAULT NULL,
		String articleNoStr = request.getParameter("articleNo");		
		
		int articleNo = Integer.parseInt(articleNoStr);
		String userId = request.getParameter("userid");
		String moveTitle = request.getParameter("movetitle");
		String articleTitle = request.getParameter("articletitle");
		String review = request.getParameter("review");
		
		MovieReview movieReview = new MovieReview();
		movieReview.setArticleNo(articleNo);
		movieReview.setUserId(userId);
		movieReview.setMovieTitle(moveTitle);
		movieReview.setArticleTitle(articleTitle);
		movieReview.setReview(review);
		
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		int result = dao.insertReview(movieReview);
		dao.closeCon();

		return result;
	}
		
	public MovieReview getPremiere(HttpServletRequest request){
		
		String articleNoStr = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoStr);
		
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieReview result = null;

		if (dao.selectReview(articleNo) != null) {
			// 조회수 증가 실행 결과가 1 이상이면 해당글이 존재하므로 select 실행
			result = dao.selectReview(articleNo);
		}

		dao.closeCon();
		return result;
	}
	
	public void MovieReviewUpdate(HttpServletRequest request) throws Exception {
//
//		// pstmt.setString(1, movieArticle.getGenre1());
//		// pstmt.setString(2, movieArticle.getGenre2());
//		// pstmt.setString(3, movieArticle.getDirector());
//		// pstmt.setString(4, movieArticle.getStar());
//		// pstmt.setString(5, movieArticle.getProduction());
//		// pstmt.setString(6, movieArticle.getStory());
//		// pstmt.setInt(7, movieArticle.getYmd());		
//		String gen1 = request.getParameter("gen1");
//		String gen2 = request.getParameter("gen2");
//		String director = request.getParameter("director");
//		String star = request.getParameter("star");
//		// 시간을 Date -> String -> int
//		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
//		Date nowDate = new Date();
//		String tempDate = sdFormat.format(nowDate);
//		int time = Integer.parseInt(tempDate);
//
//		String title = request.getParameter("title");
//
//		MovieArticle article = new MovieArticle();
//		article.setGenre1(gen1);
//		article.setGenre2(gen2);
//		article.setDirector(director);
//		article.setStar(star);
//		article.setYmd(time);
//		article.setMovieTitle(title);
//
//		MovieDao dao = MovieDao.getInstance();
//		dao.startCon();
//
//		MovieArticle original = dao.selectMovie(title);
//		if (original == null) {
//			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
//			dao.closeCon();
//			throw new Exception("존재하지 않는 영화입니다. 수정 불가!");
//		}
//		if (original.getMovieTitle().equals(title)) {
//			dao.updateMovie(article);
//			dao.closeCon();
//		} else {
//			dao.closeCon();
//			throw new Exception("update 오류!");
//		}
	}

}
