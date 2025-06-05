package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.ArrayBuffer

case class Enemy(initSprite: Sprite,
                 startPosition: Vector2 = new Vector2(),
                 initLifePoints: Int = 50,
                 private var spawn: Vector2 = new Vector2(100, Gdx.graphics.getHeight - 100)
                ) extends DrawableObject with Character with Simulatable {

  sprite = initSprite
  position = startPosition
  lifePoints = initLifePoints
  var direction: Int = 1

  override def shoot(weapon: Int = 0, bullets: ArrayBuffer[Bullet]): Unit = {
    if (weapon >= getWeapons.length) {
      getWeapons(0).shoot(position, bullets, -1)
    } else {
      getWeapons(weapon).shoot(position, bullets, -1)
    }
  }

  // Every frame, we need to update
  override def update(): Unit = {
  }
}

object Enemy {
  private var enemiesMap: Map[Int, Enemy] = Map.empty

  /** Enemies sprites init */
  private val characterSprites: Map[Int, Sprite] = Map.apply(
    0 -> Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4),
    1 -> Sprite(256, 256, "data/images/spriteSheet/Mudry_wink_20.png", 0, 20),
    2 -> Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4),
    3 -> Sprite(256, 256, "data/images/spriteSheet/player_walk.png", 0, 4)
  )

  def initEnemiesMap(): Unit = {
    enemiesMap = Map.apply(
      0 -> new Enemy(initSprite = characterSprites(0)),
      1 -> new Enemy(initSprite = characterSprites(1)),
      2 -> new Enemy(initSprite = characterSprites(2)),
      3 -> new Enemy(initSprite = characterSprites(3))
    )
  }

  def getEnemy(t: Int): Enemy = {
    enemiesMap(t)
  }
}