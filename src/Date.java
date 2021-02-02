import java.util.Calendar;

public class Date {
    private int year;
    private int month;
    private int day;

    public static final int MIN_YEAR = 1900;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    public static final int MIN_DAY = 1;
    public static final int STANDARD_MONTH = 30;
    public static final int EXTENDED_MONTH = 31;
    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;

    /**
     * A Date constructor to create a Date object based on a date in the format mm/dd/yyyy
     *
     * @param date
     */
    public Date(String date) {
        // split date String by "/" to isolate month, day, and year
        String[] dateData = date.split("/");

        this.month = Integer.parseInt(dateData[0]);
        this.day = Integer.parseInt(dateData[1]);
        this.year = Integer.parseInt(dateData[2]);
    }

    /**
     * A Date constructor that returns today's date
     */
    public Date() {
        Calendar cal = Calendar.getInstance();

        // get values of all the calendar date fields
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.year = cal.get(Calendar.YEAR);
    }

    /**
     * Checks if a date meets certain criteria
     *
     * @return
     */
    public boolean isValid() {
        // ensure the date is within the min year and the current date
        if (this.year < MIN_YEAR || this.isAfter(new Date())) {
            return false;
        }

        // check day range
        if (this.day < MIN_DAY || this.day > EXTENDED_MONTH) {
            return false;
        }

        // check month range
        if (this.month < MIN_MONTH || this.month > MAX_MONTH) {
            return false;
        }

        // check if day is greater than 30 for months that only have 30 days
        if (this.month == APRIL || this.month == JUNE || this.month == SEPTEMBER || this.month == NOVEMBER) {
            if (this.day > STANDARD_MONTH) {
                return false;
            }
        }

        // check if this.year is a leap year
        boolean isLeap = false;
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                if (this.year % QUARTERCENTENNIAL == 0) {
                    isLeap = true;
                }
            }
            else {
                isLeap = true;
            }
        }

        // check if the day is valid for February
        if (this.month == FEBRUARY) {
            if (this.day > 29 || (this.day == 29 && !isLeap)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if input date comes after this date chronologically.
     * @param date Date to be compared
     * @return true if input date is after this date chronologically, false otherwise
     */
    public boolean isAfter(Date date) {
        // narrow down date if year and month
        if (this.year > date.getYear()) {
            return true;
        }
        if (this.year == date.getYear()) {
            if (this.month > date.getMonth()) {
                return true;
            }
            if (this.month == date.getMonth()) {
                if (this.day > date.getDay()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Getter method for private variable year
     * @return the year of the current date object
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter method for private variable month
     * @return the month of the current date object
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter method for private variable day
     * @return the day of the current date object
     */
    public int getDay() {
        return day;
    }
}
