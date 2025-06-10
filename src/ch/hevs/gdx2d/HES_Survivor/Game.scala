package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.{Gdx, Input}
import ch.hevs.gdx2d.lib.GdxGraphics
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.graphics.Color
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Game {
  val height: Int = 1080
  val width: Int = 1920
  val margin: Int = width / 8
  val enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
}

class Game extends PortableApplication(Game.width, Game.height) {
  /** Base attributes */
  private var player: Player = _
  private var enemyQty = 6
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  private var SHOOT_TIME: Double = 1 // Duration of each frame
  private var dt: Float = 0
  private var currentWave: Int = 1

  private def gameOver(win: Boolean, g: GdxGraphics): Unit = {
    val winString: String = if (win) "won" else "lost"
    g.clear(Color.BLACK)
    g.drawStringCentered(getWindowHeight * 1.5f, s"You $winString !")
    g.drawFPS()
    g.drawSchoolLogo()
  }

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")

    /** Player init */
    player = new Player(name = "Raph", initSprite = new Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4))

    /** Enemies init */
    Enemy.waveSpawn(currentWave,enemyQty)
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen and get game area size */
    g.clear()
    g.setBackgroundColor(Color.LIGHT_GRAY)

    /** Get mouse position */
    val mouseX = Gdx.input.getX
    val mouseY = Game.height - Gdx.input.getY

    /** Update and display player */
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
    for (e <- Game.enemies) {
      if (dt > SHOOT_TIME) {
        dt = 0
        if (SHOOT_TIME > 0.4) {
          SHOOT_TIME *= 0.95
        } else {
          SHOOT_TIME = 0.1
        }
        Game.enemies(Random.nextInt(Game.enemies.length)).shoot(0, bullets)
      }

      /** Move enemy */
      e.moveDelta(2, 0)
      e.onCollision(bullets)
      e.draw(g)

    }
    // check if ennemi are dead and remove them
    Enemy.die()
    // check if ennemi are dead and remove them
    Bullet.impact(bullets)

    // rajouter un if enemies Empti , on recréer des instance , on incrémente la current wave , et on refait un spawn
    if (Game.enemies.isEmpty) { // ici modifier , quand le boss est vancu
      println("You won !")
      gameOver(win = true, g)
    }
    if (player.getLifePoints <= 0) {
      println("You lost !")
      Enemy.die()
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

    override def onDrag(x: Int, y: Int): Unit = {
      super.onDrag(x, y)
      player.shoot(0, bullets)
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

