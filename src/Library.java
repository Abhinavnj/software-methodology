/**
 * An abstract data type that represents a Library where Book objects are stored and managed.
 * Each library contains a "bag" (Book array) to hold books added and keeps track of the number of books.
 * @author Jin Sebastian, Abhinav Sirohi
 */
public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag
    private static final int GROWTH_SIZE = 4;
    private static final int NOT_FOUND = -1;

    /**
     * Default constructor to create an empty bag.
     */
    public Library() {
        books = new Book[GROWTH_SIZE];
    }

    /**
     * Helper method to find a book in the bag.
     *
     * @param book book to be found within the bag
     * @return index where the book is found in the bag array, return -1 if not found
     */
    private int find(Book book) {
        // linear search through bag to find equivalent book's index
        for (int i = 0; i < numBooks; i++) {
            if (books[i].equals(book)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Helper method to grow the capacity of books by 4.
     */
    private void grow() {
        Book[] newBooks = new Book[books.length + GROWTH_SIZE];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        this.books = newBooks;
    }

    /**
     * Getter method to get the number of books in the library
     *
     * @return int of number of books in the bag
     */
    public int getNumBooks() {
        return numBooks;
    }

    /**
     * Adds a new book to the books array.
     * If the books array is full, grow will be called to increase the array capacity by 4.
     *
     * @param book new book to be added
     */
    public void add(Book book) {
        if (books.length == numBooks) {
            grow();
        }
        books[numBooks] = book;
        numBooks++;
    }

    /**
     * Removes a book from the books array.
     * Shifts all books after the removed slot to the left (so that no gap exists between any two books).
     *
     * @param book book to be removed
     * @return true if book was successfully removed, false if book does not exist
     */
    public boolean remove(Book book) {
        if (book.getNumber() == null) {
            return false;
        }
        int removeIndex = find(book);
        if (removeIndex == NOT_FOUND) {
            return false;
        }
        for (int i = removeIndex + 1; i < numBooks; i++) {
            books[i - 1] = books[i];
        }
        //Setting the duplicate of the last book equal to null
        books[numBooks - 1] = null;
        numBooks--;
        return true;
    }

    /**
     * Checks out a book from the bag.
     * Sets checkedOut field of Book to true if not already checked out (true).
     *
     * @param book book that will be checked out
     * @return true if checked out successfully, false if already checked out or book does not exist
     */
    public boolean checkOut(Book book) {
        if (book.getNumber() == null) {
            return false;
        }
        int bookIndex = find(book);
        if (bookIndex == NOT_FOUND) {
            return false;
        }
        if (books[bookIndex].isCheckedOut()) {
            return false;
        }
        books[bookIndex].setCheckedOut(true);
        return true;
    }

    /**
     * Returns a checked out book to the bag.
     * Sets checkedOut field of Book to false if checked out.
     *
     * @param book book that will be returned
     * @return true if book returned successfully, false if not checked out or book does not exist
     */
    public boolean returns(Book book) {
        if (book.getNumber() == null) {
            return false;
        }
        int bookIndex = find(book);
        //if book is not found, return false
        if (bookIndex == NOT_FOUND) {
            return false;
        }
        if (!books[bookIndex].isCheckedOut()) {
            return false;
        }
        books[bookIndex].setCheckedOut(false);
        return true;
    }

    /**
     * Print the list of books in the bag according to current sequence.
     */
    public void printStandard() {
        for (int i = 0; i < numBooks; i++) {
            System.out.println(books[i].toString());
        }
    }

    /**
     * Print the list of books by datePublished (ascending).
     */
    public void printByDate() {
        for (int i = 0; i < numBooks; i++) {
            for (int j = 1; j < numBooks - i; j++) {
                //Check if Book at index i is published before Book at index j
                if (books[j - 1].getDatePublished().isAfter(
                        books[j].getDatePublished()) ||
                        books[j - 1].getDatePublished().isEqual(
                                books[j].getDatePublished())) {
                    Book temp = books[j - 1];
                    books[j - 1] = books[j];
                    books[j] = temp;
                }
            }
        }

        printStandard();
    }

    /**
     * Print the list of books by number (ascending).
     */
    public void printByNumber() {
        for (int i = 0; i < numBooks; i++) {
            for (int j = 1; j < numBooks - i; j++) {
                // Check if Book at index i is published before Book at index j
                if (books[j - 1].getNumber().compareTo(
                        books[j].getNumber()) > 0) {
                    Book temp = books[j - 1];
                    books[j - 1] = books[j];
                    books[j] = temp;
                }
            }
        }

        printStandard();
    }

    /**
     * Getting the Book object given its serial number.
     *
     * @param serialNumber serial number of the book
     * @return Book object with given serial number if found, otherwise returns empty Book
     */
    public Book bookData(String serialNumber) {
        // linear search through bag to find equivalent book's index
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getNumber().equals(serialNumber)) {
                return books[i];
            }
        }

        return new Book();
    }


}
