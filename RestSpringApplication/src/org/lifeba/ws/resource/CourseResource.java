package org.lifeba.ws.resource;

import org.lifeba.ws.model.Student;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/***
 * 
 * @author Steven
 * http://www.lifeba.org
 */
public class CourseResource extends ServerResource{
	private int id;
	@Override
    protected void doInit() throws ResourceException {
		id = Integer.valueOf((String) getRequestAttributes().get("courseId"));
	}
	
	@Get
	public Representation get(Representation entity) {
		return new StringRepresentation("course id:"+id);
	}
}
