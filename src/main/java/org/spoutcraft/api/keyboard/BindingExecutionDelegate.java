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
package org.spoutcraft.api.keyboard;

public abstract class BindingExecutionDelegate {
	@Deprecated
	public void onKeyPress(int key, KeyBinding binding) {
	}
	@Deprecated
	public void onKeyRelease(int key, KeyBinding binding) {
	}

	/**
	 * Called when the Keybinding is pressed.
	 * Please check event.getScreen() when you don't want handle this binding on other screens than the main screen.
	 * @param event
	 */
	public void onKeyPress(KeyBindingPress event) {
	}

	/**
	 * Called when the Keybinding is released.
	 * Please check event.getScreen() when you don't want handle this binding on other screens than the main screen.
	 * @param event
	 */
	public void onKeyRelease(KeyBindingPress event) {
	}
}
