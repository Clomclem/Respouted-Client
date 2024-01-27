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
package org.spoutcraft.client.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import org.spoutcraft.api.entity.EntitySkinType;
import org.spoutcraft.api.util.FixedLocation;
import org.spoutcraft.api.util.MutableLocation;

public class CraftLivingEntity extends CraftEntity {
	public CraftLivingEntity(LivingEntity living) {
		super(living);
	}

	public LivingEntity getEntityLiving() {
		return (LivingEntity) handle;
	}

	public float getHealth() {		
		return getEntityLiving().getHealth();
	}

	public void setHealth(float health) {
		getEntityLiving().setHealth(health);
	}

	public double getEyeHeight() {
		return getEntityLiving().getEyeHeight();
	}

	public double getEyeHeight(boolean ignoreSneaking) {
		if (ignoreSneaking) {
			return getEntityLiving().height;
		}
		return getEyeHeight();
	}

	public FixedLocation getEyeLocation() {
		return new MutableLocation(handle.posX, handle.posY + getEyeHeight(), handle.posZ);
	}

	public boolean isInsideVehicle() {
		return handle.ridingEntity != null;
	}

	public boolean leaveVehicle() {
		if (isInsideVehicle()) {
			handle.mountEntity(null);
			return true;
		}
		return false;
	}

	public int getRemainingAir() {
		return getEntityLiving().getAir();
	}

	public void setRemainingAir(int ticks) {
		if (ticks < 0) {
			throw new IllegalArgumentException("The Remaining Air can not be below 0");
		}
		getEntityLiving().setAir(ticks);
	}

	public int getMaximumAir() {
		return getEntityLiving().maxAir;
	}

	public void setMaximumAir(int ticks) {
		if (ticks <= 0) {
			throw new IllegalArgumentException("The Maximum Air can not be below 1");
		}
		getEntityLiving().maxAir = ticks;
	}

	public void damage(float amount) {
		getEntityLiving().damage(DamageSource.GENERIC, amount);
	}

	public void damage(int amount, CraftEntity source) {
		DamageSource reason = DamageSource.GENERIC;

		if (source instanceof CraftHumanEntity) {
			reason = DamageSource.player(((CraftHumanEntity)source).getMCPlayer());
		} else if (source instanceof CraftLivingEntity) {
			reason = DamageSource.mob(((CraftLivingEntity)source).getEntityLiving());
		}

		getEntityLiving().damage(reason, amount);
	}

	public int getMaximumNoDamageTicks() {
		return getEntityLiving().maxHurtResistantTime;
	}

	public void setMaximumNoDamageTicks(int ticks) {
		getEntityLiving().maxHurtResistantTime = ticks;
	}

	public float getLastDamage() {		
		return getEntityLiving().lastDamage;
	}

	public void setLastDamage(float damage) {
		getEntityLiving().lastDamage = damage;
	}

	public int getNoDamageTicks() {
		return getEntityLiving().hurtResistantTime;
	}

	public void setNoDamageTicks(int ticks) {
		getEntityLiving().hurtResistantTime = ticks;
	}

	public String getTitle() {		
		return getEntityLiving().displayName;
	}

	public void setTitle(String title) {
		getEntityLiving().displayName = title;
	}

	public void resetTitle() {
		getEntityLiving().displayName = null;
		if (handle instanceof PlayerEntity) {
			getEntityLiving().displayName = ((PlayerEntity)handle).username;
		}
	}

	@Override
	public void setSkin(String skinURI, EntitySkinType type) {
		getEntityLiving().setCustomTexture(skinURI, type.getId());
	}
}
