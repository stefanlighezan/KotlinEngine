package engine

import renderer.Window

class Engine(val window: Window) {
    private var currentSceneIndex: Int = 0
    private val scenes: ArrayList<Scene> = arrayListOf()


}