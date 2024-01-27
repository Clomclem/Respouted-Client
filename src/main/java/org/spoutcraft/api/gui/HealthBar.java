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

import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.UnsafeClass;
import org.spoutcraft.api.io.SpoutInputStream;
import org.spoutcraft.api.io.SpoutOutputStream;

@UnsafeClass
public class HealthBar extends GenericWidget {
	private int icons = 10;
	private int iconOffset = 8;
	private float dangerPercent = 20f;

	public HealthBar() {
		super();
		setX(427 / 2 - 91); // 122
		setY(201);
		setAnchor(WidgetAnchor.BOTTOM_CENTER);
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setMaxNumHearts(input.readInt());
		setIconOffset(input.readInt());
		setDangerPercent(input.readFloat());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(getMaxNumHearts());
		output.writeInt(getIconOffset());
		output.writeFloat(getDangerPercent());
	}

	public WidgetType getType() {
		return WidgetType.HealthBar;
	}

	@Override
	public UUID getId() {
		return new UUID(0, 4);
	}

	@Override
	public double getScreenX() {
		double mid = getScreen() != null ? getScreen().getWidth() / 2 : 427 / 2D;
		double diff = super.getScreenX() - mid - 31;
		return getScreen() != null ? getScreen().getWidth() / 2D - diff : this.getX();
	}

	@Override
	public double getScreenY() {
		int diff = (int) (240 - this.getY());
		return getScreen() != null ? getScreen().getHeight() - diff : this.getY();
	}

	public void render() {
		Spoutcraft.getClient().getRenderDelegate().render(this);
	}

	/**
	 * Gets the maximum number of hearts displayed on the HUD.
	 *
	 * Health is scaled to fit the number of hearts appropriately.
	 *
	 * @return hearts displayed
	 */
	public int getMaxNumHearts() {
		return icons;
	}

	/**
	 * Sets the maximum number of hearts displayed on the HUD.
	 *
	 * Health is scaled to fit the number of hearts appropriately.
	 *
	 * @param hearts to display
	 * @return this
	 */
	public HealthBar setMaxNumHearts(int hearts) {
		this.icons = hearts;
		return this;
	}

	/**
	 * Gets the number of pixels each heart is offset when drawing the next heart.
	 *
	 * @return pixel offset
	 */
	public int getIconOffset() {
		return iconOffset;
	}

	/**
	 * Sets the number of pixels each heart is offset when drawing the next heart.
	 *
	 * @param offset when drawing hearts
	 * @return this
	 */
	public HealthBar setIconOffset(int offset) {
		iconOffset = offset;
		return this;
	}

	/**
	 * Gets the percent of health a player needs to fall to or below in order for the hearts to begin blinking.
	 *
	 * Valid percents are between zero and one hundred, inclusive.
	 *
	 * @return danger percent
	 */
	public float getDangerPercent() {
		return dangerPercent;
	}

	/**
	 * Sets the percent of health a player needs to fall to or below in order for the hearts to begin blinking.
	 *
	 * Valid percents are between zero and one hundred, inclusive.
	 *
	 * @param percent
	 * @return this
	 */
	public HealthBar setDangerPercent(float percent) {
		dangerPercent = percent;
		return this;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
}
