package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.Interpolation
import ch.hevs.gdx2d.components.bitmaps.{BitmapImage, Spritesheet}
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys

import scala.collection.mutable.ArrayBuffer

class Game extends PortableApplication(1920, 1080) {
  private var imgBitmap: BitmapImage = null
  private var playerBitmap: BitmapImage = null
  private var badGuyBitmap: BitmapImage = null
  private var player: Player = null
  private var enemy: Enemy = null
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()

  def initGame(): Unit = {

  }

  def initLevel(level: Level): Unit = {

  }

  def gameOver(): Unit = {

  }

  override def onInit(): Unit = {
    setTitle("Hello World - HES Survivor")
    // Load a custom image (or from the lib "res/lib/icon64.png")
    imgBitmap = new BitmapImage("data/images/ISC_logo.png")
    playerBitmap = new BitmapImage("data/images/ISC_logo.png")
    badGuyBitmap = new BitmapImage("data/images/ISC_logo.png")


    player = new Player(name = "Raph")
    enemy = new Enemy()

  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen */
    g.clear()

    val margin: Int = Gdx.graphics.getWidth / 8
    val height: Int = Gdx.graphics.getHeight
    val width: Int = Gdx.graphics.getWidth

    /** Get mouse position */
    val mouseX = Gdx.input.getX
    val mouseY = height - Gdx.input.getY

    /** Update and display player */
    player.update()
    player.draw1(g, mouseX, mouseY)
    player.moveTo(mouseX, mouseY)

    /** Update bullets position */
    for(b <- bullets){
      //if(b.getPosition.x )
      b.move()
      b.draw(g)
    }

    /** Update and display enemy */
    enemy.update()
    enemy.draw1(g)

    /** Draw stuff */
    if (enemy.getPosition.x == width - margin) {
      enemy.direction = -1
    } else if (enemy.getPosition.x == margin) {
      enemy.direction = 1
    }
    enemy.moveDelta(enemy.direction * 10, 0)

    g.drawStringCentered(getWindowHeight * 0.8f, "Welcome to gdx2d !")
    g.drawFPS()
    g.drawSchoolLogo()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)

    keycode match {
      case Keys.LEFT =>
        player.moveDelta(-10, 0)
      case Keys.RIGHT =>
        player.moveDelta(10, 0)
    }

  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    if (button == Input.Buttons.LEFT) {
      player.shoot(bullets = bullets)
    }
    if (button == Input.Buttons.RIGHT) {
      player.shoot(1, bullets)
    }
  }
}
