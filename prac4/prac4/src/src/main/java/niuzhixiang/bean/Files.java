package niuzhixiang.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(value = {FileBean.class})
public class Files {
	
	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Files(List<FileBean> files) {
		super();
		this.files = files;
	}

	private List<FileBean> files;

	public List<FileBean> getFiles() {
		return files;
	}

	public void setFiles(List<FileBean> files) {
		this.files = files;
	}
}
