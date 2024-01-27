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

import java.util.LinkedList;

public interface Polygon extends Widget {
	/**
	 * Adds a point to the Polygon using the last set color.
	 * @param p the point to add
	 * @return the instance
	 * @throws IllegalStateException when no color has been set before.
	 */
	public Polygon addPoint(Point p) throws IllegalStateException;

	/**
	 * Convenience method for addPolygon(new Point(x,y))
	 * @param x x-coord of the point
	 * @param y y-coord of the point
	 * @return the instance
	 * @throws IllegalStateException when no color has been set before.
	 */
	public Polygon addPoint(int x, int y) throws IllegalStateException;

	/**
	 * Adds a point to the polygon with a color
	 * @param p the point to add
	 * @param c the color of that point
	 * @return the instance
	 */
	public Polygon addPoint(Point p, Color c);

	/**
	 * Convenience method for addPoint(new Point(x, y), c)
	 * @param x the x-coord of the point
	 * @param y the y-coord of the point
	 * @param c the color of that point
	 * @return the instance
	 */
	public Polygon addPoint(int x, int y, Color c);

	/**
	 * Gets a list of all the points.
	 * This list corresponds to the color list that you get with getColors()
	 * @see getColors()
	 * @return a list of all points attached.
	 */
	public LinkedList<Point> getPoints();

	/**
	 * Gets a list of all the colors.
	 * This list corresponds to the point list that you get with getPoints()
	 * @see getPoints()
	 * @return a list of all colors attached.
	 */
	public LinkedList<Color> getColors();
}
