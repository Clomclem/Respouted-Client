/*
 * This file is part of Spoutcraft.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org/>
 * Spoutcraft is licensed under the GNU Lesser General Public License.
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.api.inventory;

import java.util.HashMap;

import org.spoutcraft.api.material.Material;

public class ShapedRecipe implements Recipe {
	private ItemStack output;
	private String[] rows;
	private HashMap<Character, Material> ingredients = new HashMap<Character, Material>();

	/**
	 * Create a shaped recipe to craft the specified ItemStack. The constructor merely determines the
	 * result and type; to set the actual recipe, you'll need to call the appropriate methods.
	 * @param result The item you want the recipe to create.
	 * @see ShapedRecipe#shape(String...)
	 * @see ShapedRecipe#setIngredient(char, Material)
	 * @see ShapedRecipe#setIngredient(char, Material, int)
	 * @see ShapedRecipe#setIngredient(char, MaterialData)
	 */
	public ShapedRecipe(ItemStack result) {
		this.output = result;
	}

	/**
	 * Set the shape of this recipe to the specified rows. Each character represents a different
	 * ingredient; exactly what each character represents is set separately.
	 * @param shape The rows of the recipe (up to 3 rows).
	 * @return The changed recipe, so you can chain calls.
	 */
	public ShapedRecipe shape(String... shape) {
		if (shape == null || shape.length > 3 || shape.length < 1) {
			throw new IllegalArgumentException("Crafting recipes should be 1, 2, or 3 rows.");
		}
		for (String row : shape) {
			if (row == null || row.length() > 3 || row.length() < 1) {
				throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
			}
		}
		this.rows = shape;

		// Remove character mappings for characters that no longer exist in the shape
		HashMap<Character, Material> ingredientsTemp = this.ingredients;

		this.ingredients = new HashMap<Character, Material>();
		for (char key : ingredientsTemp.keySet()) {
			try {
				setIngredient(key, ingredientsTemp.get(key));
			} catch (IllegalArgumentException e) {}
		}
		return this;
	}

	/**
	 * Sets the material that a character in the recipe shape refers to.
	 * @param key The character that represents the ingredient in the shape.
	 * @param ingredient The ingredient.
	 * @return The changed recipe, so you can chain calls.
	 */
	public ShapedRecipe setIngredient(char key, Material ingredient) {
		if (!hasKey(key)) {
			throw new IllegalArgumentException("Symbol " + key + " does not appear in the shape.");
		}
		ingredients.put(key, ingredient);
		return this;
	}

	private boolean hasKey(char c) {
		String key = Character.toString(c);

		for (String row : rows) {
			if (row.contains(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the ingredients map.
	 * @return The mapping of character to ingredients.
	 */
	public HashMap<Character, Material> getIngredientMap() {
		return ingredients;
	}

	/**
	 * Get the shape.
	 * @return The recipe's shape.
	 */
	public String[] getShape() {
		return rows;
	}

	/**
	 * Get the result.
	 * @return The result stack.
	 */
	public ItemStack getResult() {
		return output;
	}
}
