package coffeSystem;

import coffeSystem.BancoDeDados;

public class BancoAcess {
	

	
	public static void main(String[] arg){
		BancoDeDados banco = new BancoDeDados();
		banco.conectar();
		if(banco.estaConectado()){
			//banco.inserirUsuario("Bibi", "bia321", "g");
			
			banco.desconectar();
		}else{
			System.out.println("não conseguiu conexão com o bancod de dados");
		}
	}
}