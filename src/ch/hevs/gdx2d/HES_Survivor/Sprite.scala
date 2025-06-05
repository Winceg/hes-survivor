package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.components.bitmaps.Spritesheet
import com.badlogic.gdx.Gdx

case class Sprite(spriteDimentionX: Int = 256,
             spriteDimentionY: Int = 256,
             spriteFile: String,
             private var currentFrame: Int = 0,
             private val nFrames: Int = 10,
             private val FRAME_TIME: Double = 0.15, // Duration of each frame
             private var dt: Float = 0) {

  val spriteSheet: Spritesheet = new Spritesheet(spriteFile, spriteDimentionX, spriteDimentionY)

  def syncSprite(): Int ={
    dt += Gdx.graphics.getDeltaTime
    if (dt > FRAME_TIME) {
      dt = 0
      currentFrame = (currentFrame + 1) % nFrames
    }
    currentFrame
  }
}