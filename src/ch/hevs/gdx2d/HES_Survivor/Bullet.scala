package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.utils.Drawable

class Bullet(private val damage: Int = 10, private var position: Vector2,
             private val trajectory: Trajectory = null) extends DrawableObject{

  override def draw(g: GdxGraphics): Unit = {
    g.drawFilledCircle(position.x, position.y, 5, Color.GRAY)
  }

  def shoot(): Unit = {
    position.x += 50
  }
}
