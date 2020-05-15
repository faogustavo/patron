package dev.patron.dsl.defaults.visibility

import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.modifiers.Visibility

class PatronVisibilityChanger(private val target: ChangeableVisibility) : VisibilityChanger {

    override var visibility: Visibility
        get() = target.visibility
        set(value) {
            target.visibility = value
        }
}
