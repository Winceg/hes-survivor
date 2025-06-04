package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.utils.Drawable

class Bullet(private val damage: Int = 5, startPos: Vector2,
             private val trajectory: Trajectory = null, var playerBullet: Int = 1) extends DrawableObject {

  private var position: Vector2 = new Vector2()
  position.x = startPos.x
  position.y = startPos.y

  override def draw(g: GdxGraphics): Unit = {
    g.drawFilledCircle(position.x, position.y, damage, Color.LIGHT_GRAY)
  }

  def getPosition: Vector2 = position

  def move(playerBullet: Int = 1): Unit = {
    position.y += playerBullet * 50
  }
}
