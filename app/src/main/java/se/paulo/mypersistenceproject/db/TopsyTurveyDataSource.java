package se.paulo.mypersistenceproject.db;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import se.paulo.mypersistenceproject.models.Recipe;
import se.paulo.mypersistenceproject.models.RecipeFields;

/** * Created by Paulo Vila Nova on 2017-07-04.
 */

public class TopsyTurveyDataSource {

    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    private Realm realm;


    public void open(){
        realm = Realm.getDefaultInstance();
        Log.d(TAG, "Database is opened");
    }

    public void close(){
        realm.close();
        Log.d(TAG, "Database is closed");
    }


    public void createRecipe(final Recipe recipe){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(recipe);
            }
        });
        Log.d(TAG, "Recipe with id: " + recipe.getId());
    }

    public List<Recipe> getAllRecipes(){

        return realm.where(Recipe.class)
//                .isNotEmpty(RecipeFields.STEPS.$)
//                .or()
//                .contains(RecipeFields.NAME, "ie")
                .findAll();
    }


    public void modifyDescription(){
        final Recipe recipe = realm.where(Recipe.class).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipe.setDescription("Wonderful yellow cake!");
            }
        });
    }


    public void deleteRecipe(Recipe recipe){
        final Recipe recipeManage = realm.where(Recipe.class).equalTo(RecipeFields.ID, recipe.getId()).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipeManage.deleteFromRealm();
            }
        });
    }


    public void deleteAllRecipe(){
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipes.deleteAllFromRealm();
            }
        });
    }


}
