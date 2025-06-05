package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.utils.Drawable

class Bullet(private val damage: Int = 5, startPos: Vector2 = new Vector2(0, 0),
             private val trajectory: Trajectory = null, var playerBullet: Int = 1, private var sprite: Sprite) extends DrawableObject {

  private var position: Vector2 = new Vector2()
  position.x = startPos.x
  position.y = startPos.y

  override def draw(g: GdxGraphics): Unit = {
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }

  def getPosition: Vector2 = position

  def setPosition(pos: Vector2): Unit = {
    position.x = pos.x
    position.y = pos.y
  }

  def setPlayerBullet(x: Int): Unit = {
    playerBullet = x
  }

  def move(playerBullet: Int = 1): Unit = {
    position.y += playerBullet * 10
  }
}
