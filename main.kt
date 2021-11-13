// Hello World Program

fun main(args : Array<String>) {
    // var: variables (changes)
    // val: values (do not change)
    var hour: Int = 0

    val controller = Controller(hour, true, true)

    controller.registerSensors(ForecastSensor(), MoistureSensor())

    while(true) {
        println("it's hour " + controller.getCurrentTime())
        println("moisture level: " + controller.getCurrentMoisture())
        //precipitation = (hour >= precipitationStart) and (hour <= precipitationStop)
        println("precipitation: " + controller.getIsPrecipitating())
        // controller
        controller.update()

        //time passes

        // imaging every time we press enter, one hour passes
        val foo = readLine()!!
        // end program through command line
        if(foo == "quit"){
            break
        }

        // imagine every second is one hour irl
        // Thread.sleep(1000)
    }
}

