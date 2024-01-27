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
public interface Label extends Widget {
	/**
	 * Gets the text of the label
	 *
	 * @return text
	 */
	public String getText();

	/**
	 * Sets the text of the label
	 *
	 * @param text to set
	 * @return label
	 */
	public Label setText(String text);

	/**
	 * Gets the color for the text
	 *
	 * @return color
	 */
	public Color getTextColor();

	/**
	 * Sets the color for the text
	 *
	 * @param color to set
	 * @return label
	 */
	public Label setTextColor(Color color);

	/**
	 * Determines if text expands to fill width and height
	 * If word wrapping and auto is enabled, the height of the label will reflect the heigth of all lines.
	 *
	 * @param auto
	 * @return label
	 */
	public Label setAuto(boolean auto);

	/**
	 * Gets if the text will expand to fill width and height
	 *
	 * @param auto
	 * @return label
	 */
	public boolean isAuto();

	public WidgetAnchor getAlign();

	public Widget setAlign(WidgetAnchor pos);

	/**
	 * Set the scale of the text
	 * @param scale to set
	 */
	public Label setScale(float scale);

	/**
	 * Gets the scale of the text
	 * @return scale of text
	 */
	public float getScale();

	/**
	 * Sets whether or not the label has a shadow.
	 * @param shadow
	 */
	public Label setShadow(boolean shadow);

	/**
	 * Gets whether or not the label has a shadow.
	 * @return
	 */
	public boolean hasShadow();
}
