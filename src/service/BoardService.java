package service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import repository.FreeDao;
import vo.FreeArticle;
import vo.FreeArticlePage;

public class BoardService {

	private BoardService() {
	}

	private static BoardService instance = new BoardService();

	public static BoardService getInstance() {
		return instance;
	}

	public static final int COUNT_PER_PAGE = 10;

	public int write(HttpServletRequest request) {

		// String title = request.getParameter("title");
		// String writerName = request.getParameter("writerName");
		// String password = request.getParameter("password");
		// String content = request.getParameter("content");
		// String id = request.getParameter("member_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// String count = request.getParameter("count");
		//
		// pstmt.setString(1, freeArticle.getUserId());
		// pstmt.setString(2, freeArticle.getArticleTitle());
		// pstmt.setString(3, freeArticle.getContent());
		// pstmt.setInt(4, freeArticle.getReadCount()+1);
		// pstmt.setTimestamp(5,new Timestamp(freeArticle.getYmd().getTime()));
		HttpSession session = request.getSession();
		String temp = String.valueOf(session.getAttribute("user"));

		FreeArticle article = new FreeArticle();
		article.setUserId(temp);
		article.setArticleTitle(title);
		article.setContent(content);
		article.setYmd(new Date());

		System.out.println(temp + title + content);
		System.out.println(new Date());
		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		int result = dao.insertFree(article);
		dao.closeCon();

		return result;
	}

	// 조회 후 count 값 올라 갈때
	public FreeArticle read(HttpServletRequest request) {

		String idStr = request.getParameter("articleNum");
		int id = 0;
		if (idStr != null && idStr.length() > 0)
			id = Integer.parseInt(idStr);

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		FreeArticle result = null;

		// if (dao.updatedReadCount(id) > 0) {
		// // 조회수 증가 실행 결과가 1 이상이면 해당글이 존재하므로 select 실행
		// result = dao.selectArticle(articleId);
		// }
		result = dao.selectFree(id);

		return result;
	}

	public void ArticleUpdate(HttpServletRequest request) throws Exception {

		// String articleNoStr = request.getParameter("article_num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		HttpSession session = request.getSession();
		String articleNoStr = String.valueOf(session.getAttribute("num"));

		// HttpSession session = request.getSession();
		String user = String.valueOf(session.getAttribute("user"));

		// System.out.println(temp);

		int articleNum = 0;
		if (articleNoStr != null && articleNoStr.length() > 0)
			articleNum = Integer.parseInt(articleNoStr);

		FreeArticle article = new FreeArticle();
		article.setArticleNo(articleNum);
		article.setArticleTitle(title);
		article.setContent(content);

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		FreeArticle original = dao.selectFree(articleNum);
		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 글입니다. 수정 불가!");
		}
		if (original.getUserId().equals(user)) {
			dao.updateFree(article);
			dao.closeCon();
		} else {
			dao.closeCon();
			throw new Exception("ID 오류!");
		}
		// if (article.getPassword() != null &&
		// article.getPassword().equals(original.getPassword())) {
		// dao.updateArticle(article); // 비밀번호 검사 통과하면 수정사항 db에 저장함.
		// dao.closeConnection();
		// } else {
		// // 비밀번호가 틀린 경우 예외객체 던짐.
		// dao.closeConnection();
		// throw new Exception("비밀번호 오류!");
		// }
	}

	public void deleteArticle(HttpServletRequest request) throws Exception {		
		String idStr = request.getParameter("article_num");
		System.out.println(idStr);
		int articleNum = Integer.parseInt(idStr);		
		
		HttpSession session = request.getSession();
		// String articleNum = String.valueOf(session.getAttribute("num"));
		// int artnum =Integer.parseInt(articleNum);
		// HttpSession session = request.getSession();
		String user = String.valueOf(session.getAttribute("user"));

		System.out.println(articleNum);
		System.out.println(user);

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		FreeArticle original = dao.selectFree(articleNum); // article.getArticleId());

		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 글입니다. 수정 불가!");
		}

		if (articleNum == original.getArticleNo() && original.getUserId().equals(user)) {
			dao.deleteFree(articleNum); // articleNum db에 있는 거 삭제시킴.
			dao.closeCon();
		} else {
			// 비밀번호가 틀린 경우 예외객체 던짐.
			dao.closeCon();
			throw new Exception("articleNum 없음!");
		}
	}

	// 조회후 수정할때 count 값 올라가지 않을때.
	public FreeArticle readWithOutReadCount(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String idStr = request.getParameter("articleNum");
		int articleId = Integer.parseInt(idStr);

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		FreeArticle result = dao.selectFree(articleId);// selectArticle(articleId);

		dao.closeCon();
		return result;
	}

	// 자유게시판 페이지 만큼 읽어 올때
	public FreeArticlePage getArticlePage(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// 현재 요청하는 페이지 파라미터 int로 받아내기
		String pageStr = request.getParameter("page");
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		// 총 게시글의 갯수 조회
		int totalArticleCount = dao.selectFreeCount(); // dao.selectArticleCount();

		// 게시글이 없는 경우
		if (totalArticleCount == 0) {
			return new FreeArticlePage();
		}

		// 총 페이지수 계산
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;

		// 현재 페이지에 보여질 글들을 DB에서 조회하기
		List<FreeArticle> articleList = dao.selectFreeList(startRow, COUNT_PER_PAGE); // dao.selectArticleList(startRow,
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
		return new FreeArticlePage(articleList, requestPage, totalPageCount, startPage, endPage);
	}
}
