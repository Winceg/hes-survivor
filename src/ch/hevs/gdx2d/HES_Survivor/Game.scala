package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.math.Vector2
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.graphics.Color
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Game extends PortableApplication(1920, 1080) {
  /** Base attributes */
  private var player: Player = null
  private val enemyQty = 4
  private val enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  private var SHOOT_TIME: Double = 1 // Duration of each frame
  private var dt: Float = 0

  //  def initGame(): Unit = {
  //
  //  }
  //
  //  def initLevel(level: Level): Unit = {
  //
  //  }

  private def gameOver(win: Boolean, g: GdxGraphics): Unit = {
    val winString: String = if (win) "won" else "lost"
    g.clear(Color.BLACK)
    g.drawStringCentered(getWindowHeight * 1.5f, s"You $winString !")
    g.drawFPS()
    g.drawSchoolLogo()
  }

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")
    Enemy.initEnemiesMap()
    Weapon.initBulletArray()

    /** Get Window size */
    val margin: Int = Gdx.graphics.getWidth / 8
    val height: Int = Gdx.graphics.getHeight
    val width: Int = Gdx.graphics.getWidth

    /** Player init */
    player = new Player(name = "Raph", initSprite = Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4))
    player.addWeapon(new Weapon(bulletType = 1))

    /** Enemies init */
    for (_ <- 0 until enemyQty) {
      enemies.append(Enemy.getEnemy(0).copy(startPosition = new Vector2(width / 8 + enemies.length * 250, height - margin / 2), initSprite = Enemy.getEnemy(0).getSprite).copy())
    }

    /**
     * To ask :
     * -Case classes vs classes ?
     * -Why do Gdx.graphics.getWidth/Height not return the same value from different places ?
     * -Method draw in different classes : is the same, should I make a trait ? Even if it's already in one (DrawableObject) ?
     */
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen and get game area size */
    g.clear()
    val height: Int = Gdx.graphics.getHeight

    /** Get mouse position */
    val mouseX = Gdx.input.getX
    val mouseY = height - Gdx.input.getY

    /** Update and display player */
    player.update()
    player.draw(g)
    player.moveTo(mouseX, mouseY)
    player.onCollision(bullets)

    /** Update bullets position */
    for (b <- bullets) {
      b.move()
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
        enemies(Random.nextInt(enemies.length)).shoot(0, bullets)
      }

      /** Move enemy */
      e.moveDelta(2, 0)
      e.onCollision(bullets)
      e.update()
      e.draw(g)

    }
    Enemy.die(enemies)
    Bullet.impact(bullets)

    if (enemies.isEmpty) {
      println("You won !")
      gameOver(win = true, g)
    }
    if (player.getLifePoints <= 0) {
      println("You lost !")
      Enemy.die(enemies)
      gameOver(win = false, g)
    }

    g.drawStringCentered(getWindowHeight * 0.8f, "Welcome to gdx2d !")
    g.drawFPS()
    g.drawSchoolLogo()
  }

  //  override def onKeyDown(keycode: Int): Unit = {
  //    super.onKeyDown(keycode)
  //
  //    keycode match {
  //      case Keys.LEFT =>
  //        player.moveDelta(-10, 0)
  //      case Keys.RIGHT =>
  //        player.moveDelta(10, 0)
  //    }
  //
  //  }

  //  override def onDrag(x: Int, y: Int): Unit = {
  //    super.onDrag(x, y)
  //    player.shoot(0, bullets)
  //  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    if (button == Input.Buttons.LEFT) {
      player.shoot(0, bullets)
    }
    if (button == Input.Buttons.RIGHT) {
      player.shoot(1, bullets)
    }
  }
}
