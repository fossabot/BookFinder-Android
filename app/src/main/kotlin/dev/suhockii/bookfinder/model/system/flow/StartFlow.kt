package ru.terrakok.gitlabclient.model.system.flow

import ru.terrakok.cicerone.commands.Command

class StartFlow(
        val screenKey: String,
        val transitionData: Any?
) : Command