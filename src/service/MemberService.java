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

	// �������� ���� ��û�� ��й�ȣ �˻� �� ���� �����ϴ� �޼ҵ�
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
		// userDao.updateUser(user); // ��й�ȣ �˻� ����ϸ� �������� db�� ������.
		// userDao.closeCon();
		// } else {
		// // ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
		// userDao.closeCon();
		// throw new Exception("��й�ȣ ����!");
		// }

		// Member member = new Member();
		// member.setPassword(password);
		// article�� ��������ڰ� �Է��� ������ ����ְ�
		// original�� �������� db���� ���� ������ �������.
		// MemberDao dao = MemberDao.getInstance();
		// dao.startConnection();
		//
		// Member original = dao.selectMember(member.getMember_Id());
		//
		// if (original == null) {
		// // �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
		// dao.closeConnection();
		// throw new Exception("�������� ID�Դϴ�. �����Ұ�!");
		// }
		//
		// if (member.getPassword() != null) {
		// dao.updateMember(member); // ��й�ȣ �˻� ����ϸ� �������� db�� ������.
		// dao.closeConnection();
		// } else {
		// // ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
		// dao.closeConnection();
		// throw new Exception("��й�ȣ ����!");
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
			throw new Exception("�������� �ʴ� ID ���� �Ұ�!");
		}

		if (member_id == original.getUserId()) {
			dao.deleteUser(member_id); // articleId db�� �ִ� �� ������Ŵ.
			dao.closeCon();;
		} else {
			// ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
			dao.closeCon();
			throw new Exception("ID ����!");
		}

		// MemberDao dao = MemberDao.getInstance();
		// dao.startConnection();
		//
		// Member original = dao.selectMember(member_id);
		// //article.getArticleId());
		//
		// if (original == null) {
		// // �����ϰ��� �ϴ� �۹�ȣ�� �������� �ʴ� �۹�ȣ�� ���
		// dao.closeConnection();
		// throw new Exception("�������� �ʴ� ���Դϴ�. ���� �Ұ�!");
		// }
		//
		// if (member_id == original.getMember_Id()) {
		// dao.deleteMember(member_id); // articleId db�� �ִ� �� ������Ŵ.
		// dao.closeConnection();
		// } else {
		// // ��й�ȣ�� Ʋ�� ��� ���ܰ�ü ����.
		// dao.closeConnection();
		// throw new Exception("articleId ����!");
		// }
	}
}
