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
  private var transactionTypeId = 0

  override def onInit(): Unit = {
    setTitle("HES Survivor - Will you pass the test ?")
    Logger.log("Press enter/space to show the next screen, 1/2/3 to transition to them")
    s.registerScreen(classOf[Menu])
    s.registerScreen(classOf[Game])
    s.registerScreen(classOf[Help])
    s.registerScreen(classOf[GameOver])
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
    if (keycode == Input.Keys.ENTER) s.transitionTo(1, ScreenManager.TransactionType.SMOOTH)
    if (Game.player != null) {
      if (keycode == Input.Keys.SPACE && Game.player.getLifePoints <= 0) s.activateScreen(0)
    }
    if (keycode == Input.Keys.ESCAPE) s.activateScreen(0)
    if (keycode == Input.Keys.BACKSPACE) s.activateScreen(2)
    if (keycode == Input.Keys.NUM_1) s.activateScreen(3)

    // Switch to next screen using all available transitions effects
//    if (keycode == Input.Keys.SPACE) {
//      s.transitionToNext(ScreenManager.TransactionType.values(transactionTypeId))
//      // Switch to the next transition effect
//      transactionTypeId = (transactionTypeId + 1) % ScreenManager.TransactionType.values.length
//    }
//    if (keycode == Input.Keys.NUM_1) s.transitionTo(0, ScreenManager.TransactionType.SLICE) // s.activateScreen(0);
//    if (keycode == Input.Keys.NUM_2) s.transitionTo(1, ScreenManager.TransactionType.SLIDE) // s.activateScreen(1);
//    if (keycode == Input.Keys.NUM_3) s.transitionTo(2, ScreenManager.TransactionType.SMOOTH) // s.activateScreen(2);
  }
}