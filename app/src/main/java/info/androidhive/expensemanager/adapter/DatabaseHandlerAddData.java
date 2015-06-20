package info.androidhive.expensemanager.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 02-06-2015.
 */
public class DatabaseHandlerAddData extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ExpenseManager";

    // logins table name
    private static final String TABLE_DATA = "db";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TRS_TYPE = "type";
    private static final String KEY_AMNT = "amount";
    private static final String KEY_NEG_AMNT = "negativeamount";
    private static final String KEY_TRS_NOTE = "note";
    private static final String KEY_TRS_DATE = "date";



    public DatabaseHandlerAddData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TRS_TYPE + " TEXT,"
                + KEY_AMNT + " INTEGER,"+ KEY_NEG_AMNT + " INTEGER,"
                + KEY_TRS_NOTE+ " TEXT,"+ KEY_TRS_DATE + " TEXT" + ")";
        db.execSQL(CREATE_DATA_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
   public void addPosData(DataClass dcl) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRS_TYPE, dcl.getType()); // Contact Phone
        values.put(KEY_AMNT, dcl.getAmnt()); // Contact Name
        values.put(KEY_TRS_NOTE, dcl.getNote());
        values.put(KEY_TRS_DATE, dcl.getDate()); // Contact Name

        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }
    public void addNegData(DataClass dcl) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRS_TYPE, dcl.getType()); // Contact Phone
        values.put(KEY_NEG_AMNT, dcl.getNeg_amnt()); // Contact Name
        values.put(KEY_TRS_NOTE, dcl.getNote());
        values.put(KEY_TRS_DATE, dcl.getDate()); // Contact Name

        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }
    //mnnn////mmmm//
    public List<DataClass>getPosData(String date)
    {


        List<DataClass> dataList = new ArrayList<DataClass>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = " select coalesce(sum(amount),0) from db where date like '%"+date+"%';";

        Cursor cursor = db.rawQuery(selectQuery, null);


//        Cursor cursor = db.query(TABLE_DATA, new String[] { "COALESCE(SUM("+KEY_AMNT+"),0)"
//                }, KEY_TRS_DATE + "=?",
//                new String[] { String.valueOf(date) }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataClass adc = new DataClass();
                adc.setAmnt(Integer.parseInt(cursor.getString(0)));
                Log.d("abc",String.valueOf(cursor.getString(0)));
                // Adding login to list
                dataList.add(adc);
            } while (cursor.moveToNext());
        }

        // return login list
        return dataList;

    }
    public List<DataClass>getNegData(String date)
    {
        List<DataClass> dataList = new ArrayList<DataClass>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = " select coalesce(sum(negativeamount),0) from db where date like '%"+date+"%';";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataClass adc = new DataClass();
                adc.setNeg_amnt(Integer.parseInt(cursor.getString(0)));

                // Adding login to list
                dataList.add(adc);
            } while (cursor.moveToNext());
        }

        // return login list
        return dataList;

    }

//
    public List<SumByClass>getPIEpos(String date) {
List<SumByClass> dlc = new ArrayList<SumByClass>();
    SQLiteDatabase db = this.getWritableDatabase();

    String selectQuery = " select type, coalesce(sum(amount),0) from db where date like '%"+date+"%'group by type";

    Cursor cursor = db.rawQuery(selectQuery, null);


    if (cursor.moveToFirst()) {
        do {

SumByClass sbc = new SumByClass();
            Log.d("Value", cursor.getString(0));
            Log.d("Value",cursor.getString(1));
           sbc.setGroup_name(cursor.getString(0));
            sbc.setAmnt(cursor.getInt(1));
            dlc.add(sbc);
        } while (cursor.moveToNext());
    }
return dlc;
    // return login list

}

    // Getting All logins
    public List<DataClass> getAllData() {
        List<DataClass> dataList = new ArrayList<DataClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataClass dlc = new DataClass();
                dlc.setId(Integer.parseInt(cursor.getString(0)));
                dlc.setType(cursor.getString(1));
                dlc.setAmnt((Integer.parseInt(cursor.getString(2))));
                dlc.setNeg_amnt(Integer.parseInt(cursor.getString(3)));
                dlc.setNote(cursor.getString(4));
                dlc.setNote(cursor.getString(5));
                // Adding login to list
                dataList.add(dlc);
            } while (cursor.moveToNext());
        }

        // return login list
        return dataList;
    }

    public List<DataClass> getAllPosData() {
        List<DataClass> dataList = new ArrayList<DataClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataClass dlc = new DataClass();
                dlc.setId(Integer.parseInt(cursor.getString(0)));
                dlc.setType(cursor.getString(1));
                dlc.setAmnt((Integer.parseInt(cursor.getString(2))));

                dlc.setNote(cursor.getString(3));
                dlc.setNote(cursor.getString(4));
                // Adding login to list
                dataList.add(dlc);
            } while (cursor.moveToNext());
        }

        // return login list
        return dataList;
    }

    // Updating single contact


    // Getting logins Count
    public int getloginsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}


