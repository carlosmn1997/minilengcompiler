package JavaCC;

import tablaSimbolos.Simbolo;
import tablaSimbolos.SimboloYaExistenteException;

public class Errores{
	public static void error_semantico(String informacion) {
		System.out.println("ERROR SEMANTICO(linea,columna):"+informacion);
	}
	
	
	public static void validarIdentificador(Token t, Simbolo s) {
		System.out.println("Leido el identificador: " + t.image);

		boolean introducido = true;
		
		try {
		  // Ya comprueba si existe o no
		  s.setNombre(t.image);
		  minilengcompiler.tabla.introducir_simbolo(s, minilengcompiler.nivel, 0);
		 // minilengcompiler.tabla.introducir_variable(t1.image, opciones.getTipoVariable, minilengcompiler.nivel, 0);
		  //Estadisticas.tabla.imprimir_tabla();
		} catch (SimboloYaExistenteException e) { // Esta repetido
			Errores.error_semantico("Identificador duplicado");
			introducido = false;
		}
		
		// Si era un parametro hay que asociar la accion, en el caso de que se introdujo
		if (introducido && s.es_parametro() && s.accionAsociada != null) { 
			try {
				minilengcompiler.tabla.asociar_parametro_accion(s.accionAsociada, s, minilengcompiler.nivel);
			} catch (SimboloYaExistenteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("SIMBOLO YA EXISTENTE DENTRO DE VALIDACION DE IDENTIFICADOR");
			}
		}
	}
}