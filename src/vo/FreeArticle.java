package vo;

public class FreeArticle {
	private int articleNo;
	private String userId;
	private String articleTitle;
	private String content;
	private int readCount;
	private String ymd;
	
	public FreeArticle(){
	}
	public FreeArticle(int articleNo, String userId, String articleTitle, String content, int readCount, String ymd) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.articleTitle = articleTitle;
		this.content = content;
		this.readCount = readCount;
		this.ymd = ymd;
	}
	
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
}
