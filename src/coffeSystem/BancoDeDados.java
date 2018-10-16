package coffeSystem;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class BancoDeDados {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	
	public void conectar(){
		String servidor = "jdbc:mysql://localhost:3306/cafe";
		String usuario = "root";
		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		
		try{
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
			System.out.println("Conectou");
		} catch (Exception e){
			System.out.println("Erro: "+e.getMessage());
			System.out.print("Deu ruim...");
		
		}
	}
	
	public boolean estaConectado(){
		if(this.connection != null){
			return true;
		} else {
			return false;
		}
	}
	
	
	//PRODUTOS
	
	
	public String pegaProdutos(String dado, String cod){
		try{
			String query="SELECT * FROM produto WHERE codProduto = '"+cod+"'";
			this.resultset = this.statement.executeQuery(query);
			this.resultset.next();
			return this.resultset.getString(dado);
		} catch (Exception e){
			System.out.println("Não deu prapegar o "+dado+" de Funcionario \nErro: "+e);
		}
		return "0";
	}
	
	public String[] pegaCods(){
		try{
			int count = 0;
			String query = "SELECT * FROM produto ORDER BY codProduto";
			this.resultset = this.statement.executeQuery(query);
			while(this.resultset.next()){
				count++;
			}
			this.resultset = this.statement.executeQuery(query);
			String  cod[] = new String[count];
			for(count=0; this.resultset.next(); count++){
				cod[count]=this.resultset.getString("codProduto");
			}
			return cod;
		}catch(Exception e){
			System.out.println("Erro: "+ e.getMessage());
			System.out.println("Listar produtos não deu bom....");
		}
		return null;
	}
	
	
	public void inserirProduto(String cod, String nom, String desc, double val){
		try{
			String query="INSERT INTO produto VALUES('"+cod+"', '"+nom+"', '"+desc+"', '"+val+"');";
			this.statement.executeUpdate(query);
			System.out.println("Inseriu o produto: "+query);
		} catch (Exception e ) {
			System.out.println("Erro: "+e);
			System.out.println("Não deu pra inserir");
		}
	}
	
	public void editarProduto(String cod, String nom, String desc, double val){
		try{
			System.out.print("Alterou "+cod+", "+nom+", "+desc+", "+val);
			String query = "UPDATE produto SET codProduto = '"+cod+"', nomeProduto = '"+nom+"', descProduto = '"+desc+"', valorProduto = '"+val+"'where codProduto = "+cod+";";
			this.statement.executeUpdate(query);
			System.out.println(" para: "+cod+", "+nom+", "+desc+", "+val);
		} catch (Exception e) {
			System.out.println("Não deu pra editar\n Erro: "+e.getMessage());
		}
	}

	public void apagarProduto(String cod){
		try{
			String query = "DELETE FROM produto WHERE codProduto = '"+cod+"';";
			this.statement.executeUpdate(query);
			System.out.println("Apagou: "+cod);
		} catch (Exception e){
			System.out.println("Não deu para apagar\nErro: "+e.getMessage());
		}
		
	}
	
	//Funcionario
	
	public void inserirUsuario(String login, String senha, String nome, String matricula, String cpf, String nasc){
		try{
			String query="INSERT INTO usuario VALUES('f', '"+login+"', '"+senha+"', '"+nome+"', '"+matricula+"', default, '"+cpf+"', '"+nasc+"');";
			this.statement.executeUpdate(query);
			System.out.println("Inseriu o funcionario: "+query);
		} catch (Exception e ) {
			System.out.println("Erro: "+e);
			System.out.println("Não deu pra inserir");
		}
		
	}
		
	public void alteraUsuario(String atributo, String matricula, String novoDado){
		try{
			String query = "UPDATE usuario SET "+atributo+" = '"+novoDado+"'where matricula = "+matricula+";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Não deu pra editar\n Erro: "+e.getMessage());
		}
	}
	
	public void deletaUsuario(String mat){
		try{
			String query = "DELETE FROM usuario WHERE mat = '"+mat+"';";
			this.statement.executeUpdate(query);
		} catch (Exception e){
			System.out.println("Não deu para apagar\nErro: "+e.getMessage());
		}
		
	}
	
	public boolean confirmaUsuario( String senha){
		try{
			String query = "SELECT * FROM usuario WHERE senha = '"+senha+"';";
			this.resultset = this.statement.executeQuery(query);
			this.resultset.next();
				if(this.resultset.getString("senha").equals(senha)){
					return true;
				}
		
		}catch(Exception e){
			System.out.print("Erro: "+ e.getMessage());
			System.out.print("\nConfirmar usuário não deu bom....");
		}
		return false;
	}

	public String pegaDeFuncionario(String mat, String dado){
		try{
			String query="SELECT * FROM usuario WHERE matricula = '"+mat+"'";
			this.resultset = this.statement.executeQuery(query);
			this.resultset.next();
			return this.resultset.getString(dado);
		} catch (Exception e){
			System.out.println("Não deu prapegar o "+dado+" de Funcionario \nErro: "+e);
		}
		return "0";
	}
	
	public String pegaDeFuncionarioS(String senha, String dado){
		try{
			String query="SELECT * FROM usuario WHERE senha = '"+senha+"'";
			this.resultset = this.statement.executeQuery(query);
			this.resultset.next();
			return this.resultset.getString(dado);
		} catch (Exception e){
			System.out.println("Não deu prapegar o "+dado+" de Funcionario \nErro: "+e);
		}
		return "0";
	}
	
	public String[] pegaDado(String dado){
		try{
			int count = 0;
			String query = "SELECT * FROM usuario ORDER BY nome";
			this.resultset = this.statement.executeQuery(query);
			while(this.resultset.next()){
				count++;
			}
			this.resultset = this.statement.executeQuery(query);
			String  cod[] = new String[count];
			for(count=0; this.resultset.next(); count++){
				cod[count]=this.resultset.getString(dado);
			}
			return cod;
		}catch(Exception e){
			System.out.println("Erro: "+ e.getMessage());
			System.out.println("Não pôde pegar o "+dado);
		}
		return null;
	}
	
	//Vendas
	public void inserirVenda(String val, String login){
		try{
			String query="INSERT INTO venda VALUES(default, '"+val+"', '"+login+"', default);";
			this.statement.executeUpdate(query);
			System.out.println("Inseriu o venda: "+query);
		} catch (Exception e ) {
			System.out.println("Erro: "+e);
			System.out.println("Não deu pra inserir a venda");
		}
	}
	
	public String[] pegaCodVendas(){
		try{
			int count = 0;
			String query = "SELECT * FROM venda";
			this.resultset = this.statement.executeQuery(query);
			while(this.resultset.next()){
				count++;
			}
			this.resultset = this.statement.executeQuery(query);
			String  cod[] = new String[count];
			for(count=0; this.resultset.next(); count++){
				cod[count]=this.resultset.getString("cod");
			}
			return cod;
		}catch(Exception e){
			System.out.println("Erro: "+ e.getMessage());
			System.out.println("Não pôde pegar o codigo");
		}
		return null;
	}
	public String pegaDeVendas(String dado, String cod){
		try{
			String query="SELECT * FROM venda WHERE cod = '"+cod+"'";
			this.resultset = this.statement.executeQuery(query);
			this.resultset.next();
			return this.resultset.getString(dado);
		} catch (Exception e){
			System.out.println("Não deu prapegar o "+dado+" de Venda \nErro: "+e);
		}
		return "0";
	}
	
	public String[] pegaCodVenOperador(String login){
		try{
			int count = 0;
			String query = "SELECT * FROM venda WHERE func = '"+login+"'";
			this.resultset = this.statement.executeQuery(query);
			while(this.resultset.next()){
				count++;
			}
			this.resultset = this.statement.executeQuery(query);
			String  cod[] = new String[count];
			for(count=0; this.resultset.next(); count++){
				cod[count]=this.resultset.getString("cod");
			}
			return cod;
		}catch(Exception e){
			System.out.println("Erro: "+ e.getMessage());
			System.out.println("Não pôde pegar o codigo");
		}
		return null;
	}
	public void desconectar(){
		try{
			this.connection.close();
			System.out.println("xau");
		}catch(Exception e){
			System.out.println("Não foi possivel desconectar\nErro: "+e.getMessage());
		}
	}

}
