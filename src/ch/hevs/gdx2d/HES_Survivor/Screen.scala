package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Screen.{height, width}
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.Input

object Screen {
  val height: Int = 1080
  val width: Int = 1920
}

class Screen extends PortableApplication(width, height) {
  private val s = new ScreenManager

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")
    s.registerScreen(classOf[Menu])
    s.registerScreen(classOf[Game])
    s.registerScreen(classOf[Help])
    s.registerScreen(classOf[Options])
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    s.render(g)
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    s.getActiveScreen.onClick(x, y, button)
  }

  override def onDrag(x: Int, y: Int): Unit = {
    s.getActiveScreen.onDrag(x, y)
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)

    /** ENTER lauches the game from the main menu */
    if (keycode == Input.Keys.ENTER && s.getActiveScreen.getClass == classOf[Menu]) {
      s.transitionTo(1, ScreenManager.TransactionType.SMOOTH)
      Game.resetGame()
    }

    /** SPACE goes back to menu after the end of the game or from the controls screen */
    if (Game.player != null) {
      if (keycode == Input.Keys.SPACE && (Game.gameOver || s.getActiveScreen.getClass == classOf[Help])) s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)
    }

    /** ESC quits the game */
    if (keycode == Input.Keys.ESCAPE && (s.getActiveScreen.getClass == classOf[Options] || s.getActiveScreen.getClass == classOf[Menu])) s.activateScreen(0)

    /** From the main menu, F1 opens the game options, and F2 the controls */
    if (keycode == Input.Keys.F1 && s.getActiveScreen.getClass == classOf[Menu]) s.transitionTo(3, ScreenManager.TransactionType.SMOOTH)
    if (keycode == Input.Keys.F2 && s.getActiveScreen.getClass == classOf[Menu]) s.transitionTo(2, ScreenManager.TransactionType.SMOOTH)

    /** From the options menu, 1, 2 and 3 select the difficulty level */
    if (keycode == Input.Keys.NUMPAD_1 && s.getActiveScreen.getClass == classOf[Options]) {
      Game.enemyQty = 3
      s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)
    }
    if (keycode == Input.Keys.NUMPAD_2 && s.getActiveScreen.getClass == classOf[Options]) {
      Game.enemyQty = 5
      s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)
    }
    if (keycode == Input.Keys.NUMPAD_3 && s.getActiveScreen.getClass == classOf[Options]) {
      Game.enemyQty = 7
      s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)
    }
  }
}