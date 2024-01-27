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
public interface Container extends Widget {
	/**
	 * Adds a single widget to a container
	 * @param child The widget to add
	 * @return Widget
	 */
	public Container addChild(Widget child);

	/**
	 * Adds a single widget to a container
	 * @param index The position to insert it, use -1 for append
	 * @param child The widget to add
	 * @return Widget
	 */
	public Container insertChild(int index, Widget child);

	/**
	 * Adds a list of children to a container.
	 * @param children The widgets to add
	 * @return
	 */
	public Container addChildren(Widget... children);

	/**
	 * Removes a single widget from this container
	 * @param child The widget to add
	 * @return
	 */
	public Container removeChild(Widget child);

	/**
	 * Get a list of widgets inside this container.
	 * @return
	 */
	public Widget[] getChildren();

	/**
	 * Set the automatic layout type for children, triggered by setWidth() or setHeight()
	 * @param type ContainerType.VERTICAL, .HORIZONTAL or .OVERLAY
	 * @return the container
	 */
	public Container setLayout(ContainerType type);

	/**
	 * Get the automatic layout type for children
	 * @return the type of container
	 */
	public ContainerType getLayout();

	/**
	 * Force the container to re-layout all non-fixed children.
	 * Unless you specifically need to update the layout at this instant,
	 * you should use use deferLayout() instead.
	 * This will re-position and resize all child elements.
	 * @return
	 */
	public Container updateLayout();

	/**
	 * Automatically call updateLayout during the next onTick.
	 * This is automatically called when anything changes that would affect the container layout.
	 * NOTE: Subclasses should ensure they don't prevent Container.onTick() from running.
	 * @return
	 */
	public Container deferLayout();

	/**
	 * Set the contents alignment.
	 * @return
	 */
	public Container setAlign(WidgetAnchor anchor);

	/**
	 * Get the contents alignment.
	 * @return
	 */
	public WidgetAnchor getAlign();

	/**
	 * Reverse the drawing order (right to left or bottom to top).
	 * @param reverse Set to reverse direction
	 * @return
	 */
	public Container setReverse(boolean reverse);

	/**
	 * If this is drawing in reverse order.
	 * @return
	 */
	public boolean getReverse();

	/**
	 * Determines if children expand to fill width and height
	 * @param auto
	 * @return
	 */
	public Container setAuto(boolean auto);

	/**
	 * True if the children will expand to fill width and height
	 * @return
	 */
	public boolean isAuto();
}
