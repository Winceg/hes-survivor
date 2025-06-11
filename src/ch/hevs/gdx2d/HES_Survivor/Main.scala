package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.desktop.PortableApplication
import ch.hevs.gdx2d.lib.utils.Logger
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import com.badlogic.gdx.Input

object Main extends App {
  val height: Int = 1080
  val width: Int = 1920
  val margin: Int = width / 8

  new Game
  new Menu
}

  class Main extends PortableApplication(Main.width, Main.height) {

    var mainScreen = new ScreenManager
    private var transactionTypeId = 0

    override def onInit(): Unit = {

      setTitle("Multiple screens and transitions")
      Logger.log("Press enter/space to show the next mainScreen, 1/2/3 to transition to them")
      mainScreen.registerScreen(classOf[Menu])
      mainScreen.registerScreen(classOf[Dialog])
      mainScreen.registerScreen(classOf[Game])
      mainScreen.registerScreen(classOf[GameOver])
      mainScreen.registerScreen(classOf[GameWin])

    }

    override def onGraphicRender(g: GdxGraphics): Unit = {
      mainScreen.render(g)
    }


    override def onKeyDown(keycode: Int): Unit = {
      super.onKeyDown(keycode)
      // Display the next mainScreen without transition
      if (keycode == Input.Keys.ENTER) mainScreen.activateNextScreen()
      // Switch to next mainScreen using all available transitions effects
      /*
      if (keycode == Input.Keys.SPACE) {
        mainScreen.transitionToNext(ScreenManager.TransactionType.values(transactionTypeId))
        // Switch to the next transition effect
        transactionTypeId = (transactionTypeId + 1) % ScreenManager.TransactionType.values.length
      }
      if (keycode == Input.Keys.NUM_1) mainScreen.transitionTo(0, ScreenManager.TransactionType.SLICE) // mainScreen.activateScreen(0);
      if (keycode == Input.Keys.NUM_2) mainScreen.transitionTo(1, ScreenManager.TransactionType.SLIDE) // mainScreen.activateScreen(1);
      if (keycode == Input.Keys.NUM_3) mainScreen.transitionTo(2, ScreenManager.TransactionType.SMOOTH) // mainScreen.activateScreen(2);
    }

       */

      def main(args: Array[String]): Unit = {
        new Main
      }

    }
  }



