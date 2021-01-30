public class Date {
    private int year;
    private int month;
    private int day;
    public Date(String date) {
        //taking mm/dd/yyyy and create a Date object
    }
    public Date() {
        //return today’s date
    }
    public boolean isValid() {
        return true;
    }

    /**
     * Checks if input date comes after this date chronologically.
     * @param date Date to be compared
     * @return true if input date is after this date chronologically, false otherwise
     */
    public boolean isAfter(Date date) {
        return true;
    }
}
