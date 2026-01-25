public class Member extends Person {

    private int issuedCount = 0;
    private final int LIMIT = 3;

    public Member(String name) {
        super(name);
    }

    public boolean canIssue() {
        return issuedCount < LIMIT;
    }

    public void issueBook() {
        issuedCount++;
    }

    public void returnBook() {
        if (issuedCount > 0) {
            issuedCount--;
        }
    }
}
