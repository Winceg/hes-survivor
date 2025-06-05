package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.{Interpolation, Vector2}
import ch.hevs.gdx2d.components.bitmaps.{BitmapImage, Spritesheet}
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Game extends PortableApplication(1920, 1080) {
  private var player: Player = null
  private var enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  private var SHOOT_TIME: Double = 1 // Duration of each frame
  private var dt: Float = 0
  private val enemyQty = 6
  private var enemyShootIndex = 0
  private var characterSprites: Map[Int, Sprite] = null


  def initGame(): Unit = {

  }

  def initLevel(level: Level): Unit = {

  }

  def gameOver(): Unit = {

  }

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")

    /** Character sprites init */
    characterSprites = Map.apply(
      0 -> new Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4),
      1 -> new Sprite(256, 256, "data/images/spriteSheet/Mudry_wink_20.png", 0, 20),
      2 -> new Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4),
      3 -> new Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4)
    )

    Weapon.initBulletArray()

    /** Player init */
    player = new Player(name = "Raph", sprite = characterSprites(0))
    player.addWeapon(new Weapon(bulletType = 1))

    /** Enemies init */
    for (i <- 0 until enemyQty) {
      val spriteNr: Int = if (characterSprites.size > 2) Random.between(0, characterSprites.size - 1) + 1 else 1
      enemies.append(new Enemy(position = new Vector2(Gdx.graphics.getWidth / 8 + enemies.length * 250, Gdx.graphics.getHeight - 100), sprite = characterSprites(spriteNr)))
    }

  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen and get game area size */
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
    for (b <- bullets) {
      b.move(b.playerBullet)
      b.draw(g)
    }

    /** Update and display enemy */
    dt += Gdx.graphics.getDeltaTime

    for (e <- enemies) {
      if (dt > SHOOT_TIME) {
        dt = 0
        if (SHOOT_TIME > 0.4) {
          SHOOT_TIME *= 0.95
        } else {
          SHOOT_TIME = 0.1
        }
        enemies(Random.nextInt(enemyQty - 1)).shoot(0, bullets)
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
      player.shoot(0, bullets)
    }
    if (button == Input.Buttons.RIGHT) {
      player.shoot(1, bullets)
    }
  }
}
