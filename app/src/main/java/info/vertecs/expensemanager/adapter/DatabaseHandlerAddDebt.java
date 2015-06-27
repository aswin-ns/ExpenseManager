package info.vertecs.expensemanager.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 27-06-2015.
 */
public class DatabaseHandlerAddDebt extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DebtManager";

    // logins table name
    private static final String TABLE_DEBT = "debt";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TRS_TYPE = "type";
    private static final String KEY_BORROWED_AMNT = "borrowedamount";
    private static final String KEY_LEASED_AMNT = "lended_amnt";

    private static final String KEY_NAME = "name";




    public DatabaseHandlerAddDebt(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DEBT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME+ " TEXT,"
                + KEY_TRS_TYPE + " TEXT," + KEY_BORROWED_AMNT + " INTEGER,"+ KEY_LEASED_AMNT + " INTEGER" + ")";
        db.execSQL(CREATE_DATA_TABLE);
    }

public void OnDebtAdd(Debt dbt)
{
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, dbt.getName()); // Contact Phone
    values.put(KEY_TRS_TYPE, dbt.getType()); // Contact Name
    values.put(KEY_BORROWED_AMNT, dbt.getBorrowedamnt());
 // Contact Name

    // Inserting Row
    db.insert(TABLE_DEBT, null, values);
    db.close(); // Closing database connection
}
    public void OnLeaseAdd(Debt dbt)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, dbt.getName()); // Contact Phone
        values.put(KEY_TRS_TYPE, dbt.getType()); // Contact Name
        values.put(KEY_LEASED_AMNT, dbt.getLeasedamnt());
        // Contact Name

        // Inserting Row
        db.insert(TABLE_DEBT, null, values);
        db.close(); // Closing database connection
    }

List<Debt>getDebtData() {
    List<Debt> dbtdata = new ArrayList<Debt>();
    SQLiteDatabase db = this.getWritableDatabase();
    String selectQuery = "select name, borrowedamount from debt where date like '%borrowed' ";

    Cursor cursor = db.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
        do {
            Debt dbt = new Debt();
            dbt.setName(cursor.getString(0));
            dbt.setBorrowedamnt(Integer.parseInt(cursor.getString(1)));
            dbtdata.add(dbt);
        } while (cursor.moveToNext());
    }
        return dbtdata;
    }
    List<Debt>getLeaseData() {
        List<Debt> dbtdata = new ArrayList<Debt>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "select name, leasedamount from debt where date like '%lended' ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Debt dbt = new Debt();
                dbt.setName(cursor.getString(0));
                dbt.setLeasedamnt(Integer.parseInt(cursor.getString(1)));
                dbtdata.add(dbt);
            } while (cursor.moveToNext());
        }
        return dbtdata;
    }

    public void deletedetails(Debt dbt) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEBT, KEY_NAME + " = ?",
                new String[] { dbt.getName() });
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
