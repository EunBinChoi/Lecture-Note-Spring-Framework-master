package ex01;

// 게시물 자바 빈즈
public class BoardBean {
	private int num; 		 // 게시번호 저장
	private String name; 	 // 게시물 작성자 이름 저장
	private String subject;  // 게시물 제목 저장
	private String content;  // 게시물 본문 내용 저장
	private int pos;  		 // 게시물의 상대적인 위치 저장 (화면에 순서대로 뿌려주기 위함)          
	private int depth;  	 // 답변글일 경우 답변의 깊이를 저장       
	private int ref;    	 // 답변 글일 경우 소속된 부모 글을 가리키는 번호 저장    
	private String regdate;  // 게시물이 작상된 날짜 저장   
	private String pass;     // 게시물 작성자 패스워드 저장     
	private String  ip;		 // 게시물 작성자의 IP 주소 저장
	private int count;       // 게시물의 조회수 저장 
	private String filename; // 게시물에 업로드된 파일 이름 저장
	private int filesize;    // 게시물에 업로드된 파일 크기 저장
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}  
}
