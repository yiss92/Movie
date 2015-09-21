package vo;

public class Comment {
	private int commentNo;
	private int articleNo;
	private int comment_reply_no;
	private String userId;
	private String comment;
	private String ymd;
	
	public Comment(){
	}
	public Comment(int commentNo, int articleNo, int comment_reply_no, String userId, String comment, String ymd) {
		this.commentNo = commentNo;
		this.articleNo = articleNo;
		this.comment_reply_no = comment_reply_no;
		this.userId = userId;
		this.comment = comment;
		this.ymd = ymd;
	}
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public int getComment_reply_no() {
		return comment_reply_no;
	}
	public void setComment_reply_no(int comment_reply_no) {
		this.comment_reply_no = comment_reply_no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
}
