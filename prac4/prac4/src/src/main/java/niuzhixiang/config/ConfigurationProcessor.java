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
	
	//֪ͨ���������Ķ���Ӧ�ó����Ѿ����س����ǹر�
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
//�Ѿ������ػ��ǳ�ʼ��
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		doConfiguration();
	}
	
	/**
	 * �������ù���
	 */
	
	//config��ʵ�ǿ���web�����ڳ�ʼ��ʱʹ��һ��ServletConfig(��config)������JSPҳ�洫����Ϣ��
	//��������Ϣ������ʼ���������ڵ�ǰWebӦ�õ�Ӧ�ò��������ļ�web.xml�ж��壩
	//�Լ���ʾServlet��JSPҳ������WebӦ�õ�ServletContext����
	public void doConfiguration() {		
		try {
			//��ӡ����־����Ϣ
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			logger.info("classpath:" + ConfigurationProcessor.class.getClassLoader().getResource("config.xml").toString());
			
			//��Ϊ.class.getClassLoader()��Ϊ�˻��һ�������������������classpath�µ�.class�ļ����ѡ�
			//getResource���ص���һ��URL���󣬲�Ҫ�򵥵���Ϊ�������Դ�ľ��Ե�ַ������һ��Java�з�װ�Ķ���
			String configPath = ConfigurationProcessor.class.getClassLoader().getResource("config.xml").toString();
			configPath = pathFormat(configPath);
			File xmlFile = new File(configPath);
			
			
			//DOM4j��ȡXML�ļ���SAXReader��SAXReader��дxml�ļ�
			SAXReader saxReader = new SAXReader();
			
			//�õ�Document����
			Document doc = saxReader.read(xmlFile);
			Element root = doc.getRootElement();//��ø��ڵ�
			Iterator iterator = root.elementIterator();//�Ӹ��ڵ�����ӽڵ�
			//ʵ����ֻѭ��һ��
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
	 * ��ʽ��·��
	 * @param path ԭ·��
	 * @return ��ʽ�����·��
	 */
	public String pathFormat(String path) {
		return path.replaceAll("%20", " ").replaceAll("file:/", "");
	}
	
	public static void main(String[] args) {
		System.out.println(String.class.getClassLoader());
		System.out.println(ConfigurationProcessor.class.getClassLoader());
	}
}
