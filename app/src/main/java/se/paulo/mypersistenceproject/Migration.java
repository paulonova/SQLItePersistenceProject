package se.paulo.mypersistenceproject;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import se.paulo.mypersistenceproject.models.RecipeFields;

/** * Created by Paulo Vila Nova on 2017-07-11.
 */

class Migration implements RealmMigration {


    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if(oldVersion == 1){
            RealmObjectSchema recipeSchema = schema.get("Recipe");
            recipeSchema.addField(RecipeFields.NUMBER_OF_STARS, Integer.class)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set(RecipeFields.NUMBER_OF_STARS, 5);
                        }
                    });
        }
    }
}
