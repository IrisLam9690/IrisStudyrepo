package org.lifeba.ws.app;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/***
 * 
 * @author Steven
 * http://www.lifeba.org
 */
public class Client {
	public static void main(String[] args){
		Client client = new Client();
		//client.student_delete();
		//client.student_post();
		
		client.student_put();
	}
	public void student_delete(){
		try {
			ClientResource client = new ClientResource("http://localhost:8085/RestSpringApplication/student/1");
			Representation representation =client.delete(); 
			System.out.println(representation.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void student_put(){
		try {
			Form queryForm = new Form();
			queryForm.add("name","steven_spring_modify");
			queryForm.add("clsId","201012");
			queryForm.add("sex","12");
			queryForm.add("age","24");
			
			ClientResource client = new ClientResource("http://localhost:8085/RestSpringApplication/student/2");
			Representation representation =client.put(queryForm);
			System.out.println(representation.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void student_post(){
		try {
			Form queryForm = new Form();
			queryForm.add("name","steven_spring");
			queryForm.add("clsId","201002");
			queryForm.add("sex","2");
			queryForm.add("age","12");
			ClientResource client = new ClientResource("http://localhost:8085/RestSpringApplication/student");
			Representation representation =client.post(queryForm.getWebRepresentation());  
			System.out.println(representation.getText());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
