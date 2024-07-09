package engine

class Scene {
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

    fun base() {
        objects.forEach { obj ->
            obj.renderObject()
        }
    }

    fun addObject(obj: Object) {
        objects.add(obj)
    }

    fun deleteObject(obj: Object) {
        objects.remove(obj)
    }
}
