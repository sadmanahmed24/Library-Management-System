public class Book {

    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void issue() {
        available = false;
    }

    public void returned() {
        available = true;
    }

    public String getInfo() {
        return id + ". " + title + " - " + author +
               (available ? " (Available)" : " (Not Available)");
    }

    
    public String toFileString() {
        return id + "," + title + "," + author + "," + available;
    }
	
	public static Book fromFileString(String line) {
		String[] p = line.split(",");
		return new Book(
		Integer.parseInt(p[0]),
        p[1],
        p[2],
        Boolean.parseBoolean(p[3])
    );
}


}
