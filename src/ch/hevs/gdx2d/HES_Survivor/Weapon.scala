package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.math.Vector2

class Weapon(val weaponName: String = "",
             private val bulletType: Int = 0,
             val damage: Int = 10
             //private val fireRate: Int = 1
            ) {

  def shoot(position: Vector2, playerBullet: Int): Unit = {
    Game.bullets.append(createBullet(bulletType, position, playerBullet, damage))
  }

  private def createBullet(bulletType: Int, position: Vector2, playerBullet: Int, damage: Int): Bullet = {
    bulletType match {
      case 0 => new Bullet(initDamage = damage, startPos = position, playerBullet = playerBullet, sprite = new Sprite(64, 64, "data/images/spriteSheet/bullets/integral_64_8.png", 0, 8, 0.05))
      case 1 => new Bullet(initDamage = damage, startPos = position, playerBullet = playerBullet, speed = 18, sprite = new Sprite(64, 64, "data/images/spriteSheet/bullets/sum_64_8.png", 0, 8))
      case 2 => new Bullet(initDamage = damage, startPos = position, playerBullet = playerBullet, speed = 22, sprite = new Sprite(64, 64, "data/images/spriteSheet/bullets/ScalaBullet_64_10.png", 0, 8))
      case 3 => new Bullet(initDamage = damage, startPos = position, playerBullet = playerBullet, speed = 14, sprite = new Sprite(64, 64, "data/images/spriteSheet/bullets/limit_64_8.png", 0, 8))
      case 4 => new Bullet(initDamage = damage, startPos = position, playerBullet = playerBullet, speed = 14, sprite = new Sprite(64, 64, "data/images/spriteSheet/bullets/PythonBullet_64_10.png", 0, 8))
    }
  }
}