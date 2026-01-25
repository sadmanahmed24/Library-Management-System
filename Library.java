import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Library extends JFrame implements ActionListener {

    Font font20 = new Font("Cambria", Font.BOLD, 20);

    JTextArea displayArea;
	JScrollPane scrollPane;
	JButton addBtn, removeBtn, viewBtn, issueBtn, returnBtn;
   

    AutoLibrarian auto;
    Member member;

    public Library() {

        super("Library Management System");
		
		

        this.setSize(800, 430);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("logo.png").getImage());

        JLabel title = new JLabel("Auto Library System");
        title.setBounds(200, 10, 300, 40);
        title.setFont(font20);
        this.add(title);

        addBtn = new JButton("Add Book");
        addBtn.setBounds(30, 80, 200, 40);
        addBtn.setFont(font20);
        addBtn.setBackground(Color.GREEN);
        addBtn.addActionListener(this);
        this.add(addBtn);

        removeBtn = new JButton("Remove Book");
        removeBtn.setBounds(30, 140, 200, 40);
        removeBtn.setFont(font20);
        removeBtn.setBackground(Color.RED);
        removeBtn.addActionListener(this);
        this.add(removeBtn);

        issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(30, 200, 200, 40);
        issueBtn.setFont(font20);
        issueBtn.setBackground(Color.WHITE);
        issueBtn.addActionListener(this);
        this.add(issueBtn);

        returnBtn = new JButton("Return Book");
        returnBtn.setBounds(30, 260, 200, 40);
        returnBtn.setFont(font20);
        returnBtn.setBackground(Color.WHITE);
        returnBtn.addActionListener(this);
        this.add(returnBtn);

        viewBtn = new JButton("View Available Books");
        viewBtn.setBounds(30, 320, 200, 40);
        viewBtn.setFont(font20);
        viewBtn.setBackground(Color.YELLOW);
        viewBtn.addActionListener(this);
        this.add(viewBtn);

        displayArea = new JTextArea();
        displayArea.setBounds(260, 80, 350, 280);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Cambria", Font.PLAIN, 14));
        this.add(displayArea);
		
		


        auto = new AutoLibrarian();
        member = new Member("Student");

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {

            String title = JOptionPane.showInputDialog(this, "Enter Book Title:");
            String author = JOptionPane.showInputDialog(this, "Enter Author:");

            if (title != null && author != null) {
                auto.addNewBook(title, author);
                JOptionPane.showMessageDialog(this, "Book Added Successfully");
            }
        }

        else if (e.getSource() == removeBtn) {

            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Enter Book ID:")
            );
            JOptionPane.showMessageDialog(this, auto.removeBookById(id));
        }

        else if (e.getSource() == issueBtn) {

            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Enter Book ID:")
            );
            JOptionPane.showMessageDialog(this, auto.issueBookById(id, member));
        }

        else if (e.getSource() == returnBtn) {

            int id = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Enter Book ID:")
            );
            JOptionPane.showMessageDialog(this, auto.returnBookById(id, member));
        }

        else if (e.getSource() == viewBtn) {
            displayArea.setText(auto.showAvailableBooks());
        }
    }

    public static void main(String[] args) {
        new Library();
    }
}
