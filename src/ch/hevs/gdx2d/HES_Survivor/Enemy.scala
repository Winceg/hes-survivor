package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Enemy(private var level: Int = 1, private var lifePoints: Int = 100, private var position: Vector2 = new Vector2(100, Gdx.graphics.getHeight - 100),
            private var spawn: Vector2 = new Vector2(100, Gdx.graphics.getHeight - 100), private var weapons: ArrayBuffer[Weapon] = null) extends DrawableObject with Simulatable {
  var direction: Int = 1

  override def draw(g: GdxGraphics): Unit = {
    g.drawFilledCircle(position.x, position.y, 15, Color.RED)
  }

  def shoot(): Unit = {

  }

  // Every frame, we need to update
  override def update(): Unit = {

  }

  def getPosition: Vector2 = position

  def moveTo(x: Float, y: Float): Unit = {
    position.x = x
    position.y = y
  }

  def moveDelta(x: Int, y: Int): Unit = {
    moveTo(position.x + x, position.y + y)
  }
}
