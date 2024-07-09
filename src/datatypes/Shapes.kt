package datatypes

import java.awt.Shape
import java.awt.geom.*

enum class Shapes(val shape: Shape) {
    RECTANGLE(Rectangle2D.Float()),
    ROUND_RECTANGLE(RoundRectangle2D.Float()),
    ELLIPSE(Ellipse2D.Float());

    companion object {
        fun fromString(shapeName: String): Shapes? {
            return values().find { it.name.equals(shapeName, ignoreCase = true) }
        }
    }
}
