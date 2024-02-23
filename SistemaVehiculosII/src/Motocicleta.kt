import kotlin.math.round
/**
 * Clase que representa una motocicleta, que es un tipo de vehículo.
 *
 * @property cilindrada La cilindrada de la motocicleta.
 * @constructor Crea una motocicleta con la marca, modelo, capacidad de combustible, combustible actual, kilometraje actual
 * y cilindrada especificados.
 */
class Motocicleta(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val cilindrada: Int)
    : Vehiculo(nombre, marca, modelo ,capacidadCombustible,combustibleActual, kilometrosActuales)
{

    init {
        require(cilindrada in 125..1000) {"La cilindrada debe estar entre 125 y 1000cc"}
    }

    companion object{
        const val KM_LITROS = 20f
        const val CABALLITO = 6.5f  // kilometros a los que equivale el gasto de combustible por hacer el caballito
    }

    /**
     * Calcula la autonomía restante del vehículo en kilómetros.
     *
     * Este método calcula la autonomía restante del vehículo en función de su cilindrada y la cantidad de combustible actual.
     * Si la cilindrada del vehículo es igual a 1000, la autonomía se calcula multiplicando la cantidad de combustible actual por la constante KM_LITROS.
     * Para otras cilindradas, la autonomía se calcula restando la cilindrada del vehículo dividida por 1000 de la cantidad de combustible actual.
     *
     * @return La autonomía restante del vehículo en kilómetros.
     */
    override fun calcularAutonomia(): Float {
        if (cilindrada == 1000){
            return combustibleActual * KM_LITROS
        }else{
            return combustibleActual - (cilindrada / 1000)
        }
    }



    override fun realizaViaje(distancia: Float): Float {
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
     * Realiza un caballito con la motocicleta.
     *
     * Simula un gasto adicional en el combustible equivalente a haber recorrido 5 km.
     *
     * @return El combustible restante después de realizar el caballito.
     */
    fun realizaCaballito(): Float{
        return (calcularAutonomia() - CABALLITO) / 10
    }


}