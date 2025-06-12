package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.ArrayBuffer

trait Character {
  /** Attributes */
  protected var position: Vector2 = new Vector2(100, 100)
  var maxLifePoints: Int = 100
  protected var lifePoints: Int = maxLifePoints
  protected var sprite: Sprite = _
  protected var characterType: Int = 0
  protected val weapons: ArrayBuffer[Weapon] = ArrayBuffer.empty
  protected var collisionBox: (Int, Int) = _

  /** Position and movements */
  protected def getPosition: Vector2 = position

  def moveTo(x: Float, y: Float): Unit = {
    position.x = x
    position.y = y
  }

  /** Weapons and shooting */
  def addWeapon(weapon: Weapon): Unit = {
    weapons.append(weapon)
  }

  private def getWeapons: ArrayBuffer[Weapon] = weapons

  def shoot(weapon: Int = 0): Unit = {
    if (weapon >= getWeapons.length) {
      weapons(0).shoot(getPosition, characterType)
    } else {
      weapons(weapon).shoot(getPosition, characterType)
    }
  }

  /** Collisions and damage */
  private def getCollisionX: (Float, Float) = (position.x - collisionBox._1, position.x + collisionBox._1)

  private def getCollisionY: (Float, Float) = (position.y - collisionBox._2, position.y + collisionBox._2)


  /** If a bullet gets inside the collision box of the player, the player takes damage and the bullet is destroyed */
  def onCollision(): Unit = {
    for (b <- Game.bullets) {
      if (b.playerBullet == -characterType
        && b.getPosition.x > this.getCollisionX._1
        && b.getPosition.x < this.getCollisionX._2
        && b.getPosition.y > this.getCollisionY._1
        && b.getPosition.y < this.getCollisionY._2) {
        this.takeAShot(b.getDamage)

        /** Bullet is marked for deletion in the array at the end of the onRender function */
        b.impacted()
      }
    }
  }

  /** If an enemy gets inside the collision box of the player, the player takes damage */
  def onCollisionWithEnemy(): Unit = {
    for (e <- Game.enemies) {
      if (characterType == 1
        && e.getPosition.x > this.getCollisionX._1
        && e.getPosition.x < this.getCollisionX._2
        && e.getPosition.y > this.getCollisionY._1
        && e.getPosition.y < this.getCollisionY._2) {
        this.takeAShot(1)
      }
    }
  }

  /** Removes lifepoints after a collision */
  private def takeAShot(damage: Int): Unit = {
    lifePoints -= damage
  }

  /** HUD */
  def getLifePoints: Int = lifePoints

  //private def displayLifePoints: String = s"Life : $lifePoints"

  private def displayLifeBar(g: GdxGraphics): Unit = {
    val lifePercent: Int = ((lifePoints.toFloat / maxLifePoints.toFloat) * 100f).toInt

    def lifeBarColor(): Color = if (lifePercent > 33) Color.LIME else if (lifePercent > 10) Color.YELLOW else Color.RED

    if (characterType == 1) {
      g.setColor(Color.BLACK)
      for (x <- 0 to 2 * lifePercent) {
        g.drawFilledRectangle(x + 50, 50, 1, 20, 0, lifeBarColor())
      }
      g.drawRectangle(150, 50, 202, 20, 0)
    } else {
      g.setColor(Color.BLACK)
      for (x <- 0 to lifePercent) {
        g.drawFilledRectangle(position.x + x - 50, position.y + sprite.spriteDimentionX * 2 / 3, 1, 10, 0, lifeBarColor())
      }
      g.drawRectangle(position.x, position.y + sprite.spriteDimentionX * 2 / 3, 102, 10, 0)
    }
  }

  /** Graphics */
  def draw(g: GdxGraphics): Unit = {
    displayLifeBar(g)
    g.setColor(Color.BLACK)
    //g.drawFilledRectangle(position.x, position.y, collisionBox._1 * 2, collisionBox._2 * 2, 0, Color.RED)
    //g.drawString(position.x - 25, position.y + sprite.spriteDimentionX * 2 / 3 + 20, displayLifePoints)
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }
}