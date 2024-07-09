package engine

import javax.swing.SwingUtilities

class Scene {
    lateinit var gameEngine: Engine
    val objects: ArrayList<Object> = arrayListOf()
    private var startFunction: (() -> Unit)? = null
    private var updateFunction: ((Float) -> Unit)? = null

    fun setStartFunction(start: () -> Unit) {
        startFunction = start
    }

    fun setUpdateFunction(update: (Float) -> Unit) {
        updateFunction = update
    }

    fun Start() {
        base()
        startFunction?.invoke()
    }

    fun Update(deltaTime: Float) {
        base()
        updateFunction?.invoke(deltaTime)
    }

    private fun base() {
        println(objects.size)
        objects.forEach { obj ->
            obj.renderObject()
        }
    }

    fun addObject(obj: Object) {
        objects.add(obj)
        obj.renderable!!.render()
        obj.renderable!!.repaint()
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
