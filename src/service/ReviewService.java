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
		// // ��ȸ�� ���� ���� ����� 1 �̻��̸� �ش���� �����ϹǷ� select ����
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
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ��ȭ�Դϴ�. ���� �Ұ�!");
		}
		if (original.getArticleNo() == articleNo) {
			dao.updateReview(article);
			dao.closeCon();
		} else {
			dao.closeCon();
			throw new Exception("update ����!");
		}
	}

	public MovieReviewPage getReviewMovieArticlePage(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		// ���� ��û�ϴ� ������ �Ķ���� int�� �޾Ƴ���
		String pageStr = request.getParameter("page");
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		MovieDao dao = MovieDao.getInstance();
		dao.startCon();

		// �� �Խñ��� ���� ��ȸ
		int totalArticleCount = dao.selectReviewCount();// selectArticleCount();

		// �Խñ��� ���� ���
		if (totalArticleCount == 0) {
			return new MovieReviewPage();
		}

		// �� �������� ���
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;
		// ���� �������� ������ �۵��� DB���� ��ȸ�ϱ�
		List<MovieReview> articleList = dao.selectReviewList(startRow, COUNT_PER_PAGE);

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
		return new MovieReviewPage(articleList, requestPage, totalPageCount, startPage, endPage);
	}

	// ��ȸ�� �����Ҷ� count �� �ö��� ������.
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
			throw new Exception("�������� �ʴ� ID ���� �Ұ�!");
		}
		
		HttpSession session = request.getSession();
		String temp = String.valueOf(session.getAttribute("user"));

		if(original.getUserId().equals(temp) && original.getArticleNo() == articleNo){
			dao.deleteReview(articleNo); 
			dao.closeCon();
		} else {
			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
			dao.closeCon();
			throw new Exception("reviewMovie ����!");
		}
	
	}	
	

}
