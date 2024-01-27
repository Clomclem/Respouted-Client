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
package org.spoutcraft.client.gui.settings.controls;

import net.minecraft.client.MinecraftClient;

import org.spoutcraft.api.gui.GenericSlider;

public class MusicSlider extends GenericSlider {
	public MusicSlider() {
		super("Music");
		setSliderPosition(MinecraftClient.getInstance().gameSettings.musicVolume);
		setTooltip("Music Volume\nControls the volume of all the music in game.");
	}

	@Override
	public void onSliderDrag(float old, float newPos) {
		MinecraftClient.getInstance().gameSettings.musicVolume = newPos;
		MinecraftClient.getInstance().gameSettings.saveOptions();
		MinecraftClient.getInstance().sndManager.onSoundOptionsChanged();
	}

	public String getText() {
		return "Music: " + (this.getSliderPosition() > 0F ? (int)(this.getSliderPosition() * 100) + "%" : "OFF");
	}
}
