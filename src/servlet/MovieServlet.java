package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.MovieArticleService;
import vo.User;

@WebServlet(urlPatterns="/movie")
public class MovieServlet extends HttpServlet{
	
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

		MovieArticleService service =MovieArticleService.getInstance();
		//HttpSession session = request.getSession(); ///

		try {

			if (type == null || type.equals("")) {
				viewpath = "";

			} else if (type.equals("")) {

				viewpath = "";
				
			

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			viewpath = "movie_error.html";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
		dispatcher.forward(request, response);
	}
}
