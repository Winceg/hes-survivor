package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

object Font {

  var pusabFile: FileHandle = _
  var pusab30: BitmapFont = _
  var pusab60: BitmapFont = _

  pusabFile = Gdx.files.internal("data/images/Fonts/8_bit_pusab/8-bit-pusab.ttf")

  val parameter = new FreeTypeFontGenerator.FreeTypeFontParameter
  val generator = new FreeTypeFontGenerator(pusabFile)
  parameter.size = generator.scaleForPixelHeight(30)
  parameter.color = Color.WHITE
  parameter.shadowOffsetY = 2
  parameter.shadowOffsetX = 2
  parameter.shadowColor = Color.DARK_GRAY
  pusab30 = generator.generateFont(parameter)

  parameter.size = generator.scaleForPixelHeight(60)
  parameter.color = Color.WHITE
  parameter.borderColor = Color.BLACK
  parameter.borderWidth = 4
  parameter.shadowOffsetY = 6
  parameter.shadowOffsetX = 6
  parameter.shadowColor = Color.DARK_GRAY
  pusab60 = generator.generateFont(parameter)

}
