package service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import repository.MovieDao;
import vo.MovieReview;
import vo.MovieReviewPage;

public class ReviewService {

	private ReviewService() {
	}

	private static ReviewService instance = new ReviewService();

	public static ReviewService getInstance() {
		return instance;
	}

	public static final int COUNT_PER_PAGE = 10;

	public int ReviewMovieWrite(HttpServletRequest request) {

		String strArticleNo = request.getParameter("article_no");
		int reviewArticNo = Integer.parseInt(strArticleNo);
		String user_id = request.getParameter("user_id");
		String title = request.getParameter("title");
		String articleTitle = request.getParameter("article_title");
		String review = request.getParameter("review");
		String count = request.getParameter("count");
		int readCount = Integer.parseInt(count);

		MovieReview article = new MovieReview();
		article.setArticleNo(reviewArticNo);
		article.setUserId(user_id);
		article.setMovieTitle(title);
		article.setArticleTitle(articleTitle);
		article.setReview(review);
		article.setReadCount(readCount);
		article.setYmd(new Date());

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		int result = dao.insertReview(article);
		dao.closeCon();

		return result;
	}

	public MovieReview ReviewMovieArticleread(HttpServletRequest request) {

		String reviewArticleStr = request.getParameter("reviewArticleNo");
		int reviewArticleNo = Integer.parseInt(reviewArticleStr);

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieReview result = null;

		// if (dao.updatedReadCount(id) > 0) {
		// // 조회수 증가 실행 결과가 1 이상이면 해당글이 존재하므로 select 실행
		// result = dao.selectArticle(articleId);
		// }
		result = dao.selectReview(reviewArticleNo);

		return result;
	}

	public void ReviewMovieArticleUpdate(HttpServletRequest request) throws Exception {

		String articleTitle = request.getParameter("articleTitle");
		String review = request.getParameter("review");
		String articleNoStr = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoStr);

		MovieReview article = new MovieReview();
		article.setArticleTitle(articleTitle);
		article.setReview(review);

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieReview original = dao.selectReview(articleNo);
		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 영화입니다. 수정 불가!");
		}
		if (original.getArticleNo() == articleNo) {
			dao.updateReview(article);
			dao.closeCon();
		} else {
			dao.closeCon();
			throw new Exception("update 오류!");
		}
	}

	public MovieReviewPage getReviewMovieArticlePage(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		// 현재 요청하는 페이지 파라미터 int로 받아내기
		String pageStr = request.getParameter("page");
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		// 총 게시글의 갯수 조회
		int totalArticleCount = dao.selectReviewCount();// selectArticleCount();

		// 게시글이 없는 경우
		if (totalArticleCount == 0) {
			return new MovieReviewPage();
		}

		// 총 페이지수 계산
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;
		// 현재 페이지에 보여질 글들을 DB에서 조회하기
		List<MovieReview> articleList = dao.selectReviewList(startRow, COUNT_PER_PAGE);

		// 페이지 하단에 링크할 startPage, endPage 계산
		int startPage = requestPage - 5;
		if (startPage < 1) {
			startPage = 1;
		}
		int endPage = requestPage + 5;
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		dao.closeCon(); // closeConnection();
		return new MovieReviewPage(articleList, requestPage, totalPageCount, startPage, endPage);
	}

	// 조회후 수정할때 count 값 올라가지 않을때.
	public MovieReview readWithOutReadCount(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String articleNoStr = request.getParameter("article_no");
		int articleNo = Integer.parseInt(articleNoStr);

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieReview result = dao.selectReview(articleNo);// selectMovie(title);// selectArticle(articleId);

		dao.closeCon();
		return result;
	}
	
	public void deleteReviewMovie(HttpServletRequest request) throws Exception {
		//String sql="delete from MOVIE_TB where MOVIE_TITLE=?";
		
		String articleNoStr = request.getParameter("article_no");
		int articleNo = Integer.parseInt(articleNoStr);
		
		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		MovieReview original = dao.selectReview(articleNo); // selectUser(member_id);
		if (original == null) {
			dao.closeCon();
			throw new Exception("존재하지 않는 ID 수정 불가!");
		}
		
		HttpSession session = request.getSession();
		String temp = String.valueOf(session.getAttribute("user"));

		if(original.getUserId().equals(temp) && original.getArticleNo() == articleNo){
			dao.deleteReview(articleNo); 
			dao.closeCon();
		} else {
			// 비밀번호가 틀린 경우 예외객체 던짐.
			dao.closeCon();
			throw new Exception("reviewMovie 없음!");
		}
	
	}	
	

}
