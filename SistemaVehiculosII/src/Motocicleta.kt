import kotlin.math.round
/**
 * Clase que representa una motocicleta, que es un tipo de vehículo.
 *
 * @property cilindrada La cilindrada de la motocicleta.
 * @constructor Crea una motocicleta con la marca, modelo, capacidad de combustible, combustible actual, kilometraje actual
 * y cilindrada especificados.
 */
class Motocicleta(marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Int, val cilindrada: Int): Vehiculo(marca, modelo ,capacidadCombustible,combustibleActual, kilometrosActuales) {

    /**
     * Calcula la autonomía de la motocicleta.
     *
     * Supone un rendimiento de 20 km por litro.
     *
     * @return La autonomía de la motocicleta en kilómetros.
     */
    override fun calcularAutonomia(): Int {
        return round(combustibleActual * 20).toInt()
    }

    /**
     * Verifica si la motocicleta puede realizar un viaje de la distancia especificada.
     *
     * @param distancia La distancia del viaje en kilómetros.
     * @return `true` si la motocicleta tiene suficiente autonomía para realizar el viaje, `false` de lo contrario.
     */
    override fun realizaViaje(distancia: Int): Boolean {
        val distanciaPosible = calcularAutonomia()
        return distanciaPosible >= distancia
    }

    /**
     * Realiza un caballito con la motocicleta.
     *
     * Simula un gasto adicional en el combustible equivalente a haber recorrido 5 km.
     *
     * @return El combustible restante después de realizar el caballito.
     */
    fun realizaCaballito(): Float{
        combustibleActual -= (5f/10) //Equivale a 5km

        return combustibleActual
        
    }

}