import javax.mail.MessagingException;

public class Main {
    public static void main(String[] args) throws MessagingException {

        String dataFile = args[0];
        FileReader welcomeNote = FileReader.createFileReader("./SampleData/WelcomeNote.txt");
        String messageTemplate = welcomeNote.getContent();

        FileReader reader = FileReader.createFileReader(dataFile);
        String fileContents = reader.getContent();

        CustomerGroup group = new CustomerGroup();
        group.buildCustomer(fileContents);
        System.out.println("Sending Mails");
        System.out.println("----------------------");
        group.sendMail("Announcement of our website", messageTemplate);

    }

}
