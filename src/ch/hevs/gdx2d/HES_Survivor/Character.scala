package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.ArrayBuffer

trait Character {
  protected var position: Vector2 = new Vector2(100, 100)
  protected var lifePoints: Int = 100
  protected var sprite: Sprite = null
  private var level: Int = 1
  private val weapons: ArrayBuffer[Weapon] = ArrayBuffer(new Weapon())

/** Position and movements */
  def getPosition: Vector2 = position

  def moveTo(x: Float, y: Float): Unit = {
    position.x = x
    position.y = y
  }

  def moveDelta(x: Int, y: Int): Unit = {
    moveTo(position.x + x, position.y + y)
  }

  /** Weapons and shooting */
  def addWeapon(weapon: Weapon): Unit = {
    weapons.append(weapon)
  }
  def getWeapons: ArrayBuffer[Weapon] = weapons

  def shoot(weapon: Int = 0, bullets: ArrayBuffer[Bullet]): Unit = {
    if (weapon >= getWeapons.length) {
      weapons(0).shoot(getPosition, bullets, 1)
    } else {
      weapons(weapon).shoot(getPosition, bullets, 1)
    }
  }

  /** Collisions and damage */
  val collisionBox: (Int, Int) = (20, 50)

  def takeAShot(damage: Int): Unit = {
    lifePoints -= damage
  }

  /** Graphics */
  def draw(g: GdxGraphics): Unit = {
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }

  def getSprite: Sprite = sprite

}
