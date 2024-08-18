package conanthecivilian.oat;

import conanthecivilian.oat.entity.IFactionEntityLiving;
import conanthecivilian.oat.model.ModelOatHumanoid;
import conanthecivilian.oat.renderer.RendererOatHumanoid;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.util.phys.AABB;
import turniplabs.halplibe.helper.EntityHelper;

import java.util.List;

public class OatEntityHelper {
	public static void createHumanoidEntity(Class<? extends net. minecraft. core. entity. Entity> clazz, int id, String name) {
		EntityHelper.createEntity(
			clazz,
			id,
			name,
			() -> new RendererOatHumanoid(new ModelOatHumanoid())
		);
	}

	public static <T extends Entity> T getClosestEntity(
		Entity seekingEntity,
		double x,
		double y,
		double z,
		double radius
	) {
		List<Entity> entitiesNearMonster = seekingEntity.world.getEntitiesWithinAABBExcludingEntity(
			seekingEntity,
			AABB.getBoundingBoxFromPool(x, y, z, x + 1.0, y + 1.0, z + 1.0).expand(16.0, 4.0, 16.0)
		);

		double closestDistance = Double.POSITIVE_INFINITY;
		T foundEntity = null;

		if (radius < 0.0) {
			for (Entity entity : entitiesNearMonster) {
				if (entity instanceof EntityLiving) {
					T currentEntity = (T) entity;

					if (!OatEntityHelper.shouldFactionEntitiesFight(
						(IFactionEntityLiving) seekingEntity,
						(IFactionEntityLiving) currentEntity
					)) {
						continue;
					}

					double currentDistance = currentEntity.distanceToSqr(x, y, z);
					if (currentDistance < closestDistance) {
						closestDistance = currentDistance;
						foundEntity = currentEntity;
					}
				}
			}
		} else {
			double rSquared = radius * radius;

			for (Entity entity : entitiesNearMonster) {
				if (entity instanceof EntityLiving) {
					T currentEntity = (T) entity;
					double currentDistance = currentEntity.distanceToSqr(x, y, z);

					if (!OatEntityHelper.shouldFactionEntitiesFight(
						(IFactionEntityLiving) seekingEntity,
						(IFactionEntityLiving) currentEntity
					)) {
						continue;
					}

					if (currentDistance < rSquared && currentDistance < closestDistance) {
						closestDistance = currentDistance;
						foundEntity = currentEntity;
					}
				}
			}
		}

		return foundEntity;
	}

	public static <T extends Entity> T getClosestEntityToEntity(
		Entity seekingEntity,
		double radius
	) {
		return OatEntityHelper.getClosestEntity(
			seekingEntity,
			seekingEntity.x,
			seekingEntity.y,
			seekingEntity.z,
			radius
		);
	}

	public static boolean shouldFactionEntitiesFight(
		IFactionEntityLiving entity1,
		IFactionEntityLiving entity2
	) {
		if (entity1.getFaction() == null || entity2.getFaction() == null) {
			return false;
		}

		// Neutral factions is not to be attacked by default
		if (entity1.getFaction().id == 1 || entity2.getFaction().id == 1) {
			return false;
		}

		return entity1.getFaction().id != entity2.getFaction().id;
	}
}
