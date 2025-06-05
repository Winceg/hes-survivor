package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.{Interpolation, Vector2}
import ch.hevs.gdx2d.components.bitmaps.{BitmapImage, Spritesheet}
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys

import scala.collection.mutable.ArrayBuffer

class Game extends PortableApplication(1920, 1080) {
  private var player: Player = null
  private var enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  private var SHOOT_TIME: Double = 1 // Duration of each frame
  private var dt: Float = 0
  private val enemyQty = 3

  def initGame(): Unit = {

  }

  def initLevel(level: Level): Unit = {

  }

  def gameOver(): Unit = {

  }

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")

    player = new Player(name = "Raph")
    player.addWeapon(new Weapon(damage = 20))

    for(i <- 0 until  enemyQty){
      enemies.append(new Enemy(position = new Vector2(Gdx.graphics.getWidth / 8 + enemies.length * 250, Gdx.graphics.getHeight - 100)))
    }

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
      b.move(b.playerBullet)
      b.draw(g)
    }

    /** Update and display enemy */
    dt += Gdx.graphics.getDeltaTime

    for(e <- enemies) {
      if (dt > SHOOT_TIME) {
        dt = 0
        if (SHOOT_TIME > 0.4) {
          SHOOT_TIME *= 0.95
        } else {
          SHOOT_TIME = 0.1
        }
        e.shoot(0, bullets)
      }
      e.update()
      e.draw1(g)

      /** Move enemy */
      if (e.getPosition.x == width - margin) {
        e.direction = -1
      } else if (e.getPosition.x == margin) {
        e.direction = 1
      }
      e.moveDelta(e.direction * 10, 0)
    }
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
