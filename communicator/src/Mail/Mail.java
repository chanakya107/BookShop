package Mail;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    private final String USERNAME = "customer.care.pustak@gmail.com";
    private final String PASSWORD = "Pustak123";
    private final String subject;
    private final String messageBody;
    private MailStatus mailStatusListener;
    private Session session;
    private Properties props;

    public Mail(String subject, String messageBody) {
        this.subject = subject;
        this.messageBody = messageBody;
        createProperties();
    }

    private void createProperties() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

    }

    public void createSession() {
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
    }

    public void sendMail(String recipientMailId) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMailId));
        message.setSubject(subject);
        message.setText(messageBody);
        Transport.send(message);
        mailStatusListener.sendSuccessfull(recipientMailId);
    }

    public void bind(MailStatus mailStatus) {
        mailStatusListener = mailStatus;
    }
}
