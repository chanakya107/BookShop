package Mail;

public interface MailStatus {
    public void sendSuccessfull(String recipientEmailID);

    public void sendFailed(String recipientEmailID);
}
