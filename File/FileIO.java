package File;

import java.io.*;

public class FileIO {

    public static void saveInFile(String data) {

        try {
            File file = new File("File/books.txt"); 
            FileWriter writer = new FileWriter(file, true); 
			

            writer.write(data + "\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("File writing error");
        }
    }
}
