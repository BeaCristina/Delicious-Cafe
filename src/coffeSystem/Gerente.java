package coffeSystem;

public class Gerente extends Funcionario{
	
	Gerente(String senha) {
		super(senha);
		// TODO Auto-generated constructor stub
	}
	public void setLogin(String log) {
		banco.conectar();
		banco.alteraUsuario("login", getLogin(), log);
		banco.desconectar();
	}
	public void setSenha(String senha) {
		banco.conectar();
		banco.alteraUsuario("senha", getSenha(), senha);
		banco.desconectar();
	}
	public void setNome(String nome) {
		banco.conectar();
		banco.alteraUsuario("nome", getNome(), nome);
		banco.desconectar();
	}	
	public void setNascimento(String nascimento) {
		banco.conectar();
		banco.alteraUsuario("nascimento", getNascimento(), nascimento);
		banco.desconectar();
	}
	public void setCpf(String cpf) {
		banco.conectar();
		banco.alteraUsuario("cpf", getCpf(), cpf);
		banco.desconectar();
	}
	
	
}
