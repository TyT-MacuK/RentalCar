package by.training.carrent.controller;

import static by.training.carrent.controller.command.RequestParameter.*;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.controller.command.PagePath;
import by.training.carrent.controller.command.RequestParameter;

@WebServlet("/upload/image")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                 maxFileSize = 1024 * 1024 * 5,
                 maxRequestSize = 1024 * 1024 * 25)
public class ImageServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();
	private static final String UPLOAD_DIR = "image";
	private static final String MIME_TYPE_IMAGE = "image/";
	private static final String MAIN_SERVLET = "/controller";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "method processRequest()");
		String commandName = request.getParameter(RequestParameter.COMMAND);
		Part part = null;
		if (commandName != null) {
			part = request.getPart(RequestParameter.IMAGE);
			processPart(request, response, part);
		} else {
			logger.log(Level.WARN, "user is trying to upload a file that is larger than the available size");
			request.setAttribute(INVALID_FILE, true);                 //TODO
			response.sendRedirect(PagePath.ADMIN_ADD_CAR_REDIRECT);
		}
	}

	private void processPart(HttpServletRequest request, HttpServletResponse response, Part part)
			throws ServletException, IOException {
		logger.log(Level.INFO, "method processPart()");
		String fileName = part.getSubmittedFileName();
		if (!fileName.isBlank()) {
			String mimeType = getServletContext().getMimeType(fileName);
			if (mimeType.startsWith(MIME_TYPE_IMAGE)) {
				String uploadFileDir = defineUploadFileDirectory(request);
				request.setAttribute(RequestParameter.PART, part);
				request.setAttribute(RequestParameter.IMAGE_UPLOAD_DIRECTORY, uploadFileDir);
				request.getRequestDispatcher(MAIN_SERVLET).forward(request, response);
			} else {
				logger.log(Level.WARN, "user is trying to upload a file that has other type");
				request.setAttribute(INVALID_FILE, true);                    //TODO
				response.sendRedirect(PagePath.ADMIN_ADD_CAR_REDIRECT);
			}
		} else {
			logger.log(Level.WARN, "file is empty");
			request.setAttribute(INVALID_FILE, true);                           //TODO
			response.sendRedirect(PagePath.ADMIN_ADD_CAR_REDIRECT);
		}
	}

	private String defineUploadFileDirectory(HttpServletRequest request) {
		logger.log(Level.INFO, "method defineUploadFileDirectory()");
		String applicationDir = request.getServletContext().getRealPath("");
		String uploadFileDir = applicationDir.concat(UPLOAD_DIR).concat(File.separator);
		File fileSaveDir = new File(uploadFileDir);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		return uploadFileDir;
	}
}
