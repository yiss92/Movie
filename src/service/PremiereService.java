package service;

public class PremiereService {
	
	private PremiereService() {
	}

	private static PremiereService instance = new PremiereService();

	public static PremiereService getInstance() {
		return instance;
	}

}
