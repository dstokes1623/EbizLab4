package domain;

import database.TimecardDA;
import exceptions.RecordNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timecard implements Serializable{
    private int timecardID;
    private Date date;
    private int employeeID;
    private double hoursWorked;
    private double overtimeHours;
    
    public Timecard(){
        this.setTimecardID(-1);
        this.setDate(new Date());
        this.setHoursWorked(0.0);
        this.setOvertimeHours(0.0);
    }
    
    public void add() {
        TimecardDA.add(this);
    }
    
    public void delete(){
        TimecardDA.delete(this);
    }
    
    public static Timecard find(int id) throws RecordNotFoundException, SQLException{
        return TimecardDA.find(id);
    }

    public Date getDate() {
        return date;
    }
    
    public String getDateShort(){
        return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
    } 
    
    public String getDateFormatted(){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        return dateFormat.format(date);
    }

    public int getEmployeeID() {
        return employeeID;
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID) throws SQLException, RecordNotFoundException{
        try {
            return TimecardDA.getEmployeeTimecards(ID);
        } catch (RecordNotFoundException ex) {
            Logger.getLogger(Timecard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Timecard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID, Date begDate, Date endDate) throws SQLException, RecordNotFoundException {
        try {
            return TimecardDA.getEmployeeTimecards(ID, begDate, endDate);
        } catch (RecordNotFoundException ex) {
            Logger.getLogger(Timecard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public double getHoursWorked() {
        return hoursWorked;
    }
    
    public String getHoursWorkedFormatted() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(hoursWorked);
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }
    
    public String getOvertimeHoursFormatted(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(overtimeHours);
    }

    public int getTimecardID() {
        return timecardID;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setTimecardID(int timecardID) {
        this.timecardID = timecardID;
    }
    
    public String toString(){
        return getDateFormatted() + "  " + employeeID + "  " + hoursWorked + "  " + overtimeHours;
    }
    
    public void update() throws RecordNotFoundException, SQLException{
        try {
            TimecardDA.update(this);
        } catch (RecordNotFoundException ex) {
            Logger.getLogger(Timecard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Timecard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}