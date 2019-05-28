package test;

import static org.junit.Assert.*;

import org.junit.Test;

import co.com.cyxtera.enums.OperacionesEnum;
import co.com.cyxtera.servicio.CyxteraServicio;

import org.junit.Assert;

public class FacturacionTest {

	@Test
	public void test() {
		try {
			System.out.println("Prueba");

			CyxteraServicio cyxteraServicio = (CyxteraServicio) javax.ejb.embeddable.EJBContainer.createEJBContainer()
					.getContext()
					.lookup("java:global/Cyxtera-ear-0.0.1-SNAPSHOT/CyxteraWeb-0.0.1-SNAPSHOT/CyxteraServicio!co.com.cyxtera.servicio.CyxteraServicio");

			String res = null;
			res = cyxteraServicio.seleccionOperacion(OperacionesEnum.RESTA.getNombre());
			Long acutal = 2L;
			assertEquals(acutal, Long.valueOf(res));
			fail("NO ES EL DATO ESPERADO");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
