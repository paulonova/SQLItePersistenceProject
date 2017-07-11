package se.paulo.mypersistenceproject.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import se.paulo.mypersistenceproject.R;
import se.paulo.mypersistenceproject.models.Recipe;
import se.paulo.mypersistenceproject.models.RecipeStep;

/** * Created by Paulo Vila Nova on 2017-07-03.
 */

public class RecipesDataProvider {

    public static List<Recipe> recipesList;

    static
    {
        recipesList = new ArrayList<>();

        addRecipe(new Recipe("Cake", "Wonderful cake", R.drawable.cake_1, 4));

        addRecipe(new Recipe("Pie", "Delicious Pie", R.drawable.pie_1, 3));

        addRecipe(new Recipe("Pound Cake", "Fluffy cake", R.drawable.cake_2, 5),
                new RecipeStep(1, "mix all the ingredients"),
                new RecipeStep(2, "preheat the oven"),
                new RecipeStep(3, "stir"),
                new RecipeStep(4, "bake"));
    }

    private static void addRecipe(Recipe recipe, RecipeStep... steps) {
        if (steps.length > 0) {
            recipe.setSteps(new RealmList<RecipeStep>());
            recipe.getSteps().addAll(Arrays.asList(steps));
        }
        recipesList.add(recipe);
    }

}
