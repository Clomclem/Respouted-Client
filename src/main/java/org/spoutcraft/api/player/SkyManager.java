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
package org.spoutcraft.api.player;

import org.spoutcraft.api.gui.Color;

public interface SkyManager {
	public int getCloudHeight();

	public void setCloudHeight(int y);

	public boolean isCloudsVisible();

	public void setCloudsVisible(boolean visible);

	public int getStarFrequency();

	public void setStarFrequency(int frequency);

	public boolean isStarsVisible();

	public void setStarsVisible(boolean visible);

	public int getSunSizePercent();

	public void setSunSizePercent(int percent);

	public boolean isSunVisible();

	public void setSunVisible(boolean visible);

	public String getSunTextureUrl();

	public void setSunTextureUrl(String Url);

	public int getMoonSizePercent();

	public void setMoonSizePercent(int percent);

	public boolean isMoonVisible();

	public void setMoonVisible(boolean visible);

	public String getMoonTextureUrl();

	public void setMoonTextureUrl(String Url);

	public void setSkyColor(float red, float green, float blue);

	public void setSkyColor(Color color);

	public Color getSkyColor();

	public void setFogColor(Color color);

	public Color getFogColor();

	public void setCloudColor(Color color);

	public Color getCloudColor();
}
