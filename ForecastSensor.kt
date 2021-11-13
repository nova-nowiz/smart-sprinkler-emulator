class ForecastSensor: SensorInterface {
    private lateinit var myController: Controller
    private var currentTime: Int = 0
    private var deltaTime: Int = 0
    private var isPrecipitating: Boolean = false

    override fun registerController(controller: Controller) {
        myController = controller
        currentTime = controller.currentTime
    }

    override fun pollUpdates(newTime: Int) {
        deltaTime = newTime - currentTime
        currentTime = newTime

        updateIsPrecipating()
        myController.isPrecipitating = isPrecipitating
    }

    private fun updateIsPrecipating() {
        isPrecipitating = false;   // set default false
    }
}
