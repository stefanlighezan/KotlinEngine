package engine

import datatypes.Color
import datatypes.Shapes
import datatypes.Vector2
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.geom.*
import javax.swing.Box

class Renderable(
    var w: Int,
    var h: Int,
    var position: Vector2,
    var color: Color,
    var type: Shapes
) : Box(0) {

    init {
        preferredSize = Dimension(w, h)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val g2d = g as Graphics2D
        g2d.color = color.toAWTColor()

        when (type) {
            Shapes.RECTANGLE -> {
                val rect = Rectangle(position.x.toInt(), position.y.toInt(), w, h)
                g2d.fill(rect)
            }
            Shapes.ELLIPSE -> {
                val ellipse = Ellipse2D.Float(position.x, position.y, w.toFloat(), h.toFloat())
                g2d.fill(ellipse)
            }
            Shapes.ROUND_RECTANGLE -> {
                val roundRect = RoundRectangle2D.Float(position.x, position.y, w.toFloat(), h.toFloat(), 20f, 20f)
                g2d.fill(roundRect)
            }
        }
    }

    fun render() {
        repaint()
    }
}
