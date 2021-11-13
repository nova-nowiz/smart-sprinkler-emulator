

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
    //private var registeredSensors - ArrayList of Sensor object refs
    private var isSprinkling: Boolean

    init {
        currentTime = newTime
        state = newState
        mode = newMode
        currentMoisture = 0
        isPrecipitating = false
        isSprinkling = false
    }

    // register one or more sensors with this controller
    fun registerSensors(/* vararg of Sensor object refs */){

    }

    fun update(newTime: Int)
    {

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