package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2

case class Bullet(private val damage: Int = 5,
                  startPos: Vector2 = new Vector2(0, 0),
                  private val trajectory: Trajectory = null,
                  var playerBullet: Int = 1,
                  private var speed: Int = 10,
                  private var sprite: Sprite
                 ) extends DrawableObject {

  private val position: Vector2 = new Vector2()
  position.x = startPos.x
  position.y = startPos.y

  override def draw(g: GdxGraphics): Unit = {
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }

  def getPosition: Vector2 = position

  def getSprite: Sprite = sprite

  def getDamage: Int = damage

  def move(): Unit = {
    trajectory match {
      case _ =>
        position.y += playerBullet * speed
    }
  }
}
