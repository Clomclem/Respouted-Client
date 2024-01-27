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
package org.spoutcraft.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the various type of game modes that {@link HumanEntity}s may have
 */
public enum GameMode {
	/**
	 * Creative mode may fly, build instantly, become invulnerable and create free items
	 */
	CREATIVE(1),

	/**
	 * Survival mode is the "normal" gameplay type, with no special features.
	 */
	SURVIVAL(0);

	private final int value;
	private final static Map<Integer, GameMode> modes = new HashMap<Integer, GameMode>();

	private GameMode(final int value) {
		this.value = value;
	}

	/**
	 * Gets the mode value associated with this GameMode
	 *
	 * @return An integer value of this gamemode
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the GameMode represented by the specified value
	 *
	 * @param value Value to check
	 * @return Associative {@link GameMode} with the given value, or null if it doesn't exist
	 */
	public static GameMode getByValue(final int value) {
		return modes.get(value);
	}

	static {
		for (GameMode mode : GameMode.values()) {
			modes.put(mode.getValue(), mode);
		}
	}
}
