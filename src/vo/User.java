package vo;

import java.util.Date;

public class User {
	private String userCd;
	private String userId;
	private String pw;
	private String nickname;
	private String email;
	private Date ymd;
	
	public User(){
	}
	public User(String userCd, String userId, String pw, String nickname, String email, Date ymd){
		this.userCd = userCd;
		this.userId = userId;
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.ymd = ymd;
	}
	
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getYmd() {
		return ymd;
	}
	public void setYmd(Date ymd) {
		this.ymd = ymd;
	}
}
