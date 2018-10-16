package coffeSystem;


public class Pessoa {
	
	private String nome;
	private String nascimento;
	private String cpf;
	private String senha;
	
	BancoDeDados banco = new BancoDeDados();
	
	Pessoa(String senha){

		this.senha = senha;
		banco.conectar();
		getNome();
		getNascimento();
		getCpf();
		

	}
	
	public String getNome() {
		banco.conectar();
		this.nome = banco.pegaDeFuncionarioS(this.senha, "nome");
		banco.desconectar();
		return this.nome;
	}
	
	public String getNascimento() {
		banco.conectar();
		this.nascimento=banco.pegaDeFuncionarioS(this.senha, "nascimento");
		banco.desconectar();
		return this.nascimento;
	}

	
	public String getCpf() {
		banco.conectar();
		this.cpf = banco.pegaDeFuncionarioS(this.senha, "cpf");
		banco.desconectar();
		return this.cpf;
	}
	
}
