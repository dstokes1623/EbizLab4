package database;

import domain.Employee;
import domain.HourlyEmployee;
import domain.SalaryEmployee;
import exceptions.RecordNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class EmployeeDA {

    public static void add(Employee emp) {
        int empID = emp.getEmployeeID();
        int empType = emp.getEmployeeType();
        String firstName = emp.getFirstName();
        String lastName = emp.getLastName();
        long SSN = emp.getSSN();
        String userName = emp.getUserID();
        String password = emp.getPassword();

        String sqlString = "Insert INTO Employee "
                + "(Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password) "
                + "Values "
                + "(" + empID + ", " + empType + ", " + firstName + ", " + lastName + ", " + SSN + ", " + userName + ", " + password + ")";
        
        Connection connection = PayrollSystemDA.getConnection();
        
        try{
          Statement statement = connection.createStatement();  
          
          statement.executeUpdate(sqlString);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static Employee find(int ID) throws RecordNotFoundException, SQLException {
        Employee employee = null;
        int empID;
        int empType;
        String firstName;
        String lastName;
        long SSN;
        String userName;
        String password;

        String sqlString = "Select Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password "
                + "From Employee "
                + "Where Employee_ID = '" + ID + "'";

        System.out.println(sqlString);
        System.out.println("getting connection");
        Connection connection = PayrollSystemDA.getConnection();
        System.out.println("connection received");
        try {
            System.out.println("creating statement");
            Statement statement = connection.createStatement();
            ResultSet rs;
            System.out.println("executing query");
            rs = statement.executeQuery(sqlString);

            rs.next();

            empID = rs.getInt(1);
            System.out.println(empID);
            empType = rs.getInt(2);
            System.out.println(empType);
            firstName = rs.getString(3);
            System.out.println(firstName);
            lastName = rs.getString(4);
            System.out.println(lastName);
            SSN = rs.getLong(5);
            System.out.println(SSN);
            userName = rs.getString(6);
            System.out.println(userName);
            password = rs.getString(7);
            System.out.println(password);

            if (empType == Employee.HOURLY) {
                employee = new HourlyEmployee();
                sqlString = "Select Hourly_Rate, Overtime_Rate "
                        + "From Hourly_Employee "
                        + "Where Employee_ID = " + empID;
                System.out.println(sqlString);
                rs = statement.executeQuery(sqlString);
                rs.next();

                double hourlyRate = rs.getDouble(1);
                double overtimeRate = rs.getDouble(2);
                employee.setHourlyRate(hourlyRate);
                employee.setOvertimeRate(overtimeRate);

            } else {
                employee = new SalaryEmployee();
                sqlString = "Select Salary "
                        + "From Salary_Employee "
                        + "Where Employee_ID = " + empID;
                rs = statement.executeQuery(sqlString);
                rs.next();

                double Salary = rs.getDouble(1);
                employee.setSalary(Salary);
            }

            employee.setEmployeeID(empID);
            employee.setEmployeeType(empType);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setSSN(SSN);
            employee.setUserID(userName);
            employee.setPassword(password);
            
            statement.close();

        } catch (Exception e) {
            System.out.println("Exception = " + e);
            RecordNotFoundException ex = new RecordNotFoundException("Employee " + ID + " not found.");
            throw ex;
        } 
        System.out.println(employee);
        return employee;
    }

    public static Employee findByUserID(String userID) throws RecordNotFoundException, SQLException {
        Employee employee = null;
        int empID;
        int empType;
        String firstName;
        String lastName;
        long SSN;
        String userName;
        String password;

        String sqlString = "Select Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password "
                + "From Employee "
                + "Where User_ID = '" + userID + "'";

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

            rs.next();

            empID = rs.getInt(1);
            System.out.println(empID);
            empType = rs.getInt(2);
            System.out.println(empType);
            firstName = rs.getString(3);
            System.out.println(firstName);
            lastName = rs.getString(4);
            System.out.println(lastName);
            SSN = rs.getLong(5);
            System.out.println(SSN);
            userName = rs.getString(6);
            System.out.println(userName);
            password = rs.getString(7);
            System.out.println(password);

            if (empType == Employee.HOURLY) {
                employee = new HourlyEmployee();
                sqlString = "Select Hourly_Rate, Overtime_Rate "
                        + "From Hourly_Employee "
                        + "Where Employee_ID = " + empID;
                System.out.println(sqlString);
                rs = statement.executeQuery(sqlString);
                rs.next();

                double hourlyRate = rs.getDouble(1);
                double overtimeRate = rs.getDouble(2);
                employee.setHourlyRate(hourlyRate);
                employee.setOvertimeRate(overtimeRate);

            } else {
                employee = new SalaryEmployee();
                sqlString = "Select Salary "
                        + "From Salary_Employee "
                        + "Where Employee_ID = " + empID;
                rs = statement.executeQuery(sqlString);
                rs.next();

                double Salary = rs.getDouble(1);
                employee.setSalary(Salary);
            }

            employee.setEmployeeID(empID);
            employee.setEmployeeType(empType);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setSSN(SSN);
            employee.setUserID(userName);
            employee.setPassword(password);
            
            statement.close();

        } catch (Exception e) {
            System.out.println("Exception = " + e);
            RecordNotFoundException ex = new RecordNotFoundException("Employee " + userID + " not found.");
            throw ex;
        } 
        System.out.println(employee);
        return employee;
    }

    public static ArrayList<Employee> getEmployees() throws SQLException, RecordNotFoundException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee employee = null;
        int empID;
        int empType;
        String firstName;
        String lastName;
        long SSN;
        String userName;
        String password;

        String sqlString = "Select Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password "
                + "From Employee ";

        Connection connection = PayrollSystemDA.getConnection();

        try {
            System.out.println("creating statement");
            Statement statement = connection.createStatement();
            ResultSet rs;
            System.out.println("executing query");
            rs = statement.executeQuery(sqlString);

            while (rs.next()) {
                empID = rs.getInt(1);
                System.out.println(empID);
                empType = rs.getInt(2);
                System.out.println(empType);
                firstName = rs.getString(3);
                System.out.println(firstName);
                lastName = rs.getString(4);
                System.out.println(lastName);
                SSN = rs.getLong(5);
                System.out.println(SSN);
                userName = rs.getString(6);
                System.out.println(userName);
                password = rs.getString(7);
                System.out.println(password);

                if (empType == Employee.HOURLY) {

                    ResultSet hourlyRS;
                    employee = new HourlyEmployee();
                    sqlString = "Select Hourly_Rate, Overtime_Rate "
                            + "From Hourly_Employee "
                            + "Where Employee_ID = " + empID;
                    System.out.println(sqlString);
                    hourlyRS = statement.executeQuery(sqlString);
                    hourlyRS.next();

                    double hourlyRate = hourlyRS.getDouble(1);
                    double overtimeRate = hourlyRS.getDouble(2);
                    employee.setHourlyRate(hourlyRate);
                    employee.setOvertimeRate(overtimeRate);

                } else {
                    employee = new SalaryEmployee();
                    ResultSet salaryRS;
                    sqlString = "Select Salary "
                            + "From Salary_Employee "
                            + "Where Employee_ID = " + empID;
                    salaryRS = statement.executeQuery(sqlString);
                    salaryRS.next();

                    double Salary = salaryRS.getDouble(1);
                    employee.setSalary(Salary);
                }
                employee.setEmployeeID(empID);
                employee.setEmployeeType(empType);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setSSN(SSN);
                employee.setUserID(userName);
                employee.setPassword(password);

                employees.add(employee);
                
                statement.close();
            }
        } catch (Exception e) {
            System.out.println("Exception = " + e);
            RecordNotFoundException ex = new RecordNotFoundException("Employees not found");
            throw ex;
        }
        return employees;
    }

}
