package database;

import domain.Timecard;
import exceptions.RecordNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimecardDA {
    private static ArrayList<Timecard> timecards = new ArrayList<Timecard>(5);
    private static ArrayList<Timecard> employeeTimecards = new ArrayList<Timecard>();
    
    public static void add(Timecard tc) {
        
        int timecardID = tc.getTimecardID();
        Date date = tc.getDate();
        int employeeID = tc.getEmployeeID();
        double hoursWorked = tc.getHoursWorked();
        double overtimeHours = tc.getOvertimeHours();

        String sqlString = "INSERT INTO Timecard " 
                           + "(Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)"
                + "Values "
                + "(" + timecardID + ", " + date + ", " + employeeID + ", " + hoursWorked + ", " + overtimeHours + ")";
        
        Connection connection = PayrollSystemDA.getConnection();
        
        try{
          Statement statement = connection.createStatement();  
          
          statement.executeUpdate(sqlString);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void delete(Timecard t){
        int timecardID = t.getTimecardID();

        String sqlString = "DELETE FROM Timecard " 
                           + "WHERE Timecard_ID = " + timecardID;
        
        Connection connection = PayrollSystemDA.getConnection();
        
        try{
          Statement statement = connection.createStatement();  
          
          statement.executeUpdate(sqlString);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static Timecard find(int id) throws RecordNotFoundException, SQLException{
        Timecard timecard = null;
        int timecardID;
        Date date;
        int employeeID;
        double hoursWorked;
        double overtimeHours;

        String sqlString = "Select Timecard_ID, Date, Employee_ID, SSN, Hours_Worked, Overtime_Hours "
                + "From Timecard "
                + "Where Timecard_ID = '" + id + "'";

        System.out.println(sqlString);
        System.out.println("getting connection");
        Connection connection = PayrollSystemDA.getConnection();
        System.out.println("connection received" + connection);
        try {
            System.out.println("creating statement");
            Statement statement = connection.createStatement();
            ResultSet rs;
            System.out.println("executing query");
            rs = statement.executeQuery(sqlString);

            rs.next();

            timecardID = rs.getInt(1);
            System.out.println(timecardID);
            date = rs.getDate(2);
            System.out.println(date);
            employeeID = rs.getInt(3);
            System.out.println(employeeID);
            hoursWorked = rs.getDouble(4);
            System.out.println(hoursWorked);
            overtimeHours = rs.getDouble(5);
            System.out.println(overtimeHours);
         

           

            timecard.setTimecardID(timecardID);
            timecard.setDate(date);
            timecard.setEmployeeID(employeeID);
            timecard.setHoursWorked(hoursWorked);
            timecard.setOvertimeHours(overtimeHours);

        } catch (Exception e) {
            System.out.println("Exception = " + e);
            RecordNotFoundException ex = new RecordNotFoundException("Timecard " + id + " not found.");
            throw ex;
        } finally {

            connection.close();

        }
        System.out.println(timecard);
        return timecard;
    }
    
    public static void initialize(){
    }

    public static ArrayList<Timecard> getEmployeeTimecards(int ID) throws RecordNotFoundException, SQLException {
        employeeTimecards.clear();
        
        Timecard timecard = null;
        int timecardID;
        Date date;
        int employeeID;
        double hoursWorked;
        double overtimeHours;

        String sqlString = "Select Timecard_ID, Date, Employee_ID, Hours_Worked, Overtime_Hours "
                + "From Timecard "
                + "Where Employee_ID = '" + ID + "'";

        System.out.println(sqlString);
        System.out.println("getting connection");
        Connection con = PayrollSystemDA.getConnection();
        System.out.println("connection received connection = " + con);
        try {
            System.out.println("creating statement");
            Statement statement = con.createStatement();
            ResultSet rs;
            System.out.println("executing query");
            rs = statement.executeQuery(sqlString);

            while(rs.next()){
                timecardID = rs.getInt(1);
                System.out.println(timecardID);
                date = rs.getDate(2);
                System.out.println(date);
                employeeID = rs.getInt(3);
                System.out.println(employeeID);
                hoursWorked = rs.getDouble(4);
                System.out.println(hoursWorked);
                overtimeHours = rs.getDouble(5);
                System.out.println(overtimeHours);

                timecard.setTimecardID(timecardID);
                timecard.setDate(date);
                timecard.setEmployeeID(employeeID);
                timecard.setHoursWorked(hoursWorked);
                timecard.setOvertimeHours(overtimeHours);
                
                employeeTimecards.add(timecard);
            }
           

        } catch (Exception e) {
            System.out.println("Exception = " + e);
        } finally {

            con.close();

        }
        return employeeTimecards;
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID, Date begDate, Date endDate) throws SQLException, RecordNotFoundException {
        employeeTimecards.clear();
        Timecard timecard = null;
        
        int timecardID;
        Date date;
        int employeeID;
        double hoursWorked;
        double overtimeHours;

        String sqlString = "Select Timecard_ID, Date, Employee_ID, Hours_Worked, Overtime_Hours "
                + "From Timecard "
                + "Where Employee_ID = " + ID + "AND Date between '" + begDate + "' and '" + endDate + "'";

        System.out.println(sqlString);
        System.out.println("getting connection");
        Connection connection = PayrollSystemDA.getConnection();
        System.out.println("connection received connection = " + connection);
        try {
            System.out.println("creating statement");
            Statement statement = connection.createStatement();
            ResultSet rs;
            System.out.println("executing query");
            rs = statement.executeQuery(sqlString);

            while(rs.next()){
                timecardID = rs.getInt(1);
                System.out.println(timecardID);
                date = rs.getDate(2);
                System.out.println(date);
                employeeID = rs.getInt(3);
                System.out.println(employeeID);
                hoursWorked = rs.getDouble(4);
                System.out.println(hoursWorked);
                overtimeHours = rs.getDouble(5);
                System.out.println(overtimeHours);

                timecard.setTimecardID(timecardID);
                timecard.setDate(date);
                timecard.setEmployeeID(employeeID);
                timecard.setHoursWorked(hoursWorked);
                timecard.setOvertimeHours(overtimeHours);
                
                employeeTimecards.add(timecard);
            }
           

        } catch (Exception e) {
            System.out.println("Exception = " + e);
            RecordNotFoundException ex = new RecordNotFoundException("Timecards for employee " + ID + " not found.");
            throw ex;
        } finally {

            connection.close();

        }
        return employeeTimecards;
        
    }
    
    public static void update(Timecard tc) throws RecordNotFoundException, SQLException {
        Timecard timecard = find(tc.getTimecardID());
        Date date = tc.getDate();
        double hoursWorked = tc.getHoursWorked();
        double overtimeHours = tc.getOvertimeHours();
        
        String sqlString = "Update Timecard " + 
                            "Set Date = '" + date + "', Hours_Worked = " + hoursWorked + ", Overtime_Hours = " + overtimeHours +
                            "Where Timecard_ID = " + tc.getTimecardID();
        
        Connection connection = PayrollSystemDA.getConnection();
        
        try{
          Statement statement = connection.createStatement();  
          
          statement.executeUpdate(sqlString);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}