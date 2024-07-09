import datatypes.Color
import datatypes.Shapes
import datatypes.Vector2
import engine.Engine
import engine.Object
import engine.Scene
import engine.Renderable
import renderer.Window

fun main() {
    val window = Window("Game Window", 800, 600, visible = true, resizable = false, exitOnClose = true)
    val scene = Scene()

    val obj = Object().apply {
        renderable = Renderable(
            w = 100,
            h = 100,
            position = Vector2(0f, 100f),
            color = Color(255, 0, 0, 255),
            type = Shapes.ELLIPSE
        )
    }

    scene.apply {
        addObject(obj)
        setStartFunction {
            println("Started App")
        }
        setUpdateFunction {
            scene.objects[0].renderable!!.type = Shapes.RECTANGLE
            println("Running")
        }
    }

    val engine = Engine(window, 60).apply {
        addScene(scene)
        run()
    }
}
