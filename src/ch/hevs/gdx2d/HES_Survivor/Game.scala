package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Game.screen
import com.badlogic.gdx.{Gdx, Input}
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Game {


  val enemies: ArrayBuffer[Enemy] = new ArrayBuffer[Enemy]
  var SHOOT_TIME: Double = 1 // Duration of each frame
  var dt: Float = 0
  var bullets: ArrayBuffer[Bullet] = new ArrayBuffer[Bullet]()
  var currentWave: Int = 1
  var enemyQty = 6
  var screen: ScreenManager = new ScreenManager()
}

class Game extends PortableApplication(Main.width, Main.height) {
  /** Base attributes */
  private var player: Player = _
  // modify to screenmanager
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
    player = new Player(name = "Raph", initSprite = new Sprite(256, 256, "data/images/spriteSheet/entity/player_walk.png", 0, 4))

    /** Enemies init */
    Enemy.waveSpawn()
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    /** Clears the screen and get game area size */
    g.clear()
    g.setBackgroundColor(Color.LIGHT_GRAY)

    /** Get mouse position */
    val mouseX = Gdx.input.getX
    val mouseY = Main.height - Gdx.input.getY

    /** Update and display player */
    player.draw(g)
    player.moveTo(mouseX, mouseY)
    player.onCollision()
    player.onCollisionWithEnemy()

    /** Update bullets position */
    for (b <- Game.bullets) {
      b.move()
      b.draw(g)
    }

    /** Update and display enemy */
    Game.dt += Gdx.graphics.getDeltaTime
    for (e <- Game.enemies) {
      if (Game.dt > Game.SHOOT_TIME) {
        Game.dt = 0
        if (Game.SHOOT_TIME > 0.4) {
          Game.SHOOT_TIME *= 0.95
        } else {
          Game.SHOOT_TIME = 0.1
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

    // rajouter un if enemies Empti , on recréer des instance , on incrémente la current wave , et on refait un spawn
    if (Game.enemies.isEmpty) { // ici modifier , quand le boss est vaincu
      Game.currentWave += 1
      player.levelUp()
      Enemy.waveSpawn()
//      println("You won !")
//      gameOver(win = true, g)
    }
    if (player.getLifePoints <= 0) {
      println("You lost !")
      Enemy.die()
      gameOver(win = false, g)
    }
    g.drawString(50, 80, s"Semester: ${Game.currentWave}")
    g.drawFPS()
    g.drawSchoolLogo()
  }

    override def onKeyDown(keycode: Int): Unit = {
      super.onKeyDown(keycode)

      keycode match {
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

