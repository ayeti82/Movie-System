package msp;

import java.sql.Connection;

import java.sql.DriverManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.sql.SQLException;

public class CommonFunctions {
    //Default Constructor
    public CommonFunctions() {
        super();
    }
    //returns Integer
    public static Integer parseInteger(String inputText) {
        Integer retVal;
        try {
            retVal = Integer.parseInt(inputText);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }
    //returns Double
    public static Double parseDouble(String inputText) {
        Double retVal;
        try {
            retVal = Double.parseDouble(inputText);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }
    
    //returns java.sql.Date
    public static java.sql.Date parseDate(String inputText) {
        Date retDate;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
            retDate = df.parse(inputText);
            String newDateString = df.format(retDate);
        } catch (Exception e) {
            retDate = null;
        }
        
        return new java.sql.Date(retDate.getTime());
    }
    
    //returns Connection
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con =DriverManager.getConnection("jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=MovieSystem", "sa",
                                                 "as7Ipt8");
        }catch(SQLException e) {
                e.printStackTrace();
                }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
