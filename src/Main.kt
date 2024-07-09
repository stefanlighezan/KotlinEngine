import datatypes.Color
import datatypes.Shapes
import datatypes.Vector2
import engine.*
import renderer.Window

fun main() {
    val window = Window("Game Window", 800, 600, visible = true, resizable = false, exitOnClose = true)
    val scene = Scene()
    val inputManager = InputManager()
    inputManager.attachTo(window.panel)

    val obj = Object().apply {
        renderable = Renderable(
            w = 100,
            h = 100,
            position = Vector2(0f, 100f),
            color = Color(255, 0, 0, 255),
            type = Shapes.RECTANGLE
        )
    }

    scene.gameEngine = Engine(window, 60)
    scene.addObject(obj)

    scene.setStartFunction {
        println("Started App")
    }

    scene.setUpdateFunction { deltaTime ->
        if (inputManager.isMouseButtonDown(0)) {
            val obj2 = Object().apply {
                renderable = Renderable(
                    w = 100,
                    h = 100,
                    position = Vector2(inputManager.getMouseX().toFloat(), inputManager.getMouseY().toFloat()),
                    color = Color(0, 0, 0, 100),
                    type = Shapes.ELLIPSE
                )
            }
            scene.addObject(obj2)
            println("(${inputManager.getMouseX()}, ${inputManager.getMouseY()})")
            inputManager.reset() // Reset mouse click state
        }
    }

    scene.gameEngine.addScene(scene)
    scene.gameEngine.run()
}

