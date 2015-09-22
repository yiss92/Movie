package service;

public class GradeService {
	
	private GradeService() {
	}

	private static GradeService instance = new GradeService();

	public static GradeService getInstance() {
		return instance;
	}
	

}
