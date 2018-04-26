package tablaSimbolos;

import java.util.LinkedList;
import java.util.ListIterator;

import java.util.Iterator;

public class Tabla_simbolos{
	
	private int tamanyoTabla = 10;
	
	private LinkedList<Simbolo> tabla[];
	
	private int pearson(String nombre) {
		int h = 0;
		for (int i = 0; i < nombre.length(); i++) {
			char caracter = nombre.charAt(i);
			h = h ^ caracter; // XOR 
		}
		return h % tamanyoTabla;
	}
	
	/*******************************************************
	Crea una tabla de símbolos vacía.  Este procedimiento debe invocarse 
	antes de hacer ninguna operación con la tabla de símbolos.
	**********************************************************************/
	public void inicializar_tabla(){
		System.out.println("inicializo la tabla de simbolos");
        tabla = new LinkedList[tamanyoTabla];
         
        for(int i = 0; i < tamanyoTabla ; i++){
            tabla[i] = new LinkedList<>();
        }
	}
	
	public void imprimir_tabla() {
		for(int i = 0; i < tamanyoTabla; i++) {
			System.out.print(i);
			Iterator<Simbolo> it = tabla[i].iterator();
					
			while(it.hasNext()){
				Simbolo puntero = (Simbolo) it.next();
				System.out.print(" --> " + puntero.getNombre());
			}
			System.out.println();
		}
	}

	
	/**********************************************************************
	Busca en la tabla el símbolo de mayor nivel cuyo nombre coincida
	con el del parámetro (se distinguen minúsculas y mayúsculas).  Si
	existe, devuelve un puntero como resultado, de lo contrario devuelve
	NULL o lanza una excepción (se deja a vuestra elección un mecanismo 
	u otro).
	**********************************************************************/
	public Simbolo buscar_simbolo(String nombre) throws SimboloNoEncontradoException {
		int h = pearson(nombre);
		LinkedList<Simbolo> elemento = tabla[h];
		
		Iterator<Simbolo> it = elemento.iterator();
		Simbolo encontrado = null; 
				
		while(it.hasNext() && encontrado != null){
			Simbolo puntero = (Simbolo) it.next();
			if (puntero.getNombre() == nombre) {
				encontrado = puntero;
			}
		}
		
		if(encontrado == null) {
			throw new SimboloNoEncontradoException();
		}
		return encontrado;
	}
	
	
	
	
	
	/**********************************************************************
	Introduce en la tabla un simbolo PROGRAMA,  con el nombre
	del parametro, de nivel 0, con la direccion del parametro. Dado que debe
	ser el primer simbolo a introducir, NO SE VERIFICA QUE EL SIMBOLO YA
	EXISTA.
	**********************************************************************/
	public Simbolo introducir_programa(String nombre, int dir){
		int h = pearson(nombre);
		Simbolo programa = Simbolo.introducir_programa(nombre, dir);
		tabla[h].addFirst(programa); // añade por la izquierda
		return programa;
	}
	
	
	private Simbolo buscar_simbolo_nivel(String nombre, int nivel) throws SimboloNoEncontradoException{
		int h = pearson(nombre);
		LinkedList<Simbolo> elemento = tabla[h];
		
		Iterator<Simbolo> it = elemento.iterator();
		Simbolo encontrado = null; 
		while(it.hasNext() && encontrado == null){
			Simbolo puntero = (Simbolo) it.next();
			if (puntero.getNombre().equals(nombre) && puntero.getNivel() == nivel) {
				encontrado = puntero;
			}
		}
		
		if(encontrado == null) {
			throw new SimboloNoEncontradoException();
		}
		return encontrado;
	}

	
	public Simbolo introducir_simbolo (Simbolo s, int nivel, int dir) throws SimboloYaExistenteException {
		Simbolo nuevo = null;
		boolean catchEjecutado = false;
		try {
			buscar_simbolo_nivel(s.getNombre(), nivel);
		}
		catch (SimboloNoEncontradoException e) { // Si hay excepcion es que no estaba, por lo que se añade
			// En principio debería encontralo siempre
			int h = pearson(s.getNombre());
			//nuevo = Simbolo.introducir_parametro(nombre, variable, parametro, nivel);
			s.setNivel(nivel);
			s.setDir(dir);
			tabla[h].addFirst(s); // añade por la izquierda
			catchEjecutado = true;
		}
		if (!catchEjecutado) {
			throw new SimboloYaExistenteException();
		}
		return nuevo;
	}
	
	/**********************************************************************
	Si existe un símbolo en la tabla del mismo nivel y con el mismo
	nombre, devuelve NULL (o una excepción, esto se deja a vuestra 
	elección.  De lo contrario, introduce un símbolo VARIABLE (simple) 
	con los datos de los argumentos.
	**********************************************************************/
	public Simbolo introducir_variable(String nombre, Tipo_variable variable, int nivel, int dir) throws SimboloYaExistenteException {
		Simbolo nuevo = null;
		boolean catchEjecutado = false;
		try {
			buscar_simbolo_nivel(nombre, nivel);
		}
		catch (SimboloNoEncontradoException e) { // Si hay excepcion es que no estaba, por lo que se añade
			int h = pearson(nombre);
			nuevo = Simbolo.introducir_variable(nombre, variable, nivel, dir);
			tabla[h].addFirst(nuevo); // añade por la izquierda
			catchEjecutado = true;
		}
		if (!catchEjecutado) {
			throw new SimboloYaExistenteException();
		}
		return nuevo;
	}
	
	
	/**********************************************************************
	Si existe un símbolo en la tabla del mismo nivel y con el mismo
	nombre, devuelve NULL.  De lo contrario, introduce un símbolo
	ACCION con los datos de los argumentos.
	 * @throws SimboloYaExistenteException 
	**********************************************************************/
	public Simbolo introducir_accion (String nombre, int nivel, int dir) throws SimboloYaExistenteException {
		Simbolo nuevo = null;
		boolean catchEjecutado = false;
		try {
			buscar_simbolo_nivel(nombre, nivel);
		}
		catch (SimboloNoEncontradoException e) { // Si hay excepcion es que no estaba, por lo que se añade
			int h = pearson(nombre);
			nuevo = Simbolo.introducir_accion(nombre, nivel, dir);
			tabla[h].addFirst(nuevo); // añade por la izquierda
			catchEjecutado = true;
		}
		if (!catchEjecutado) {
			throw new SimboloYaExistenteException();
		}
		return nuevo;
	}
	
	// TODO LOS PARAMETROS VAN DENTRO DE FUNCIONES
	/**********************************************************************
	Si existe un símbolo en la tabla del mismo nivel y con el mismo
	nombre, devuelve NULL.  De lo contrario, introduce un símbolo
	PARAMETRO con los datos de los argumentos. 
	 * @throws SimboloYaExistenteException 
	**********************************************************************/
	public Simbolo introducir_parametro (String nombre, Tipo_variable variable, Clase_parametro parametro, int nivel, int dir) throws SimboloYaExistenteException {
		Simbolo nuevo = null;
		boolean catchEjecutado = false;
		try {
			buscar_simbolo_nivel(nombre, nivel);
		}
		catch (SimboloNoEncontradoException e) { // Si hay excepcion es que no estaba, por lo que se añade
			// En principio debería encontralo siempre
			int h = pearson(nombre);
			nuevo = Simbolo.introducir_parametro(nombre, variable, parametro, nivel);
			tabla[h].addFirst(nuevo); // añade por la izquierda
			catchEjecutado = true;
		}
		if (!catchEjecutado) {
			throw new SimboloYaExistenteException();
		}
		return nuevo;
	}
	
	private Simbolo actualizar_simbolo_nivel(Simbolo simbolo, int nivel){
		int h = pearson(simbolo.getNombre());
		LinkedList<Simbolo> elemento = tabla[h];
		
		Iterator<Simbolo> it = elemento.iterator();
		Simbolo encontrado = null; 
		
		int indice = 0;
		while(it.hasNext() && encontrado != null){
			Simbolo puntero = (Simbolo) it.next();
			if (puntero.getNombre() == simbolo.getNombre() && puntero.getNivel() == nivel) {
				encontrado = puntero;
			}
			indice++;
		}
		
		elemento.set(indice, simbolo);
		tabla[h] = elemento; // Se vuelve a guardar para actualizar los cambios
		
		return encontrado;
	}
	
	public Simbolo asociar_parametro_accion (Simbolo accionAsociada, Simbolo parametroAsociar, int nivel) throws SimboloYaExistenteException {
		Simbolo asociar = null;
		boolean catchEjecutado = false;
		
		try {
			asociar = buscar_simbolo_nivel(accionAsociada.getNombre(), nivel);
		}
		catch (SimboloNoEncontradoException e) { // Si hay excepcion es que no estaba, por lo que se añade
			catchEjecutado = true;
		}
		
		if (!catchEjecutado) { // Si existe, entonces se actualiza
			asociar.asociar_accion_parametro(parametroAsociar);
			actualizar_simbolo_nivel(asociar, nivel-1); // es -1 porque la accion esta un nivel anterior
		}
		return asociar;
	}

	/**********************************************************************
	Elimina de la tabla todos los símbolos PROGRAMA de nivel 0 (debería 
	haber uno solo).
	**********************************************************************/
	public void eliminar_programa(){
		for(int i = 0; i < tamanyoTabla; i++) {
			Iterator<Simbolo> it = tabla[i].iterator();
					
			while(it.hasNext()){
				Simbolo puntero = (Simbolo) it.next();
				if (puntero.es_programa()) {
					it.remove();
				}
			}
			
		}
	}
	
	// TODO no se utiliza
	/**********************************************************************
	Elimina de la tabla todas las variables que sean del nivel del argumento. 
	NO ELIMINA PARÁMETROS.
	**********************************************************************/
	public void eliminar_variables(int nivel) 
	{
		for(int i = 0; i < tamanyoTabla; i++) {
			Iterator<Simbolo> it = tabla[i].iterator();
					
			while(it.hasNext()){
				Simbolo puntero = (Simbolo) it.next();
				if (puntero.es_variable() && puntero.getNivel() == nivel) {
					it.remove();
				}
			}
			
		}
	}
	
	// Se utiliza esta versión
	public void eliminar_variables_parametros(int nivel) 
	{
		for(int i = 0; i < tamanyoTabla; i++) {
			Iterator<Simbolo> it = tabla[i].iterator();
					
			while(it.hasNext()){
				Simbolo puntero = (Simbolo) it.next();
				if ((puntero.es_variable() || puntero.es_parametro()) && puntero.getNivel() == nivel) {
					it.remove();
				}
			}
			
		}
	}

	// TODO no se utiliza porque duplico el simbolo de parametro y variable
	/**********************************************************************
	Marca todos los parámetros de un nivel como ocultos para que no puedan
	ser encontrados, pero se mantenga la definición completa de la
	acción donde están declarados para verificación de invocaciones a la acción.
	**********************************************************************/
	public void ocultar_parametros(int nivel)
	{
		for(int i = 0; i < tamanyoTabla; i++) {
			ListIterator<Simbolo> it = tabla[i].listIterator();
					
			while(it.hasNext()){
				Simbolo puntero = (Simbolo) it.next();
				if (puntero.es_parametro() && puntero.getNivel() == nivel) {
					it.set(puntero.set_visible(false));
				}
			}
			
		}
	}
	
	// TODO tampoco se utiliza
	/**********************************************************************
	Elimina de la tabla todas los parámetros que hayan sido ocultados
	previamente.  LOS PROCEDIMIENTOS Y FUNCIONES DONDE ESTABAN DECLARADOS
	DEBEN SER ELIMINAODS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
	**********************************************************************/
	public void eliminar_parametros_ocultos(int nivel)
	{
		
	}

	
	/**********************************************************************
	Elimina de la tabla todas los procedimientos de un nivel.  
	LOS PARAMETROS DE ESTAS ACCIONES
	DEBEN SER ELIMINADOS TAMBIEN PARA MANTENER LA COHERENCIA DE LA TABLA.
	**********************************************************************/
	public void eliminar_acciones(int nivel)
	{
		for(int i = 0; i < tamanyoTabla; i++) {
			Iterator<Simbolo> it = tabla[i].iterator();
					
			while(it.hasNext()){
				Simbolo puntero = (Simbolo) it.next();
				if (puntero.es_accion() && puntero.getNivel() == nivel) {
					it.remove();
				}
			}
			
		}
	}
}