/**
 * Clase que representa un automóvil, que es un tipo de vehículo.
 *
 * @property esElectrico Indica si el automóvil es eléctrico. Por defecto es `false`.
 * @property condicionBritania Indica si el automóvil está configurado para conducción británica (volante a la derecha).
 * @constructor Crea un automóvil con la marca, modelo, capacidad de combustible, combustible actual, kilometraje actual,
 * si es eléctrico y la condición de conducción británica.
 */
class Automovil(marca: String, modelo: String, capacidadCombustible: Float, combustibleActual: Float, kilometrosActuales: Int, val esElectrico: Boolean = false, var condicionBritania: Boolean): Vehiculo(marca, modelo ,capacidadCombustible,combustibleActual, kilometrosActuales) {

    /**
     * Calcula la autonomía del automóvil.
     *
     * Si el automóvil no es eléctrico, se reduce en 5 km la autonomía calculada por la clase base.
     *
     * @return La autonomía del automóvil en kilómetros.
     */
    override fun calcularAutonomia(): Int {
        val autonomia = super.calcularAutonomia()
        return if (!esElectrico){
            autonomia - 5
        } else {
            autonomia
        }
    }

    /**
     * Cambia la configuración de conducción británica del automóvil.
     *
     * @param nuevaCondicion La nueva configuración de conducción británica.
     */
    fun cambiarCondicionBritania(nuevaCondicion: Boolean){
        condicionBritania = nuevaCondicion
    }

    /**
     * Simula un derrape del automóvil.
     *
     * Realiza un gasto adicional en el combustible equivalente a haber recorrido 5 km.
     *
     * @return El combustible restante después del derrape.
     */
    fun realizaDerrape(): Float{
        combustibleActual -= (5f/10) //Equivale a 5km

        return combustibleActual
    }

}