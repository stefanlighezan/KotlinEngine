package datatypes

import kotlin.properties.Delegates

class SyncVar<T>(initialValue: T) {
    private var internalValue: T by Delegates.observable(initialValue) { _, _, newValue ->
        onChangeListeners.forEach { it(newValue) }
    }

    private val onChangeListeners = mutableListOf<(T) -> Unit>()

    init {
        internalValue = initialValue
    }

    fun addOnChangeListener(listener: (T) -> Unit) {
        onChangeListeners.add(listener)
    }

    var value: T
        get() = internalValue
        set(newValue) {
            internalValue = newValue
        }
}