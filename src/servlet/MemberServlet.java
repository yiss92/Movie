package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repository.UserDao;
import service.MemberService;
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

		try {

			if (type == null || type.equals("loginForm")) {
				// viewpath="FrontPage1.html";
				viewpath = "loginForm.html";
				
		     } else if (type.equals("login")) {
                 User result= service.memberSelect(request);
                 request.setAttribute("result", result);
                 HttpSession session = request.getSession(); ///
                 session.setAttribute("user", result.getUserId());
                  viewpath = "login.html";

			} else if (type.equals("joinForm")) {
				viewpath = "joinForm.html";

			} else if (type.equals("join")) {
				int result = service.registeMember(request);
				request.setAttribute("result", result);
				viewpath = "join.jsp";

			} else if (type.equals("myPage")) {
				viewpath = "myPage.html";	
				
			} else if (type.equals("updateForm")) {
				viewpath = "UpdateForm.html";
				
			} else if (type.equals("update")) {
				service.updateMember(request);
				viewpath = "Update.html";

			} else if (type.equals("deleteForm")) {
				viewpath = "DeleteForm.html";	
				
			} else if (type.equals("delete")) {
				service.deleteMember(request);
				viewpath = "Delete.html";

			}

		} catch (Exception e) {
			request.setAttribute("exception", e);
			viewpath = "error.html";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
		dispatcher.forward(request, response);
	}
}
