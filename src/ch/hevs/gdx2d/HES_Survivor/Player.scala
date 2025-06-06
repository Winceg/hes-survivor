package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2

class Player(private val name: String = "Player 1",
             startPosition: Vector2 = new Vector2(),
             initSprite: Sprite,
            ) extends DrawableObject with Character with Simulatable {

  /** Player attributes */
  sprite = initSprite
  position = startPosition
  characterType = 1

  override def toString: String = name

  // Every frame, we need to update
  override def update(): Unit = {
  }
}
