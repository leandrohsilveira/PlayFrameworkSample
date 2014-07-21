package enums;

public enum PapelEnum {

	ADMINISTRADOR("Administrador"),USUARIO("Usuário");
	
	private PapelEnum(String rotulo) {
		this.rotulo = rotulo;
	}

	private String rotulo;

	public String getRotulo() {
		return rotulo;
	}
	
}
