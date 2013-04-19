
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class SendMail {

    public static void main(String[] args) {

        final String username = "keerthigireesh4@gmail.com";
        final String password = "vineethagireesh";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("keerthigireesh4@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("manigann@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear deepthi,"
                    + "\n\n haiiiiiiiiiiiiii !");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
