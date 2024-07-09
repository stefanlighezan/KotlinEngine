import datatypes.Color
import datatypes.Vector2
import engine.Engine
import engine.Object
import engine.Renderable
import engine.Scene
import renderer.Window

fun main() {
    val window = Window("Game Window", 800, 600, visible = true, resizable = false, exitOnClose = true)

    val scene = Scene()

    val renderable = Renderable(100, 100, Vector2(100f, 100f), Color(255, 255, 0, 100))
    val obj = Object(renderable)

    scene.addObject(obj)

    scene.setStartFunction {
        println("Started App")
    }

    scene.setUpdateFunction {

        println("Running")
    }

    val engine = Engine(window, 60)

    engine.addScene(scene)

    engine.run()
}
