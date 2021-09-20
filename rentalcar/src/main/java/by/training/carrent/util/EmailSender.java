package by.training.carrent.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.carrent.exception.ServiceException;

/**
 * The Class EmailSender.
 */
public class EmailSender {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger();
	
	/** The Constant INSTANCE. */
	private static final EmailSender INSTANCE = new EmailSender();
	
	/** The Constant MAIL_PROPERTIES_PATH. */
	private static final String MAIL_PROPERTIES_PATH = "properties/mail.properties";
	
	/** The Constant USER_KEY. */
	private static final String USER_KEY = "mail.user.user";
	
	/** The Constant PASSWORD_KEY. */
	private static final String PASSWORD_KEY = "mail.user.password";
	
	/** The Constant TITLE_MAIL. */
	private static final String TITLE_MAIL = "Registration message from Car4U";
	
	/** The Constant MESSAGE_TO_CODE. */
	private static final String MESSAGE_TO_CODE = "Your registration confirmation code: ";
	
	/** The Constant MESSAGE_TO_LINK. */
	private static final String MESSAGE_TO_LINK = "Please, follow the link and enter a code: ";
	
	/** The Constant REGISTRATION_CONFIRMATION_LINK. */
	private static final String REGISTRATION_CONFIRMATION_LINK = "http://localhost:8081/rentalcar/controller?command=to_code_entery_page_command";

	/**
	 * Instantiates a new email sender.
	 */
	private EmailSender() {
	}

	/**
	 * Gets the single instance of EmailSender.
	 *
	 * @return single instance of EmailSender
	 */
	public static EmailSender getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Send mail.
	 *
	 * @param emailTo the recipient email address
	 * @param code the confirmation code
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean sendMail(String emailTo, String code) throws ServiceException {
		logger.log(Level.INFO, "method senMail");
		boolean result;
		Properties properties = new Properties();
		try {
			InputStream inputStream = EmailSender.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES_PATH);
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
			
			BodyPart firstMessagePart = new MimeBodyPart();
			firstMessagePart.setText(MESSAGE_TO_CODE.concat(code).concat(System.lineSeparator()));
			BodyPart secondMessagePart = new MimeBodyPart();
			secondMessagePart.setText(MESSAGE_TO_LINK.concat(REGISTRATION_CONFIRMATION_LINK));
			Multipart  multipartMessage = new MimeMultipart();
			multipartMessage.addBodyPart(firstMessagePart);
			multipartMessage.addBodyPart(secondMessagePart);
			
			message.setContent(multipartMessage);
			Transport.send(message);
			result = true;
			logger.log(Level.INFO, "message was sent");
		} catch (MessagingException e) {
			logger.log(Level.ERROR, "Email did not send ", e);
			result = false;
		}
		return result;
	}

	/**
	 * Creates the session.
	 *
	 * @param properties the properties
	 * @param user the sender
	 * @param password the sender's email password
	 * @return the session
	 */
	private Session createSession(Properties properties, String user, String password) {
		return Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
	}
}