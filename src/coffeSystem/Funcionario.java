package coffeSystem;


public class Funcionario extends Pessoa{
	
	Funcionario(String senha) {
		super(senha);
		this.senha = senha;
		banco.conectar();
		getMatricula();
		getDataInclusao();
		getTipo();
		banco.desconectar();

		
	}
	private String matricula;
	private String dataInclusao;
	private String login;
	private String senha;
	private String tipo;
	
	
	//Get
	
	public String getMatricula() {
		banco.conectar();
		this.matricula=banco.pegaDeFuncionarioS(this.senha, "matricula");
		banco.desconectar();
		return this.matricula;
	}

	public String getDataInclusao() {
		banco.conectar();
		this.dataInclusao=banco.pegaDeFuncionarioS(this.senha, "dataInclusao");
		banco.desconectar();
		return this.dataInclusao;
	}

	public String getLogin() {
		banco.conectar();
		this.login=banco.pegaDeFuncionarioS(this.senha, "login");
		banco.desconectar();
		return this.login;
	}

	public String getSenha() {
		
		return this.senha;
	}
	public String getTipo() {
		banco.conectar();
		this.tipo=banco.pegaDeFuncionarioS(this.senha, "tipo");
		banco.desconectar();
		
		return this.tipo;
	}	
	
}
