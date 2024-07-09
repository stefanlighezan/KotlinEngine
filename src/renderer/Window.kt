package renderer

import java.awt.BorderLayout
import java.awt.Panel
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SpringLayout
import javax.swing.WindowConstants

class Window(
    title: String,
    width: Int,
    height: Int,
    visible: Boolean = true,
    resizable: Boolean = false,
    exitOnClose: Boolean = true
) {
    val frame: JFrame = JFrame(title).apply {
        defaultCloseOperation = if (exitOnClose) WindowConstants.EXIT_ON_CLOSE else WindowConstants.DISPOSE_ON_CLOSE
        setSize(width, height)
        isVisible = visible
        isResizable = resizable
    }

    val panel = JPanel(SpringLayout())

    init {
        frame.contentPane.add(panel)
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
