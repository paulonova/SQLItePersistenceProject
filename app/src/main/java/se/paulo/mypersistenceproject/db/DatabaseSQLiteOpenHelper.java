package se.paulo.mypersistenceproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** * Created by Paulo Vila Nova on 2017-07-03.
 */

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "topsy_turvey.db";
    public static final int VERSION_NUMBER = 1;


    //Constructor
//  public DatabaseSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    public DatabaseSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
