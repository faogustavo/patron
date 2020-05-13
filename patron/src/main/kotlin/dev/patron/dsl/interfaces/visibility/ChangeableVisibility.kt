package dev.patron.dsl.interfaces.visibility

import dev.patron.modifiers.Visibility

interface ChangeableVisibility {
    fun updateVisibility(newVisibility: Visibility)
}
