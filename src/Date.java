import java.util.Calendar;

/**
 * An abstract data type to hold, modify, and display a date.
 * Each Date object has three ints to specify the date's year, month, and day.
 * Ensures that a date is valid before storing.
 *
 * @author Jin Sebastian, Abhinav Sirohi
 */
public class Date {
    private int year;
    private int month;
    private int day;

    // constants used in leap year checks
    public static final int MIN_YEAR = 1900;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    // constants representing the months numerically
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

    // constants representing the ranges of the months and days
    public static final int MIN_DAY = 1;
    public static final int STANDARD_MONTH = 30;
    public static final int EXTENDED_MONTH = 31;
    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;

    public static final int MONTH_OFFSET = 1;

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
        this.month = cal.get(Calendar.MONTH) + MONTH_OFFSET;
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.year = cal.get(Calendar.YEAR);
    }

    /**
     * Checks if a date meets certain criteria
     *
     * @return true if date is valid, otherwise returns false
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
            } else {
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
     *
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

    /**
     * Checks if input date is equal to this date.
     *
     * @param date Date to be compared
     * @return true if input date is equal to this date chronologically, false otherwise
     */
    public boolean isEqual(Date date) {
        if (this.year == date.getYear() && this.month == date.getMonth()
                && this.day == date.getDay()) {
            return true;
        }
        return false;
    }


    /**
     * Method to display the Date object in mm/dd/yyyy format
     *
     * @return string in mm/dd/yyyy format
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Getter method for private variable year
     *
     * @return the year of the current date object
     */
    public int getYear() {
        return year;
    }

    /**
     * Getter method for private variable month
     *
     * @return the month of the current date object
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter method for private variable day
     *
     * @return the day of the current date object
     */
    public int getDay() {
        return day;
    }

    /**
     * Testbed main used to test the code in this class
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // default date (current Date)
        Date date1 = new Date();
        System.out.println("The date, " + date1.toString() + ", is valid: " + date1.isValid());

        // leap year
        Date date2 = new Date("2/29/2019");
        System.out.println("The date, " + date2.toString() + ", is valid: " + date2.isValid());
        Date date3 = new Date("02/29/2020");
        System.out.println("The date, " + date3.toString() + ", is valid: " + date3.isValid());

        // invalid future date
        Date date4 = new Date("1/1/3030");
        System.out.println("The date, " + date4.toString() + ", is valid: " + date4.isValid());

        // standard and Nonstandard months
        Date date5 = new Date("4/31/2019");
        System.out.println("The date, " + date5.toString() + ", is valid: " + date5.isValid());

        // dates outside allowed range
        Date date6 = new Date("1/32/2019");
        System.out.println("The date, " + date6.toString() + ", is valid: " + date6.isValid());
        Date date7 = new Date("1/-1/2019");
        System.out.println("The date, " + date7.toString() + ", is valid: " + date7.isValid());
        Date date8 = new Date("12/5/1899");
        System.out.println("The date, " + date8.toString() + ", is valid: " + date8.isValid());
        Date date9 = new Date("0/5/1901");
        System.out.println("The date, " + date9.toString() + ", is valid: " + date9.isValid());
    }
}
