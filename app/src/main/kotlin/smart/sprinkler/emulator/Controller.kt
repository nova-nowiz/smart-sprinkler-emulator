package smart.sprinkler.emulator
class Controller(
    _currentTime: Int
) {
    var currentTime: Int = _currentTime
    val moistureSensor: MoistureSensor = MoistureSensor()
    val forecastSensor: ForecastSensor = ForecastSensor()
    var currentMoisture: Int = moistureSensor.currentMoisture
    var isPrecipitating: Boolean = false
    var isSprinkling: Boolean = false

    fun update() {
        // poll forecast sensor
        isPrecipitating = forecastSensor.isPrecipitating(currentTime)

        if (isPrecipitating && isSprinkling) {
            isSprinkling = false
        }

        // poll moisture sensor
        moistureSensor.update(isPrecipitating, isSprinkling)
        currentMoisture = moistureSensor.currentMoisture

        if ((currentMoisture <= 0) and !isPrecipitating and !isSprinkling) {
            isSprinkling = true
            // update 'Sprinkler'
        } else if ((currentMoisture >= 100) and isSprinkling) {
            isSprinkling = false
            // update 'Sprinkler'
        }

        // progress time
        currentTime++
        if (currentTime % 24 == 0) {
            currentTime = 0
        }
    }
}
