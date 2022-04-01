package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayrollSystemDA {
    private static Connection connection;
    
    public static void initialize() throws SQLException {
//        EmployeeDA.initialize();
//        TimecardDA.initialize();
        try{
             Connection connection;
             connection = DriverManager.getConnection("jdbc:derby://localhost:1527/PayrollSystemDB", "CIS640", "cis640");
        } catch(Exception e){
            System.out.println(e);
        }
         
        WithholdingTypeDA.initialize();
    }

    public static Connection getConnection() {
        return connection;
    }
    
    
}
