import java.util.Scanner;

/**
 * A driver that provides library services and processes command lines from the console.
 * It interprets input from the console and displays the result for the user given the command.
 *
 * @author Jin Sebastian, Abhinav Sirohi
 */
public class Kiosk {
    /**
     * Method to begin a kiosk session and take inputs to be interpreted from the command line.
     */
    public void run() {
        Library library = new Library();

        System.out.println("Library Kiosk running.");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            String[] input = inputString.split(",");
            if (input[0].equals("Q")) { // quit
                System.out.println("Kiosk session ended.");
                break;
            }

            // add a book
            else if (input[0].equals("A")) {
                // validate date and creating a new book object
                Date datePublished = new Date(input[2]);
                if (datePublished.isValid()) {
                    Book book = new Book(input[1], false, new Date(input[2]));
                    library.add(book);
                    System.out.println(input[1] + " added to the library.");
                } else {
                    System.out.println("Invalid Date!");
                }
            }

            // remove a book
            else if (input[0].equals("R")) {
                Boolean isRemoved = library.remove(library.bookData(input[1]));
                if (!isRemoved) {
                    System.out.println("Unable to remove, the library does not have this book.");
                } else {
                    System.out.println("Book# " + input[1] + " removed.");
                }
            }

            // checking out a book
            else if (input[0].equals("O")) {
                Boolean isCheckedOut = library.checkOut(library.bookData(input[1]));
                if (!isCheckedOut) {
                    System.out.println("Book#" + input[1] + " is not available.");
                } else {
                    System.out.println("You've checked out Book#" + input[1] + ". Enjoy!");
                }
            }

            // returning a book
            else if (input[0].equals("I")) {
                Boolean isReturned = library.returns(library.bookData(input[1]));
                if (!isReturned) {
                    System.out.println("Unable to return Book#" + input[1] + ".");
                } else {
                    System.out.println("Book#" + input[1] + " return has completed. Thanks!");
                }
            }

            // standard print of catalog
            else if (input[0].equals("PA")) {
                if (library.getNumBooks() > 0) {
                    System.out.println("**List of books in the library.");
                    library.printStandard();
                    System.out.println("**End of list");
                } else {
                    System.out.println("Library catalog is empty!");
                }
            }

            // number sorted print of catalog
            else if (input[0].equals("PN")) {
                if (library.getNumBooks() > 0) {
                    System.out.println("**List of books by the book numbers.");
                    library.printByNumber();
                    System.out.println("**End of list");
                } else {
                    System.out.println("Library catalog is empty!");
                }
            }

            // date sorted print of catalog
            else if (input[0].equals("PD")) {
                if (library.getNumBooks() > 0) {
                    System.out.println("**List of books by the dates published.");
                    library.printByDate();
                    System.out.println("**End of list");
                } else {
                    System.out.println("Library catalog is empty!");
                }
            }

            // if command does not match any of the cases
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
