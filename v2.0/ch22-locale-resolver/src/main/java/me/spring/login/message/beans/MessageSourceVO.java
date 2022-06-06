package me.spring.login.message.beans;

public class MessageSourceVO {
	private String home;
	private String board;
	private String album;
	private String FAQ;
	private String heading;
	private String loginMessage;
	private String reviseMember;
	private String deleteMember;
	private String logout;
	
	
	public MessageSourceVO() {}

	public MessageSourceVO(String home, String board, String album, String FAQ, String heading, String loginMessage,
			String reviseMember, String deleteMember, String logout) {
		this.home = home;
		this.board = board;
		this.album = album;
		this.FAQ = FAQ;
		this.heading = heading;
		this.loginMessage = loginMessage;
		this.reviseMember = reviseMember;
		this.deleteMember = deleteMember;
		this.logout = logout;
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

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public String getReviseMember() {
		return reviseMember;
	}

	public void setReviseMember(String reviseMember) {
		this.reviseMember = reviseMember;
	}

	public String getDeleteMember() {
		return deleteMember;
	}

	public void setDeleteMember(String deleteMember) {
		this.deleteMember = deleteMember;
	}

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	@Override
	public String toString() {
		return "MessageSourceVO [home=" + home + ", board=" + board + ", album=" + album + ", FAQ=" + FAQ + ", heading="
				+ heading + ", loginMessage=" + loginMessage + ", reviseMember=" + reviseMember + ", deleteMember="
				+ deleteMember + ", logout=" + logout + "]";
	}
	
	
	
}
