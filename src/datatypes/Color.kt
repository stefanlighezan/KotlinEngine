package datatypes

data class Color(val red: Int, val green: Int, val blue: Int, val alpha: Int = 255) {

    init {
        require(red in 0..255) { "Red component must be in the range 0-255" }
        require(green in 0..255) { "Green component must be in the range 0-255" }
        require(blue in 0..255) { "Blue component must be in the range 0-255" }
        require(alpha in 0..255) { "Alpha component must be in the range 0-255" }
    }

    companion object {
        val WHITE = Color(255, 255, 255)
        val BLACK = Color(0, 0, 0)
        val RED = Color(255, 0, 0)
        val GREEN = Color(0, 255, 0)
        val BLUE = Color(0, 0, 255)
        val YELLOW = Color(255, 255, 0)
        val CYAN = Color(0, 255, 255)
        val MAGENTA = Color(255, 0, 255)
        val TRANSPARENT = Color(0, 0, 0, 0)
    }

    fun toAWTColor(): java.awt.Color {
        return java.awt.Color(red, green, blue, alpha)
    }
}
