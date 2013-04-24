import junit.framework.Assert;
import org.junit.Test;

public class FileReaderTest {
    @Test
    public void getContent_gets_contents_of_file() {
        String fileName = "SampleData/sample.csv";
        FileReader reader = FileReader.createFileReader(fileName);
        String name = "Abhilash,V,abhilashfeb30@gmail.com,9845712345" + "\n";
        Assert.assertEquals(name, reader.getContent());
    }

}
