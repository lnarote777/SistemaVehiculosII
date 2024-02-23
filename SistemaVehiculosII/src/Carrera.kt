import kotlin.random.Random

/**
 * Clase que representa una carrera entre varios vehículos.
 *
 * @property nombreCarrera El nombre de la carrera.
 * @property distanciaTotal La distancia total que deben recorrer los vehículos para completar la carrera, en kilómetros.
 * @property participantes La lista de vehículos participantes en la carrera.
 */
class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Int,
    val participantes: List<Vehiculo>
) {
    private var estadoCarrera: Boolean = false
    private var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
    private var posiciones: MutableList<Pair<String, Int>> = mutableListOf()

    /**
     * Inicializa una instancia de la clase Carrera.
     *
     * @throws IllegalArgumentException Si la distancia total es menor o igual a cero.
     * @throws IllegalArgumentException Si la lista de participantes está vacía.
     */
    init {
        require(distanciaTotal > 0) {"La carrera debe tener una distancia total mayor que cero."}
        require(participantes.isNotEmpty()) {"La lista de participantes no puede estar vacía"}
        for (vehiculo in participantes){
            posiciones.add(Pair(vehiculo.nombre, 0))
        }
    }


    /**
     * Inicia la carrera, realizando iteraciones hasta que se determine un ganador.
     * Durante cada iteración, un vehículo aleatorio avanza y se actualizan las posiciones.
     * Si se determina un ganador, el estado de la carrera se establece en false y se sale del bucle.
     */
    fun iniciarCarrera(){
        estadoCarrera = true

        while (estadoCarrera){

            val vehiculo = participantes.random()

            avanzarVehiculo(vehiculo)

            actualizarPosiciones()
            determinarGanador() //si hay ganador actualizar el estado a false para salir del bucle
        }
    }


    /**
     * Avanza el vehículo una distancia aleatoria entre 10 y 200 km.
     * Si la distancia a avanzar es mayor que el combustible actual del vehículo,
     * se realiza un repostaje antes de continuar.
     *
     * @param vehiculo El vehículo que avanzará.
     */
    private fun avanzarVehiculo(vehiculo: Vehiculo){
        
        val distancia = ((10..200).random()).toFloat()

        vehiculo.realizaViaje(distancia)

        if (distancia > vehiculo.combustibleActual){
            repostarVehiculo(vehiculo, vehiculo.capacidadCombustible)
        }

        vehiculo.kilometrosActuales += distancia

    }

    // dividir la carrera por tramos (cada 20 km realizar filigrana)
    //avanzarTramo()

    /**
     * Reposta el vehículo con la cantidad especificada de combustible.
     *
     * @param vehiculo El vehículo que se repostará.
     * @param cantidad La cantidad de combustible que se añadirá al vehículo.
     */
    private fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float){

        val cantRepostada = vehiculo.repostar(cantidad)

        registrarAccion(vehiculo.nombre, "Repostó $cantRepostada L" )
    }

    /**
     * Determina aleatoriamente si un vehículo realiza una filigrana (derrape o caballito) y registra la acción.
     */
    private fun realizarFiligrana(vehiculo: Vehiculo){
        val filigranas = Random.nextInt(1,3)

        TODO()

    }


    /**
     * Actualiza las posiciones de los vehículos en la carrera.
     * Itera sobre la lista de vehículos participantes y actualiza la lista de posiciones
     * con la distancia recorrida por cada vehículo.
     * Si un vehículo ya está presente en la lista de posiciones, se actualiza su distancia recorrida.
     * Si un vehículo no está presente, se agrega a la lista con su distancia recorrida actual.
    */
    private fun actualizarPosiciones(){

        for ((index, vehiculo) in participantes.withIndex()){
            val nuevaDistancia = posiciones[index].second + vehiculo.kilometrosActuales
            posiciones[index] = Pair(vehiculo.nombre, nuevaDistancia.toInt())
        }

    }


    /**
     * Revisa las posiciones de los vehículos en la carrera para identificar al ganador o ganadores.
     * Si un vehículo ha alcanzado o superado la distancia total de la carrera, se determina como ganador.
     * Una vez encontrado el ganador, se establece el estado de la carrera como finalizado y se devuelve el ganador o ganadores.
     * Si ningún vehículo ha alcanzado la distancia total, la carrera continúa y no se determina un ganador.
     *
     * @return La lista de vehículos ganadores de la carrera, o una lista vacía si ningún vehículo ha ganado aún.
     */
     fun determinarGanador(): List<Vehiculo>{

        val ganadores = mutableListOf<Vehiculo>()

        for (posicion in posiciones){
            val nombreVehiculo = posicion.first
            val distanciaRecorrida = posicion.second

            val vehiculo = participantes.find { it.nombre == nombreVehiculo }

            if (vehiculo != null && distanciaRecorrida >= distanciaTotal){
                ganadores.add(vehiculo)
            }
        }

        // si hay un ganador al menos, termina la carrera
        if (ganadores.isNotEmpty()){
            estadoCarrera = false
        }

        return ganadores
    }

    fun mostrarGanadores(){

        val ganadores = determinarGanador()

        for (ganador in ganadores){
            print(ganador.nombre)
        }


    }



    /**
     * Registra una acción realizada por un vehículo en su historial de acciones.
     *
     * @param vehiculo El nombre del vehículo al que se le registrará la acción en su historial.
     * @param accion La acción que se registrará en el historial del vehículo.
     */
    private fun registrarAccion(vehiculo: String, accion: String){

        val historialVehiculo = historialAcciones[vehiculo] ?: mutableListOf() //obtiene el historial del vehículo o crea uno si no tiene

        historialVehiculo.add(accion)

        historialAcciones[vehiculo] = historialVehiculo //actualiza el historial del vehículo

    }

    /**
     * Devuelve una clasificación final de los vehículos, cada elemento tendrá el nombre del vehículo,
     * posición ocupada, la distancia total recorrida, el número de paradas para repostar y el historial de acciones.
     * La colección estará ordenada por la posición ocupada.
     */
    private fun obtenerResultados(): List<ResultadoCarrera>{
        val resultados = mutableListOf<ResultadoCarrera>()

        for (vehiculo in participantes) {
            val posicion = obtenerPosicion(vehiculo)
            // Calcula el número de paradas para repostar que ha realizado el vehículo sino ha realizado ninguna devuelve 0
            val paradasRepostaje = historialAcciones[vehiculo.nombre]?.count { it.startsWith("Repostó") } ?: 0
            // Obtiene el historial de acciones del vehículo durante la carrera
            val historialVehiculo = historialAcciones[vehiculo.nombre] ?: emptyList()
            val resultadoVehiculo = ResultadoCarrera(vehiculo, posicion, vehiculo.kilometrosActuales, paradasRepostaje, historialVehiculo)
            resultados.add(resultadoVehiculo)
        }

        //Devuelde la lista de vehiculos ordenada según la posición
        return resultados.sortedBy { it.posicion }
    }

    fun imprimirResultados() {
        val resultados = obtenerResultados()

       for ((index, resultado) in resultados.withIndex()){
           println("Resultado ${index + 1}: ")
           println("-Nombre del vehículo: ${resultado.vehiculo.nombre}")
           println("-Distancia recorrida: ${resultado.vehiculo.kilometrosActuales}Km")
           println("-Posición: ${resultado.posicion}")
           println("-Paradas a repostar: ${resultado.paradasRepostaje}")
           println("-Historial de acciones:")
           for (accion in resultado.historialAcciones){
               println("\t- $accion")
           }
           println()
       }
    }

    private fun obtenerPosicion(vehiculo: Vehiculo): Int {
        for ((indice,posicion) in posiciones.withIndex()){
            if (vehiculo.nombre == posicion.first){
                return indice + 1 //Para que las posiciones enmpiecen en 1 y no en 0
            }
        }
        return -1
    }

    //Es opcional si consigo hacerlo funcionar lo dejo
    /**
     * Representa el resultado final de un vehículo en la carrera, incluyendo su posición final, el kilometraje total recorrido,
     * el número de paradas para repostar, y un historial detallado de todas las acciones realizadas durante la carrera.
     *
     * @property vehiculo El [Vehiculo] al que pertenece este resultado.
     * @property posicion La posición final del vehículo en la carrera, donde una posición menor indica un mejor rendimiento.
     * @property kilometraje El total de kilómetros recorridos por el vehículo durante la carrera.
     * @property paradasRepostaje El número de veces que el vehículo tuvo que repostar combustible durante la carrera.
     * @property historialAcciones Una lista de cadenas que describen las acciones realizadas por el vehículo a lo largo de la carrera, proporcionando un registro detallado de su rendimiento y estrategias.
     */
    data class ResultadoCarrera(
        val vehiculo: Vehiculo,
        val posicion: Int,
        val kilometraje: Float,
        val paradasRepostaje: Int,
        val historialAcciones: List<String>
    )

}