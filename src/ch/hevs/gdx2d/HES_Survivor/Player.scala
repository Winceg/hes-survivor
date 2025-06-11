package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
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
  addWeapon(new Weapon(weaponName = "The Sum Gun", bulletType = 1))

  override def toString: String = name

  /** Adds lifepoints and new weapons after completing a level */
  def levelUp(): Unit = {
    maxLifePoints = maxLifePoints * Game.currentWave / (Game.currentWave - 1)
    lifePoints = maxLifePoints
    Game.currentWave match {
      case 1 =>
      case 2 =>
        addWeapon(new Weapon(weaponName = "The Limit Blaster", bulletType = 3))
        println("New limit weapon")
      case 3 =>
        addWeapon(new Weapon(weaponName = "The Integral Desintegrator", bulletType = 0, damage = 15))
        println("New integral weapon")
      case _ =>
    }
  }

  def displayArmory(g: GdxGraphics): Unit = {
    var i: Int = 1
    g.drawString(50, 350, "Armory:", Font.pusab30)
    for (w <- weapons) {
      g.drawString(50, 350 - 30 * i, w.weaponName, Font.pusab30)
      i += 1
    }
  }

  /** Reset player value at the end of a game, and before a new one */
  def reset(): Unit = {
    maxLifePoints = 100
    lifePoints = maxLifePoints
    weapons.clear()
    addWeapon(new Weapon())
  }
}
