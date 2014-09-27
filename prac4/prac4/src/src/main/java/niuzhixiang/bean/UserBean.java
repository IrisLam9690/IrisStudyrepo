package niuzhixiang.bean;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "UserBean")
@Table(name = "user")
@XmlRootElement
public class UserBean {

	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBean(Integer id, String username, String password, String email,
			SchoolBean schoolBean) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.schoolBean = schoolBean;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolid")
	private SchoolBean schoolBean;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public SchoolBean getSchoolBean() {
		return schoolBean;
	}

	public void setSchoolBean(SchoolBean schoolBean) {
		this.schoolBean = schoolBean;
	}
	
}
