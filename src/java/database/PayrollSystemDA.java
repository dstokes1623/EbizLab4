package database;

public class PayrollSystemDA {
    public static void initialize() {
        EmployeeDA.initialize();
        TimecardDA.initialize();
        WithholdingTypeDA.initialize();
    }
}
