package engine

import java.awt.Graphics

class Object(private val renderable: Renderable) {
    var isVisible: Boolean = true
    private val components: ArrayList<Component> = arrayListOf()

    fun render() {
        renderable.render()
    }
}
