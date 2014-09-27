package niuzhixiang.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HomeBeanPC extends AbstractHomeBean {
	
	private ListContainer<ItemBean> items;

	public ListContainer<ItemBean> getItems() {
		return items;
	}

	public void setItems(ListContainer<ItemBean> items) {
		this.items = items;
	}
	
	public HomeBeanPC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HomeBeanPC(UserBean userBean, ListContainer<ItemBean> items){
		super(userBean);
		this.items = items;
	}

}
