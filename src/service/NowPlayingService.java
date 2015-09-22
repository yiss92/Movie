package service;

public class NowPlayingService {
	
	private NowPlayingService() {
	}

	private static NowPlayingService instance = new NowPlayingService();

	public static NowPlayingService getInstance() {
		return instance;
	}

}
