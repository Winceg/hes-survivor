package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.components.bitmaps.Spritesheet
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Enemy(private var level: Int = 1,
            private var lifePoints: Int = 100,
            private val sprite: Sprite,
            private var position: Vector2,
            private var spawn: Vector2 = new Vector2(100, Gdx.graphics.getHeight - 100),
            private var weapons: ArrayBuffer[Weapon] = ArrayBuffer(new Weapon())
           ) extends DrawableObject with Simulatable {

  var direction: Int = 1

  override def draw(g: GdxGraphics): Unit = {
  }

  def draw1(g: GdxGraphics): Unit = {
    g.draw(sprite.spriteSheet.sprites(0)(sprite.syncSprite()), position.x - sprite.spriteDimentionX / 2, position.y - sprite.spriteDimentionY / 2)
  }

  def shoot(weapon: Int = 0, bullets: ArrayBuffer[Bullet]): Unit = {
    if (weapon >= weapons.length) {
      weapons(0).shoot(position, bullets, -1)
    } else {
      weapons(weapon).shoot(position, bullets, -1)
    }
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
