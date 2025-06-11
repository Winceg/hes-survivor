package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

case class Bullet(initDamage: Int = 10,
                  startPos: Vector2 = new Vector2(0, 0),
                  private val trajectory: Int = 0,
                  var playerBullet: Int = 1,
                  private var speed: Int = 10,
                  private var sprite: Sprite
                 ) extends DrawableObject {

  /** Attributes */
  private val damage: Int = initDamage * Game.currentWave
  private val position: Vector2 = new Vector2()
  position.x = startPos.x
  position.y = startPos.y
  private var impact: Boolean = false

  override def draw(g: GdxGraphics): Unit = {
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }

  def getPosition: Vector2 = position

  def getDamage: Int = damage

  def impacted(): Unit = impact = true

  /** Bullets can move on different trajectories */
  def move(): Unit = {
    trajectory match {
      case _ =>
        position.y += playerBullet * speed
    }
    if (position.y >= Gdx.graphics.getHeight) impacted()
    if (position.y <= 0) impacted()
  }
}

object Bullet {
  /** Once a bullet has hit a target, it is removed from the array */
  def impact(): Unit = {
    for (b <- Game.bullets.filter(_.impact == true)) {
      Game.bullets.remove(Game.bullets.indexOf(b))
    }
  }

  /** Empties the bullet array */
  def reset(): Unit = {
    Game.bullets.clear()
  }
}
