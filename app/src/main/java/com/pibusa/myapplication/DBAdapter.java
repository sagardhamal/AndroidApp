package com.pibusa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    private final Context _context;
    private DatabaseHelper _databaseHelper;
    private SQLiteDatabase _sqliteDB;

    /**
     * Constructor of class
     *
     * @param context
     */
    public DBAdapter(Context context) {
        this._context = context;
        _databaseHelper = new DatabaseHelper(_context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DatabaseConstants.DATABASE_NAME, null,
                    DatabaseConstants.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DatabaseConstants.CREATE_DATA_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "
                    + DatabaseConstants.TABLE_DATA);
        }

    }

    /**
     * Method to Open the database
     *
     * @return DBAdapter opened writable database
     * @throws SQLException if the database cannot be opened for writing
     */
    public DBAdapter open() throws SQLException {
        _sqliteDB = _databaseHelper.getWritableDatabase();
        return this;
    }

    /**
     * Method to close the database
     */
    public void close() {
        _databaseHelper.close();
        _databaseHelper = null;
    }

    /**
     * Method to get database store path
     */
    public String DataBasePath() {
        return _sqliteDB.getPath();
    }

    /**
     * Method to delete table rows
     *
     * @param tableName
     * @param primaryID
     * @param rowId
     * @return boolean true if row is deleted else false
     */
    public boolean deleteTableRow(String tableName, String primaryID,
                                  String rowId) {
        return _sqliteDB.delete(tableName, primaryID + "=" + rowId, null) > 0;
    }



    public long DeleteRow(String tableName, String coloumnName,
                          String dateToDeleteData) {
        long value = -1;
        /*
         * String strFilter = " " + coloumnName + " = '" + dateToDeleteData +
		 * "'"; try { _sqliteDB.delete(tableName, strFilter, null); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
        _sqliteDB.delete(tableName, coloumnName + "=?",
                new String[]{dateToDeleteData});
        return value;

    }

    /**
     * Method to delete table
     *
     * @param tableName
     * @return boolean true if table is deleted else false
     */
    public boolean clearTable(String tableName) {
        return _sqliteDB.delete(tableName, null, null) > 0;
    }

    /**
     * Method to add perodic data which is failed to be sent over socket to
     * database
     *
     * @param startDate
     * @param perodicData
     * @param isSend 1=send ,0=not send
     * @return long the row ID of the newly inserted row, or -1 if an error
     * occurred
     */
    public long addData(String startDate, String perodicData, String isSend) {
        ContentValues args = new ContentValues();
        args.put(DatabaseConstants.COLOUMN_PERODIC_DATA, perodicData);
        return _sqliteDB.insert(DatabaseConstants.TABLE_DATA, null,
                args);
    }



    /**
     * Method to get data from particulare table
     *
     * @param tableName
     * @return Cursor containing data
     */
    public Cursor getTableData(String tableName) {
        return _sqliteDB.rawQuery("SELECT * from " + tableName, null);
    }


    /**
     * * Method to delete particular row from table
     *
     * @param tableName
     * @param coloumnName
     * @param dateToDeleteData
     * @return long the number of rows affected if a whereClause is passed in, 0
     * otherwise. To remove all rows and get a count pass "1" as the
     * whereClause.
     */
    public long deleteRowsFromTable(String tableName, String coloumnName,
                                    String dateToDeleteData) {
        String strFilter = null;
        strFilter = " " + coloumnName + " < '" + dateToDeleteData + "'";
        long value = -1;
        try {
            value = _sqliteDB.delete(tableName, strFilter, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Update notifation table
     *
     * @param tableName
     * @param uniqueColoumnName
     * @param updateColoumnName
     * @param id
     * @param updatedValue
     * @return updated row id otherwise -1
     */
    public long update(String tableName, String uniqueColoumnName,
                       String updateColoumnName, int id, String updatedValue) {
        long value = -1;
        ContentValues values = new ContentValues();
        values.put(updateColoumnName, updatedValue);
        // updating row
        value = _sqliteDB.update(tableName, values, uniqueColoumnName + " = ?",
                new String[]{String.valueOf(id)});
        return value;
    }


    }
