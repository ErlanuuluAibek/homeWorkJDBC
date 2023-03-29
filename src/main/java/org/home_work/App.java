package org.home_work;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println(DB.getListCities());
        System.out.println(DB.getListCountries());


    }

}
