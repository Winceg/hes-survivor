package ch.hevs.gdx2d.HES_Survivor

import com.badlogic.gdx.math.Vector2

class Weapon (private var ammo: Int = -1, private val fireRate: Int = 1, private val simultaneousBullets: Int = 1) {
  def shoot(position: Vector2): Unit = {
    val b : Bullet = new Bullet(position = position)
    b.shoot()
  }
}
