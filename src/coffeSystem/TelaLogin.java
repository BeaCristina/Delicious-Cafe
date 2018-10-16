package coffeSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class TelaLogin extends JFrame implements ActionListener{
	
	//Objetos
		Font roboto18 = new Font("Roboto Condensed", Font.BOLD, 18);
		Font arial12 = new Font("Arial", Font.PLAIN, 12);
		ImageIcon logo = new ImageIcon(getClass().getResource("FundoLogin.jpg"));
		JLabel lblLogo = new JLabel(logo);
		JLabel lblLogin = new JLabel("Login:");  
		JLabel lblSenha = new JLabel("Senha:");
		JTextField txtLogin = new JTextField();
		JTextField txtSenha = new JTextField();
		JButton btnOk = new JButton("OK");
		Funcionario func;
		Gerente ger;
		
	//Variáveis
		private String senha;
		private String login;
		private String tipo;
	
		
		
	public TelaLogin(){
		
	//Janela
		setLayout(null);  
		setTitle("Delicious Café");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	//BoxText
		add(txtLogin);
		txtLogin.setBounds(95, 246, 400, 25);
		txtLogin.setFont(arial12);
		add(txtSenha);
		txtSenha.setBounds(95, 332, 400, 25);
		txtSenha.setFont(arial12);
	//Botão
		add(btnOk);
		btnOk.setBounds(424, 370, 60, 30);
		btnOk.setFont(arial12);
		btnOk.addActionListener(this);
	//Labels
		add(lblLogin); 
		lblLogin.setBounds(95, 185, 100, 100);
		lblLogin.setFont(roboto18);
		add(lblSenha);
		lblSenha.setBounds(95, 270, 100, 100);
		lblSenha.setFont(roboto18);
		add(lblLogo);
		lblLogo.setBounds(0, 0, 600,500);
}
	@Override
	public void actionPerformed(ActionEvent e){
		this.login=txtLogin.getText();
		this.senha=txtSenha.getText();
		validaLogin();
	}
	
	public void validaLogin(){
		BancoDeDados banco= new BancoDeDados();
		banco.conectar();
		if(banco.confirmaUsuario(this.senha)){
			banco.conectar();
			if(banco.pegaDeFuncionarioS(this.senha, "tipo").equals("f")){
				func = new Funcionario(senha);
				new TelaFuncionario(func);
				dispose();
			} else if(banco.pegaDeFuncionarioS(this.senha, "tipo").equals("g")){
				ger = new Gerente(this.senha);
				new TelaGerente(ger);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Algum dado errado no BD");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Usuário Inválido");
		}
		banco.desconectar();
	}
}
