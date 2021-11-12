// Hello World Program

fun main(args : Array<String>) {
    // var: variables (changes)
    // val: values (do not change)
    var hour: Int = 0
    var moisture: Int = 100
    val sprinklingRate = 20
    val dryingRate = 5
    val precipitationStart = 3
    val precipitationStop = 5
    val precipitationRate = 30
    var precipitation: Boolean = false
    var sprinkling: Boolean = false
    while(true) {
        println("it's hour " + hour)
        println("moisture level: " + moisture)
        precipitation = (hour >= precipitationStart) and (hour <= precipitationStop)
        println("precipitation: " + precipitation)
        // controller
        if((moisture <= 0) and (!precipitation)) {
           sprinkling = true
        }
        if(sprinkling and (moisture >= 100)) {
            sprinkling = false
        }

        //time passes

        // imaging every time we press enter, one hour passes
        val foo = readLine()!!

        // imagine every second is one hour irl
        // Thread.sleep(1000)

        hour++
        if(precipitation) {
            moisture += precipitationRate
        } else if(sprinkling) {
            moisture += sprinklingRate
        } else {
            moisture -= dryingRate
        }
    }
}
