package org.lifeba.ws.resource;

import org.lifeba.ws.model.Student;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;


/***
 * 
 * @author Steven
 * http://www.lifeba.org
 */
public class StudentResource extends ServerResource{
	private int id;
	@Override
    protected void doInit() throws ResourceException {
		id = Integer.valueOf((String) getRequestAttributes().get("studentId"));
	}
	
	@Get
	public Representation get(Representation entity) {
		Student student =  ResourceHelper.findStudent(id);
		return new StringRepresentation(student.toString());
	}
	
	@Delete
	public Representation delete() {
		int status = ResourceHelper.deleteStudent(id);
		return new StringRepresentation(String.valueOf(status));
	}

	@Put
	public Representation put(Representation entity)
			throws ResourceException {
		 Form form = new Form(entity); 
		 Student student = ResourceHelper.findStudent(id);
		 
		 String name = form.getFirstValue("name");
		 int clsId = Integer.parseInt(form.getFirstValue("clsId"));
		 int sex = Integer.parseInt(form.getFirstValue("sex"));
		 int age = Integer.parseInt(form.getFirstValue("age"));
		 student.setClsId(clsId);
		 student.setName(name);
		 student.setSex(sex);
		 student.setAge(age); 
		 return new StringRepresentation(String.valueOf(ResourceHelper.updateStudent(student)));
	}
}
