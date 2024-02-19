import kotlin.math.round
/**
 * Clase abstracta que representa un vehículo genérico.
 *
 * @property marca La marca del vehículo.
 * @property modelo El modelo del vehículo.
 * @property capacidadCombustible La capacidad máxima de combustible del vehículo en litros.
 * @property combustibleActual La cantidad actual de combustible en el vehículo en litros.
 * @property kilometrosActuales La cantidad de kilómetros recorridos por el vehículo.
 */
abstract class Vehiculo(
    val marca: String,
    val modelo: String,
    val capacidadCombustible: Float,
    var combustibleActual: Float,
    var kilometrosActuales: Int)
{

    /**
     * Obtiene la información sobre la autonomía del vehículo.
     *
     * @return Un mensaje con la información sobre la autonomía del vehículo.
     */
    fun obtenerInformacion(): String{
        val kilometros = round(combustibleActual * 10)
        if(combustibleActual > 0){
            return "Con el combustible actual $combustibleActual hay para hacer ${kilometros}km."
        }else{
            return "No hay suficiente combustible"
        }
    }

    /**
     * Calcula la autonomía del vehículo en kilómetros.
     *
     * @return La autonomía del vehículo en kilómetros.
     */
    open fun calcularAutonomia(): Int{
        return round(combustibleActual * 10).toInt()
    }

    /**
     * Realiza un viaje con el vehículo.
     *
     * @param distancia La distancia del viaje en kilómetros.
     * @return La distancia restante que no se pudo recorrer debido a la falta de combustible.
     */
    open fun realizaViaje(distancia: Int): Int{
        val distanciaPosible = calcularAutonomia()

        if (distanciaPosible >= distancia ){
            val combustibleGastado = distancia / 10f
            combustibleActual -= combustibleGastado
            kilometrosActuales += distancia

            return 0
        }else{
            val distanciaRecorrida = calcularAutonomia()
            kilometrosActuales += distanciaRecorrida
            combustibleActual = 0f

            return distancia - distanciaRecorrida
        }
    }

    /**
     * Reposta combustible en el vehículo.
     *
     * @param cantidad La cantidad de combustible a repostar en litros.
     * @return La cantidad efectiva de combustible repostada en litros.
     */
    fun repostar(cantidad: Float): Float {
        var cantidadRepostada : Float
        if (cantidad >= 0 || cantidad + combustibleActual >= capacidadCombustible){
            cantidadRepostada = capacidadCombustible - combustibleActual
        }else{
            cantidadRepostada = cantidad
        }

        combustibleActual += cantidadRepostada

        return cantidadRepostada
    }

}