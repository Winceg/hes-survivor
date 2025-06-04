package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.components.bitmaps.Spritesheet
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Enemy(private var level: Int = 1, private var lifePoints: Int = 100, private var position: Vector2 = new Vector2(100, Gdx.graphics.getHeight - 100),
            private var spawn: Vector2 = new Vector2(100, Gdx.graphics.getHeight - 100), private var weapons: ArrayBuffer[Weapon] = null) extends DrawableObject with Simulatable {
  var direction: Int = 1
  val spriteDimentionX : Int = 256
  val spriteDimentionY: Int = 256
  val ennemySprite = new Spritesheet("data/images/spriteSheet/Mudry_wink_20.png",spriteDimentionX,spriteDimentionY)
  private var currentFrame: Int = 0
  private val nFrames: Int = 20
  private val FRAME_TIME: Double = 0.15 // Duration of each frame
  private var dt: Float = 0

  override def draw(g: GdxGraphics): Unit = {

  }

  def draw1(g: GdxGraphics): Unit = {
    /** Update and count sprite state */

    dt += Gdx.graphics.getDeltaTime

    if (dt > FRAME_TIME) {
      dt = 0
      currentFrame = (currentFrame + 1) % nFrames
    }

    g.draw(ennemySprite.sprites(0)(currentFrame), position.x - (spriteDimentionX) / 2, position.y - (spriteDimentionY / 2))
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
