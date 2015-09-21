package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class MemberService {
	// singleton
	private MemberService() {
	}

	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	public int registeMember(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		int member_id = Integer.parseInt(request.getParameter("member_id"));

		return 0;
	}

}
