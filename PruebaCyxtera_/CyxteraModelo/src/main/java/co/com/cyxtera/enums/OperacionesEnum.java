package co.com.cyxtera.enums;

public enum OperacionesEnum {
	
	SUMA(1,"SUMA"),
	RESTA(2,"RESTA"),
	MULTIPLICACION(3,"MULTIPLICACION"),
	DIVISION(4,"DIVISION"),
	POTENCIACION(5,"POTENCIACION");
	
	
	private Integer id;
	private String nombre;
	
	private OperacionesEnum(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
}
