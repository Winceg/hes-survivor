package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Game.player
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.{Gdx, Input}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Game {
  /** Base attributes */
  val height: Int = 1080
  val width: Int = 1920
  val margin: Int = width / 8
  val enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
  var player: Player = _
  private var SHOOT_TIME: Double = 1 // Duration of each frame
  var dt: Float = 0
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  var currentWave: Int = 1
  var enemyQty = 3
  var gameOver: Boolean = false

  def resetGame(): Unit = {
    Enemy.reset()
    Bullet.reset()
    Game.currentWave = 1
    Game.SHOOT_TIME = 1
    if (player != null) {
      player.reset()
    }
    gameOver = false
    Enemy.waveSpawn()
  }
}

class Game extends RenderingScreen {
  /** Base attributes */
  private var backGround: BitmapImage = _
  private var gameOverScreen: BitmapImage = _
  private var gameWin: BitmapImage = _

  /** Game Over screen */
  private def gameOver(win: Boolean, g: GdxGraphics): Unit = {
    g.clear()
    g.drawBackground(gameOverScreen, 0, 0)
    g.drawFilledRectangle(Screen.width / 2 + 10, Screen.height / 2 - 10, Screen.width / 3, Screen.height / 3, 0, Color.DARK_GRAY)
    g.drawFilledRectangle(Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 3, 0, Color.BLACK)
    g.drawFilledRectangle(Screen.width / 2, Screen.height / 2, Screen.width / 3 - 10, Screen.height / 3 - 10, 0, Color.LIGHT_GRAY)

    g.drawStringCentered(Screen.height * 3 / 5, s"${if (win) "Congratulations!" else "Game over!"}", Font.pusab60)
    g.drawStringCentered(Screen.height * 3 / 6, s"You${if (win) " got your Bachelor!" else "'ll do better next time!"}", Font.pusab30)
    g.drawStringCentered(Screen.height * 3 / 7, "Press F3 to go back to main menu", Font.pusab30)
  }

  override def onInit(): Unit = {
    backGround = new BitmapImage("data/images/Screens/backGround.png")
    gameOverScreen = new BitmapImage("data/images/Screens/start_menu.png")
    gameWin = new BitmapImage("data/images/Screens/winScreen.png")

    /** Player init */
    player = new Player(name = "Raph", initSprite = new Sprite(256, 256, "data/images/spriteSheet/entity/player_walk.png", 0, 4))
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen and get game area size */
    g.clear()
    g.drawBackground(backGround, 0, 0)

    /** Get mouse position */
    val mouseX = Gdx.input.getX
    val mouseY = Game.height - Gdx.input.getY

    /** Update and display player */
    player.draw(g)
    player.moveTo(mouseX, mouseY)
    player.onCollision()
    player.onCollisionWithEnemy()
    player.displayArmory(g)

    /** Update  all bullets position */
    for (b <- Game.bullets) {
      b.move()
      b.draw(g)
    }

    /** Update and display enemy */
    // MODIFY MODIFY MODIFY //
    Game.dt += Gdx.graphics.getDeltaTime
    for (e <- Game.enemies) {
      if (Game.dt > Game.SHOOT_TIME) {
        Game.dt = 0
        if (Game.SHOOT_TIME > 0.1) {
          Game.SHOOT_TIME *= 0.95
        } else {
          Game.SHOOT_TIME = 0.1
        }
        Game.enemies(Random.nextInt(Game.enemies.length)).shoot()
        println(s"Current wave: ${Game.currentWave}")
      }

      /** Move enemy */
      e.moveDelta(2, 2)
      e.onCollision()
      e.draw(g)

    }

    /** Removing all enemies marked as hit for deletion */
    Enemy.die()

    /** Removing all bullets hit for deletion */
    Bullet.impact()

    /** HUD */
    g.drawString(50, 90, s"Semester: ${Game.currentWave}", Font.pusab30)
    g.drawFPS()
    g.drawSchoolLogo()

    /** When all enemies of a wave have been eliminated, step to the next wave and level up */
    if (Game.enemies.isEmpty) {
      Game.currentWave += 1
      player.levelUp()
      if (Game.currentWave < 6) {
        Enemy.waveSpawn()
      } else if (Game.currentWave < 7) {
        Enemy.bossSpawn()
      } else {
        Game.gameOver = true
        gameOver(win = true, g)
      }

    }
    if (player.getLifePoints <= 0) { // ici afficher le game over screeen et sortir de la boucle
      Game.gameOver = true
      gameOver(win = false, g)
    }

  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)

    keycode match { // try catch pour eviter crash
      case Keys.SPACE =>
        player.shoot(2)
    }
  }

//  override def onDrag(x: Int, y: Int): Unit = {
//    super.onDrag(x, y)
//    player.shoot()
//  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    if (button == Input.Buttons.LEFT) {
      player.shoot()
    }
    if (button == Input.Buttons.RIGHT) {
      player.shoot(1)
    }
  }
}

