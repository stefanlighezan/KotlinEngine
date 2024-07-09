package renderer

import javax.swing.JFrame
import javax.swing.WindowConstants

class Window(
    title: String,
    width: Int,
    height: Int,
    visible: Boolean = true,
    resizable: Boolean = false,
    exitOnClose: Boolean = true
) {
    private val frame: JFrame = JFrame(title)

    init {
        frame.defaultCloseOperation = if (exitOnClose) WindowConstants.EXIT_ON_CLOSE else WindowConstants.DISPOSE_ON_CLOSE
        frame.setSize(width, height)
        frame.isVisible = visible
        frame.isResizable = resizable
    }

    fun setTitle(title: String) {
        frame.title = title
    }

    fun setSize(width: Int, height: Int) {
        frame.setSize(width, height)
    }

    fun setResizable(resizable: Boolean) {
        frame.isResizable = resizable
    }

    fun setVisible(visible: Boolean) {
        frame.isVisible = visible
    }

    fun close() {
        frame.dispose()
    }
}
