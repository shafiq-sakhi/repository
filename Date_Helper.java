
package repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Date_Helper {
    
        public static  boolean isValidDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
        formatter.setLenient(false);

        try {
            formatter.parse(dateStr);
            String[] dateArray = dateStr.split("/");
            String year = dateArray[0];
            String month = dateArray[1];
            String day = dateArray[2];

            if (year.length() != 4) {
                return false;
            }

            if (month.length() != 2 && month.length() != 1) {
                return false;
            }

            if (day.length() != 2 && day.length() != 1) {
                return false;
            }

        } catch (ParseException ex) {
            return false;

        }

        return true;

    }
}
