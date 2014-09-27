package niuzhixiang.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterBean {
	
	public RegisterBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterBean(ListContainer<SchoolBean> schools) {
		super();
		this.schools = schools;
	}
	
	private ListContainer<SchoolBean> schools;

	public ListContainer<SchoolBean> getSchools() {
		return schools;
	}

	public void setSchools(ListContainer<SchoolBean> schools) {
		this.schools = schools;
	}

}
