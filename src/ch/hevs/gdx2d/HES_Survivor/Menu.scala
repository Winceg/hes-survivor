package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Game.screen
import ch.hevs.gdx2d.components.bitmaps.BitmapImage
import ch.hevs.gdx2d.components.screen_management.RenderingScreen
import com.badlogic.gdx.{Gdx, Input}
import ch.hevs.gdx2d.lib.{GdxGraphics, ScreenManager}
import ch.hevs.gdx2d.desktop.PortableApplication
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color


object Menu  {}

class Menu extends RenderingScreen(){

  var screen : BitmapImage = _

  override def onInit(): Unit = {
    screen = new BitmapImage("data/images/School.png")
  }
  override def onGraphicRender(g: GdxGraphics): Unit = {
    g.drawPicture(g.getScreenWidth / 2.0f, g.getScreenHeight / 3.0f, screen)
    g.drawSchoolLogo()
  }




}
