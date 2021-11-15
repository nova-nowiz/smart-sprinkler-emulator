// Hello World Program

fun main(args : Array<String>) {
    // var: variables (changes)
    // val: values (do not change)
    val hour: Int = 0

    val controller = Controller(hour)

    while(true) {
        println("it's hour " + controller.currentTime)
        println("moisture level: " + controller.currentMoisture)
        //precipitation = (hour >= precipitationStart) and (hour <= precipitationStop)
        println("precipitation: " + controller.isPrecipitating)
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

