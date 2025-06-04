package ch.hevs.gdx2d.HES_Survivor


import ch.hevs.gdx2d.components.bitmaps.Spritesheet
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Player(private val name: String = "Player 1", private var level: Int = 1, private var lifePoints: Int = 10,
             private var position: Vector2 = new Vector2(100, 100),
             private var weapons: ArrayBuffer[Weapon] = ArrayBuffer(new Weapon())) extends DrawableObject with Simulatable {

  val playerSprite = new Spritesheet("data/images/spriteSheet/player_walk.png", 64, 64)

  override def draw(g: GdxGraphics): Unit = {
    g.drawFilledCircle(position.x, position.y, 15, Color.BLUE)
  }

  def draw1(g: GdxGraphics, x: Float, y: Float, currentFrame: Int): Unit = {

    g.draw(playerSprite.sprites(0)(currentFrame), x - 32, y - 32)

  }

  def getPosition: Vector2 = position

  def shoot(weapon: Int = 0, bullets: ArrayBuffer[Bullet]): Unit = {
    if (weapon >= weapons.length) {
      weapons(0).shoot(position, bullets)
    } else {
      weapons(weapon).shoot(position, bullets)
    }

  }

  // Every frame, we need to update
  override def update(): Unit = {

  }

  def moveTo(x: Float, y: Float): Unit = {
    position.x = x
    position.y = y
  }

  def moveDelta(x: Int, y: Int): Unit = {
    moveTo(position.x + x, position.y + y)
  }


}
