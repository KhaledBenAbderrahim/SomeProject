export class Receipe {
    constructor(receipeId, name, time, categories, ingredients, steps) {
        this.receipeId = receipeId;
        this.name = name;
        this.time = time;
        this.categories = categories;
        this.ingredients = ingredients;
        this.steps = steps;
    }
}