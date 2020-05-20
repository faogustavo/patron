package dev.patron.defaults.visibility

import dev.patron.interfaces.visibility.ChangeableVisibility
import dev.patron.interfaces.visibility.VisibilityChanger
import dev.patron.modifiers.Visibility

class PatronVisibilityChanger(private val target: ChangeableVisibility) :
    VisibilityChanger {

    override var visibility: Visibility
        get() = target.visibility
        set(value) {
            target.visibility = value
        }
}
