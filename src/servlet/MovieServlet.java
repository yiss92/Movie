package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.GradeService;
import service.MemberService;
import service.MovieArticleService;
import vo.MovieArticle;
import vo.MovieArticlePage;
import vo.MovieScorePage;
import vo.User;

@WebServlet(urlPatterns="/Mmovie")
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
		GradeService gSerice = GradeService.getInstance();
		//HttpSession session = request.getSession(); ///

		try {

			if (type == null || type.equals("nowMovie")) {
				MovieArticlePage movieArticlePage = service.getArticlePage(request);
				request.setAttribute("movieArticlePage", movieArticlePage);
				viewpath = "NowMovie.jsp";
				

			} else if (type.equals("nowMovieRead")) {
				//데이터가 안나옴.
				MovieArticle now =service.MovieArticleread(request);
				System.out.println("1"+request);
				request.setAttribute("now",now);
//				MovieScorePage comment = gSerice.getCommentPageArticlePage(request);
//				request.setAttribute("comment", comment);
				viewpath = "NowMovieRead.jsp";
		
			}  else if (type.equals("soonMovie")) {
				MovieArticlePage movieArticlePage = service.ExpectedPrgetArticlePage(request);
				request.setAttribute("movieArticlePage", movieArticlePage);
				viewpath = "SoonMovie.jsp";
		
				
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
