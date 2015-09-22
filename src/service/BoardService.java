package service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import repository.FreeDao;
import vo.FreeArticle;

public class BoardService {
	
	private BoardService() {
	}

	private static BoardService instance = new BoardService();

	public static BoardService getInstance() {
		return instance;
	}	
	
	public int write(HttpServletRequest request){
		
//		String title = request.getParameter("title");
//		String writerName = request.getParameter("writerName");
//		String password = request.getParameter("password");
//		String content = request.getParameter("content");
		String id = request.getParameter("member_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
//		String count = request.getParameter("count");
		
//		pstmt.setString(1, freeArticle.getUserId());
//		pstmt.setString(2, freeArticle.getArticleTitle());
//		pstmt.setString(3, freeArticle.getContent());
//		pstmt.setInt(4, freeArticle.getReadCount()+1);
//		pstmt.setTimestamp(5,new Timestamp(freeArticle.getYmd().getTime()));
		FreeArticle article = new FreeArticle();
		article.setUserId(id);
		article.setArticleTitle(title);
		article.setContent(content);
		article.setYmd(new Date());
		
		FreeDao dao = FreeDao.getInstance();
		dao.startCon();
		
		int result = dao.insertFree(article);
		dao.closeCon();
		
		return result;
	}
	
	public FreeArticle read(HttpServletRequest request){
		
		String idStr = request.getParameter("member_id");
		int id=0;
		if(idStr !=null && idStr.length()>0)
			id = Integer.parseInt(idStr);
		
		FreeDao dao = FreeDao.getInstance();		
		dao.startCon();
		
		FreeArticle result = null;
		
//		if (dao.updatedReadCount(id) > 0) {
//			// ��ȸ�� ���� ���� ����� 1 �̻��̸� �ش���� �����ϹǷ� select ����
//			result = dao.selectArticle(articleId);
//		}
		result = dao.selectFree(id);

		return result;
	}
	
	public void ArticleUpdate(HttpServletRequest request) throws Exception{
		
		String articleNoStr = request.getParameter("article_num");		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int articleNum = 0;
		if(articleNoStr !=null && articleNoStr.length()>0)
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
		
		dao.updateFree(article);
//		if (article.getPassword() != null && article.getPassword().equals(original.getPassword())) {
//			dao.updateArticle(article); // ��й�ȣ �˻� ����ϸ� �������� db�� ������.
//			dao.closeConnection();
//		} else {
//			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
//			dao.closeConnection();
//			throw new Exception("��й�ȣ ����!");
//		}
	}
	
	public void deleteArticle(HttpServletRequest request) throws Exception{
		
		String idStr = request.getParameter("article_num");
		int articleNum = Integer.parseInt(idStr);		
		
		FreeDao dao = FreeDao.getInstance();
		dao.startCon();
		
		FreeArticle original = dao.selectFree(articleNum);   //article.getArticleId());

		if (original == null) {
			// �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
			dao.closeCon();
			throw new Exception("�������� �ʴ� ���Դϴ�. ���� �Ұ�!");
		}

		if (articleNum  == original.getArticleNo()) {
			dao.deleteFree(articleNum); // articleNum db�� �ִ� �� ������Ŵ.
			dao.closeCon();
		} else {
			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
			dao.closeCon();
			throw new Exception("articleNum ����!");
		}
	}
}
