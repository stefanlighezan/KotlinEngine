import datatypes.Color
import datatypes.Shapes
import datatypes.SyncVar
import datatypes.Vector2
import engine.*
import renderer.Window
import java.awt.event.KeyEvent

fun main() {
    val window = Window("Game Window", 800, 600, visible = true, resizable = false, exitOnClose = true)
    val scene = Scene()
    val inputManager = InputManager()
    inputManager.attachTo(window.panel)
    window.panel.requestFocusInWindow() // Ensure the panel has focus


    val obj = Object().apply {
        renderable = Renderable(
            w = 100,
            h = 100,
            position = Vector2(0f, 100f),
            color = Color(255, 0, 0, 255),
            type = Shapes.RECTANGLE
        )
    }

    val position: SyncVar<Vector2> = SyncVar(Vector2(100f, 100f))

    position.addOnChangeListener {
        obj.renderable!!.position = position.value
    }

    scene.gameEngine = Engine(window, 60)
    scene.addObject(obj)

    scene.setStartFunction {
        println("Started App")
    }

    var speed: Float

    scene.setUpdateFunction { deltaTime ->
        println("DeltaTime: $deltaTime")

        // Handle input for object movement
        speed = 60f * deltaTime // Adjust speed as needed
        println("Speed: $speed")

        if (inputManager.isKeyDown(KeyEvent.VK_W)) {
            position.value = Vector2(position.value.x, position.value.y - speed)
        }
        if (inputManager.isKeyDown(KeyEvent.VK_S)) {
            position.value = Vector2(position.value.x, position.value.y + speed)
        }
        if (inputManager.isKeyDown(KeyEvent.VK_A)) {
            position.value = Vector2(position.value.x - speed, position.value.y)
        }
        if (inputManager.isKeyDown(KeyEvent.VK_D)) {
            position.value = Vector2(position.value.x + speed, position.value.y)
        }
    }



    scene.gameEngine.addScene(scene)
    scene.gameEngine.run()
}

