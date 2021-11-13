class MoistureSensor: SensorInterface {
    private lateinit var myController: Controller
    private var currentTime: Int = 0
    private var deltaTime: Int = 0
    private var currentMoisture: Int = 100
    private val precipitationRate = 30
    private val dryingRate = 5
    private val sprinklingRate = 20



    override fun registerController(controller: Controller) {
        myController = controller
        controller.getCurrentTime()
    }

    override fun pollUpdates(newTime: Int) {
        deltaTime = newTime - currentTime
        currentTime = newTime

        updateCurrentMoistureValue()

        myController.setCurrentMoisture(currentMoisture)
    }

    private fun updateCurrentMoistureValue (){
        if(myController.getIsPrecipitating()){
            currentMoisture += precipitationRate * deltaTime
        }
        else if(myController.getIsSprinkling()){
            currentMoisture = sprinklingRate * deltaTime
        }
        else {
            currentMoisture -= dryingRate * deltaTime
            if(currentMoisture < 0){
                currentMoisture = 0
            }
        }
    }

}