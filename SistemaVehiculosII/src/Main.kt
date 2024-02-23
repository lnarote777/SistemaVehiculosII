import java.awt.print.Printable

fun main() {
// Ejemplo vehiculos
    val vehiculos = listOf(Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true),
     Automovil("Boreal", "BMW", "M8", 80f, 80f * 0.1f, 0f, false),
    Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500),
     Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true),
    Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false),
     Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0f, 250))


    val carrera = Carrera("Prueba", 1000, vehiculos)

    println("Que empiece la carrera '${carrera.nombreCarrera}'!!!")
    println("----------------------------------------------------")
    println()
    carrera.iniciarCarrera()


    println(carrera.determinarGanador())

}