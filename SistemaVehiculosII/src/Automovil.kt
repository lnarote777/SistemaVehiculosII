/**
 * Clase que representa un automóvil, que es un tipo de vehículo.
 *
 * @property esHibrido Indica si el automóvil es híbrido (eléctrico + gasolina) o no (solo gasolina). Por defecto es `false`.
 *
 * @constructor Crea un automóvil con el nombre, marca, modelo, capacidad de combustible, combustible actual, kilometraje actual y
 * si es híbrido.
 */
class Automovil(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadCombustible: Float,
    combustibleActual: Float,
    kilometrosActuales: Float,
    val esHibrido: Boolean = false,
    )
    : Vehiculo(nombre, marca, modelo ,capacidadCombustible,combustibleActual, kilometrosActuales)
{

    companion object{

        const val KM_LITROS_HIBRIDO = 15f
        const val GASTO_DERRAPE_HIBRIDO = 6.25f
        const val GASTO_DERRAPE = 7.5f

        var condicionBritanica: Boolean = false

        /**
         * Cambia la configuración de conducción británica del automóvil.
         *
         * @param nuevaCondicion La nueva configuración de conducción británica.
         */
        fun cambiarCondicionBritania(nuevaCondicion: Boolean){
            condicionBritanica = nuevaCondicion
        }
    }

    /**
     * Calcula la autonomía del vehículo.
     * Si el vehículo no es híbrido, utiliza la implementación predeterminada de la superclase.
     * Si es híbrido, calcula la autonomía basándose en el combustible actual y el rendimiento del híbrido.
     *
     * @return La autonomía del vehículo en kilómetros.
     */
    override fun calcularAutonomia(): Float{
        if (!esHibrido){
           return super.calcularAutonomia()
        }else{
            return combustibleActual * KM_LITROS_HIBRIDO
        }
    }

    /**
     * Simula un derrape del automóvil.
     *
     * Realiza un gasto adicional en el combustible equivalente a haber recorrido 5 km.
     *
     * @return El combustible restante después del derrape.
     */
    fun realizaDerrape(): Float{
        if(!esHibrido){
            return (calcularAutonomia() - GASTO_DERRAPE) / 10
        }else{
            return (calcularAutonomia() - GASTO_DERRAPE_HIBRIDO) / 10
        }
    }

}