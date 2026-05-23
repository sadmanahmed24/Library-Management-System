import java.sql.*;

public class AutoLibrarian {

    private Book[] books = new Book[50];
    private int count = 0;

    public AutoLibrarian() {
        loadFromDatabase();
    }

    public void addNewBook(String title, String author) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO BOOK VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, (int) (Math.random() * 1000));

            ps.setString(2, title);

            ps.setString(3, author);

            ps.setInt(4, 1);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // REMOVE BOOK
    // =========================

    public String removeBookById(int id) {

        for (int i = 0; i < count; i++) {

            if (books[i].getId() == id) {

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
                    books[j] = books[j + 1];
                }

                books[count - 1] = null;

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

        for (int i = 0; i < count; i++) {

            if (books[i].getId() == id) {

                books[i].returned();

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

            String query = "SELECT * FROM BOOK WHERE STATUS=1";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                result += "ID: " +
                        rs.getInt("BOOK_ID") +

                        " | Title: " +
                        rs.getString("TITLE") +

                        " | Author: " +
                        rs.getString("AUTHOR") +

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

                books[count++] = new Book(
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
