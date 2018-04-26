package JavaCC;

public class Estadisticas{
	/* ESTADISTICAS */

	  // Palabras reservadas
	  public static int tprograma, tvar, tand, tor, tnot, tprincipio, tfin, tsi = 0; 
	  public static int tent, tsi_no, tfsi, tmq, tfmq, tescribir, tleer, tmod, tdiv = 0;
	  public static int tentero, tbooleano, tcaracter, ttrue, tfalse, tentacar, tcaraent, taccion = 0;
	  public static int tval, tref, tmayor, tmenor, tigual, tmai, tmei, tni, topas = 0;

	  // Operadores aritmeticos
	  public static int tmas, tmenos, tmul, tdivent = 0;

	  // Constantes e identificadores
	  public static int tconstcad, tconstchar, tconstant, tidentificador, tconstentera = 0;  

	  private static void formatear(String nombre, int numApariciones) {
	    System.out.format("||%-23s||%-26s||\n", nombre, numApariciones);
	  }

	  
	  public static void mostrarEstadisticas() {
		    System.out.format("||--------------------ESTADISTICAS-------------------||\n");
		    System.out.format("||---------------------------------------------------||\n");
		    System.out.format("||   PALABRA RESERVADA   ||  NUMERO DE APARICIONES   ||\n");
		    System.out.format("||---------------------------------------------------||\n");
		    formatear("programa", tprograma);
		    formatear("var", tvar);
		    formatear("and", tand);
		    formatear("or", tor);
		    formatear("not", tnot);
		    formatear("principio", tprincipio);
		    formatear("fin", tfin);
		    formatear("si", tsi);
		    formatear("ent", tent);
		    formatear("si_no", tsi_no);
		    formatear("fsi", tfsi);
		    formatear("mq", tmq);
		    formatear("fmq", tfmq);
		    formatear("escribir", tescribir);
		    formatear("leer", tleer);
		    formatear("mod", tmod);
		    formatear("div", tdiv);
		    formatear("entero", tentero);
		    formatear("booleano", tbooleano);
		    formatear("caracter", tcaracter);
		    formatear("true", ttrue);
		    formatear("false", tfalse);
		    formatear("entacar", tentacar);
		    formatear("caraent", tcaraent);
		    formatear("accion", taccion);
		    formatear("val", tval);
		    formatear("ref", tref);
		    formatear(" > ", tmayor);
		    formatear(" < ", tmenor);
		    formatear(" = ", tigual);
		    formatear(" >= ", tmai);
		    formatear(" <= ", tmei);
		    formatear(" <> ", tni);
		    formatear(" := ", topas);
		    System.out.format("||---------------------------------------------------||\n");
		    System.out.format("||               OPERADORES ARITMETICOS              ||\n");
		    System.out.format("||---------------------------------------------------||\n");
		    formatear(" + ", tmas);
		    formatear(" - ", tmenos);
		    formatear(" * ", tmul);
		    formatear(" / ", tdivent);
		    System.out.format("||---------------------------------------------------||\n");
		    System.out.format("||            CONSTANTES E IDENTIFICADORES           ||\n");
		    System.out.format("||---------------------------------------------------||\n");
		    formatear("identificadores", tidentificador);
		    formatear("cadenas", tconstcad);
		    formatear("caracteres", tconstchar);
		    formatear("constantes numericas", tconstentera);
		    
		    
		    
		  }
		  
		    
		    
}