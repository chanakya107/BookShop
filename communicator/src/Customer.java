import Mail.Mail;
import Mail.MailStatus;

import javax.mail.MessagingException;

/**
 * Job: Understands the  recipient of a good.
 */
public class Customer implements MailStatus {
    private final String name;
    private final String emailId;
    private Mail mail;

    private Customer(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
    }

    public static Customer createClient(String name, String emailId) {
        if (name == null) throw new IllegalArgumentException("Customer name is null");
        if (emailId == null) throw new IllegalArgumentException("EmailId is null");
        return new Customer(name, emailId);
    }

    @Override
    public String toString() {
        return name + emailId + "\n";
    }

    public String getName() {
        return name;
    }

    public String getEmailID() {
        return emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (emailId != null ? !emailId.equals(customer.emailId) : customer.emailId != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        return result;
    }

    public void sendMail(String messageTemplate, String subject) throws MessagingException {
        mail = new Mail(messageTemplate, subject);
        mail.bind(this);
        mail.createSession();
        mail.sendMail(emailId);
    }

    @Override
    public void sendSuccessfull(String recipientEmailID) {
        System.out.println("Email Successfully Sent to:" + name + " " + recipientEmailID);
    }

    @Override
    public void sendFailed(String recipientEmailID) {
        System.out.println("Email UnSuccessfully  to:" + name + "" + recipientEmailID);
    }
}
