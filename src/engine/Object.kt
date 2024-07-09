package engine

import engine.Renderable
import java.awt.Graphics

class Object() {
    var renderable: Renderable? = null
    var isVisible: Boolean = true
    private val components: ArrayList<Component> = arrayListOf()

    public fun renderObject() {
        if(renderable == null) {
            throw IllegalArgumentException("Cannot render object without a renderable")
        } else {
            renderable!!.render()
        }
    }
}
