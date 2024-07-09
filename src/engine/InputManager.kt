package engine

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener

class InputManager : KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private val keyState = BooleanArray(256)
    private val mouseButtonDown = BooleanArray(3)
    private var mouseX: Int = 0
    private var mouseY: Int = 0
    private var mouseWheelRotation: Int = 0

    // KeyListener methods
    override fun keyPressed(e: KeyEvent) {
        keyState[e.keyCode] = true
    }

    override fun keyReleased(e: KeyEvent) {
        keyState[e.keyCode] = false
    }

    override fun keyTyped(e: KeyEvent) {
        // Handle key typed events if needed
    }

    // MouseListener methods
    override fun mouseClicked(e: MouseEvent) {
        // Handle mouse clicked events
    }

    override fun mousePressed(e: MouseEvent) {
        when (e.button) {
            MouseEvent.BUTTON1 -> mouseButtonDown[0] = true // Left mouse button
            MouseEvent.BUTTON2 -> mouseButtonDown[2] = true // Right mouse button
            MouseEvent.BUTTON3 -> mouseButtonDown[1] = true // Middle (scroll wheel) button
        }
    }

    override fun mouseReleased(e: MouseEvent) {
        when (e.button) {
            MouseEvent.BUTTON1 -> mouseButtonDown[0] = false // Left mouse button
            MouseEvent.BUTTON2 -> mouseButtonDown[2] = false // Right mouse button
            MouseEvent.BUTTON3 -> mouseButtonDown[1] = false // Middle (scroll wheel) button
        }
    }

    override fun mouseEntered(e: MouseEvent) {
        // Handle mouse entered the component events
    }

    override fun mouseExited(e: MouseEvent) {
        // Handle mouse exited the component events
    }

    // MouseMotionListener methods
    override fun mouseDragged(e: MouseEvent) {
        // Handle mouse dragged events (movement with button pressed)
        mouseX = e.x
        mouseY = e.y
    }

    override fun mouseMoved(e: MouseEvent) {
        // Handle mouse moved events (movement without button pressed)
        mouseX = e.x
        mouseY = e.y
    }

    // MouseWheelListener method
    override fun mouseWheelMoved(e: MouseWheelEvent) {
        // Handle mouse wheel rotation events
        mouseWheelRotation = e.wheelRotation
    }

    // Utility methods to query input state
    fun isKeyDown(keyCode: Int): Boolean {
        return keyState[keyCode]
    }

    fun isMouseButtonDown(button: Int): Boolean {
        return mouseButtonDown.getOrNull(button) ?: false
    }

    fun getMouseX(): Int {
        return mouseX
    }

    fun getMouseY(): Int {
        return mouseY
    }

    fun getMouseWheelRotation(): Int {
        return mouseWheelRotation
    }

    // Reset method to clear state, if necessary
    fun reset() {
        keyState.fill(false)
        mouseButtonDown.fill(false)
        mouseX = 0
        mouseY = 0
        mouseWheelRotation = 0
    }

    // Method to attach input listeners to a component
    fun attachTo(component: java.awt.Component) {
        component.addKeyListener(this)
        component.addMouseListener(this)
        component.addMouseMotionListener(this)
        component.addMouseWheelListener(this)
    }

    // Method to detach input listeners from a component
    fun detachFrom(component: java.awt.Component) {
        component.removeKeyListener(this)
        component.removeMouseListener(this)
        component.removeMouseMotionListener(this)
        component.removeMouseWheelListener(this)
    }
}
