package service;

public class ReviewService {
	
	private ReviewService() {
	}

	private static ReviewService instance = new ReviewService();

	public static ReviewService getInstance() {
		return instance;
	}

}
