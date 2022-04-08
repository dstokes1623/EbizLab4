package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayrollSystemDA {
    private static Connection connection;
    private static boolean isInitialized = false;
    
    public static void initialize() throws SQLException {
//        EmployeeDA.initialize();
//        TimecardDA.initialize();
        if(isInitialized == false){
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/PayrollSystemDB", "CIS640", "cis640");
            System.out.println("PayrollSytemDA.initialize connection = " + connection);
            WithholdingTypeDA.initialize();
            
            isInitialized = true;
        }
         
         
        
    }

    public static Connection getConnection() {
        System.out.println("PayrollSytemDA.getConnection connection = " + connection);
        return connection;
    }
    
    
}
