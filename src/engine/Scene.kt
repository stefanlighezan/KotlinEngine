package engine

import javax.swing.SwingUtilities

class Scene {
    lateinit var gameEngine: Engine
    val objects: ArrayList<Object> = arrayListOf()
    private var startFunction: (() -> Unit)? = null
    private var updateFunction: ((Float) -> Unit)? = null
    public var hasBeenRendered: Boolean = false

    fun setStartFunction(start: () -> Unit) {
        startFunction = start
    }

    fun setUpdateFunction(update: (Float) -> Unit) {
        updateFunction = update
    }

    fun Start() {
        hasBeenRendered = true
        startFunction?.invoke()
    }

    fun Update(deltaTime: Float) {
        updateFunction?.invoke(deltaTime)
    }

    fun addObject(obj: Object) {
        objects.add(obj)
        SwingUtilities.invokeLater {
            gameEngine.window.panel.add(obj.renderable)
            gameEngine.window.panel.revalidate()
            gameEngine.window.panel.repaint()
        }
        obj.renderable!!.render()
    }

    fun deleteObject(obj: Object) {
        objects.remove(obj)
        SwingUtilities.invokeLater {
            gameEngine.window.panel.remove(obj.renderable)
            gameEngine.window.panel.revalidate()
            gameEngine.window.panel.repaint()
        }
    }
}
