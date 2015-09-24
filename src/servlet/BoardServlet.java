package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import service.BoardService;
import service.MemberService;
import vo.FreeArticle;
import vo.FreeArticlePage;
import vo.User;

@WebServlet(urlPatterns = "/board")
public class BoardServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	};

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");

		String type = request.getParameter("type");
		String viewpath = "";

		BoardService service = BoardService.getInstance();
		HttpSession session = request.getSession(); 
		
		try {
			if (type == null || type.equals("list")) {
				// 목록보기 
				FreeArticlePage freeArticlePage = service.getArticlePage(request);
				request.setAttribute("freeArticlePage", freeArticlePage);
				viewpath="list.jsp";
				
			}else if(type.equals("write_form")){
				//글 쓰기 폼
				viewpath = "write_form.jsp";
			
			}else if(type.equals("write")){
				//글 쓰기 
				int result = service.write(request);
				request.setAttribute("result", result);
				viewpath = "write.jsp";
				
			}else if(type.equals("read")){
				//글 읽기
				FreeArticle free=service.read(request);
				request.setAttribute("free", free);
				viewpath = "read.jsp";
				
			}else if(type.equals("update_form")){
				//글 수정 폼
				FreeArticle ori= service.readWithOutReadCount(request);
				session.setAttribute("num", ori.getArticleNo());
				request.setAttribute("ori", ori);
				viewpath = "update_form.jsp";
				
			}else if(type.equals("update")){
				//글 수정
				service.ArticleUpdate(request);
				viewpath = "update.jsp";
				
			}else if(type.equals("delete_form")){
				//글 삭제 폼
				FreeArticle ori= service.readWithOutReadCount(request);
				request.setAttribute("ori", ori);
				viewpath = "delete_form.jsp";
			
			}else if(type.equals("delete")){
				//글 삭제
				System.out.println("delete");				
				service.deleteArticle(request);				
				viewpath = "delete.jsp";
			}
			

		} catch (Exception e) {
			viewpath = "board_error.jsp";
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
		dispatcher.forward(request, response);
	}
}