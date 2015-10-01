package service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import repository.CommentDao;
import repository.ScoreDao;
import vo.Comment;
import vo.MovieScore;
import vo.MovieScorePage;

public class GradeService {
	
	private GradeService() {
	}

	private static GradeService instance = new GradeService();

	public static GradeService getInstance() {
		return instance;
	}
	
	public static final int COUNT_PER_PAGE = 2; //������ �ΰ��� 
	
	public int insertGrade(HttpServletRequest request){

		String articleNoStr = request.getParameter("articleNo");		
		String replyNoStr = request.getParameter("replyNo");
		String articleIdStr = request.getParameter("usrid");
		String dbName = request.getParameter("dbname");
		//int articleId = Integer.parseInt(articleIdStr);
		int articleNo = Integer.parseInt(articleNoStr);
		int replyNo = Integer.parseInt(replyNoStr);
		String comment = request.getParameter("comment");
		
		CommentDao dao = CommentDao.getInstance();
		dao.startCon();
		
		Comment cmt = new Comment();
		cmt.setArticleNo(articleNo);
		cmt.setUserId(articleIdStr);
		cmt.setComment_reply_no(replyNo);
		cmt.setComment(comment);
		cmt.setYmd(new Date());
		
		int result = dao.insertComment(cmt, dbName);
		dao.closeCon();

		return result;		
	}
	
	public MovieScorePage getCommentPageArticlePage(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {
		// ���� ��û�ϴ� ������ �Ķ���� int�� �޾Ƴ���
		String pageStr = request.getParameter("page");
		String movieTitle = request.getParameter("movieTitle");
//		System.out.println("1001 ����1 :"+pageStr);
		//pageStr�� movieService�� boardService������ null����.
//		System.out.println("1001 ����2: "+movieTitle);
		//����Ʈ�ߴ��� null�� ����...
		
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		ScoreDao dao = ScoreDao.getInstance();
		dao.startCon();	    
		
		// �� �Խñ��� ���� ��ȸ
		int totalArticleCount = dao.selectScoreCount();// selectArticleCount();

		// �Խñ��� ���� ���
		if (totalArticleCount == 0) {
			return new MovieScorePage();
		}

		// �� �������� ���
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;
		// ���� �������� ������ �۵��� DB���� ��ȸ�ϱ�
		List<MovieScore> articleList = dao.selectScoreList(startRow, COUNT_PER_PAGE, movieTitle);

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
		return new MovieScorePage(articleList, requestPage, totalPageCount, startPage, endPage);
	}
	

}
