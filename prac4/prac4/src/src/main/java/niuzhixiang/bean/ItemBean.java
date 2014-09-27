package niuzhixiang.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity(name = "ItemBean")
@Table(name = "item")
@XmlRootElement
public class ItemBean {
	
	public ItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ItemBean(int id, String title, String content, Date time) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "time")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
