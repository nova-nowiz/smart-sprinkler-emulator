class Controller (
        // class properties
        _currentTime:Int
) {
    var currentTime: Int = _currentTime
    var moistureSensor: MoistureSensor = MoistureSensor(currentTime)
    var forecastSensor: ForecastSensor = ForecastSensor(currentTime)
    var currentMoisture: Int = moistureSensor.currentMoisture
    var isPrecipitating: Boolean = false
    var isSprinkling: Boolean = false

    fun update()
    {
        // poll forecast sensor
        forecastSensor.update(currentTime)
        isPrecipitating = forecastSensor.isPrecipitating

        if(isPrecipitating && isSprinkling){
            isSprinkling = false
        }

        // poll moisture sensor
        moistureSensor.update(currentTime, isPrecipitating, isSprinkling)
        currentMoisture = moistureSensor.currentMoisture

        if(currentMoisture <= 0 and !isPrecipitating and !isSprinkling) {
                isSprinkling = true
                // update 'Sprinkler'
            }
        }
        else if (currentMoisture >= 100 && isSprinkling)
        {
            isSprinkling = false
            // update 'Sprinkler'
        }

        // progress time
        currentTime++
        if(currentTime % 24 == 0)
        {
            currentTime = 0
        }
    }
}
