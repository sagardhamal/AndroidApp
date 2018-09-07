package com.pibusa.database;

public class DatabaseConstants {

    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "My_test_v1.0";

    // Perodic data keeping table sent via socket
    public static final String TABLE_DATA = "Table_Data";
    public static final String COLOUMN_PERODIC_DATA_ROW_ID = "Data_Row_Id";
    public static final String COLOUMN_PERODIC_DATA = "Perodic_Data";


    // Table string to store trip data
    public static final String CREATE_DATA_TABLE = "Create Table if not exists "
            + TABLE_DATA
            + "("
            + COLOUMN_PERODIC_DATA_ROW_ID
            + " Integer primary key autoincrement, "
            + COLOUMN_PERODIC_DATA + " text not null);";

}
