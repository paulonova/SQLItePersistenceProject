package se.paulo.mypersistenceproject.models;

import java.util.List;

/** * Created by Paulo Vila Nova on 2017-07-03.
 */

public class Recipe {

    private Long _id;

    private String name;

    private String description;

    private int imageResourceId;

    private List<RecipeStep> steps;

    private Integer numberOfStars;

    public Recipe() {
    }

    public Recipe(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
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

    public List<RecipeStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps) {
        this.steps = steps;
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
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageResourceId=" + imageResourceId +
                '}';
    }
}
