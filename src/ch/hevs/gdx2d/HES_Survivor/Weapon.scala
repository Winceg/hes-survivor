package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Weapon(private var ammo: Int = -1, private val fireRate: Int = 1, private val damage: Int = 5) {

  def shoot(position: Vector2, bullets: ArrayBuffer[Bullet], playerBullet: Int): Unit = {
    bullets.append(new Bullet(damage = damage, startPos = position, playerBullet = playerBullet))
    ammo -= 1
  }
}
