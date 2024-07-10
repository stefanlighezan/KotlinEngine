package engine

import renderer.Window

class Engine(val window: Window, private val fps: Int) {
    private var currentSceneIndex: Int = 0
    private val scenes: ArrayList<Scene> = arrayListOf()

    private fun Start() {
        scenes[currentSceneIndex].Start()
        scenes[currentSceneIndex].objects.forEach { obj ->
            window.panel.add(obj.renderable)
        }
    }

    private fun clearPanel() {
        window.panel.removeAll()
        window.panel.revalidate()
        window.panel.repaint()
    }

    private fun Update(deltaTime: Float) {
        scenes[currentSceneIndex].Update(deltaTime)
    }

    fun run() {
        Start() // Initial call to Start

        val frameTime = 1000 / fps // Time in milliseconds per frame
        var lastFrameTime = System.currentTimeMillis()

        while (true) {
            val currentTime = System.currentTimeMillis()
            val deltaTime = (currentTime - lastFrameTime) / 1000.0f // Delta time in seconds

            if (deltaTime >= 1.0f / fps) {
                Update(deltaTime)
                lastFrameTime = currentTime
            }
        }
    }

    fun addScene(scene: Scene) {
        scenes.add(scene)
    }

    fun switchScene(newScene: Scene) {
        val newSceneIndex = scenes.indexOf(newScene)

        if (newSceneIndex == -1) {
            println("Scene not found in the engine.")
            return
        }

        clearPanel() // Clear the panel of objects from the current scene

        currentSceneIndex = newSceneIndex

        // Add objects from the new scene to the rendering panel
        scenes[currentSceneIndex].objects.forEach { obj ->
            window.panel.add(obj.renderable)
        }

        // If the new scene has not been rendered before, call Start
        if (!scenes[currentSceneIndex].hasBeenRendered) {
            scenes[currentSceneIndex].Start()
        }
    }
}
