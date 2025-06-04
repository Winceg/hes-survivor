package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.Interpolation
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys

class Game extends PortableApplication(1920, 1080) {
  private var imgBitmap: BitmapImage = null
  private var playerBitmap: BitmapImage = null
  private var badGuyBitmap: BitmapImage = null
  private var player : Player = null
  private var enemy : Enemy = null

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
    player.draw(g)
    player.moveTo(mouseX, mouseY)

    /** Update and display enemy */
    enemy.update()
    enemy.draw(g)
    /** Draw stuff */
    if(enemy.getPosition.x < width - margin && enemy.direction == 1) {
      enemy.moveDelta(10, 0)
    }else if(enemy.getPosition.x == width - margin){
      enemy.direction = -1
    }else if(enemy.getPosition.x > margin && enemy.direction == 1) {
      enemy.moveDelta(-10, 0)
    }else if(enemy.getPosition.x == margin){
      enemy.direction = 1
    }

    g.drawStringCentered(getWindowHeight * 0.8f, "Welcome to gdx2d !")
    g.drawFPS()
    g.drawSchoolLogo()
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)

    keycode match{
      case Keys.LEFT =>
        player.moveDelta(-10, 0)
      case Keys.RIGHT =>
        player.moveDelta(10, 0)
    }

  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    if (button == Input.Buttons.LEFT) {
      player.shoot()
    }
  }
}
