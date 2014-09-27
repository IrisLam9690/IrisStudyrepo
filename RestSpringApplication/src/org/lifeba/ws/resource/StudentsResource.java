 package org.lifeba.ws.resource;

import java.util.Iterator;

import org.lifeba.ws.model.Student;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


/***
 * 
 * @author Steven
 * http://www.lifeba.org
 */
public class StudentsResource extends ServerResource{
	
	@Override
    protected void doInit() throws ResourceException {
		
	}
	
	@Get
	public Representation get(Representation entity) {
		StringBuilder sb = new StringBuilder();
		Iterator it = ResourceHelper.students.keySet().iterator();
		while(it.hasNext()){
			sb.append(ResourceHelper.students.get(it.next()).toString()+"\r\n\r\n");
		}
		return new StringRepresentation(sb.toString());
	}
	
	@Post
	public Representation post(Representation entity)
			throws ResourceException {
		 Form form = new Form(entity);
		 String name = form.getFirstValue("name");
		 int clsId = Integer.parseInt(form.getFirstValue("clsId"));
		 int sex = Integer.parseInt(form.getFirstValue("sex"));
		 Student student = new Student();
		 student.setClsId(clsId);
		 student.setName(name);
		 student.setSex(sex);
		 ResourceHelper.maxId++;
		 int id =  ResourceHelper.maxId;
		 student.setId(id);
		 
		 return new StringRepresentation(String.valueOf(ResourceHelper.addStudent(student)));
	}
}
