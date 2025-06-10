package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.math.Vector2
import scala.collection.mutable.ArrayBuffer

class Weapon(private val bulletType: Int = 0
             //private val fireRate: Int = 1
            ) {

  def shoot(position: Vector2, bullets: ArrayBuffer[Bullet], playerBullet: Int): Unit = {
    //val bulletShot: Bullet = bulletMap(bulletType).copy(startPos = position, playerBullet = playerBullet, sprite = bulletMap(bulletType).getSprite.copy())
    bullets.append(createBullet(bulletType, position, playerBullet))
  }

  private def createBullet(bulletType: Int, position: Vector2, playerBullet: Int): Bullet = {
    bulletType match {
      case 0 => new Bullet(damage = 10, startPos = position, playerBullet = playerBullet, sprite = new Sprite(64, 64, "data/images/spriteSheet/integral_64_8.png", 0, 8, 0.05))
      case 1 => new Bullet(damage = 8, startPos = position, playerBullet = playerBullet, speed = 18, sprite = new Sprite(64, 64, "data/images/spriteSheet/sum_64_8.png", 0, 8))
      case 2 => new Bullet(damage = 6, startPos = position, playerBullet = playerBullet, speed = 18, sprite = new Sprite(64, 64, "data/images/spriteSheet/ScalaBullet_64_10.png"))
      case 3 => new Bullet(damage = 7, startPos = position, playerBullet = playerBullet, speed = 18, sprite = new Sprite(64, 64, "data/images/spriteSheet/limit_64_8.png", 0, 8))
    }
  }
}
