package smart.sprinkler.emulator

class App(_time: Time) {
    val hour: Int = 0
    val controller = Controller(hour)
    var input: String = ""
    var time = _time
    fun run() {
        while (input != "quit") {
            println("it's hour " + controller.currentTime)
            println("moisture level: " + controller.currentMoisture)
            println("precipitation: " + controller.isPrecipitating)

            controller.update()

            if (time == Time.TurnBased) {
                input = readLine() ?: ""
            } else {
                Thread.sleep(1000)
            }
        }
    }
}

enum class Time {
    TurnBased,
    RealTime
}

fun main() {
    val app = App(Time.TurnBased)
    app.run()
}
