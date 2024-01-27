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

public enum RenderDistance {
	FAR(0),
	NORMAL(1),
	SHORT(2),
	TINY(3);

	private final int value;

	RenderDistance(final int i) {
		value = i;
	}

	public final int getValue() {
		return value;
	}

	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}

	public static RenderDistance getRenderDistanceFromValue(int value) {
		for (RenderDistance rd : values()) {
			if (rd.getValue() == value) {
				return rd;
			}
		}
		return null;
	}
}
