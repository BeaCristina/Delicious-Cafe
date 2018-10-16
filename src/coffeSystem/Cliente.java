package coffeSystem;

public class Cliente extends Pessoa{
	
	Cliente(String senha) {
		super(senha);
		// TODO Auto-generated constructor stub
	}

	protected String dataCadastro;

	
	
	
	
	
	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
}
