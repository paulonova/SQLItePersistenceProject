package se.paulo.mypersistenceproject.db;

import android.provider.BaseColumns;

/** * Created by Paulo Vila Nova on 2017-07-03.
 */

final class RecipeContract {

    private RecipeContract() {}

    static final String CREATE_RECIPE_ENTRY_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME +
            " ( " +
            RecipeEntry._ID + " INTEGER PRIMERY KEY AUTOINCREMENT, " +
            RecipeEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_IMAGE_RESOURCE_ID + " INTEGER NOT NULL, " +
            "UNIQUE ( " + RecipeEntry._ID + ") ON CONFLICT REPLACE )";

    /**
     * UNIQUE: What this is going to do is allow us to insert records into our recipe table without
     * having to worry about conflicts as those things will automatically be replaced
     * if something has a matching ID. This is a standard way of handling duplicate ID insertions with SQLite*/



    public static class RecipeEntry implements BaseColumns{

        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id";

    }

    public static class RecipeStepEntry implements BaseColumns {
        
        public static final String TABLE_NAME = "recipe_step";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_NUMBER = "step_number";
        public static final String COLUMN_INSTRUCTION = "instruction";
    }

}
