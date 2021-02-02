/**
 * An abstract data type that represents a Book in the Library class.
 * Each book contains a serial number, name, date published, and availability of book (checked out or not).
 * @author Jin Sebastian, Abhinav Sirohi
 */
public class Book {
    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    /**
     * Default constructor for creating a Book object.
     */
    public Book() { }

    /**
     * Constructor for creating a Book object.
     * @param number serial number as a string
     * @param name the title of the book
     * @param checkedOut boolean determining if the book is checked out or not
     * @param datePublished Date object containing the published date
     */
    public Book(String number, String name, boolean checkedOut, Date datePublished) {
        this.number = number;
        this.name = name;
        this.checkedOut = checkedOut;
        this.datePublished = datePublished;
    }

    /**
     * Checks if two Book objects have equal serial numbers.
     * @param obj Book that is being compared to
     * @return true if serial numbers are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        // create a Book object using obj and then compares serial numbers
        Book bookToBeCompared = (Book) obj;
        return bookToBeCompared.number.equals(this.number);
    }

    /**
     * Returns a string detailing the instance variables of a Book.
     * @return formatted string version of a book object
     */
    @Override
    public String toString() {
        String availability = (checkedOut) ? "is not available." : "is available.";
        return "Book#" + number + "::" + name + "::" + datePublished.toString() + " " + availability;
    }

    /**
     * Getter method to return serial number.
     * @return serial number string
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter method to set if book is checked out.
     * @param checkedOut value to set checkedOut boolean
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * Getter method to return if book is checked out.
     * @return true if book is checked out, false otherwise
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * Getter method to return date published of the book
     * @return Date object of the book
     */
    public Date getDatePublished() {
        return datePublished;
    }
}
