package se.paulo.mypersistenceproject.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import se.paulo.mypersistenceproject.models.Recipe;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/** * Created by Paulo Vila Nova on 2017-07-04.
 */

public class TopsyTurveyDataSource {

    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteHelper dbHelper;


    public TopsyTurveyDataSource(Context context) {
        this.dbHelper = new DatabaseSQLiteHelper(context);
    }


    public void open() throws SQLException {
        this.database = dbHelper.getWritableDatabase();
        Log.d(TAG, "Database is opened");
    }

    public void close(){
        dbHelper.close();
        Log.d(TAG, "Database is closed");
    }


    public void createRecipe(Recipe recipe){

        long rowId = cupboard().withDatabase(database).put(recipe);
        Log.d(TAG, "Recipe with id: " + rowId);
    }


    public List<Recipe> getAllRecipes(){

        return cupboard().withDatabase(database)
                .query(Recipe.class)
                .list();
    }

    public void updateRecipe(Recipe recipe){
        cupboard().withDatabase(database).put(recipe);
    }

    public void deleteRecipe(Recipe recipe){
        cupboard().withDatabase(database).delete(recipe);
//        cupboard().withDatabase(database).delete(Recipe.class, 2L);
    }

    public void deleteAllRecipe(){
        cupboard().withDatabase(database).delete(Recipe.class, null);
    }



}
