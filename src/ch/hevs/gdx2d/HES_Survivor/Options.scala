package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.graphics.Color

class Options extends RenderingScreen {
  var menuScreen: BitmapImage = _

  override def onInit(): Unit = {
    menuScreen = new BitmapImage("data/images/Screens/start_menu_with_student.png")
  }

  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.clear()
    g.drawBackground(menuScreen, 0, 0)
    g.drawFilledRectangle(Screen.width / 2 + 10, Screen.height / 2 - 10, Screen.width / 3, Screen.height / 3, 0, Color.DARK_GRAY)
    g.drawFilledRectangle(Screen.width / 2, Screen.height / 2, Screen.width / 3, Screen.height / 3, 0, Color.BLACK)
    g.drawFilledRectangle(Screen.width / 2, Screen.height / 2, Screen.width / 3 - 10, Screen.height / 3 - 10, 0, Color.LIGHT_GRAY)

    g.drawStringCentered(Screen.height * 3 / 5, "Game options", Font.pusab60)
    g.drawStringCentered(Screen.height * 3 / 6, "Choose difficulty:", Font.pusab30)
    g.drawStringCentered(Screen.height * 3 / 6 - 25, "Easy: press NUMPAD1", Font.pusab30)
    g.drawStringCentered(Screen.height * 3 / 6 - 50, "Medium: press NUMPAD2", Font.pusab30)
    g.drawStringCentered(Screen.height * 3 / 6 - 75, "Hard: press NUMPAD3", Font.pusab30)
  }
}
//
//g.drawStringCentered(Screen.height * 3 / 6,
//  "Choose difficulty:" +
//    "Easy: press NUMPAD1" +
//    "Medium: press NUMPAD2" +
//    "Hard: press NUMPAD3", Font.pusab30)
