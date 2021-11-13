

class Controller (
        // class properties
        newTime:Int,
        newState: Boolean, // TRUE = Active, FALSE = Standby (placeholder)
        newMode: Boolean//, // TRUE = Smart mode, FALSE = Manual mode (placeholder)
) {
    private var currentTime: Int
    private var state: Boolean
    private var mode: Boolean
    private var currentMoisture: Int
    private var isPrecipitating: Boolean
    private var registeredSensors: ArrayList<SensorInterface>
    private var isSprinkling: Boolean

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

    fun update(newTime: Int)
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

    fun setCurrentMoisture(newMoistureValue: Int){
        currentMoisture = newMoistureValue
    }

    fun getCurrentMoisture(): Int {
        return currentMoisture;
    }

    fun setIsPrecipitating(newIsPrecipitating: Boolean){
        isPrecipitating = newIsPrecipitating
    }

    fun getIsPrecipitating() : Boolean {
        return isPrecipitating
    }

    fun getIsSprinkling(): Boolean {
        return isSprinkling
    }

    fun setState(newState: Boolean){
        state = newState
    }

    fun setMode(newMode: Boolean){
        mode = newMode
    }

    fun getCurrentTime(): Int  {
        return currentTime
    }
}