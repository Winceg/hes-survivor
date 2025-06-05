package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.HES_Survivor.Weapon.{bulletMap}
import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Weapon(private var ammo: Int = -1, private val fireRate: Int = 1, private val bulletType: Int = 0) {

  def shoot(position: Vector2, bullets: ArrayBuffer[Bullet], playerBullet: Int): Unit = {
    val bulletShot: Bullet = bulletMap(bulletType)
    bulletShot.setPosition(position)
    bulletShot.setPlayerBullet(playerBullet)
    bullets.append(bulletShot)

    //new Bullet(damage = damage, startPos = position, playerBullet = playerBullet, sprite = sprite))
    ammo -= 1
  }
}

object Weapon {

  var bulletMap: Map[Int, Bullet] = null

  def initBulletArray(): Unit = {
    /** Init of the different bullet types */
    bulletMap = Map.apply(
      0 -> new Bullet(damage = 1, playerBullet = 1, sprite = new Sprite(64, 64, "data/images/spriteSheet/ScalaBullet_64_10.png", 0, 10)),
      1 -> new Bullet(damage = 10, playerBullet = 1, sprite = new Sprite(64, 64, "data/images/spriteSheet/ScalaBullet_64_10.png", 0, 10))
    )
  }
}
