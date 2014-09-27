package niuzhixiang.bean;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(value = { ItemBean.class, SchoolBean.class, FileBean.class })
public class ListContainer<T> {
	
	private List<T> list;

	public ListContainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListContainer(List<T> list) {
		super();
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
