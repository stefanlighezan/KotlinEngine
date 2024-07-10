import datatypes.*
import engine.*
import renderer.Window
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import kotlin.random.Random

fun main() {
    val window = Window("App", 800, 600, visible = true, resizable = false, exitOnClose = true)

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

    val circlePos: SyncVar<Vector2> = SyncVar(Vector2(200f, 200f))

    val obj2 = Object().apply {
        renderable = Renderable(
            w = 100,
            h = 100,
            position = circlePos.value,
            color = color.value,
            type = Shapes.ELLIPSE
        )

        scene2.addObject(this)
    }

    scene.setUpdateFunction {
        if(inputManager.clicked()) {
            engine.switchScene(scene2)
            println("er")
        }
    }


    scene2.setUpdateFunction {
        circlePos.value = Vector2(circlePos.value.x, y)
        obj2.renderable!!.render()
        if(inputManager.clicked()) {
            engine.switchScene(scene)
            println("h")
        }
    }

    engine.addScene(scene)
    engine.addScene(scene2)
    engine.run()
}

