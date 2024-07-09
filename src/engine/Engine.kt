package engine

import renderer.Window

class Engine(private val window: Window, private val fps: Int) {
    private var currentSceneIndex: Int = 0
    private val scenes: ArrayList<Scene> = arrayListOf()

    private fun Start() {
        scenes[currentSceneIndex].Start()

        scenes[currentSceneIndex].objects.forEach { obj ->
            window.frame.add(obj.renderable)
        }
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
}
