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

import java.io.IOException;
import java.util.UUID;

import org.spoutcraft.api.UnsafeClass;
import org.spoutcraft.api.io.SpoutInputStream;
import org.spoutcraft.api.io.SpoutOutputStream;

@UnsafeClass
public class ChatBar extends GenericWidget implements Widget {
	private int cursorX = 4, cursorY = 240;
	protected Color textColor = new Color((short) 255, (short) 255, (short) 255, (short) 0);

	public ChatBar() {
		super();
		setX(2);
		setY(-2);
		setWidth(425);
		setHeight(12);
		setAnchor(WidgetAnchor.BOTTOM_LEFT);
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setCursorX(input.readInt());
		setCursorY(input.readInt());
		setTextColor(input.readColor());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(getCursorX());
		output.writeInt(getCursorY());
		output.writeColor(getTextColor());
	}

	public WidgetType getType() {
		return WidgetType.ChatBar;
	}

	@Override
	public UUID getId() {
		return new UUID(0, 2);
	}

	/**
	 * Gets the x position that the cursor starts at when typing chat
	 * @return cursor x
	 */
	public int getCursorX() {
		return cursorX;
	}

	/**
	 * Sets the x position that the cursor starts at when typing
	 * @param x position to set
	 * @return this
	 */
	public ChatBar setCursorX(int x) {
		cursorX = x;
		return this;
	}

	/**
	 * Gets the y position that the cursor starts at when typing chat
	 * @return cursor y
	 */
	public int getCursorY() {
		int diff = 240 - cursorY;
		return (int) (getScreen() != null ? getScreen().getHeight() - diff : cursorY);
	}

	/**
	 * Sets the y position that the cursor starts at when typing
	 * @param y position to set
	 * @return this
	 */
	public ChatBar setCursorY(int y) {
		cursorY = y;
		return this;
	}

	/**
	 * Gets the default color of the text for the chat bar
	 * @return default text color
	 */
	public Color getTextColor() {
		return textColor;
	}

	/**
	 * Sets the default color of the text for the chat bar
	 * @param color to set
	 * @return this
	 */
	public ChatBar setTextColor(Color color) {
		textColor = color;
		return this;
	}

	public void render() {
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 2;
	}
}
