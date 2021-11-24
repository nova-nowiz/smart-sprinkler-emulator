package smart.sprinkler.emulator

class App(val time: Time) {
    var currentTime: Int = 0
    var currentDay: Int = 0
    val moistureSensor = MoistureSensor(
        currentMoisture = 10,
        precipitationRate = 30,
        dryingRate = 3,
        sprinklingRate = 20
    )
    val forecastSensor = ForecastSensor()
    val controller = Controller(moistureSensor, forecastSensor, time)
    var input: String = ""
    fun run() {
        while (input != "quit") {
            moistureSensor.update(
                forecastSensor.isPrecipitating(currentDay, currentTime),
                controller.isSprinkling
            )
            controller.update(currentDay, currentTime)

            // progress time
            currentTime++
            if (currentTime % 24 == 0) {
                currentTime = 0
                currentDay = (currentDay + 1) % 7
            }

            if (time == Time.TurnBased) {
                input = readLine() ?: ""
            } else {
                Thread.sleep(100)
            }
        }
    }
}

enum class Time {
    TurnBased,
    RealTime
}

fun main() {
    val app = App(Time.RealTime)
    app.run()
}
