package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

object Font {

  /** Pusab Font */
  private var pusabFile: FileHandle = _
  var pusab20: BitmapFont = _
  var pusab30: BitmapFont = _
  var pusab60: BitmapFont = _

  pusabFile = Gdx.files.internal("data/images/Fonts/8_bit_pusab/8-bit-pusab.ttf")

  private val parameter = new FreeTypeFontGenerator.FreeTypeFontParameter
  private val generator = new FreeTypeFontGenerator(pusabFile)

  /** Font size 20 */
  parameter.size = generator.scaleForPixelHeight(20)
  parameter.color = Color.WHITE
  parameter.shadowOffsetY = 2
  parameter.shadowOffsetX = 2
  parameter.shadowColor = Color.DARK_GRAY
  pusab20 = generator.generateFont(parameter)

  /** Font size 30 */
  parameter.size = generator.scaleForPixelHeight(30)
  parameter.color = Color.WHITE
  parameter.shadowOffsetY = 2
  parameter.shadowOffsetX = 2
  parameter.shadowColor = Color.DARK_GRAY
  pusab30 = generator.generateFont(parameter)

  /** Font size 60 */
  parameter.size = generator.scaleForPixelHeight(60)
  parameter.color = Color.WHITE
  parameter.borderColor = Color.BLACK
  parameter.borderWidth = 4
  parameter.shadowOffsetY = 6
  parameter.shadowOffsetX = 6
  parameter.shadowColor = Color.DARK_GRAY
  pusab60 = generator.generateFont(parameter)

  /** Pixelpoiiz Font */

  private var pixelpoiizFile: FileHandle = _
  var pixelpoiiz20: BitmapFont = _
  var pixelpoiiz40: BitmapFont = _

  pixelpoiizFile = Gdx.files.internal("data/images/Fonts/pixelpoiiz/pixelpoiiz.ttf")

  /** Font size 20 */
  parameter.size = generator.scaleForPixelHeight(20)
  parameter.color = Color.WHITE
  parameter.shadowOffsetY = 1
  parameter.shadowOffsetX = 1
  parameter.shadowColor = Color.DARK_GRAY
  pixelpoiiz20 = generator.generateFont(parameter)

  /** Font size 40 */
  parameter.size = generator.scaleForPixelHeight(40)
  parameter.color = Color.WHITE
  parameter.borderColor = Color.BLACK
  parameter.borderWidth = 4
  parameter.shadowOffsetY = 6
  parameter.shadowOffsetX = 6
  parameter.shadowColor = Color.DARK_GRAY
  pixelpoiiz40 = generator.generateFont(parameter)
}
