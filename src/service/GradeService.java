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
	
	public static final int COUNT_PER_PAGE = 2; //평점은 두개만 
	
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
		// 현재 요청하는 페이지 파라미터 int로 받아내기
		String pageStr = request.getParameter("page");
		String movieTitle = request.getParameter("movieTitle");
//		System.out.println("1001 에러1 :"+pageStr);
		//pageStr는 movieService와 boardService에서도 null나옴.
//		System.out.println("1001 에러2: "+movieTitle);
		//프린트했더니 null로 나옴...
		
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		ScoreDao dao = ScoreDao.getInstance();
		dao.startCon();	    
		
		// 총 게시글의 갯수 조회
		int totalArticleCount = dao.selectScoreCount();// selectArticleCount();

		// 게시글이 없는 경우
		if (totalArticleCount == 0) {
			return new MovieScorePage();
		}

		// 총 페이지수 계산
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;
		// 현재 페이지에 보여질 글들을 DB에서 조회하기
		List<MovieScore> articleList = dao.selectScoreList(startRow, COUNT_PER_PAGE, movieTitle);

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
		return new MovieScorePage(articleList, requestPage, totalPageCount, startPage, endPage);
	}
	

}
