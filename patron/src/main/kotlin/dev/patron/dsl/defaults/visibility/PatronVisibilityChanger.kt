package dev.patron.dsl.defaults.visibility

import dev.patron.dsl.interfaces.visibility.ChangeableVisibility
import dev.patron.dsl.interfaces.visibility.VisibilityChanger
import dev.patron.modifiers.Visibility

class PatronVisibilityChanger(private val target: ChangeableVisibility) :
    VisibilityChanger {
    override var visibility: Visibility = Visibility.PUBLIC
        set(value) {
            field = value
            target.updateVisibility(value)
        }
}
