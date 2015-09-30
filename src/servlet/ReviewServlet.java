package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReviewService;
import vo.MovieReview;
import vo.MovieReviewPage;

@WebServlet(urlPatterns = "/review")
public class ReviewServlet extends HttpServlet {
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

		String type = request.getParameter("type");
		String viewpath = "";

		ReviewService service = ReviewService.getInstance();
		HttpSession session = request.getSession();
		//Å×½ºÆ®X
		try {
			if (type == null || type.equals("reviewList")) {
				MovieReviewPage movieReviewPage = service.getReviewMovieArticlePage(request);
				request.setAttribute("movieReviewPage", movieReviewPage);
				viewpath = "reviewList.jsp";

			} else if (type.equals("reviewWrite_form")) {
				 viewpath = "reviewWriteForm.jsp";

			} else if (type.equals("reviewWrite")) {
				int result= service.ReviewMovieWrite(request);
				request.setAttribute("result", result);
				viewpath="reviewWrite.jsp";
				// int result = service.write( request);
				// request.setAttribute( "result", result);
				// viewpath = "write.jsp";

			} else if (type.equals("reviewRead")) {
				MovieReview movie=service.ReviewMovieArticleread(request);
				request.setAttribute("movie",movie);
				viewpath = "reviewRead.jsp";
				// FreeArticle free= service.read( request);
				// request.setAttribute( "free", free);
				// viewpath = "read.jsp";

			} else if (type.equals("reviewUpdate_form")) {
				MovieReview ori=service.readWithOutReadCount(request);
				session.setAttribute("num", ori.getArticleNo());
				request.setAttribute("ori", ori);
				viewpath="reviewUpdate_form.jsp";
				// FreeArticle ori= service.readWithOutReadCount( request);
				// session.setAttribute( "num", ori.getArticleNo());
				// request.setAttribute( "ori", ori);
				// viewpath = "update_form.jsp";

			} else if (type.equals("reviewUpdate")) {
				service.ReviewMovieArticleUpdate(request);
				viewpath="reviewUpdate.jsp";
				// service.ArticleUpdate( request);
				// viewpath = "correctionPage.jsp";

			} else if (type.equals("reviewDelete_form")) {
				MovieReview ori=service.readWithOutReadCount(request);
				request.setAttribute("ori", ori);
				viewpath="reviewDelete_form.jsp";
				// FreeArticle ori= service.readWithOutReadCount( request);
				// request.setAttribute( "ori", ori);
				// viewpath = "delete_form.jsp";

			} else if (type.equals("reviewDelete")) {
				service.deleteReviewMovie(request);
				viewpath="reviewDelete.jsp";
				// System. out.println( "delete");
				// service.deleteArticle( request);
				// viewpath = "delete.jsp";
			}

		} catch (Exception e) {
			viewpath = "review_error.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
		dispatcher.forward(request, response);
	}
}
