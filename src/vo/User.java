package vo;

public class User {
	private String userCd;
	private String userId;
	private String pw;
	private String nickname;
	private String email;
	private String ymd;
	
	public User(){
	}
	public User(String userCd, String userId, String pw, String nickname, String email, String ymd){
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
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
}
