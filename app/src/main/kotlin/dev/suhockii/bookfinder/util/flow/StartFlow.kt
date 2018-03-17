package dev.suhockii.bookfinder.util.flow

import ru.terrakok.cicerone.commands.Command

class StartFlow(
        val screenKey: String,
        val transitionData: Any?
) : Command