package co.comcystera.test;

import java.util.List;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialException;

import co.com.cyxtera.controller.BitsoClient;
import co.com.cyxtera.enums.OperacionesEnum;
import co.com.cyxtera.modelo.Operacion;

public class TestCyxtera {

	public static void name(String[] arg ) throws SerialException{
		try {
			BitsoClient client = new BitsoClient("http://localhost:8081/CyxteraWeb/calculadora/cysteraCalculo/", "adicionarNumero");
			Double ripplePrice = client.getNumero(5.0);
			Double ripplePrice2 = client.getNumero(5.0);
			
			List<Double> n = new ArrayList<>();
			n.add(ripplePrice);
			n.add(ripplePrice2);
			
			BitsoClient client2 = new BitsoClient("http://localhost:8081/CyxteraWeb/calculadora/cysteraCalculo/", "adicionarOperadores");
			List<String> s = new ArrayList<>();
			String operacion = client2.getOperaciones(OperacionesEnum.SUMA.getNombre());
			s.add(operacion);
			
			BitsoClient client3 = new BitsoClient("http://localhost:8081/CyxteraWeb/calculadora/cysteraCalculo/", "verResultado");
			Double res = client.getResult(n,s).getResult();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
