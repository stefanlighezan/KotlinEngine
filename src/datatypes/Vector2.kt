package datatypes

import kotlin.math.sqrt

data class Vector2(val x: Float, val y: Float) {

    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)

    operator fun minus(other: Vector2) = Vector2(x - other.x, y - other.y)

    operator fun times(scalar: Float) = Vector2(x * scalar, y * scalar)

    operator fun div(scalar: Float) = Vector2(x / scalar, y / scalar)

    fun length() = sqrt(x * x + y * y)

    fun normalize(): Vector2 {
        val len = length()
        return if (len != 0f) this / len else throw IllegalStateException("Cannot normalize the zero vector")
    }

    infix fun dot(other: Vector2) = x * other.x + y * other.y

    infix fun cross(other: Vector2) = x * other.y - y * other.x
}
