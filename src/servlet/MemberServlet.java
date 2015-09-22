package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				String member_id = request.getParameter("member_id");

				
				
				String member_pw = request.getParameter("member_pw");
				
				User user = new User();
				
				user.setUserCd(member_id);
				user.setPw(member_pw);
				
				repository.UserDao Dao = UserDao.getInstance();
				Dao.startCon();
				
				User ori = Dao.selectUser(user.getUserId());
				
				if((user.getUserId() != null && user.getUserId().equals(ori.getUserId()))
					&&(user.getPw() != null && user.getPw().equals(ori.getPw()))){
						viewpath = "login.html";
					}else{
						viewpath = "loginError.html";
					}

			} else if (type.equals("joinForm")) {
				viewpath = "joinForm.html";

			} else if (type.equals("join")) {
				int result = service.registeMember(request);
				request.setAttribute("result", result);
				viewpath = "join.jsp";

			} else if (type.equals("MyPage")) {
				viewpath = "MyPage.html";

			} else if (type.equals("MyPageUpdate")) {
				viewpath = "MyPageUpdate.html";

			} else if (type.equals("MyPageDelete")) {
				viewpath = "MyPageDelete.html";

			}

		} catch (Exception e) {
			request.setAttribute("exception", e);
			viewpath = "error.html";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
		dispatcher.forward(request, response);
	}
}
