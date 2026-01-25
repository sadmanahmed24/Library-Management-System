import java.io.*;

public class AutoLibrarian {

    private Book[] books = new Book[50];
    private int count = 0;
    private final String FILE = "File/books.txt";

    public AutoLibrarian() {
        loadFromFile();
    }

    
    public void addNewBook(String title, String author) {
        books[count] = new Book(count + 1, title, author, true);
		count++;
        saveToFile();
    }

    
    public String removeBookById(int id) {

        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id) {

                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }

                books[count - 1] = null;
                count--;
                saveToFile();

                return "Book removed successfully";
            }
        }
        return "Book ID not found";
    }

    
    public String issueBookById(int id, Member member) {

        if (!member.canIssue()) {
            return "Issue limit reached (Max 3 books)";
        }

        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id) {

                if (!books[i].isAvailable()) {
                    return "Book is not available";
                }

                books[i].issue();
                member.issueBook();
                saveToFile();
                return "Book issued successfully";
            }
        }
        return "Book ID not found";
    }

   
    public String returnBookById(int id, Member member) {

        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id) {

                books[i].returned();
                member.returnBook();
                saveToFile();
                return "Book returned successfully";
            }
        }
        return "Book ID not found";
    }

    
    public String showAvailableBooks() {

        String result = "";
        for (int i = 0; i < count; i++) {
            if (books[i].isAvailable()) {
                result += books[i].getInfo() + "\n";
            }
        }
        return result.isEmpty() ? "No books available" : result;
    }

    
    private void saveToFile() {
    try {
        File file = new File(FILE);
        file.getParentFile().mkdirs(); 

        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for (int i = 0; i < count; i++) {
            pw.println(books[i].toFileString());
        }
        pw.close();
    } catch (Exception e) {
        e.printStackTrace(); 
    }
}


    
    private void loadFromFile() {
    try {
        File file = new File(FILE);
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            books[count++] = Book.fromFileString(line);
        }
        br.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
