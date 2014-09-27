package niuzhixiang.bean;

public abstract class AbstractHomeBean {
	
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public AbstractHomeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AbstractHomeBean(UserBean userBean) {
		super();
		this.userBean = userBean;
	}
	
}
