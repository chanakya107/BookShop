

import java.io.*;

public class FileReader {
    private final String fileName;
    private String content = "";
    private FileInputStream fileInputStream;

    private FileReader(String fileName) {
        this.fileName = fileName;
        readContent();
    }

    public static FileReader createFileReader(String fileName) {
        if (fileName == null) throw new IllegalArgumentException("fileName is null");
        return new FileReader(fileName);
    }

    public void readContent() {
        createFileInputStream();
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String strLine;

        try {
            while ((strLine = bufferedReader.readLine()) != null) {
                content += strLine + "\n";
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read the file Content");
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                throw new IllegalArgumentException("Error In reading file");
            }
        }
    }

    private void createFileInputStream() {
        fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(fileName + ": File Not Found");
        }
    }

    public String getContent() {
        return content;
    }
}
