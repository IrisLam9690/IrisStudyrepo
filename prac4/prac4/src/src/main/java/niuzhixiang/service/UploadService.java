package niuzhixiang.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import niuzhixiang.bean.FileBean;
import niuzhixiang.dao.FileDao;
import niuzhixiang.util.Multipart;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import static niuzhixiang.config.Configuration.ROOT_PATH;

@Path("/upload")
public class UploadService {

	@Resource(name = "FileDao")
	private FileDao fileDao;
	
	@POST
	@Path("/{userid}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response uploadFile(List<Attachment> attachments, @PathParam("userid") int userid) {
		if (fileDao.uploadFiles(attachments, userid)) {
			return Response.ok("upload success").status(200).build();
		} else {
			return Response.ok("upload fail").status(500).build();
		}
	}	
	
	@GET
	@Path("/{userid}")
	@Produces({ "application/json", "application/xml", "text/html" })
	public Response getFilesByUser(@PathParam("userid") int userid) {
		return Response.ok(fileDao.getFilesByUser(userid)).build();
	}
	
	@GET
	@Path("/file/{fileid}")
	public Response getFile(@PathParam("fileid") int fileid) {
		FileBean fileBean = fileDao.getFileById(fileid);
		//获取文件后缀名
		String[] foos = fileBean.getPath().split("\\."); 
		String extension = foos[foos.length-1];
		//如果是下列类型的文件，则以该文件作为响应
		if (extension.equals("pdf")||extension.equals("doc")||extension.equals("jpg")||extension.equals("gif")) {
			File file = new File(ROOT_PATH + fileBean.getPath());
			return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
					.header("content-disposition", "attachment; filename =" + file.getName())
					.status(200)
					.build();
		}
		return Response.ok().build();
	}
}
