package niuzhixiang.bean.mongo;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@XmlRootElement
public class LogonSession {

	private String sessionid;
	private int userid;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public LogonSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogonSession(String sessionid, int userid) {
		super();
		this.sessionid = sessionid;
		this.userid = userid;
	}
	
}
