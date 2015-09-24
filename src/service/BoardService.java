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

	// ��ȸ �� count �� �ö� ����
	public FreeArticle read(HttpServletRequest request) {

		String idStr = request.getParameter("articleNum");
		int id = 0;
		if (idStr != null && idStr.length() > 0)
			id = Integer.parseInt(idStr);

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		FreeArticle result = null;

		// if (dao.updatedReadCount(id) > 0) {
		// // ��ȸ�� ���� ���� ����� 1 �̻��̸� �ش���� �����ϹǷ� select ����
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
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ���Դϴ�. ���� �Ұ�!");
		}
		if (original.getUserId().equals(user)) {
			dao.updateFree(article);
			dao.closeCon();
		} else {
			dao.closeCon();
			throw new Exception("ID ����!");
		}
		// if (article.getPassword() != null &&
		// article.getPassword().equals(original.getPassword())) {
		// dao.updateArticle(article); // ��й�ȣ �˻� ����ϸ� �������� db�� ������.
		// dao.closeConnection();
		// } else {
		// // ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
		// dao.closeConnection();
		// throw new Exception("��й�ȣ ����!");
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
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ���Դϴ�. ���� �Ұ�!");
		}

		if (articleNum == original.getArticleNo() && original.getUserId().equals(user)) {
			dao.deleteFree(articleNum); // articleNum db�� �ִ� �� ������Ŵ.
			dao.closeCon();
		} else {
			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
			dao.closeCon();
			throw new Exception("articleNum ����!");
		}
	}

	// ��ȸ�� �����Ҷ� count �� �ö��� ������.
	public FreeArticle readWithOutReadCount(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String idStr = request.getParameter("articleNum");
		int articleId = Integer.parseInt(idStr);

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		FreeArticle result = dao.selectFree(articleId);// selectArticle(articleId);

		dao.closeCon();
		return result;
	}

	// �����Խ��� ������ ��ŭ �о� �ö�
	public FreeArticlePage getArticlePage(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// ���� ��û�ϴ� ������ �Ķ���� int�� �޾Ƴ���
		String pageStr = request.getParameter("page");
		int requestPage = 1;
		if (pageStr != null && pageStr.length() > 0) {
			requestPage = Integer.parseInt(pageStr);
		}

		FreeDao dao = FreeDao.getInstance();
		dao.startCon();

		// �� �Խñ��� ���� ��ȸ
		int totalArticleCount = dao.selectFreeCount(); // dao.selectArticleCount();

		// �Խñ��� ���� ���
		if (totalArticleCount == 0) {
			return new FreeArticlePage();
		}

		// �� �������� ���
		int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE != 0) {
			totalPageCount++;
		}

		int startRow = (requestPage - 1) * COUNT_PER_PAGE;

		// ���� �������� ������ �۵��� DB���� ��ȸ�ϱ�
		List<FreeArticle> articleList = dao.selectFreeList(startRow, COUNT_PER_PAGE); // dao.selectArticleList(startRow,
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
		return new FreeArticlePage(articleList, requestPage, totalPageCount, startPage, endPage);
	}
}
