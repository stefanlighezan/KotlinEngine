package engine

class Scene {
    private val objects: ArrayList<Object> = arrayListOf()
    private var startFunction: (() -> Unit)? = null
    private var updateFunction: ((Float) -> Unit)? = null

    fun setStartFunction(start: () -> Unit) {
        startFunction = start
    }

    fun setUpdateFunction(update: (Float) -> Unit) {
        updateFunction = update
    }

    fun Start() {
        startFunction?.invoke()
    }

    fun Update(deltaTime: Float) {
        objects.forEach { obj ->
            obj.render()
        }
        //updateFunction?.invoke(deltaTime)

    }

    fun addObject(obj: Object) {
        objects.add(obj)
    }

    fun deleteObject(obj: Object) {
        objects.remove(obj)
    }
}
