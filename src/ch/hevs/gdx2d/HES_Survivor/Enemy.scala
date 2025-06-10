package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Enemy(initSprite: Sprite,
                 startPosition: Vector2 = new Vector2(),
                 initLifePoints: Int = 50,
                 pattern: Int = 1
                ) extends DrawableObject with Character with Simulatable {

  /** Attributes */
  sprite = initSprite
  position = startPosition
  lifePoints = initLifePoints
  characterType = -1
  var direction: Int = 1
  val width: Int = Gdx.graphics.getWidth
  val margin: Int = Gdx.graphics.getWidth / 8

  def moveDelta(x: Int, y: Int): Unit = {



    /** Left to right movement */
    if (position.x == width - margin) {
      direction = -1
    } else if (position.x == margin) {
      direction = 1
    }

    /** Different pattern are available */
    pattern match {
      case _ =>
        moveTo(position.x + direction * x, position.y + y)
    }
  }

  // Every frame, we need to update
  override def update(): Unit = {
  }
}

object Enemy {
  private var enemiesMap: Map[Int, Enemy] = Map.empty
  val width: Int = Gdx.graphics.getWidth
  val margin: Int = Gdx.graphics.getWidth / 8

  /** Enemies sprites init */
  private val characterSprites: Map[Int, Sprite] = Map.apply(
    0 -> Sprite(128, 128, "data/images/spriteSheet/jaquemet_walk_128_4.png", 0, 4),
    1 -> Sprite(128, 128, "data/images/spriteSheet/Mudry_walk_128_4.png", 0, 4),
    2 -> Sprite(128, 128, "data/images/spriteSheet/General_walk_128_4.png", 0, 4),
    3 -> Sprite(128, 128, "data/images/spriteSheet/Mudry_Wink_20.png", 0, 20)
  )

  def initEnemiesMap(): Unit = {
    enemiesMap = Map.apply(
      0 -> new Enemy(initSprite = characterSprites(0)),
      1 -> new Enemy(initSprite = characterSprites(1)),
      2 -> new Enemy(initSprite = characterSprites(2)),
      3 -> new Enemy(initSprite = characterSprites(3))
    )
  }

  def getEnemy(t: Int): Enemy = enemiesMap(t)

  def waveSpawn(e: ArrayBuffer[Enemy],wave: Int,enemyQty : Int): Unit = {
    for (_ <- 0 until enemyQty) {
      // e.append(Enemy.getEnemy(Random.between(0,3)).copy(startPosition = new Vector2(Random.between((Gdx.graphics.getWidth + margin).toFloat, 150 ), (Gdx.graphics.getHeight-100).toFloat), initSprite = Enemy.getEnemy(0).getSprite).copy())
      e.append(Enemy.getEnemy(Random.between(0,3)).copy(startPosition = new Vector2( 300 , 150 ), initSprite = Enemy.getEnemy(0).getSprite).copy())
    }
  }
  def spawn(e: ArrayBuffer[Enemy]) :Unit = {

  }

  /** Removes killed enemiew from array */
  def die(enemies: ArrayBuffer[Enemy]): Unit = {
    for (e <- enemies.filter(_.getLifePoints <= 0)) {
      enemies.remove(enemies.indexOf(e))
    }
  }
}