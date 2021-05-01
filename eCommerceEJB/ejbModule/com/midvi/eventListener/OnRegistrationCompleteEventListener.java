package com.midvi.eventListener;

import java.util.Date;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
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

import com.midvi.dao.VirificationTokenDAO;
import com.midvi.entity.User;
import com.midvi.entity.VirificationToken;
import com.midvi.event.OnRegistrationCompleteEvent;


@Stateless
@LocalBean
public class OnRegistrationCompleteEventListener implements OnRegistrationCompleteEventListenerLocal {

    @Inject
    private VirificationTokenDAO virificationTokenDAO;
    
    // ************ email config ************
    private int port = 465;
    private final String host = "smtp.gmail.com";
    private final String from = "from"; // privide your own
    private final String username = "username"; // to be provided
    private final String password = "password"; // to be provided
    private Protocol protocol = Protocol.SMTPS;
    private boolean debug = true;
    //****************************************
    
    public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
    
    public OnRegistrationCompleteEventListener() {
       
    }
    
	@Override
	public void catchTheEvent(@Observes OnRegistrationCompleteEvent event) {
		System.out.println("\t ===== Interception the event of the user : "+event.getUser().getEmail());
		System.out.println("\t ===== of token : "+event.getToken());
		this.addUserToken(event.getUser(), event.getToken());
		try {
			this.sendVerificationEmail(event.getUser(), event.getToken(),event.getAppAdress());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUserVirificationToken(String token) {
		virificationTokenDAO.delete(virificationTokenDAO.findByToken(token));
	}
    

	public void addUserToken(User user, String token) {
		VirificationToken virificationToken = new VirificationToken();
		virificationToken.setDate(new Date());
		virificationToken.setToken(token);
        virificationToken.setUser(user);
        virificationTokenDAO.save(virificationToken);
		
	}


	@SuppressWarnings("incomplete-switch")
	public void sendVerificationEmail(User user, String token,String appURL) throws MessagingException {
		 String verificationUrl = appURL + "/verifyRegistration?token="+token;
		// send the email

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", true);
        
		switch (protocol) {
		case SMTPS:
			props.put("mail.smtp.ssl.enable", true);
			break;
		case TLS:
			props.put("mail.smtp.starttls.enable", true);
			break;
		}

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(debug);

		MimeMessage message = new MimeMessage(session);

		Multipart multipart = new MimeMultipart("alternative");
		MimeBodyPart textPart = new MimeBodyPart();
		String textContent = "verify your registration !";
		textPart.setText(textContent);
		MimeBodyPart htmlPart = new MimeBodyPart();
		String htmlContent = "<html><h1>Hi</h1><p>click on the the link to verify!</p><br>" +
				            "<a href=\""+verificationUrl+"\">"+"verify"+"</a>"+"</html>";
		htmlPart.setContent(htmlContent, "text/html");
		multipart.addBodyPart(textPart);
		multipart.addBodyPart(htmlPart);
		message.setContent(multipart);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(user.getEmail()) };
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject("verifiy registration");
			message.setSentDate(new Date());
			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void deleteUserVirificationToken(Date date) {
		virificationTokenDAO.deleteVerificationTokenByDate(date);
       
	}

}
