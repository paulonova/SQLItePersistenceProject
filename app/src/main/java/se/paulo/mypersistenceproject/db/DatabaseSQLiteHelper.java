package se.paulo.mypersistenceproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import nl.littlerobots.cupboard.tools.gson.GsonListFieldConverterFactory;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;
import se.paulo.mypersistenceproject.models.Recipe;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/** * Created by Paulo Vila Nova on 2017-07-03.
 */

public class DatabaseSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "topsy_turvey.db";
    private static final int VERSION_NUMBER = 2;
    private static final String TAG = DatabaseSQLiteHelper.class.getSimpleName();



    //Constructor
//  public DatabaseSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    public DatabaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    static{
        CupboardFactory.setCupboard(
//                new CupboardBuilder().useAnnotations().build()
                new CupboardBuilder().registerFieldConverterFactory(new GsonListFieldConverterFactory(new Gson())).build()
        );
        cupboard().register(Recipe.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
        Log.d(TAG, "onCreate: database created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        cupboard().withDatabase(db).dropAllTables();
//        cupboard().withDatabase(db).createTables();
        cupboard().withDatabase(db).upgradeTables();
        if(oldVersion == 1){
            ContentValues values = new ContentValues();
            values.put("numberOfStars", 5);
            cupboard().withDatabase(db).update(Recipe.class, values);
        }

        Log.d(TAG, "onUpgrade: database updated");

    }






}
