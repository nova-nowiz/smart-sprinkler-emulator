

class Controller (
        // class properties
        _currentTime:Int
) {
    var currentTime: Int = _currentTime
    var currentMoisture: Int = 0
    var isPrecipitating: Boolean = false
    var registeredSensors: ArrayList<SensorInterface> = arrayListOf<SensorInterface>()
    var isSprinkling: Boolean = false

    // register one or more sensors with this controller
    fun registerSensors(vararg sensorsToRegister:SensorInterface ){
        sensorsToRegister.forEach { sensor ->
            registeredSensors.add(sensor)
            sensor.registerController(this);
        }
    }

    fun update()
    {
        // poll registered sensors for data
        registeredSensors.forEach { sensor ->
            sensor.pollUpdates(currentTime)
        }

        // check moisture level and forecasted precipitation
        if (currentMoisture <= 0 && !isPrecipitating && !isSprinkling) {
            isSprinkling = true
            // update 'Sprinkler'
        }
        if (isSprinkling && (currentMoisture >= 100 || isPrecipitating)) {
            isSprinkling = false;
            // update 'Sprinkler'
        }

        // progress time
        currentTime++
    }
}
