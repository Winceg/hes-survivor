package ch.hevs.gdx2d.HES_Survivor

import ch.hevs.gdx2d.lib.GdxGraphics
import com.badlogic.gdx.math.Vector2

import scala.collection.mutable.ArrayBuffer

class Weapon(private var ammo: Int = -1, private val fireRate: Int = 1, private val simultaneousBullets: Int = 1) {

  def shoot(position: Vector2, bullets: ArrayBuffer[Bullet]): Unit = {
    bullets.append(new Bullet(position = position))
    ammo -= 1
  }
}
