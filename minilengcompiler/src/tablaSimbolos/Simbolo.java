package tablaSimbolos;

import java.util.ArrayList;

public class Simbolo{
	
	public String nombre;
	public int nivel; // Bloque en el que se ha declarado el simbolo
	public Tipo_simbolo tipo;
	public Tipo_variable variable;
	public Clase_parametro parametro;
	public boolean visible;
	public ArrayList<Simbolo> lista_parametros;
	public Simbolo accionAsociada; // En el caso de que sea un parametro
	public long dir; // De momento no lo utilizamos
	
	
	public boolean es_programa() {
		return tipo == Tipo_simbolo.PROGRAMA;
	}
	
	public boolean es_variable() {
		return tipo == Tipo_simbolo.VARIABLE;
	}
	
	public boolean es_parametro() {
		return tipo == Tipo_simbolo.PARAMETRO;
	}
	
	public boolean es_accion() {
		return tipo == Tipo_simbolo.ACCION;
	}
	
	public boolean es_valor() {
		return tipo == Tipo_simbolo.PARAMETRO && parametro == Clase_parametro.VAL;
	}
	
	public boolean es_referencia() {
		return tipo == Tipo_simbolo.PARAMETRO && parametro == Clase_parametro.REF;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nuevo) {
		nombre = nuevo;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public Tipo_variable getTipoVariable() {
		return variable;
	}
	
	public Simbolo set_visible(boolean visibilidad) {
		this.visible = visibilidad;
		return this;
	}
	
	public void setNivel(int nuevo) {
		nivel = nuevo;
	}
	
	public void setDir(int nueva) {
		dir = nueva;
	}
	
	public void setAccionAsociada(Simbolo nueva) {
		accionAsociada = nueva;
	}
	
	public Simbolo getAccionAsociada() {
		return accionAsociada;
	}
	
	public Simbolo(String nombre, Tipo_variable var, Clase_parametro clase, int nivel, int dir) {
		this.nombre = nombre;
		this.variable = var;
		this.parametro = clase;
		this.nivel = nivel;
		this.dir = dir;
		this.lista_parametros = new ArrayList<>();
		accionAsociada = null;
	}
	
	
	public void asociar_accion_parametro(Simbolo parametro) {
		this.lista_parametros.add(parametro);
	}
	
	public static Simbolo introducir_parametro(String nombre, Tipo_variable var, Clase_parametro clase, int nivel) {
		Simbolo nuevo = new Simbolo(nombre, var, clase, nivel, 0);
		return nuevo;
	}
	
	public static Simbolo introducir_programa(String nombre, int dir) {
		Simbolo nuevo = new Simbolo(nombre, Tipo_variable.DESCONOCIDO,  Clase_parametro.VAL , 0, 0);
		return nuevo;
	}
	
	public static Simbolo introducir_variable(String nombre, Tipo_variable variable, int nivel, int dir) {
		Simbolo nuevo = new Simbolo(nombre, variable,  Clase_parametro.VAL , nivel, dir);
		return nuevo;
	}
	
	public static Simbolo introducir_accion (String nombre, int nivel, int dir) {
		Simbolo nuevo = new Simbolo(nombre, Tipo_variable.DESCONOCIDO,  Clase_parametro.VAL , nivel, dir);
		return nuevo;
	}

}