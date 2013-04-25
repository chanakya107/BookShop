package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    private final String USERNAME = "customer.care.pustak@gmail.com";
    private final String PASSWORD = "Pustak123";
    private final String subject;
    private final String messageBody;
    private Session session;
    private Properties props;
    private Message message;
    private String messagetype;

    //todo: test not written for this class
    public Mail(String subject, String messageBody) {
        this.subject = subject;
        this.messageBody = messageBody;
        this.messagetype="";
        createProperties();
    }

    private void createProperties() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");

    }

    private void createSession() {
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

    }

    public void sendMail(String recipientMailId) throws MessagingException {
        createSession();
        message = new MimeMessage(session);
        message.setContent(messageBody,messagetype);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMailId));
        message.setSubject(subject);
//        message.setText(messageBody);
        Transport.send(message);
    }

    public void setContentType(String type) {
        this.messagetype=type;
    }
}
