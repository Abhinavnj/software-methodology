public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    private static String serialNumber = "10000";

    /**
     * Default constructor to create an empty bag.
     */
    public Library() {
        books = new Book[4];
    }

    /**
     * Helper method to find a book in the bag.
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
        return -1;
    }

    /**
     * Helper method to grow the capacity of books by 4.
     */
    private void grow() {
        Book[] newBooks = new Book[books.length + 4];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        this.books = newBooks;
    }

    /**
     * Getter method to get the number of books in the library
     * @return
     */
    public int getNumBooks() {
        return numBooks;
    }

    /**
     * Adds a new book to the books array.
     * If the books array is full, grow will be called to increase the array capacity by 4.
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
     * @param book book to be removed
     * @return true if book was successfully removed, false if book does not exist
     */
    public boolean remove(Book book) {
        int removeIndex = find(book);
        if (removeIndex == -1) {
            return false;
        }

        String bookRemovedSerial = books[removeIndex].getNumber();
        for (int i = removeIndex + 1; i < books.length; i++) {
            books[i - 1] = books[i];
        }
        return true;
    }

    /**
     * Checks out a book from the bag.
     * Sets checkedOut field of Book to true if not already checked out (true).
     * @param book book that will be checked out
     * @return true if checked out successfully, false if already checked out or book does not exist
     */
    public boolean checkOut(Book book) {
        int bookIndex = find(book);
        if (bookIndex == -1) {
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
     * @param book book that will be returned
     * @return true if book returned successfully, false if not checked out or book does not exist
     */
    public boolean returns(Book book) {
        int bookIndex = find(book);
        if (bookIndex == -1) {
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
        for (int i = 0; i < books.length; i++) {
            for (int j = 1; j < books.length - i; j++) {
                //Check if Book at index i is published before Book at index j
                if (books[j-1].getDatePublished().isAfter(books[j].getDatePublished())) {
                    Book temp = books[j-1];
                    books[j-1] = books[j];
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
        for (int i = 0; i < books.length; i++) {
            for (int j = 1; j < books.length - i; j++) {
                // Check if Book at index i is published before Book at index j
                if (books[j-1].getNumber().compareTo(books[j].getNumber()) > 0) {
                    Book temp = books[j-1];
                    books[j-1] = books[j];
                    books[j] = temp;
                }
            }
        }
        printStandard();
    }

    public Book getBookData(String serialNumber) {
        // linear search through bag to find equivalent book's index
        for (int i = 0; i < books.length; i++) {
            if (books[i].getNumber().equals(serialNumber)) {
                return books[i];
            }
        }

        return new Book();
    }

    /**
     * Getter for the current serial number the library is assigning
     * @return current serial number as a String
     */
    public static String getSerialNumber() {
        // convert serialNumber String to int, add 1, and convert back to int
        serialNumber = String.valueOf(Integer.parseInt(serialNumber) + 1);
        return serialNumber;
    }
}
