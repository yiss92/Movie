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
import vo.MovieArticle;
import vo.MovieArticlePage;
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
		//HttpSession session = request.getSession(); ///

		try {

			if (type == null || type.equals("nowMovie")) {
				MovieArticlePage movieArticlePage = service.getArticlePage(request);
				request.setAttribute("movieArticlePage", movieArticlePage);
				viewpath = "NowMovie.jsp";
				

			} else if (type.equals("nowMovieRead")) {
				//현재상영작에서 영화누르면 영화정보 나오게
				//아직 페이지없어서 테스트 안해봄.
				MovieArticle now =service.MovieArticleread(request);
				request.setAttribute("now", now);
				viewpath = "";
		
			}  else if (type.equals("soonMovie")) {
				MovieArticlePage movieArticlePage = service.ExpectedPrgetArticlePage(request);
				request.setAttribute("movieArticlePage", movieArticlePage);
				viewpath = "SoonMovie.jsp";
			
			} else if (type.equals("soonMovieRead")) {
				//상영예정에서 영화누르면 영화정보 나오게
				//아직 페이지없어서 테스트 안해봄.
				MovieArticle now =service.MovieArticleread(request);
				request.setAttribute("now", now);
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
