package dev.suhockii.bookfinder.util.flow

import ru.terrakok.cicerone.Router

class FlowRouter : Router() {

    fun startFlow(flowKey: String, data: Any? = null) {
        executeCommands(StartFlow(flowKey, data))
    }

    fun finishFlow(data: Any? = null) {
        executeCommands(FinishFlow(data))
    }

    fun cancelFlow() {
        finishChain()
    }
}