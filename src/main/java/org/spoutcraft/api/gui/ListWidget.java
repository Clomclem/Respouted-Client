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
package org.spoutcraft.api.gui;

import org.spoutcraft.api.UnsafeClass;

@UnsafeClass
public interface ListWidget extends Scrollable {
	/**
	 * Get all the items from the list widget
	 * @return the assigned ListWidgetItems
	 */
	public ListWidgetItem[] getItems();

	/**
	 * Returns the nth item from the listwidget
	 * @param n which item to get
	 * @return nth item from the list
	 */
	public ListWidgetItem getItem(int n);

	/**
	 * Adds an item to the list
	 * @param item the item to add.
	 * @return instance of the ListWidget
	 */
	public ListWidget addItem(ListWidgetItem item);

	/**
	 * Add items to the list
	 * @param items to add
	 * @return instance of the ListWidget
	 */
	 public ListWidget addItems(ListWidgetItem... items);

	/**
	 * Removes an item from the list.
	 * @param item to remove
	 * @return if item was found.
	 */
	public boolean removeItem(ListWidgetItem item);

	/**
	 * Clears all attached items.
	 */
	public void clear();

	/**
	 * @return the currently selected item.
	 * @returns null when no item is selected.
	 */
	public ListWidgetItem getSelectedItem();

	/**
	 * @return the currently selected row.
	 */
	public int getSelectedRow();

	/**
	 * Sets the selected item to be the nth in the list.
	 * @param n the number of the item or -1 to clear the selection
	 * @return instance of the ListWidget
	 */
	public ListWidget setSelection(int n);

	/**
	 * Clears the selection
	 * @return instance of the ListWidget
	 */
	public ListWidget clearSelection();

	/**
	 * @param n item to check
	 * @returns if the nth item is selected
	 */
	public boolean isSelected(int n);

	/**
	 * @param item to check
	 * @returns if the item is selected
	 */
	boolean isSelected(ListWidgetItem item);

	/**
	 * Moves the selection up or down by n
	 * @param n
	 * @return
	 */
	public ListWidget shiftSelection(int n);

	/**
	 * Will be called on each selection change.
	 * @param item the number of the item that was clicked/selected by keypress. Can be -1 that means that no item is selected
	 * @param doubleClick if true, item has been doubleclicked.
	 */
	public void onSelected(int item, boolean doubleClick);
}
