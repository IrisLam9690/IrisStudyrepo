package niuzhixiang.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "FileBean")
@Table(name = "file")
@XmlRootElement
public class FileBean {
	
	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FileBean(int id, String path, int userid) {
		super();
		this.id = id;
		this.path = path;
		this.userid = userid;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "userid")
	private int userid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
