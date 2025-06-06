package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Weapon.bulletMap
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Weapon(private var ammo: Int = -1,
             private val fireRate: Int = 1,
             private val bulletType: Int = 0) {

  def shoot(position: Vector2, bullets: ArrayBuffer[Bullet], playerBullet: Int): Unit = {
    val bulletShot: Bullet = bulletMap(bulletType).copy(startPos = position, playerBullet = playerBullet, sprite = bulletMap(bulletType).getSprite.copy())
    bullets.append(bulletShot)

    ammo -= 1
  }
}

object Weapon {
  var bulletMap: Map[Int, Bullet] = Map.empty

  def initBulletArray(): Unit = {
    /** Init of the different bullet types */
    bulletMap = Map.apply(
      0 -> Bullet(damage = 1, sprite = Sprite(64, 64, "data/images/spriteSheet/ScalaBullet_64_10.png", 0, 10, 0.05)),
      1 -> Bullet(damage = 10, speed = 18, sprite = Sprite(64, 64, "data/images/spriteSheet/ScalaBullet_64_10.png", 0, 10))
    )
  }
}
