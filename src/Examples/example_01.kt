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
    val scene = Scene()
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
    }

    position.addOnChangeListener {
        obj.renderable!!.position = position.value
    }

    color.addOnChangeListener {
        obj.renderable!!.color = color.value
    }

    scene.gameEngine = Engine(window, 60)
    scene.addObject(obj)

    scene.setStartFunction {
        println("Started App")
    }

    // Assuming deltaTime is passed into your update function
    val speed = 300f // Adjust speed as needed
    var targetColor: Color? = null
    var lerpTime = 0f
    val lerpDuration = 1.5f // Duration in seconds for color transition

    scene.setUpdateFunction { deltaTime ->
        // Handle input for object movement
        val moveAmount = speed * deltaTime

        if (inputManager.isKeyDown(KeyEvent.VK_W)) {
            position.value = Vector2(position.value.x, position.value.y - moveAmount)
        }
        if (inputManager.isKeyDown(KeyEvent.VK_S)) {
            position.value = Vector2(position.value.x, position.value.y + moveAmount)
        }
        if (inputManager.isKeyDown(KeyEvent.VK_A)) {
            position.value = Vector2(position.value.x - moveAmount, position.value.y)
        }
        if (inputManager.isKeyDown(KeyEvent.VK_D)) {
            position.value = Vector2(position.value.x + moveAmount, position.value.y)
        }

        // Check if current lerp is complete
        if (lerpTime >= lerpDuration || targetColor == null) {
            // Generate new random color
            targetColor = generateRandomColor()
            lerpTime = 0f
        }

        // Interpolate towards the target color
        if (targetColor != null) {
            color.value = color.value.lerp(targetColor!!, lerpTime / lerpDuration)
            lerpTime += deltaTime
        }
    }

    scene.gameEngine.addScene(scene)
    scene.gameEngine.run()
}

/**
 * Generates a random color with full opacity.
 */
fun generateRandomColor(): Color {
    val random = Random.Default
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}
