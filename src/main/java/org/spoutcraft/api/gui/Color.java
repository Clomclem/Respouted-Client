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

import org.spoutcraft.api.animation.Animatable;

public final class Color implements Animatable {
	private short red;
	private short green;
	private short blue;
	private short alpha = 255;

	public Color(float r, float g, float b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}

	public Color(float r, float g, float b, float a) {
		setRed(r);
		setGreen(g);
		setBlue(b);
		setAlpha(a);
	}

	public Color(short r, short g, short b) {
		red = r;
		green = g;
		blue = b;
	}

	public Color(short r, short g, short b, short a) {
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}

	public Color(int argb) {
		alpha = (short) (argb >>> 24);
		red = (short) ((argb & 0xFF0000) >>> 16);
		green = (short) ((argb & 0xFF00) >>> 8);
		blue = (short) (argb & 0xFF);
	}

	public float getRedF() {
		return red / 255F;
	}

	public float getGreenF() {
		return green / 255F;
	}

	public float getBlueF() {
		return blue / 255F;
	}

	public float getAlphaF() {
		return alpha / 255F;
	}

	public short getRedB() {
		return red;
	}

	public short getGreenB() {
		return green;
	}

	public short getBlueB() {
		return blue;
	}

	public short getAlphaB() {
		return alpha;
	}

	public int getRedI() {
		return (int)red;
	}

	public int getGreenI() {
		return (int)green;
	}

	public int getBlueI() {
		return (int)blue;
	}

	public int getAlphaI() {
		return (int)alpha;
	}

	public Color setRed(float r) {
		red = (short) (r * 255);
		return this;
	}

	public Color setGreen(float g) {
		green = (short) (g * 255);
		return this;
	}

	public Color setBlue(float b) {
		blue = (short) (b * 255);
		return this;
	}

	public Color setAlpha(float a) {
		alpha = (short) (a * 255);
		return this;
	}

	public Color clone() {
		return new Color(red, green, blue, alpha);
	}

	public String toString() {
		return "r: " + red + " g: " + green + " b: " + blue + " a: " + alpha;
	}

	public boolean isInvalid() {
		return red == -1 || red / 255 == -1;
	}

	public boolean isOverride() {
		return red == -2 || red / 255 == -2;
	}

	public static Color invalid() {
		return new Color(-1, -1, -1);
	}

	public static Color override() {
		return new Color(-2, -2, -2);
	}

	public int toInt() {
		return (getAlphaI() & 0xFF) << 24 | (getRedI() & 0xFF) << 16 | (getGreenI() & 0xFF) << 8 | (getBlueI() & 0xFF);
	}

	public Animatable getValueAt(double p, Animatable startValue, Animatable endValue) {
		short r, g, b, a;
		Color p1 = (Color) startValue;
		Color p2 = (Color) endValue;
		r = p1.red;
		g = p1.green;
		b = p1.blue;
		a = p1.alpha;
		r += (p2.red - r) * p;
		g += (p2.green - g) * p;
		b += (p2.blue - b) * p;
		a += (p2.alpha - a) * p;
		return new Color(r, g, b, a);
	}
}
