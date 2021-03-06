package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.UserDao;
import service.MemberService;
import service.MovieArticleService;
import vo.MovieArticle;
import vo.MovieArticlePage;
import vo.User;

@WebServlet(urlPatterns = "/movie")
public class MemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");

		String type = request.getParameter("type");
		String viewpath = "";

		MemberService service = MemberService.getInstance();
		MovieArticleService MovieSer = MovieArticleService.getInstance();
		HttpSession session = request.getSession(); ///

		try {

			if (type == null || type.equals("loginForm")) {
				List<MovieArticle> movie =MovieSer.getArticlePage5(request);				
				request.setAttribute("movie", movie);
				viewpath = "Main.jsp";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("login")) {
				List<MovieArticle> movie =MovieSer.getArticlePage5(request);				
				request.setAttribute("movie", movie);
				
				User result = service.memberSelect(request);
				request.setAttribute("result", result);

				session.setAttribute("user", result.getUserId());///
				viewpath = "Main2.jsp";
//////////////////////////////////////////////////////////////////////////////////////				
			} else if (type.equals("login-1")) {
				List<MovieArticle> movie =MovieSer.getArticlePage5(request);				
				request.setAttribute("movie", movie);
				
				viewpath = "Main2.jsp";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("joinForm")) {
				viewpath = "joinForm.html";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("join")) {
				int result = service.registeMember(request);
				request.setAttribute("result", result);
				viewpath = "join.jsp";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("myPage")) {
				viewpath = "MyPage.html";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("updateForm")) {
				viewpath = "UpdateForm.html";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("update")) {
				service.updateMember(request);
				viewpath = "Update.html";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("deleteForm")) {			
				viewpath = "deleteFormComplete.html";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("delete")) {
				service.deleteMember(request);
				session.invalidate();//
				viewpath = "withdrawMember.html";
//////////////////////////////////////////////////////////////////////////////////////
			} else if (type.equals("logout")) {
				session.invalidate();//
				viewpath = "Logout.html";

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			viewpath = "ErrorPage.html";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
		dispatcher.forward(request, response);
	}
}
