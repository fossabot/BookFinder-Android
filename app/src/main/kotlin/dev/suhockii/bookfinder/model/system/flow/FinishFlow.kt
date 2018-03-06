package ru.terrakok.gitlabclient.model.system.flow

import ru.terrakok.cicerone.commands.Command

class FinishFlow(
        val transitionData: Any?
) : Command