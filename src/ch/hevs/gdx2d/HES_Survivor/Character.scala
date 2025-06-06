package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

trait Character {
  /** Attributes */
  protected var position: Vector2 = new Vector2(100, 100)
  protected var lifePoints: Int = 100
  protected var sprite: Sprite = _
  //  private var level: Int = 1
  protected var characterType: Int = 0
  private val weapons: ArrayBuffer[Weapon] = ArrayBuffer(new Weapon())

  /** Position and movements */
  private def getPosition: Vector2 = position

  def moveTo(x: Float, y: Float): Unit = {
    position.x = x
    position.y = y
  }

  /** Weapons and shooting */
  def addWeapon(weapon: Weapon): Unit = {
    weapons.append(weapon)
  }

  private def getWeapons: ArrayBuffer[Weapon] = weapons

  def shoot(weapon: Int = 0, bullets: ArrayBuffer[Bullet]): Unit = {
    if (weapon >= getWeapons.length) {
      weapons(0).shoot(getPosition, bullets, characterType)
    } else {
      weapons(weapon).shoot(getPosition, bullets, characterType)
    }
  }

  /** Collisions and damage */
  private val collisionBox: (Int, Int) = (25, 50)

  private def getCollisionX: (Float, Float) = (position.x - collisionBox._1, position.x + collisionBox._1)

  private def getCollisionY: (Float, Float) = (position.y - collisionBox._2, position.y + collisionBox._2)

  def onCollision(bullets: ArrayBuffer[Bullet]): Unit = {
    for (b <- bullets) {
      if (b.playerBullet == -characterType
        && b.getPosition.x > this.getCollisionX._1
        && b.getPosition.x < this.getCollisionX._2
        && b.getPosition.y > this.getCollisionY._1
        && b.getPosition.y < this.getCollisionY._2) {
        this.takeAShot(b.getDamage)
        b.impacted()
      }
    }
  }

  private def takeAShot(damage: Int): Unit = {
    lifePoints -= damage
  }

  def getLifePoints: Int = lifePoints

  private def displayLifePoints: String = s"Life : $lifePoints"

  private def displayLifeBar(g: GdxGraphics): Unit = {
    var color: Color = if (lifePoints > 25) Color.LIME else if (lifePoints > 5) Color.YELLOW else Color.RED
    g.setColor(Color.LIGHT_GRAY)
    g.drawRectangle(150, 50, 200, 20, 0)
    for (x <- 0 to 2 * lifePoints) {
      g.drawFilledRectangle(x + 50, 50, 1, 20, 0, color)
    }
  }

  /** Graphics */
  def draw(g: GdxGraphics): Unit = {
    displayLifeBar(g)
    g.drawString(position.x, position.y + 100, displayLifePoints)
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }

  def getSprite: Sprite = sprite

}
