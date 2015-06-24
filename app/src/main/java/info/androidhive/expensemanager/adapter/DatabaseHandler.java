package info.androidhive.expensemanager.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 02-06-2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Login";

    // logins table name
    private static final String TABLE_DETAILS = "details";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USR_NAME = "username";
    private static final String KEY_PS_WRD = "password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USR_NAME + " TEXT,"
                + KEY_PS_WRD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
   public void addContact(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USR_NAME, login.getUsername()); // Contact Name
        values.put(KEY_PS_WRD, login.getPassword()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_DETAILS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Login getlogin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DETAILS, new String[]{KEY_ID,
                        KEY_USR_NAME, KEY_PS_WRD}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Login login = new Login((Integer.parseInt(cursor.getString(0))),
                cursor.getString(1), cursor.getString(2));
        // return login
        return login;
    }

    // Getting All logins
    public List<Login> getAllContacts() {
        List<Login> loginList = new ArrayList<Login>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DETAILS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Login login = new Login();
                login.setId(Integer.parseInt(cursor.getString(0)));
                login.setUsername(cursor.getString(1));
                login.setPassword(cursor.getString(2));
                // Adding login to list
                loginList.add(login);
            } while (cursor.moveToNext());
        }

        // return login list
        return loginList;
    }

    // Function to validate password of given username
    public static Boolean validatePwd(String userName, String passWord) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DETAILS,new String[] {KEY_PS_WRD},KEY_USR_NAME+
                                    "=?",new String[] {userName},null,null,null,"1");
        if (cursor != null){
            cursor.moveToFirst();
            return cursor.getString(0).equals(passWord);
        }
        else {
            return false;
        }

    }

    // Updating single contact
    public int updateContact(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USR_NAME, login.getUsername());
        values.put(KEY_PS_WRD, login.getPassword());

        // updating row
        return db.update(TABLE_DETAILS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(login.getId()) });
    }

    // Deleting single login
    public void deletelogin(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DETAILS, KEY_ID + " = ?",
                new String[] { String.valueOf(login.getId()) });
        db.close();
    }


    // Getting logins Count
    public int getloginsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}

