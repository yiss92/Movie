package service;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import repository.UserDao;
import vo.User;

public class MemberService {
	// singleton
	private MemberService() {
	}

	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	public int registeMember(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		 //USER_CD, USER_ID, PW, NICKNAME, EMAIL, YMD
//		pstmt.setString(1, "user");
//		pstmt.setString(2, user.getUserId());
//		pstmt.setString(3, user.getPw());
//		pstmt.setString(4, user.getNickname());
//		pstmt.setString(5, user.getEmail());
//		pstmt.setTimestamp(6,new Timestamp(user.getYmd().getTime()));
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		String member_nic = request.getParameter("nickname");
		String member_email = request.getParameter("email");
		
		User user = new User();
		user.setUserCd(member_id);
		user.setPw(member_pw);
		user.setNickname(member_nic);
		user.setEmail(member_email);
		user.setYmd(new Date());
		
		UserDao userDao = UserDao.getInstance();
		userDao.startCon();
		
		int result = userDao.insertUser(user);
		userDao.closeCon();
//		MemberDao dao = MemberDao.getInstance();
//		dao.startConnection();
//		
//		int result = dao.insertMember(member);
//		dao.closeConnection();
		return result;	
	}
}
