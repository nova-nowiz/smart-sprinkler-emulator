interface SensorInterface {
    fun registerController(controller: Controller)

    fun pollUpdates(newTime: Int)
}