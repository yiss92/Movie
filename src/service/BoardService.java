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
//			// 조회수 증가 실행 결과가 1 이상이면 해당글이 존재하므로 select 실행
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
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 글입니다. 수정 불가!");
		}
		
		dao.updateFree(article);
//		if (article.getPassword() != null && article.getPassword().equals(original.getPassword())) {
//			dao.updateArticle(article); // 비밀번호 검사 통과하면 수정사항 db에 저장함.
//			dao.closeConnection();
//		} else {
//			// 비밀번호가 틀린 경우 예외객체 던짐.
//			dao.closeConnection();
//			throw new Exception("비밀번호 오류!");
//		}
	}
	
	public void deleteArticle(HttpServletRequest request) throws Exception{
		
		String idStr = request.getParameter("article_num");
		int articleNum = Integer.parseInt(idStr);		
		
		FreeDao dao = FreeDao.getInstance();
		dao.startCon();
		
		FreeArticle original = dao.selectFree(articleNum);   //article.getArticleId());

		if (original == null) {
			// 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
			dao.closeCon();
			throw new Exception("존재하지 않는 글입니다. 수정 불가!");
		}

		if (articleNum  == original.getArticleNo()) {
			dao.deleteFree(articleNum); // articleNum db에 있는 거 삭제시킴.
			dao.closeCon();
		} else {
			// 비밀번호가 틀린 경우 예외객체 던짐.
			dao.closeCon();
			throw new Exception("articleNum 없음!");
		}
	}
}
