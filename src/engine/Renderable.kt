package engine

import datatypes.Color
import datatypes.Vector2
import java.awt.Color as AwtColor
import java.awt.Graphics
import javax.swing.JPanel

class Renderable(
    private val width: Int,
    private val height: Int,
    private val position: Vector2,
    private val color: Color
) : JPanel() {

    init {
        // Set the preferred size of the JPanel to match the dimensions of the Renderable
        preferredSize = java.awt.Dimension(width, height)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = color.toAWTColor()
        g.fillRect(position.x.toInt(), position.y.toInt(), width, height)
    }

    // Optionally, you can keep the render method if you need to trigger rendering explicitly
    fun render() {
        repaint() // Request a repaint to trigger paintComponent
    }
}
