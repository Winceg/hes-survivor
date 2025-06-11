package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.interfaces.DrawableObject
import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Enemy(initSprite: Sprite,
            startPosition: Vector2 = new Vector2(),
            initLifePoints: Int = 50,
            initWeapon: Weapon,
            initDamage: Int = 10
           ) extends DrawableObject with Character {

  /** Attributes */
  sprite = initSprite
  position = startPosition
  maxLifePoints = initLifePoints * Game.currentWave
  lifePoints = maxLifePoints
  characterType = -1
  collisionBox = (15, 30)
  private val pattern: Int = Random.nextInt(4)
  private var hDirection: Int = Random.between(-1, 1)
  if (hDirection == 0) hDirection = 1
  private var vDirection: Int = 1
  private val damage: Int = initDamage * Game.currentWave
  addWeapon(initWeapon)

  def getDamage: Int = damage

  def moveDelta(x: Int, y: Int): Unit = {
    /** Left to right movement */
    if (position.x >= Game.width - Game.margin) {
      this.hDirection = -1
    } else if (position.x <= Game.margin) {
      this.hDirection = 1
    }

    /** Up and down movement */
    if (position.y >= Game.height - Game.margin) {
      this.vDirection = -1
    } else if (position.y <= Game.height / 2) {
      this.vDirection = 1
    }

    /** Different pattern are available */
    this.pattern match {
      case 0 =>
        moveTo(position.x + hDirection * x, position.y)
      case 1 =>
        moveTo(position.x + hDirection * x, position.y + vDirection * y)
      case 2 =>
        moveTo(position.x + hDirection * 2 * x, position.y)
      case 3 =>
        moveTo(position.x + hDirection * 2 * x, position.y + vDirection * 2 * y)
    }
  }
}

object Enemy {
  private def createEnemy(enemyType: Int): Enemy = {
    val randomPosition: Vector2 = new Vector2(Random.between(Game.margin, Game.width - Game.margin), Random.between(Game.height / 2, Game.height - 100))
    enemyType match {
      case 0 => new Enemy(initSprite = new Sprite(128, 128, "data/images/spriteSheet/entity/jaquemet_walk_128_4.png", 0, 4), startPosition = randomPosition, initWeapon = new Weapon(bulletType = 0))
      case 1 => new Enemy(initSprite = new Sprite(128, 128, "data/images/spriteSheet/entity/Mudry_walk_128_4.png", 0, 4), startPosition = randomPosition, initWeapon = new Weapon(bulletType = 2))
      case 2 => new Enemy(initSprite = new Sprite(128, 128, "data/images/spriteSheet/entity/General_walk_128_4.png", 0, 4), startPosition = randomPosition, initWeapon = new Weapon(bulletType = 1))
      case 3 => new Enemy(initSprite = new Sprite(128, 128, "data/images/spriteSheet/entity/Mudry_Wink_20.png", 0, 20), startPosition = randomPosition, initWeapon = new Weapon(bulletType = 3))
    }
  }

  def waveSpawn(): Unit = {
    for (_ <- 0 until Game.currentWave * Game.enemyQty) {
      Game.enemies.append(createEnemy(Random.between(0, 3)))
    }
  }

  def spawn(e: ArrayBuffer[Enemy]): Unit = {

  }

  /** Removes killed enemiew from array */
  def die(): Unit = {
    for (e <- Game.enemies.filter(_.getLifePoints <= 0)) {
      Game.enemies.remove(Game.enemies.indexOf(e))
    }
  }
}