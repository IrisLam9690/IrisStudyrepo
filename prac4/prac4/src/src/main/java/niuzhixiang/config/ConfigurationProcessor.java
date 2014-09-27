package niuzhixiang.config;

import java.io.File;
import java.util.Iterator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.runners.JUnit4;

import com.sun.istack.logging.Logger;

public class ConfigurationProcessor implements ServletContextListener {

	private Logger logger = Logger.getLogger(ConfigurationProcessor.class);
	
	//通知正在收听的对象应用程序已经被载出或是关闭
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
//已经被加载或是初始化
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		doConfiguration();
	}
	
	/**
	 * 进行配置工作
	 */
	
	//config其实是可以web容器在初始化时使用一个ServletConfig(即config)对象向JSP页面传递信息，
	//此配置信息包括初始化参数（在当前Web应用的应用部署描述文件web.xml中定义）
	//以及表示Servlet或JSP页面所属Web应用的ServletContext对象。
	public void doConfiguration() {		
		try {
			//打印出日志的信息
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			logger.info("classpath:" + ConfigurationProcessor.class.getClassLoader().getResource("config.xml").toString());
			
			//因为.class.getClassLoader()是为了获得一个类加载器，用来加载classpath下的.class文件而已。
			//getResource返回的是一个URL对象，不要简单的认为是这个资源的绝对地址，这是一个Java中封装的对象
			String configPath = ConfigurationProcessor.class.getClassLoader().getResource("config.xml").toString();
			configPath = pathFormat(configPath);
			File xmlFile = new File(configPath);
			
			
			//DOM4j读取XML文件（SAXReader）SAXReader读写xml文件
			SAXReader saxReader = new SAXReader();
			
			//得到Document对象
			Document doc = saxReader.read(xmlFile);
			Element root = doc.getRootElement();//获得根节点
			Iterator iterator = root.elementIterator();//从根节点遍历子节点
			//实际上只循环一次
			while (iterator.hasNext()) {
				Element data = (Element) iterator.next();
				Configuration.ROOT_PATH = data.elementText("rootpath");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	/**
	 * 格式化路径
	 * @param path 原路径
	 * @return 格式化后的路径
	 */
	public String pathFormat(String path) {
		return path.replaceAll("%20", " ").replaceAll("file:/", "");
	}
	
	public static void main(String[] args) {
		System.out.println(String.class.getClassLoader());
		System.out.println(ConfigurationProcessor.class.getClassLoader());
	}
}
