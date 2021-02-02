import java.util.Scanner;

public class Kiosk {
    public void run() {
        Library library = new Library();
        System.out.println("Library Kiosk running.");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(",");
            if (input[0].equals("Q")) { // quit
                System.out.println("Kiosk session ended.");
                break;
            }
            else if (input[0].equals("A")) { // add a book
                Date datePublished = new Date(input[2]);
                if (datePublished.isValid()) {
                    Book book = new Book(library.getSerialNumber(), input[1], false, new Date(input[2]));
                    library.add(book);
                    System.out.println(input[1] + " added to the library.");
                }
                else {
                    System.out.println("Invalid Date!");
                }
            }
            else if (input[0].equals("R")) { // remove a book
                Boolean isRemoved = library.remove(library.getBookData(input[1]));
                if (!isRemoved) {
                    System.out.println("Unable to remove, the library does not have this book.");
                }
                else {
                    System.out.println("Book# " + input[1] + " removed.");
                }
            }
            else if (input[0].equals("O")) { // checking out a book
                Boolean isRemoved = library.remove(library.getBookData(input[1]));
                if (!isRemoved) {
                    System.out.println("Book#" + input[1] + " is not available.");
                }
                else {
                    System.out.println("You've checked out Book#" + input[1] + ". Enjoy!");
                }
            }
            else if (input[0].equals("I")) { // returning a book
                Boolean isReturned = library.returns(library.getBookData(input[1]));
                if (!isReturned) {
                    System.out.println("Unable to return Book#" + input[1] + ".");
                }
                else {
                    System.out.println("Book#" + input[1] + " return has completed. Thanks!");
                }
            }
            else if (input[0].equals("PA")) { // standard print of catalog
                if (library.getNumBooks() > 0) {
                    library.printStandard();
                }
                else {
                    System.out.println("Library catalog is empty!");
                }
            }
            else if (input[0].equals("PN")) { // number sorted print of catalog
                if (library.getNumBooks() > 0) {
                    library.printByNumber();
                }
                else {
                    System.out.println("Bookshelf is empty!");
                }
            }
            else if (input[0].equals("PD")) { // date sorted print of catalog
                if (library.getNumBooks() > 0) {
                    library.printByDate();
                }
                else {
                    System.out.println("Bookshelf is empty!");
                }
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
