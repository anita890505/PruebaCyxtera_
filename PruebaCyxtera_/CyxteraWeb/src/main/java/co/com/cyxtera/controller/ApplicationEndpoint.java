package co.com.cyxtera.controller;

import java.util.List;

import co.com.cyxtera.modelo.Operacion;

/**
 * utiliza para definir los endpoints a ejecutar en la aplicación
 * @author Ana Maria Alvarez
 * 
 *
 */
public class ApplicationEndpoint {
	
	private static String NUMERO = "/adicionarNumero";
	private static String RESULTADO = "/verResultado";
	private static String OPERACIONES = "/adicionarOperadores";
	 
    public static String getResultado(List<Double> d, List<String> s) {
        return RESULTADO.concat("?numerosCarga=%s"+ d + "?operacionesCarga"+s);
    }
    
    public static String getNumeros(Double g) {
        return NUMERO.concat(String.format("?numero=%s", g.toString()));
    }
    
    public static String getOperaciones(String operacion) {
        return OPERACIONES.concat(String.format("?operacion=%s", operacion));
    }
}
