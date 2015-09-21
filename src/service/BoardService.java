package service;

public class BoardService {
	
	private BoardService() {
	}

	private static BoardService instance = new BoardService();

	public static BoardService getInstance() {
		return instance;
	}	

}
