package engine

import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.JTextArea

class SceneViewer : JPanel() {
    private val sceneInfoTextArea: JTextArea = JTextArea()

    init {
        // Initialize scene viewer UI components
        sceneInfoTextArea.isEditable = false
        add(sceneInfoTextArea)
    }

    fun updateSceneInfo(currentScene: Scene?, sceneCount: Int) {
        if (currentScene != null) {
            val sceneInfo = buildString {
                append("Current Scene: ${currentScene.javaClass.simpleName}\n")
                append("Scene Count: $sceneCount\n")
                append("Objects:\n")
                currentScene.objects.forEachIndexed { index, obj ->
                    append("   Object ${index + 1}: ${obj.javaClass.simpleName}\n")
                }
            }
            sceneInfoTextArea.text = sceneInfo
        } else {
            sceneInfoTextArea.text = "No scene selected"
        }
        revalidate()
        repaint()
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        // Optionally, you can add custom painting here if needed
    }

}

//DEPRECATED
