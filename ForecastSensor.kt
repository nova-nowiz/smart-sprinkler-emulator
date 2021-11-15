class ForecastSensor (
        _currentTime: Int
){
    var currentTime: Int = _currentTime
    var isPrecipitating: Boolean = false
    val precipitationStart = 3
    val precipitationStop = 5

    fun isPrecipitating(currentTime: Int) : Boolean{
        return (currentTime >= precipitationStart) && (currentTime <= precipitationStop)
    }
}
