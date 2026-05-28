import java.sql.*;

public class AutoLibrarian {

    private Book[] book = new Book[50];
    private int count = 0;

    public AutoLibrarian() {
        loadFromDatabase();
    }

    public void addNewBook(String title, String author) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO BOOK VALUES (BOOK_SEQ.NEXTVAL, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);

            ps.setString(2, author);

            ps.setInt(3, 1);

            ps.executeUpdate();

            con.close();

            loadFromDatabase();

            System.out.println("Book Added Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // REMOVE BOOK
    // =========================

    public String removeBookById(int id) {

        loadFromDatabase();

        for (int i = 0; i < count; i++) {

            if (book[i].getId() == id) {

                try {

                    Connection con = DBConnection.getConnection();

                    String query = "DELETE FROM BOOKS WHERE ID=?";

                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setInt(1, id);

                    ps.executeUpdate();

                    con.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }

                // Remove from array
                for (int j = i; j < count - 1; j++) {
                    book[j] = book[j + 1];
                }

                book[count - 1] = null;

                count--;

                return "Book removed successfully";
            }
        }

        return "Book ID not found";
    }

    // =========================
    // ISSUE BOOK
    // =========================

    public String issueBookById(int id, Member member) {

        loadFromDatabase();

        if (!member.canIssue()) {
            return "Issue limit reached (Max 3 books)";
        }

        for (int i = 0; i < count; i++) {

            if (book[i].getId() == id) {

                if (!book[i].isAvailable()) {
                    return "Book is not available";
                }

                book[i].issue();

                member.issueBook();

                try {

                    Connection con = DBConnection.getConnection();

                    String query = "UPDATE BOOKS SET AVAILABLE=0 WHERE ID=?";

                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setInt(1, id);

                    ps.executeUpdate();

                    con.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }

                return "Book issued successfully";
            }
        }

        return "Book ID not found";
    }

    // =========================
    // RETURN BOOK
    // =========================

    public String returnBookById(int id, Member member) {

        loadFromDatabase();

        for (int i = 0; i < count; i++) {

            if (book[i].getId() == id) {

                book[i].returned();

                member.returnBook();

                try {

                    Connection con = DBConnection.getConnection();

                    String query = "UPDATE BOOKS SET AVAILABLE=1 WHERE ID=?";

                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setInt(1, id);

                    ps.executeUpdate();

                    con.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }

                return "Book returned successfully";
            }
        }

        return "Book ID not found";
    }

    public String showAvailableBooks() {

        String result = "";

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM BOOK WHERE AVAILABLE = 1";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                result += "ID: " + rs.getInt("ID") +
                        " | Title: " + rs.getString("TITLE") +
                        " | Author: " + rs.getString("AUTHOR") +
                        "\n";
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        if (result.equals("")) {

            return "No books available";
        }

        return result;
    }
    // =========================
    // LOAD FROM DATABASE
    // =========================

    private void loadFromDatabase() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM BOOKS";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                int id = rs.getInt("ID");

                String title = rs.getString("TITLE");

                String author = rs.getString("AUTHOR");

                boolean available = rs.getInt("AVAILABLE") == 1;

                book[count++] = new Book(
                        id,
                        title,
                        author,
                        available);
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
