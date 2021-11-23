package smart.sprinkler.emulator
class ForecastSensor() {
    val precipitationStart = 3
    val precipitationStop = 5

    fun isPrecipitating(currentTime: Int): Boolean {
        return (currentTime >= precipitationStart) && (currentTime <= precipitationStop)
    }
}
