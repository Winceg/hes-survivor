package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Screen.{height, width}
import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import ch.hevs.gdx2d.lib.utils.Logger
import com.badlogic.gdx.Input

object Screen {
  val height: Int = 1080
  val width: Int = 1920
}

class Screen extends PortableApplication(width, height) {

  private val s = new ScreenManager

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")
    Logger.log("Press enter/space to show the next screen, 1/2/3 to transition to them")
    s.registerScreen(classOf[Menu])
    s.registerScreen(classOf[Game])
    s.registerScreen(classOf[Help])
    s.registerScreen(classOf[Options])
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    s.render(g)
  }

  override def onClick(x: Int, y: Int, button: Int): Unit = {
    // Delegate the click to the child class
    s.getActiveScreen.onClick(x, y, button)
  }

  override def onDrag(x: Int, y: Int): Unit = {
    // Delegate the click to the child class
    s.getActiveScreen.onDrag(x, y)
  }

  override def onKeyDown(keycode: Int): Unit = {
    super.onKeyDown(keycode)
    // Display the next screen without transition
    if (keycode == Input.Keys.ENTER && s.getActiveScreen.getClass == classOf[Menu]) {
      s.transitionTo(1, ScreenManager.TransactionType.SMOOTH)
      Game.resetGame()
    }
    if (Game.player != null) {
      if (keycode == Input.Keys.SPACE && Game.gameOver) s.transitionTo(0, ScreenManager.TransactionType.SMOOTH)
    }
    if (keycode == Input.Keys.ESCAPE && (s.getActiveScreen.getClass == classOf[Options] || s.getActiveScreen.getClass == classOf[Menu])) s.activateScreen(0)
    if (keycode == Input.Keys.F1 && s.getActiveScreen.getClass == classOf[Menu]) s.transitionTo(3, ScreenManager.TransactionType.SMOOTH)
    if (keycode == Input.Keys.F2 && s.getActiveScreen.getClass == classOf[Menu]) s.transitionTo(2, ScreenManager.TransactionType.SMOOTH)
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