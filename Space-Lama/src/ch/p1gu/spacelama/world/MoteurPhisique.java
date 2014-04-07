/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.p1gu.spacelama.world;

import ch.p1gu.spacelama.Model.Entity;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Michael
 */
public class MoteurPhisique {

    public void deplacerLesEntity(List<Entity> lesEntity) {
        for (Entity entity : lesEntity) {
            entity.setPosition(entity.getX() + entity.vitesse.x, entity.getY() + entity.vitesse.y);
        }
    }

    public void deplacerEntity(Entity entity) {

        if (entity.type == Entity.Type.hero) {

            if ((entity.rectangle.x += entity.vitesse.x) < 0) {
                entity.rectangle.x = 0;
            } else if ((entity.rectangle.x += entity.vitesse.x) > World.x - entity.rectangle.width) {
                entity.rectangle.x = World.x - entity.rectangle.width;

            } else {
                entity.rectangle.x += entity.vitesse.x;
            }

            if ((entity.rectangle.y += entity.vitesse.y) < 0) {
                entity.rectangle.y = 0;
            } else if ((entity.rectangle.y += entity.vitesse.y) > World.y - entity.rectangle.height) {
                entity.rectangle.y = World.y - entity.rectangle.height;

            } else {
                entity.rectangle.y += entity.vitesse.y;
            }


        } else {
            entity.setPosition(entity.getX() + entity.vitesse.x, entity.getY() + entity.vitesse.y);
        }
    }

    public void dectecterCollision(List lesEntity1, List lesEntity2) {
        for (Iterator it = lesEntity2.iterator(); it.hasNext();) {
            Entity entity2 = (Entity) it.next();
            for (Iterator it2 = lesEntity1.iterator(); it2.hasNext();) {
                Entity entity1 = (Entity) it2.next();
                if (entity1.rectangle.overlaps(entity2.rectangle)) {
                    entity1.toucher(entity2);
                    entity2.toucher(entity1);
                }
            }



        }
    }

    public void dectecterCollision(Entity entity1, List lesEntity) {

        for (Object object : lesEntity) {
            Entity entity2 = (Entity) object;
            if (entity1.rectangle.overlaps(entity2.rectangle)) {
                entity1.toucher(entity2);
                entity2.toucher(entity1);
            }
        }

    }
}
