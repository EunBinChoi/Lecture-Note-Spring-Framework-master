package me.spring.message.beans;

public class Common {
	private String home;
	private String board;
	private String album;
	private String FAQ;
	private String heading;
	
	public Common() {}

	public Common(String home, String board, String album, String FAQ, String heading) {
		this.home = home;
		this.board = board;
		this.album = album;
		this.FAQ = FAQ;
		this.heading = heading;
	}
	

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getFAQ() {
		return FAQ;
	}

	public void setFAQ(String fAQ) {
		FAQ = fAQ;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	@Override
	public String toString() {
		return "Common [home=" + home + ", board=" + board + ", album=" + album + ", FAQ=" + FAQ + ", heading="
				+ heading + "]";
	}
	
	
	
}
