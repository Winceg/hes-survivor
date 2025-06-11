package ch.hevs.gdx2d.HES_Survivor


import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import com.badlogic.gdx.{Gdx, Input}
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Game {

  val height: Int = 1080
  val width: Int = 1920
  val margin: Int = width / 8
  val enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
  var SHOOT_TIME: Double = 1 // Duration of each frame
  var dt: Float = 0
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  var currentWave: Int = 1
  var enemyQty = 6
  }

class Game extends PortableApplication (Game.width, Game.height) {
  /** Base attributes */
  private var player: Player = _
  var menuScreen : BitmapImage = _
  var backGround : BitmapImage = _
  var gameOver : BitmapImage = _
  var gameWin : BitmapImage = _

  // NOTN USED
  private def gameOver(win: Boolean, g: GdxGraphics): Unit = {
    val winString: String = if (win) "won" else "lost"
    g.clear(Color.BLACK)
    g.drawStringCentered(getWindowHeight * 1.5f, s"You $winString !")
    g.drawFPS()
    g.drawSchoolLogo()
  }

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")

    menuScreen = new BitmapImage("data/images/Screens/School.jpg")
    backGround = new BitmapImage("data/images/Screens/backGround.png")
    gameOver = new BitmapImage("data/images/Screens/gameOver.png")
    gameWin = new BitmapImage("data/images/Screens/winScreen.png")

    /** Player init */
    player = new Player(name = "Raph", initSprite = new Sprite(256, 256, "data/images/spriteSheet/entity/player_walk.png", 0, 4))

    /** Enemies init */
    Enemy.waveSpawn()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen and get game area size */
    g.clear()
    g.drawBackground(backGround, 0, 0)
    //g.setBackgroundColor(Color.LIGHT_GRAY)

    /** Get mouse position */
    val mouseX = Gdx.input.getX
    val mouseY = Game.height - Gdx.input.getY

    /** Update and display player */
    player.draw(g)
    player.moveTo(mouseX, mouseY)
    player.onCollision()
    player.onCollisionWithEnemy()

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
        if (Game.SHOOT_TIME > 0.3) {
          Game.SHOOT_TIME *= 0.95
        } else {
          Game.SHOOT_TIME = 0.3
        }
        Game.enemies(Random.nextInt(Game.enemies.length)).shoot(0)
      }

      /** Move enemy */
      e.moveDelta(2, 2)
      e.onCollision()
      e.draw(g)

    }
    // check if ennemi are dead and remove them
    Enemy.die()
    // check if ennemi are dead and remove them
    Bullet.impact()
    g.drawString(50, 80, s"Semester: ${Game.currentWave}")
    g.drawFPS()
    g.drawSchoolLogo()

    // rajouter un if enemies Empti , on recréer des instance , on incrémente la current wave , et on refait un spawn
    if (Game.enemies.isEmpty) { // ici modifier , quand le boss est vaincu
      Game.currentWave += 1
      player.levelUp() // marche mais pas totalement (armes)
      Enemy.waveSpawn()

    }
    if (player.getLifePoints <= 0) { // ici afficher le game over screeen et sortir de la boucle
      Enemy.die()
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

    override def onDrag(x: Int, y: Int): Unit = {
      super.onDrag(x, y)
      player.shoot(0)
    }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    if (button == Input.Buttons.LEFT) {
      player.shoot()
    }
    if (button == Input.Buttons.RIGHT) {
      player.shoot(1)
    }
  }
}

