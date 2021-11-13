class ForecastSensor (
        _currentTime: Int
){
    var currentTime: Int = _currentTime
    var deltaTime: Int = 0
    var isPrecipitating: Boolean = false
    val precipitationStart = 3
    val precipitationStop = 5

    fun update(newTime: Int) {
        deltaTime = newTime - currentTime
        currentTime = newTime

        isPrecipitating = (currentTime >= precipitationStart) && (currentTime <= precipitationStop)
    }
}
