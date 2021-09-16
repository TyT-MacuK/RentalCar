package by.training.carrent.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.ServiceException;


public class EmailSender {
	private static final Logger logger = LogManager.getLogger();
	private static final EmailSender INSTANCE = new EmailSender();
	private static final String MAIL_PROPERTIES_PATH = "properties/mail.properties";
	private static final String USER_KEY = "mail.user.user";
	private static final String PASSWORD_KEY = "mail.user.password";
	private static final String MAIL_CONTENT_TYPE = "text/html";
	private static final String TITLE_MAIL = "Registration message from car rental";
    private static final String MESSAGE_TEXT = "Your registration confirmation code: ";
	
	private EmailSender() {
	}
	
	public static EmailSender getInstance() {
		return INSTANCE;
	}

	public boolean sendMail(String emailTo, String code) throws ServiceException {
		logger.log(Level.INFO, "method senMail");
		boolean result;
		Properties properties = new Properties();
		try {
			InputStream inputStream = EmailSender.class.getClassLoader()
					.getResourceAsStream(MAIL_PROPERTIES_PATH);
			if (inputStream == null) {
				logger.log(Level.ERROR, "properties for mail is not found : {}", MAIL_PROPERTIES_PATH);
				throw new ServiceException("Error. Properties for mail is not found");
			}
			properties.load(inputStream);
		} catch (IOException e) {
			logger.log(Level.ERROR, "properties fot mail cannot be read", e);
			throw new ServiceException("Error. Properties file cannot be read", e);
		}
		String user = properties.getProperty(USER_KEY);
		String password = properties.getProperty(PASSWORD_KEY);

		Session session = createSession(properties, user, password);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(TITLE_MAIL);
			message.setContent(MESSAGE_TEXT.concat(code), MAIL_CONTENT_TYPE);
			Transport.send(message);
			result = true;
			logger.log(Level.INFO, "message was sent");
		} catch (MessagingException e) {
			logger.log(Level.ERROR, "Email did not send ", e);
			result = false;
		}
		return result;
	}

	private Session createSession(Properties properties, String user, String password) {
		return Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
	}
}
