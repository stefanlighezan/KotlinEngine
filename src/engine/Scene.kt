package engine

class Scene {
    private val objects: ArrayList<Object> = arrayListOf()

    public fun addObject(obj: Object) {
        objects.add(obj)
    }

    public fun deleteObject(obj: Object) {
        objects.remove(obj)
    }
}