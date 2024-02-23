import kotlin.math.round
/**
 * Clase abstracta que representa un vehículo genérico.
 *
 * @property nombre Nombre del vehículo.
 * @property marca La marca del vehículo.
 * @property modelo El modelo del vehículo.
 * @property capacidadCombustible La capacidad máxima de combustible del vehículo en litros.
 * @property combustibleActual La cantidad actual de combustible en el vehículo en litros.
 * @property kilometrosActuales La cantidad de kilómetros recorridos por el vehículo.
 */
abstract class Vehiculo(
    val nombre: String,
    val marca: String,
    val modelo: String,
    val capacidadCombustible: Float,
    var combustibleActual: Float,
    var kilometrosActuales: Float)
{

    init {
        require(capacidadCombustible > 0 ){"La capacidad del tanque dede ser un valor positivo"}
        require(combustibleActual >= 0) {"El commbustible actual no puede ser negativo"}
        require(!nombreEStaRepetido(this.nombre)) {"Ya existe el nombre $nombre"}
    }

    companion object{
        const val KM_LITROS = 10f

        private val nombres : MutableSet<String> = mutableSetOf()

        private fun nombreEStaRepetido(nombre: String) = !nombres.add(nombre)
    }

    /**
     * Obtiene la información sobre la autonomía del vehículo.
     *
     * @return Un mensaje con la información sobre la autonomía del vehículo.
     */
    fun obtenerInformacion(): String{
        return "Con el combustible actual $combustibleActual hay para hacer ${calcularAutonomia()}km."
    }

    /**
     * Calcula la autonomía del vehículo en kilómetros.
     *
     * @return La autonomía del vehículo en kilómetros.
     */
    open fun calcularAutonomia(): Float{
        return combustibleActual * KM_LITROS
    }

    /**
     * Realiza un viaje con el vehículo hasta donde el combustible actual permite.
     *
     * Este método simula un viaje con el vehículo, recorriendo la distancia especificada o hasta donde el combustible actual lo permita.
     * Si la distancia proporcionada es mayor que la autonomía restante del vehículo, el vehículo viaja la distancia máxima posible con el combustible restante.
     * Si la distancia proporcionada es menor o igual a la autonomía restante, el vehículo viaja esa distancia.
     * Actualiza el combustible restante y la cantidad de kilómetros recorridos de acuerdo con el viaje realizado.
     * Devuelve la distancia restante que no se pudo recorrer debido a la falta de combustible, o 0 si se recorrió la distancia completa.
     *
     * @param distancia La distancia del viaje a realizar en kilómetros.
     * @return La distancia restante que no se pudo recorrer debido a la falta de combustible, o 0 si se recorrió la distancia completa.
     */
    open fun realizaViaje(distancia: Float): Float{

        val distanciaRecorrida = if (distancia > calcularAutonomia()) calcularAutonomia() else distancia

        val combustibleGastado = distanciaRecorrida / KM_LITROS
        combustibleActual -= combustibleGastado
        kilometrosActuales += distanciaRecorrida

        return if (distancia < distanciaRecorrida){
            0f
        }else{
            distancia - distanciaRecorrida
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

        if (cantidad >= 0 || (cantidad + combustibleActual) > capacidadCombustible){
            cantidadRepostada = capacidadCombustible - combustibleActual
        }else{
            cantidadRepostada = cantidad
        }

        combustibleActual += cantidadRepostada

        return cantidadRepostada
    }

}