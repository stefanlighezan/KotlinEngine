import datatypes.Color
import datatypes.Shapes
import datatypes.SyncVar
import datatypes.Vector2
import engine.*
import renderer.Window
import java.awt.event.KeyEvent
import kotlin.random.Random

fun main() {
    val window = Window("Game Window", 800, 600, visible = true, resizable = false, exitOnClose = true)

    val engine = Engine(window, 60)
    val scene = Scene().apply { gameEngine = engine }
    val scene2 = Scene().apply { gameEngine = engine }
    val inputManager = InputManager()
    inputManager.attachTo(window.panel)
    window.panel.requestFocusInWindow() // Ensure the panel has focus

    val position: SyncVar<Vector2> = SyncVar(Vector2(100f, 100f))
    val color: SyncVar<Color> = SyncVar(Color(255, 0, 0, 255))

    val obj = Object().apply {
        renderable = Renderable(
            w = 100,
            h = 100,
            position = position.value,
            color = color.value,
            type = Shapes.RECTANGLE
        )

        scene.addObject(this)
    }

    val obj2 = Object().apply {
        renderable = Renderable(
            w = 100,
            h = 100,
            position = Vector2(200f, 200f),
            color = color.value,
            type = Shapes.ELLIPSE
        )

        scene2.addObject(this)
    }

    scene.setUpdateFunction {
        if(inputManager.isMouseButtonDown(0)) {
            engine.switchScene(scene2)
            println("er")
        }
    }

    scene2.setUpdateFunction {
        if(inputManager.isMouseButtonDown(0)) {
            engine.switchScene(scene)
            println("h")
        }
    }

    engine.addScene(scene)
    engine.addScene(scene2)
    engine.run()
}

