package se.paulo.mypersistenceproject.models;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/** * Created by Paulo Vila Nova on 2017-07-03.
 */

public class Recipe extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    private int imageResourceId;

    private RealmList<RecipeStep> steps;

    private Integer numberOfStars;

    public Recipe() {
    }

    public Recipe(String name, String description, int imageResourceId, Integer numberOfStars) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.numberOfStars = numberOfStars;
    }

    public RealmList<RecipeStep> getSteps() {
        return steps;
    }

    public void setSteps(RealmList<RecipeStep> steps) {
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number of stars='" + numberOfStars + '\'' +
                ", imageResourceId=" + imageResourceId +
                '}';
    }
}
