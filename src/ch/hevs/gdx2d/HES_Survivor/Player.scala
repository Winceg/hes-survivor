package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2


class Player(private val name: String = "Player 1",
             startPosition: Vector2 = new Vector2(),
             initSprite: Sprite,
            ) extends DrawableObject with Character {

  /** Player attributes */
  sprite = initSprite
  position = startPosition
  characterType = 1
  collisionBox = (25, 50)
  addWeapon(new Weapon(bulletType = 1))

  override def toString: String = name
}
