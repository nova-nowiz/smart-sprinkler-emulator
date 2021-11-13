

class Controller (
        // class properties
        newTime:Int,
        newState: Boolean, // TRUE = Active, FALSE = Standby (placeholder)
        newMode: Boolean//, // TRUE = Smart mode, FALSE = Manual mode (placeholder)
) {
    var currentTime: Int
    var state: Boolean
    var mode: Boolean
    var currentMoisture: Int
    var isPrecipitating: Boolean
    var registeredSensors: ArrayList<SensorInterface>
    var isSprinkling: Boolean

    init {
        currentTime = newTime
        state = newState
        mode = newMode
        currentMoisture = 0
        isPrecipitating = false
        registeredSensors = arrayListOf<SensorInterface>()
        isSprinkling = false
    }

    // register one or more sensors with this controller
    fun registerSensors(vararg sensorsToRegister:SensorInterface ){
        sensorsToRegister.forEach { sensor ->
            registeredSensors.add(sensor)
            sensor.registerController(this);
        }
    }

    fun update()
    {
        // check current state is 'Active' and mode is 'Smart'
        if(state && mode) {
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
        }
        // progress time
        currentTime++
    }
}
