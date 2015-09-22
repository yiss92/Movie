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
		// USER_CD, USER_ID, PW, NICKNAME, EMAIL, YMD
		// pstmt.setString(1, "user");
		// pstmt.setString(2, user.getUserId());
		// pstmt.setString(3, user.getPw());
		// pstmt.setString(4, user.getNickname());
		// pstmt.setString(5, user.getEmail());
		// pstmt.setTimestamp(6,new Timestamp(user.getYmd().getTime()));

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

		UserDao Dao = UserDao.getInstance();
		Dao.startCon();

		int result = Dao.insertUser(user);
		Dao.closeCon();
		// MemberDao dao = MemberDao.getInstance();
		// dao.startConnection();
		//
		// int result = dao.insertMember(member);
		// dao.closeConnection();

		return result;
	}

	// 수정사항 저장 요청시 비밀번호 검사 후 수정 진행하는 메소드
	public void updateMember(HttpServletRequest request) throws Exception {
		// System.out.println("modifyArticle");
		// request.setCharacterEncoding("euc-kr");

		// System.out.println(request.getParameter("articleId"));
		//
		// String idStr = request.getParameter("member_id");
		// int member_id = Integer.parseInt(idStr);
		// String password = request.getParameter("password");
		String member_id = request.getParameter("member_id");
		String member_nic = request.getParameter("member_nicname");
		String memeber_email = request.getParameter("email");

		User user = new User();
		user.setUserId(member_id);
		user.setNickname(member_nic);
		user.setEmail(memeber_email);

		UserDao Dao = UserDao.getInstance();
		Dao.startCon();

		User original = Dao.selectUser(user.getUserId());
		if (original == null) {
			Dao.closeCon();
		}

		Dao.updateUser(user);

		// if (user.getPw() != null) {
		// userDao.updateUser(user); // 비밀번호 검사 통과하면 수정사항 db에 저장함.
		// userDao.closeCon();
		// } else {
		// // 비밀번호가 틀린 경우 예외객체 던짐.
		// userDao.closeCon();
		// throw new Exception("비밀번호 오류!");
		// }

		// Member member = new Member();
		// member.setPassword(password);
		// article은 수정희망자가 입력한 내용이 들어있고
		// original은 원본글을 db에서 꺼낸 내용이 들어있음.
		// MemberDao dao = MemberDao.getInstance();
		// dao.startConnection();
		//
		// Member original = dao.selectMember(member.getMember_Id());
		//
		// if (original == null) {
		// // 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
		// dao.closeConnection();
		// throw new Exception("존재하지 ID입니다. 수정불가!");
		// }
		//
		// if (member.getPassword() != null) {
		// dao.updateMember(member); // 비밀번호 검사 통과하면 수정사항 db에 저장함.
		// dao.closeConnection();
		// } else {
		// // 비밀번호가 틀린 경우 예외객체 던짐.
		// dao.closeConnection();
		// throw new Exception("비밀번호 오류!");
		// }
		//
	}

	public void deleteMember(HttpServletRequest request) throws Exception {
		// System.out.println("deleteArticle");
		// String idStr = request.getParameter("member_id");
		String member_id = request.getParameter("member_id");
		// int member_id = Integer.parseInt(idStr);
		// //System.out.println(articleId);
		UserDao dao = UserDao.getInstance();
		dao.startCon();

		User original = dao.selectUser(member_id);
		if (original == null) {
			dao.closeCon();
			throw new Exception("존재하지 않는 ID 수정 불가!");
		}

		if (member_id == original.getUserId()) {
			dao.deleteUser(member_id); // articleId db에 있는 거 삭제시킴.
			dao.closeCon();;
		} else {
			// 비밀번호가 틀린 경우 예외객체 던짐.
			dao.closeCon();
			throw new Exception("ID 없음!");
		}

		// MemberDao dao = MemberDao.getInstance();
		// dao.startConnection();
		//
		// Member original = dao.selectMember(member_id);
		// //article.getArticleId());
		//
		// if (original == null) {
		// // 수정하고자 하는 글번호가 존재하지 않는 글번호인 경우
		// dao.closeConnection();
		// throw new Exception("존재하지 않는 글입니다. 수정 불가!");
		// }
		//
		// if (member_id == original.getMember_Id()) {
		// dao.deleteMember(member_id); // articleId db에 있는 거 삭제시킴.
		// dao.closeConnection();
		// } else {
		// // 비밀번호가 틀린 경우 예외객체 던짐.
		// dao.closeConnection();
		// throw new Exception("articleId 없음!");
		// }
	}
}
