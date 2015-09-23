package service;


public class MovieArticleService {
	
	private MovieArticleService() {
	}

	private static MovieArticleService instance = new MovieArticleService();

	public static MovieArticleService getInstance() {
		return instance;
	}
	
//	public int write(HttpServletRequest request) {
//
//		pstmt=con.prepareStatement(sql);
//		pstmt.setInt(1, comment.getArticleNo());
//		pstmt.setInt(2, comment.getComment_reply_no());
//		pstmt.setString(3, comment.getUserId());
//		pstmt.setString(4, comment.getComment());
//		pstmt.setTimestamp(5,new Timestamp(comment.getYmd().getTime()));
		
//		int artcleNum = request.getParameter(arg0) 
//		
//		HttpSession session = request.getSession();
//		String temp = String.valueOf(session.getAttribute("user"));
//		
//		FreeArticle article = new FreeArticle();
//		article.setUserId(temp);
//		article.setArticleTitle(title);
//		article.setContent(content);
//		article.setYmd(new Date());
//
//		FreeDao dao = FreeDao.getInstance();
//		dao.startCon();
////
//		int result = dao.insertFree(article);
////		dao.closeCon();
////
//		return result;
//	}
	

}
