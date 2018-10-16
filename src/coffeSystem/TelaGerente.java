package coffeSystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TelaGerente extends JFrame{
		BancoDeDados ban = new BancoDeDados();
		JMenuBar menu = new JMenuBar();
		ImageIcon logo = new ImageIcon(getClass().getResource("FundoLogado.jpg"));
		JMenu funcionario = new JMenu("Funcionário");
		JMenu relat = new JMenu("Relatórios");
		JMenu produto = new JMenu("Produto");
		JMenu sair = new JMenu("sair");
		JMenuItem listaFuncs = new JMenuItem("Listar Operadores");
		JMenuItem adiciona = new JMenuItem("Adicionar Operador");
		JMenuItem apaga = new JMenuItem("Deletar Operador");
		JMenuItem edita = new JMenuItem("Editar Operador");
		JMenuItem perfil = new JMenuItem("Perfil");
		JMenuItem venda = new JMenuItem("Total de venda");
		JMenuItem vendasFunc = new JMenuItem("Vendas por Operador");
		JMenuItem listarProdutos = new JMenuItem("Listar produtos");
		JMenuItem adcProduto = new JMenuItem("Adicionar Produto");
		JMenuItem editarProdutos = new JMenuItem("Editar produtos");
		JMenuItem delProduto = new JMenuItem("Excluir Produto");
		JMenuItem exit = new JMenuItem("Exit");
		JLabel lblBemvindo = new JLabel();
		JLabel lblLogo = new JLabel(logo);
		JLabel lblsair = new JLabel("| Sair");
		Funcionario func;
		Font gabriola18 = new Font("Roboto Condensed", Font.PLAIN, 18);
		Font gabriola12 = new Font("Roboto Condensed", Font.BOLD, 12);
	
	TelaGerente(Funcionario f){
	
		this.func=f;
		
		setLayout(null);  
		setTitle("Sys Delicious Café");
		setSize(900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Label
		add(lblsair);
		lblsair.setBounds(800, 450, 100, 300);
		add(lblBemvindo);
		lblBemvindo.setFont(gabriola18);
		lblBemvindo.setText("Olá, "+func.getNome());
		lblBemvindo.setBounds(750, 0, 160,30);
		add(lblLogo);
		lblLogo.setBounds(0, 30, 900,620);
		//Menu
		add(menu);
		menu.setBounds(0, 0, 900, 30);
		menu.setFont(gabriola12);
		menu.add(funcionario);
		//menu.add(consulta);
		menu.add(relat);
		menu.add(produto);
		menu.add(sair);
		funcionario.add(listaFuncs);
		funcionario.add(adiciona);
		funcionario.add(edita);
		funcionario.add(apaga);
		funcionario.add(perfil);
		relat.add(venda);
		relat.add(vendasFunc);
		produto.add(listarProdutos);
		produto.add(adcProduto);
		produto.add(editarProdutos);
		produto.add(delProduto);
		sair.add(exit);
		//JPanel
		
//=================   LISTAR PRODUTOS ====================================================================================================		
		listarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			//Objetos
			ban.conectar();		
			
			JFrame lisProdutos = new JFrame();
			ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
			Font roboto20 = new Font("Roboto Condensed", Font.PLAIN, 20);
			Font roboto12 = new Font("Roboto Condensed", Font.BOLD, 12);
			Font roboto18 = new Font("Roboto Condensed", Font.PLAIN, 18);
			String [] cods=ban.pegaCods(); //Define o tamanho dos LabelsArrays
			JLabel lblTitulo = new JLabel("Listagem de Produtos");
			JLabel lvlSubTit = new JLabel("Código      Produto                              Descrição                                                                Valor");
			JLabel cod[] = new JLabel[cods.length];
			JLabel prod[] = new JLabel[cods.length];
			JLabel desc[] = new JLabel[cods.length];
			JLabel val[] = new JLabel[cods.length];
			//Layout
			lisProdutos.setLayout(null);  
			lisProdutos.setTitle("Produtos");
			lisProdutos.setSize(700, 500);
			lisProdutos.setLocationRelativeTo(null);
			lisProdutos.setResizable(false);
			lisProdutos.setVisible(true);
			lblTitulo.setFont(roboto20);
			lvlSubTit.setFont(roboto18);
			lisProdutos.add(lblTitulo);
			lisProdutos.add(lvlSubTit);
			lblTitulo.setBounds(30 , 30, 300, 30);
			lvlSubTit.setBounds(30, 90, 620, 30);

			int t = 120;//Define a altura das Labels
			
			for(int i=0;i< cods.length; i++){	
				cod[i] = new JLabel(ban.pegaProdutos("codProduto", cods[i]));
				lisProdutos.add(cod[i]);
				cod[i].setBounds(40 , t, 100, 30);
				prod[i] = new JLabel(ban.pegaProdutos("nomeProduto", cods[i]));
				lisProdutos.add(prod[i]);
				prod[i].setBounds(100 , t, 300, 30);
				desc[i] = new JLabel(ban.pegaProdutos("descProduto", cods[i]));
				lisProdutos.add(desc[i]);
				desc[i].setBounds(280 , t, 300, 30);
				val[i] = new JLabel("R$"+ban.pegaProdutos("valorProduto", cods[i]));
				lisProdutos.add(val[i]);
				val[i].setBounds(610 , t, 300, 30);
				prod[i].setFont(roboto12);
				desc[i].setFont(roboto12);
				val[i].setFont(roboto12);
				cod[i].setFont(roboto12);
				t=t+30;
			}
			ban.desconectar();
			}
		});
//=================   ADICIONAR PRODUTOS ====================================================================================================	
		adcProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Objetos
				JFrame jfAdcProd = new JFrame();
				JTextField txtcod = new JTextField();
				JTextField txtNomProd = new JTextField();
				JTextField txtDescProd = new JTextField();
				JTextField txtValProd = new JTextField();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoInsere.jpg"));
				JLabel lblTitulo = new JLabel("Adicionar Produtos");
				JLabel lblCod = new JLabel("CÓDIGO");
				JLabel lblNome = new JLabel("NOME");
				JLabel lblDesc = new JLabel("DESCRIÇÃO");
				JLabel lblVal = new JLabel("VALOR");
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				JButton btnOk = new JButton("OK");
			
				//Layout
				jfAdcProd.setLayout(null);  
				jfAdcProd.setTitle("Adicionar Produto");
				jfAdcProd.setSize(700, 500);
				jfAdcProd.setLocationRelativeTo(null);
				jfAdcProd.setResizable(false);
				jfAdcProd.setVisible(true);
				
				lblTitulo.setFont(roboto22);
				lblCod.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblDesc.setFont(roboto15);
				lblVal.setFont(roboto15);
				txtcod.setFont(roboto12);
				txtNomProd.setFont(roboto12);
				txtDescProd.setFont(roboto12);
				txtValProd.setFont(roboto12);
				btnOk.setFont(roboto15);
				
				lblTitulo.setBounds(55, 45, 200, 30);
				lblCod.setBounds(55, 125, 100, 20);
				lblNome.setBounds(190, 125, 70, 20);
				lblDesc.setBounds(55, 230, 100, 20);
				lblVal.setBounds(55, 350, 70, 20);
				lblFundo.setBounds(0,0,700,500);
				txtcod.setBounds(55, 155, 80, 20);
				txtNomProd.setBounds(190, 155, 265, 20);
				txtDescProd.setBounds(55, 260, 400, 20);
				txtValProd.setBounds(55, 380, 180, 20);
				btnOk.setBounds(455, 370, 60, 30);
				
				jfAdcProd.add(lblTitulo);
				jfAdcProd.add(lblCod);
				jfAdcProd.add(lblNome);
				jfAdcProd.add(lblDesc);
				jfAdcProd.add(lblVal);
				jfAdcProd.add(txtcod);
				jfAdcProd.add(txtNomProd);
				jfAdcProd.add(txtDescProd);
				jfAdcProd.add(txtValProd);
				jfAdcProd.add(btnOk);
				jfAdcProd.add(lblFundo);
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						
						String cod, nom, desc, valor;
						double val;
						cod = txtcod.getText();
						nom = txtNomProd.getText();
						desc = txtDescProd.getText();
						valor = txtValProd.getText();
						valor = valor.replace(",", ".");
						if((cod.equals("")) || (nom.equals("")) || (desc.equals("")) ||valor.equals("")){
							JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
							System.out.println(valor);
						} else {
							ban.conectar();
							val = Double.parseDouble(valor);
							ban.inserirProduto(cod, nom, desc, val);
							ban.desconectar();
						}
						
						
					}
				});
			}
		});
//=================   EDITAR PRODUTOS ====================================================================================================	
		editarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Objetos
				JFrame jfEdtProd = new JFrame();
				JComboBox<String> boxCod = new JComboBox();
				JTextField txtNomProd = new JTextField();
				JTextField txtDescProd = new JTextField();
				JTextField txtValProd = new JTextField();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoInsere.jpg"));
				JLabel lblTitulo = new JLabel("Editar Produtos");
				JLabel lblCod = new JLabel("INSIRA CÓDIGO");
				JLabel lblNome = new JLabel("NOME");
				JLabel lblDesc = new JLabel("DESCRIÇÃO");
				JLabel lblVal = new JLabel("VALOR");
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				JButton btnOk = new JButton("OK");
				ban.conectar();
				String cods[] = ban.pegaCods(); //Pega todos os códigos
				
				//Layout
				jfEdtProd.setLayout(null);  
				jfEdtProd.setTitle("Adicionar Produto");
				jfEdtProd.setSize(700, 500);
				jfEdtProd.setLocationRelativeTo(null);
				jfEdtProd.setResizable(false);
				jfEdtProd.setVisible(true);


				lblTitulo.setFont(roboto22);
				lblCod.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblDesc.setFont(roboto15);
				lblVal.setFont(roboto15);
				boxCod.setFont(roboto12);
				txtNomProd.setFont(roboto12);
				txtDescProd.setFont(roboto12);
				txtValProd.setFont(roboto12);
				btnOk.setFont(roboto15);
				
				lblTitulo.setBounds(55, 35, 200, 30);
				lblCod.setBounds(55, 105, 150, 20);
				lblNome.setBounds(55, 190, 70, 20);
				lblDesc.setBounds(55, 270, 100, 20);
				lblVal.setBounds(55, 350, 70, 20);
				lblFundo.setBounds(0,0,700,500);
				boxCod.setBounds(55, 135, 150, 20);
				txtNomProd.setBounds(55, 210, 400, 20);
				txtDescProd.setBounds(55, 300, 400, 20);
				txtValProd.setBounds(55, 380, 180, 20);
				btnOk.setBounds(395, 370, 60, 30);
						
				jfEdtProd.add(lblTitulo);
				jfEdtProd.add(lblCod);
				jfEdtProd.add(lblNome);
				jfEdtProd.add(lblDesc);
				jfEdtProd.add(lblVal);
				jfEdtProd.add(txtNomProd);
				jfEdtProd.add(txtDescProd);
				jfEdtProd.add(txtValProd);
				jfEdtProd.add(btnOk);
				jfEdtProd.add(boxCod);
				jfEdtProd.add(lblFundo);
				
				for(int i =0; i < cods.length; i++){ //Inicia a ComboBox
					boxCod.addItem(cods[i]);
				}
				String codBox; //Pega o dado selecionado do ComboBox
				codBox = String.valueOf(boxCod.getSelectedItem());
				txtNomProd.setText(ban.pegaProdutos("nomeProduto", codBox));
				txtDescProd.setText(ban.pegaProdutos("descProduto", codBox));
				txtValProd.setText(ban.pegaProdutos("valorProduto", codBox));
				ban.desconectar();
				
				//Ação da ComboBox
				boxCod.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						String codBox; //Pega o dado selecionado do ComboBox
						if(e.getStateChange() == ItemEvent.SELECTED){
							ban.conectar();
							codBox = String.valueOf(boxCod.getSelectedItem());
							txtNomProd.setText(ban.pegaProdutos("nomeProduto", codBox));
							txtDescProd.setText(ban.pegaProdutos("descProduto", codBox));
							txtValProd.setText(String.valueOf(ban.pegaProdutos("valorProduto", codBox)));
							ban.desconectar();
						}
					}
				});
				
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						
						String cod, nom, desc, valor;
						double val;
						nom = txtNomProd.getText();
						desc = txtDescProd.getText();
						valor = txtValProd.getText();
						valor = valor.replace(",", ".");
				
						if((nom.equals("")) || (desc.equals("")) ||valor.equals("")){
							JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
							System.out.println(valor);
						} else {
							ban.conectar();
							val = Double.parseDouble(valor);
							ban.editarProduto(String.valueOf(boxCod.getSelectedItem()), nom, desc, val);
							ban.desconectar();
						}
						
						
					}
				});
			}
		});
//=================   DELETAR PRODUTOS ====================================================================================================	
		delProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Objetos
				JFrame jfExcProd = new JFrame();
				JComboBox<String> boxCod = new JComboBox();
				JLabel lblNomProd = new JLabel();
				JLabel lblDescProd = new JLabel();
				JLabel lblValProd = new JLabel();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoInsere.jpg"));
				JLabel lblTitulo = new JLabel("Apagar Produto:");
				JLabel lblCod = new JLabel("INSIRA CÓDIGO");
				JLabel lblNome = new JLabel("NOME");
				JLabel lblDesc = new JLabel("DESCRIÇÃO");
				JLabel lblVal = new JLabel("VALOR");
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				JButton btnOk = new JButton("Apagar");
				ban.conectar();
				String cods[] = ban.pegaCods(); //Pega todos os códigos
				
				//Layout
				jfExcProd.setLayout(null);  
				jfExcProd.setTitle("Adicionar Produto");
				jfExcProd.setSize(700, 500);
				jfExcProd.setLocationRelativeTo(null);
				jfExcProd.setResizable(false);
				jfExcProd.setVisible(true);

				lblTitulo.setFont(roboto22);
				lblCod.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblDesc.setFont(roboto15);
				lblVal.setFont(roboto15);
				boxCod.setFont(roboto12);
				lblNomProd.setFont(roboto12);
				lblDescProd.setFont(roboto12);
				lblValProd.setFont(roboto12);
				btnOk.setFont(roboto15);
				
				lblTitulo.setBounds(55, 35, 200, 30);
				lblCod.setBounds(55, 105, 150, 20);
				lblNome.setBounds(55, 190, 70, 20);
				lblDesc.setBounds(55, 270, 100, 20);
				lblVal.setBounds(55, 350, 70, 20);
				lblFundo.setBounds(0,0,700,500);
				boxCod.setBounds(55, 135, 150, 20);
				lblNomProd.setBounds(55, 210, 400, 20);
				lblDescProd.setBounds(55, 300, 400, 20);
				lblValProd.setBounds(55, 380, 180, 20);
				btnOk.setBounds(385, 370, 100, 30);
						
				jfExcProd.add(lblTitulo);
				jfExcProd.add(lblCod);
				jfExcProd.add(lblNome);
				jfExcProd.add(lblDesc);
				jfExcProd.add(lblVal);
				jfExcProd.add(lblNomProd);
				jfExcProd.add(lblDescProd);
				jfExcProd.add(lblValProd);
				jfExcProd.add(btnOk);
				jfExcProd.add(boxCod);
				jfExcProd.add(lblFundo);
				
				for(int i =0; i < cods.length; i++){ //Inicia a ComboBox
					boxCod.addItem(cods[i]);
				}
				String codBox; //Pega o dado selecionado do ComboBox
				codBox = String.valueOf(boxCod.getSelectedItem());
				lblNomProd.setText(ban.pegaProdutos("nomeProduto", codBox));
				lblDescProd.setText(ban.pegaProdutos("descProduto", codBox));
				lblValProd.setText(ban.pegaProdutos("valorProduto", codBox));
				ban.desconectar();
				
				//Ação da ComboBox
				boxCod.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						String codBox; //Pega o dado selecionado do ComboBox
						if(e.getStateChange() == ItemEvent.SELECTED){
							ban.conectar();
							codBox = String.valueOf(boxCod.getSelectedItem());
							lblNomProd.setText(ban.pegaProdutos("nomeProduto", codBox));
							lblDescProd.setText(ban.pegaProdutos("descProduto", codBox));
							lblValProd.setText(String.valueOf(ban.pegaProdutos("valorProduto", codBox)));
							ban.desconectar();
						}
					}
				});
				
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						ban.conectar();
						ban.apagarProduto(String.valueOf(boxCod.getSelectedItem()));
						ban.desconectar();
						jfExcProd.dispose();
					}
				});
			}
		});
//=================   LISTAR OPERADORES ====================================================================================================	
		listaFuncs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFrame lisFunc = new JFrame();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				Font roboto20 = new Font("Roboto Condensed", Font.PLAIN, 20);
				Font roboto12 = new Font("Roboto Condensed", Font.BOLD, 12);
				Font roboto15 = new Font("Roboto Condensed", Font.PLAIN, 15);
				ban.conectar();
				String [] senhas=ban.pegaDado("senha"); //Define o tamanho dos LabelsArrays
				ban.desconectar();
				JLabel lblTitulo = new JLabel("Listagem de Operadores");
				JLabel lvlSubTit = new JLabel("Matrícula       Nome                                                         CPF                        Data de Inclusão         Nacimento");
				JLabel nome[] = new JLabel[senhas.length];
				JLabel matricula[] = new JLabel[senhas.length];
				JLabel dataInclusao[] = new JLabel[senhas.length];
				JLabel cpf[] = new JLabel[senhas.length];
				JLabel nasc[] = new JLabel[senhas.length];
				JLabel lblFundo = new JLabel(fundo);
				
				//Layout
				lisFunc.setLayout(null);  
				lisFunc.setTitle("Produtos");
				lisFunc.setSize(700, 500);
				lisFunc.setLocationRelativeTo(null);
				lisFunc.setResizable(false);
				lisFunc.setVisible(true);
				lblTitulo.setFont(roboto20);
				lvlSubTit.setFont(roboto15);
				lisFunc.add(lblTitulo);
				lisFunc.add(lvlSubTit);
				lblTitulo.setBounds(30 , 30, 300, 30);
				lvlSubTit.setBounds(20, 90, 600, 30);
				lblFundo.setBounds(0, 0, 700, 500);
				
				int t = 120;//Define a altura das Labels
				
				for(int i=0;i< senhas.length; i++){	
					ban.conectar();
					matricula[i] = new JLabel(ban.pegaDeFuncionarioS(senhas[i], "matricula"));
					lisFunc.add(matricula[i]);
					matricula[i].setBounds(40 , t, 100, 30);
					nome[i] = new JLabel(ban.pegaDeFuncionarioS(senhas[i], "nome"));
					lisFunc.add(nome[i]);
					nome[i].setBounds(100 , t, 300, 30);
					cpf[i] = new JLabel(ban.pegaDeFuncionarioS(senhas[i], "cpf"));
					lisFunc.add(cpf[i]);
					cpf[i].setBounds(300 , t, 300, 30);
					dataInclusao[i] = new JLabel(ban.pegaDeFuncionarioS(senhas[i], "dataInclusao"));
					lisFunc.add(dataInclusao[i]);
					dataInclusao[i].setBounds(400 , t, 80, 30);
					nasc[i] = new JLabel(ban.pegaDeFuncionarioS(senhas[i], "nascimento"));
					lisFunc.add(nasc[i]);
					nasc[i].setBounds(530 , t, 150, 30);
					
					matricula[i].setFont(roboto12);
					nome[i].setFont(roboto12);
					cpf[i].setFont(roboto12);
					dataInclusao[i].setFont(roboto12);
					nasc[i].setFont(roboto12);
					
					t=t+30;
					ban.desconectar();
				}
				lisFunc.add(lblFundo);
				
			}
		});
//=================   ADICIONAR OPERADORES ====================================================================================================	
		adiciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Objetos
				JFrame jfAdcFunc = new JFrame();
				JTextField txtNome = new JTextField();
				JTextField txtMatricula = new JTextField();
				JTextField txtCpf = new JTextField();
				JTextField txtNasc = new JTextField();
				JTextField txtLogin = new JTextField();
				JTextField txtSenha = new JTextField();
				JTextField txtMat = new JTextField();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				JLabel lblTitulo = new JLabel("Adicionar Operador");
				JLabel lblNome = new JLabel("NOME");
				JLabel lblMatricula = new JLabel("MATRÍCULA");
				JLabel lblCpf = new JLabel("CPF");
				JLabel lblLogin = new JLabel("Login do Operador");
				JLabel lblSenha = new JLabel("Senha");
				JLabel lblNasc = new JLabel("Nascimento (aaaa-mm-dd)");
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				JButton btnOk = new JButton("Adiciona");
				//Layout
				jfAdcFunc.setLayout(null);  
				jfAdcFunc.setTitle("Adicionar Produto");
				jfAdcFunc.setSize(700, 500);
				jfAdcFunc.setLocationRelativeTo(null);
				jfAdcFunc.setResizable(false);
				jfAdcFunc.setVisible(true);
				lblTitulo.setFont(roboto22);
				lblMatricula.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblCpf.setFont(roboto15);
				lblLogin.setFont(roboto15);
				lblSenha.setFont(roboto15);
				lblNasc.setFont(roboto15);
				txtSenha.setFont(roboto12);
				txtNome.setFont(roboto12);
				txtMatricula.setFont(roboto12);
				txtMat.setFont(roboto12);
				txtCpf.setFont(roboto12);
				txtLogin.setFont(roboto12);
				txtNasc.setFont(roboto12);
				btnOk.setFont(roboto15);
				
				lblTitulo.setBounds(55, 45, 200, 30);
				lblMatricula.setBounds(430, 240, 250, 30);
				lblNome.setBounds(55, 95, 70, 30);
				lblCpf.setBounds(55, 160, 100, 30);
				lblNasc.setBounds(430, 160, 250, 30);
				lblLogin.setBounds(55, 235, 150, 30);
				lblSenha.setBounds(55, 310, 100, 30);
				lblFundo.setBounds(0,0,700,500);
				txtNome.setBounds(55, 125, 500, 20);
				txtMat.setBounds(430, 270, 180, 20);
				txtCpf.setBounds(55, 190, 290, 20);
				txtNasc.setBounds(430, 190, 180, 20);
				txtLogin.setBounds(55, 270, 250, 20);
				txtSenha.setBounds(55, 345, 250, 20);
				btnOk.setBounds(455, 350, 120, 30);
				
				jfAdcFunc.add(txtMat);
				jfAdcFunc.add(lblTitulo);
				jfAdcFunc.add(lblNome);
				jfAdcFunc.add(lblMatricula);
				jfAdcFunc.add(lblCpf);
				jfAdcFunc.add(lblNasc);
				jfAdcFunc.add(lblLogin);
				jfAdcFunc.add(lblSenha);
				jfAdcFunc.add(txtNome);
				jfAdcFunc.add(txtMatricula);
				jfAdcFunc.add(txtCpf);
				jfAdcFunc.add(txtNasc);
				jfAdcFunc.add(txtLogin);
				jfAdcFunc.add(txtSenha);
				jfAdcFunc.add(btnOk);
				jfAdcFunc.add(lblFundo);
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						String nome, cpf, login, nasc, senha, mat;
						nome = txtNome.getText();
						cpf = txtCpf.getText();
						login = txtLogin.getText();
						nasc = txtNasc.getText();		
						senha = txtSenha.getText();	
						mat = txtMat.getText();	
						if((nome.equals("")) || (cpf.equals("")) ||login.equals("")||nasc.equals("")||senha.equals("")||mat.equals("")){
							JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
						} else {
							ban.conectar();
							ban.inserirUsuario(login, senha, nome, mat, cpf, nasc);
							JOptionPane.showMessageDialog(null, nome+" foi adicionado!!");
							txtNome.setText("");
							txtCpf.setText("");
							txtLogin.setText("");
							txtNasc.setText("");		
							txtSenha.setText("");	
							txtMat.setText("");	
							
						}
					}
				});
			}
		});
//=================   DELETAR OPERADORES ====================================================================================================	
		apaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFrame jfDelFunc = new JFrame();
				JLabel txtNome = new JLabel();
				JLabel txtMatricula = new JLabel();
				JLabel txtCpf = new JLabel();
				JLabel txtNasc = new JLabel();
				JLabel txtLogin = new JLabel();
				JLabel txtSenha = new JLabel();
				JComboBox<String> boxMat = new JComboBox();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				JLabel lblTitulo = new JLabel("Excluir Operador");
				JLabel lblNome = new JLabel("NOME");
				JLabel lblMatricula = new JLabel("MATRÍCULA");
				JLabel lblCpf = new JLabel("CPF");
				JLabel lblLogin = new JLabel("Login do Operador");
				JLabel lblSenha = new JLabel("Senha");
				JLabel lblNasc = new JLabel("Nascimento (aaaa-mm-dd)");
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				JButton btnOk = new JButton("Excluir");
				String[] mats; //Array que vai pegar todas as matriculas
				//Layout
				jfDelFunc.setLayout(null);  
				jfDelFunc.setTitle("Adicionar Produto");
				jfDelFunc.setSize(700, 500);
				jfDelFunc.setLocationRelativeTo(null);
				jfDelFunc.setResizable(false);
				jfDelFunc.setVisible(true);
				lblTitulo.setFont(roboto22);
				lblMatricula.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblCpf.setFont(roboto15);
				lblLogin.setFont(roboto15);
				lblSenha.setFont(roboto15);
				lblNasc.setFont(roboto15);
				txtSenha.setFont(roboto12);
				txtNome.setFont(roboto12);
				txtMatricula.setFont(roboto12);
				txtCpf.setFont(roboto12);
				txtLogin.setFont(roboto12);
				txtNasc.setFont(roboto12);
				btnOk.setFont(roboto15);
				
				lblTitulo.setBounds(55, 45, 200, 30);
				lblMatricula.setBounds(430, 240, 250, 30);
				lblNome.setBounds(55, 95, 70, 30);
				lblCpf.setBounds(55, 160, 100, 30);
				lblNasc.setBounds(430, 160, 250, 30);
				lblLogin.setBounds(55, 235, 150, 30);
				lblSenha.setBounds(55, 310, 100, 30);
				lblFundo.setBounds(0,0,700,500);
				txtNome.setBounds(55, 125, 500, 20);
				boxMat.setBounds(430, 270, 180, 20);
				txtCpf.setBounds(55, 190, 290, 20);
				txtNasc.setBounds(430, 190, 180, 20);
				txtLogin.setBounds(55, 270, 250, 20);
				txtSenha.setBounds(55, 345, 250, 20);
				btnOk.setBounds(455, 350, 120, 30);
				
				jfDelFunc.add(boxMat);
				jfDelFunc.add(lblTitulo);
				jfDelFunc.add(lblNome);
				jfDelFunc.add(lblMatricula);
				jfDelFunc.add(lblCpf);
				jfDelFunc.add(lblNasc);
				jfDelFunc.add(lblLogin);
				jfDelFunc.add(lblSenha);
				jfDelFunc.add(txtNome);
				jfDelFunc.add(txtMatricula);
				jfDelFunc.add(txtCpf);
				jfDelFunc.add(txtNasc);
				jfDelFunc.add(txtLogin);
				jfDelFunc.add(txtSenha);
				jfDelFunc.add(btnOk);
				jfDelFunc.add(lblFundo);
				ban.conectar();
				mats = ban.pegaDado("matricula");
				ban.desconectar();
				int tam = mats.length;
				for(int i =0; i < tam; i++){ //Inicia a ComboBox
					if (mats[i].equals(func.getMatricula())){
						tam--;
					} else {
					boxMat.addItem(mats[i]);
					}
				}
				String matBox; //Pega o dado selecionado do ComboBox
				ban.conectar();
				matBox = String.valueOf(boxMat.getSelectedItem());
				txtNome.setText(ban.pegaDeFuncionario(matBox, "nome"));
				txtCpf.setText(ban.pegaDeFuncionario(matBox, "cpf"));
				txtNasc.setText(ban.pegaDeFuncionario(matBox, "nascimento"));
				txtLogin.setText(ban.pegaDeFuncionario(matBox, "login"));
				txtSenha.setText(ban.pegaDeFuncionario(matBox, "senha"));
				ban.desconectar();
				
				//Ação da ComboBox
				boxMat.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						String matBox = (String) boxMat.getSelectedItem(); //Pega o dado selecionado do ComboBox
						if(e.getStateChange() == ItemEvent.SELECTED){
							ban.conectar();
							txtNome.setText(ban.pegaDeFuncionario(matBox, "nome"));
							txtCpf.setText(ban.pegaDeFuncionario(matBox, "cpf"));
							txtNasc.setText(ban.pegaDeFuncionario(matBox, "nascimento"));
							txtLogin.setText(ban.pegaDeFuncionario(matBox, "login"));
							txtSenha.setText(ban.pegaDeFuncionario(matBox, "senha"));
							ban.desconectar();
						}
					}
				});
				
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						String nome= txtNome.getText();
						ban.conectar();
						ban.deletaUsuario((String) boxMat.getSelectedItem());
						JOptionPane.showMessageDialog(null, nome+" excluído");
						ban.desconectar();
						jfDelFunc.dispose();
							
					}
				});
			}
		});
//=================   EDITAR OPERADORES ====================================================================================================	
		edita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Objetos
				JFrame jfEdtFunc = new JFrame();
				JTextField txtNome = new JTextField();
				JTextField txtMatricula = new JTextField();
				JTextField txtCpf = new JTextField();
				JTextField txtNasc = new JTextField();
				JTextField txtLogin = new JTextField();
				JTextField txtSenha = new JTextField();
				JComboBox<String> boxMat = new JComboBox();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				JLabel lblTitulo = new JLabel("Editar Operador");
				JLabel lblNome = new JLabel("NOME");
				JLabel lblMatricula = new JLabel("MATRÍCULA");
				JLabel lblCpf = new JLabel("CPF");
				JLabel lblLogin = new JLabel("Login do Operador");
				JLabel lblSenha = new JLabel("Senha");
				JLabel lblNasc = new JLabel("Nascimento (aaaa-mm-dd)");
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				JButton btnOk = new JButton("Editar");
				String[] mats; //Array que vai pegar todas as matriculas
				//Layout
				jfEdtFunc.setLayout(null);  
				jfEdtFunc.setTitle("Adicionar Produto");
				jfEdtFunc.setSize(700, 500);
				jfEdtFunc.setLocationRelativeTo(null);
				jfEdtFunc.setResizable(false);
				jfEdtFunc.setVisible(true);
				lblTitulo.setFont(roboto22);
				lblMatricula.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblCpf.setFont(roboto15);
				lblLogin.setFont(roboto15);
				lblSenha.setFont(roboto15);
				lblNasc.setFont(roboto15);
				txtSenha.setFont(roboto12);
				txtNome.setFont(roboto12);
				txtMatricula.setFont(roboto12);
				txtCpf.setFont(roboto12);
				txtLogin.setFont(roboto12);
				txtNasc.setFont(roboto12);
				btnOk.setFont(roboto15);
				
				lblTitulo.setBounds(55, 45, 200, 30);
				lblMatricula.setBounds(430, 240, 250, 30);
				lblNome.setBounds(55, 95, 70, 30);
				lblCpf.setBounds(55, 160, 100, 30);
				lblNasc.setBounds(430, 160, 250, 30);
				lblLogin.setBounds(55, 235, 150, 30);
				lblSenha.setBounds(55, 310, 100, 30);
				lblFundo.setBounds(0,0,700,500);
				txtNome.setBounds(55, 125, 500, 20);
				boxMat.setBounds(430, 270, 180, 20);
				txtCpf.setBounds(55, 190, 290, 20);
				txtNasc.setBounds(430, 190, 180, 20);
				txtLogin.setBounds(55, 270, 250, 20);
				txtSenha.setBounds(55, 345, 250, 20);
				btnOk.setBounds(455, 350, 120, 30);
				
				jfEdtFunc.add(boxMat);
				jfEdtFunc.add(lblTitulo);
				jfEdtFunc.add(lblNome);
				jfEdtFunc.add(lblMatricula);
				jfEdtFunc.add(lblCpf);
				jfEdtFunc.add(lblNasc);
				jfEdtFunc.add(lblLogin);
				jfEdtFunc.add(lblSenha);
				jfEdtFunc.add(txtNome);
				jfEdtFunc.add(txtMatricula);
				jfEdtFunc.add(txtCpf);
				jfEdtFunc.add(txtNasc);
				jfEdtFunc.add(txtLogin);
				jfEdtFunc.add(txtSenha);
				jfEdtFunc.add(btnOk);
				jfEdtFunc.add(lblFundo);
				ban.conectar();
				mats = ban.pegaDado("matricula");
				ban.desconectar();
				for(int i =0; i < mats.length; i++){ //Inicia a ComboBox
					boxMat.addItem(mats[i]);
				}
				String matBox; //Pega o dado selecionado do ComboBox
				ban.conectar();
				matBox = String.valueOf(boxMat.getSelectedItem());
				txtNome.setText(ban.pegaDeFuncionario(matBox, "nome"));
				txtCpf.setText(ban.pegaDeFuncionario(matBox, "cpf"));
				txtNasc.setText(ban.pegaDeFuncionario(matBox, "nascimento"));
				txtLogin.setText(ban.pegaDeFuncionario(matBox, "login"));
				txtSenha.setText(ban.pegaDeFuncionario(matBox, "senha"));
				ban.desconectar();
				
				//Ação da ComboBox
				boxMat.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						String matBox = (String) boxMat.getSelectedItem(); //Pega o dado selecionado do ComboBox
						if(e.getStateChange() == ItemEvent.SELECTED){
							ban.conectar();
							txtNome.setText(ban.pegaDeFuncionario(matBox, "nome"));
							txtCpf.setText(ban.pegaDeFuncionario(matBox, "cpf"));
							txtNasc.setText(ban.pegaDeFuncionario(matBox, "nascimento"));
							txtLogin.setText(ban.pegaDeFuncionario(matBox, "login"));
							txtSenha.setText(ban.pegaDeFuncionario(matBox, "senha"));
							ban.desconectar();
						}
					}
				});
				
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						String nome, cpf, login, nasc, senha;
						nome = txtNome.getText();
						cpf = txtCpf.getText();
						login = txtLogin.getText();
						nasc = txtNasc.getText();				senha = txtSenha.getText();		
						if((nome.equals("")) || (cpf.equals("")) ||login.equals("")||nasc.equals("")){
							JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
						} else {
							ban.conectar();
							ban.alteraUsuario("nome", (String) boxMat.getSelectedItem(), nome);
							ban.alteraUsuario("cpf", (String) boxMat.getSelectedItem(), cpf);
							ban.alteraUsuario("login",(String) boxMat.getSelectedItem(), login);
							ban.alteraUsuario("nascimento", (String)boxMat.getSelectedItem(), nasc);
							ban.desconectar();
							JOptionPane.showMessageDialog(null, "Editado");
						}
					}
				});
			}
		});
//=================   VER O PRÓPRIO PERFIL ====================================================================================================	
		perfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Objetos
				JFrame jfVerPerfil = new JFrame();
				JTextField txtNome = new JTextField();
				JTextField txtCpf = new JTextField();
				JTextField txtNasc = new JTextField();
				JTextField txtLogin = new JTextField();
				JTextField txtSenha = new JTextField();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				JLabel lblTitulo = new JLabel("Perfil");
				JLabel lblNome = new JLabel("Nome");
				JLabel lblMatricula = new JLabel("Matrícula");
				JLabel lblCpf = new JLabel("CPF");
				JLabel lblLogin = new JLabel("Login");
				JLabel lblSenha = new JLabel("Senha");
				JLabel lblNasc = new JLabel("Nascimento");
				JLabel lblDIncl = new JLabel("Data/Hora de Inclusão");
				JLabel lbldInc = new JLabel();
				JLabel lblNom = new JLabel();
				JLabel lblMat = new JLabel();
				JLabel lblcpf = new JLabel();
				JLabel lblLog = new JLabel();
				JLabel lblSen = new JLabel();
				JLabel lblNas = new JLabel();
				JLabel lblFundo = new JLabel(fundo);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 13);
				Font roboto15P = new Font("Roboto Condensed", Font.PLAIN, 15);
				JButton btnOk = new JButton("Editar");
				JButton btnConc = new JButton("Concluir");
				
				//Layout
				jfVerPerfil.setLayout(null);  
				jfVerPerfil.setTitle("Adicionar Produto");
				jfVerPerfil.setSize(700, 500);
				jfVerPerfil.setLocationRelativeTo(null);
				jfVerPerfil.setResizable(false);
				jfVerPerfil.setVisible(true);
				lblTitulo.setFont(roboto22);
				lblMatricula.setFont(roboto15);
				lblNome.setFont(roboto15);
				lblDIncl.setFont(roboto15);
				lblCpf.setFont(roboto15);
				lblLogin.setFont(roboto15);
				lblSenha.setFont(roboto15);
				lblNasc.setFont(roboto15);
				txtSenha.setFont(roboto12);
				txtNome.setFont(roboto12);
				txtCpf.setFont(roboto12);
				txtLogin.setFont(roboto12);
				txtNasc.setFont(roboto12);
				btnOk.setFont(roboto15);
				btnConc.setFont(roboto15);
				lbldInc.setFont(roboto15P);
				lblNom.setFont(roboto15P);
				lblMat.setFont(roboto15P);
				lblcpf.setFont(roboto15P);
				lblLog.setFont(roboto15P);
				lblSen.setFont(roboto15P);
				lblNas.setFont(roboto15P);

				lblTitulo.setBounds(55, 45, 200, 30);
				lblMatricula.setBounds(430, 95,100, 30);
				lblNome.setBounds(55, 95, 70, 30);
				lblCpf.setBounds(55, 160, 100, 30);
				lblNasc.setBounds(430, 160, 250, 30);
				lblLogin.setBounds(55, 235, 150, 30);
				lblSenha.setBounds(55, 310, 100, 30);
				lblFundo.setBounds(0,0,700,500);
				txtNome.setBounds(55, 125, 330, 30);
				lblMat.setBounds(430, 128, 80, 20);
				txtCpf.setBounds(55, 190, 250, 30);
				txtNasc.setBounds(430, 190, 180, 30);
				txtLogin.setBounds(55, 270, 250, 30);
				txtSenha.setBounds(55, 345, 250, 30);
				btnOk.setBounds(455, 350, 120, 30);
				btnConc.setBounds(455, 350, 120, 30);
				lblDIncl.setBounds(430, 240, 250, 30);
				lbldInc.setBounds(430, 270, 200, 20);
				lblNom.setBounds(55, 125, 500, 20);
				lblcpf.setBounds(55, 190, 290, 20);
				lblLog.setBounds(55, 270, 250, 20);
				lblSen.setBounds(55, 345, 250, 20);
				lblNas.setBounds(430, 190, 180, 20);
				
				jfVerPerfil.add(lblMat);
				jfVerPerfil.add(lblTitulo);
				jfVerPerfil.add(lblNome);
				jfVerPerfil.add(lblMatricula);
				jfVerPerfil.add(lblCpf);
				jfVerPerfil.add(lblNasc);
				jfVerPerfil.add(lblLogin);
				jfVerPerfil.add(lblSenha);
				jfVerPerfil.add(txtNome);
				jfVerPerfil.add(txtCpf);
				jfVerPerfil.add(txtNasc);
				jfVerPerfil.add(txtLogin);
				jfVerPerfil.add(txtSenha);
				jfVerPerfil.add(lblDIncl);
				jfVerPerfil.add(lbldInc);
				jfVerPerfil.add(lblNom);
				jfVerPerfil.add(lblcpf);
				jfVerPerfil.add(lblLog);
				jfVerPerfil.add(lblSen);
				jfVerPerfil.add(lblNas);
				jfVerPerfil.add(btnOk);
				jfVerPerfil.add(btnConc);
				jfVerPerfil.add(lblFundo);
				
				btnConc.setVisible(false);
				txtSenha.setVisible(false);
				txtNome.setVisible(false);
				txtCpf.setVisible(false);
				txtLogin.setVisible(false);
				txtNasc.setVisible(false);
				
				ban.conectar();
				lbldInc.setText(func.getDataInclusao());
				lblNom.setText(func.getNome());
				lblcpf.setText(func.getCpf());
				lblLog.setText(func.getLogin());
				lblSen.setText(func.getSenha());
				lblNas.setText(func.getNascimento());
				lblMat.setText(func.getMatricula());
				ban.desconectar();
				
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						btnConc.setVisible(true);
						txtSenha.setVisible(true);
						txtNome.setVisible(true);
						txtCpf.setVisible(true);
						txtLogin.setVisible(true);
						txtNasc.setVisible(true);
						btnOk.setVisible(false);
						lblSen.setVisible(false);
						lblNom.setVisible(false);
						lblcpf.setVisible(false);
						lblLog.setVisible(false);
						lblNas.setVisible(false);
						txtSenha.setText(lblSen.getText());
						txtNome.setText(lblNom.getText());
						txtCpf.setText(lblcpf.getText());
						txtLogin.setText(lblLog.getText());
						txtNasc.setText(lblNas.getText());
					}
				});
				btnConc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						String log, sen, nom, cpf, nasc, mat;
						log = txtLogin.getText();
						sen = txtSenha.getText();
						nom = txtNome.getText();
						cpf = txtCpf.getText();
						nasc = txtNasc.getText();
						mat = lblMat.getText();
						ban.conectar();
						ban.alteraUsuario("login", mat, log);
						ban.alteraUsuario("senha", mat, sen);
						ban.alteraUsuario("nome", mat, nom);
						ban.alteraUsuario("cpf", mat, cpf);
						ban.alteraUsuario("nascimento", mat, nasc);
						lbldInc.setText(func.getDataInclusao());
						lblNom.setText(func.getNome());
						lblcpf.setText(func.getCpf());
						lblLog.setText(func.getLogin());
						lblSen.setText(func.getSenha());
						lblNas.setText(func.getNascimento());
						lblMat.setText(func.getMatricula());
						ban.desconectar();
						btnConc.setVisible(false);
						txtSenha.setVisible(false);
						txtNome.setVisible(false);
						txtCpf.setVisible(false);
						txtLogin.setVisible(false);
						txtNasc.setVisible(false);
						btnOk.setVisible(true);
						lblSen.setVisible(true);
						lblNom.setVisible(true);
						lblcpf.setVisible(true);
						lblLog.setVisible(true);
						lblNas.setVisible(true);	
					}
				});
			}
		});
//=================   VER TODAS AS VENDAS ====================================================================================================	
		venda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ban.conectar();		
				JFrame lisVendas = new JFrame();
				ImageIcon logo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				Font roboto20 = new Font("Roboto Condensed", Font.BOLD, 20);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto18 = new Font("Roboto Condensed", Font.PLAIN, 18);
				Font roboto25 = new Font("Roboto Condensed", Font.PLAIN, 20);
				String [] cods=ban.pegaCodVendas(); //Define o tamanho dos LabelsArrays
				JLabel lblTitulo = new JLabel("Vendas");
				JLabel lblCodV = new JLabel("Cód. Venda");
				JLabel lblDHV = new JLabel("Data/Hora");
				JLabel lblVal = new JLabel("Valor");
				JLabel lblTOT = new JLabel("TOTAL: ");
				JLabel lblTot = new JLabel();
				JLabel lblOp = new JLabel("Operador");
				JLabel layout = new JLabel(logo);
				JLabel cod[] = new JLabel[cods.length];
				JLabel daho[] = new JLabel[cods.length];
				JLabel val[] = new JLabel[cods.length];
				JLabel log[] = new JLabel[cods.length];
				//Layout
				lisVendas.setLayout(null);  
				lisVendas.setTitle("Produtos");
				lisVendas.setSize(700, 500);
				lisVendas.setLocationRelativeTo(null);
				lisVendas.setResizable(false);
				lisVendas.setVisible(true);
				lblTitulo.setFont(roboto20);
				lblCodV.setFont(roboto18);
				lblDHV.setFont(roboto18);
				lblOp.setFont(roboto18);
				lblVal.setFont(roboto18);
				lblTOT.setFont(roboto25);
				lblTot.setFont(roboto25);
				
				lisVendas.add(lblOp);
				lisVendas.add(lblTitulo);
				lisVendas.add(lblCodV);
				lisVendas.add(lblDHV);
				lisVendas.add(lblVal);
				lisVendas.add(lblTOT);
				lisVendas.add(lblTot);
				lblTitulo.setBounds(30 , 30, 300, 30);
				lblCodV.setBounds(30, 90, 100, 30);
				lblDHV.setBounds(150, 90, 100, 30);
				lblVal.setBounds(340, 90, 100, 30);
				layout.setBounds(0, 0, 700, 500);
				lblOp.setBounds(430, 90, 120, 30);

				int t = 120;//Define a altura das Labels
				Double som=0.0 ;
				for(int i=0;i< cods.length; i++){	
					cod[i] = new JLabel(cods[i]);
					lisVendas.add(cod[i]);
					cod[i].setBounds(60 , t, 100, 30);
					daho[i] = new JLabel(ban.pegaDeVendas("dataVenda", cods[i]));
					lisVendas.add(daho[i]);
					daho[i].setBounds(150 , t, 300, 30);
					val[i] = new JLabel("R$" +ban.pegaDeVendas("valor", cods[i]));
					lisVendas.add(val[i]);
					val[i].setBounds(340 , t, 320, 30);
					log[i] = new JLabel(ban.pegaDeVendas("func", cods[i]));
					lisVendas.add(log[i]);
					log[i].setBounds(430 , t, 320, 30);
					daho[i].setFont(roboto15);
					val[i].setFont(roboto15);
					cod[i].setFont(roboto15);
					log[i].setFont(roboto15);
					t=t+30;
					som += (Double.parseDouble(val[i].getText().replace("R", "").replace("$", "")));
				}
				lblTot.setText(String.valueOf(som).replace(".", ","));
				lblTOT.setBounds(280, t, 100, 40);
				lblTot.setBounds(350, t, 100, 40);
				lisVendas.add(layout);
				ban.desconectar();
				}
		});
		
//=================   VER VENDAS DE DETERMINADO OPERADOR ====================================================================================================			
		vendasFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ban.conectar();		
				JFrame lisVendaF = new JFrame();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				Font roboto20 = new Font("Roboto Condensed", Font.BOLD, 20);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto18 = new Font("Roboto Condensed", Font.PLAIN, 18);
				Font roboto25 = new Font("Roboto Condensed", Font.PLAIN, 20);
				String [] cods;
				String [] Totcod = ban.pegaCods();
				JLabel lblTitulo = new JLabel();
				JLabel lblCodV = new JLabel("Cód. Venda");
				JLabel lblDHV = new JLabel("Data/Hora");
				JLabel lblVal = new JLabel("Valor");
				JLabel lblTOT = new JLabel("TOTAL: ");
				JLabel lblTot = new JLabel();
				JLabel cod[] = new JLabel[Totcod.length]; 
				JLabel daho[] = new JLabel[Totcod.length];
				JLabel val[] = new JLabel[Totcod.length];
				JLabel Fundo = new JLabel(fundo);
				JComboBox<String> boxMat = new JComboBox();
				String[] mats;
				//Layout
				lisVendaF.setLayout(null);  
				lisVendaF.setTitle("Produtos");
				lisVendaF.setSize(700, 500);
				lisVendaF.setLocationRelativeTo(null);
				lisVendaF.setResizable(false);
				lisVendaF.setVisible(true);
				lblTitulo.setFont(roboto20);
				lblCodV.setFont(roboto18);
				lblDHV.setFont(roboto18);
				lblVal.setFont(roboto18);
				lblTOT.setFont(roboto25);
				lblTot.setFont(roboto25);
				
				lisVendaF.add(lblTitulo);
				lisVendaF.add(lblCodV);
				lisVendaF.add(lblDHV);
				lisVendaF.add(lblVal);
				lisVendaF.add(lblTOT);
				lisVendaF.add(lblTot);
				lisVendaF.add(boxMat);

				Fundo.setBounds(0 , 0, 700, 500);
				lblTitulo.setBounds(30 , 30, 300, 30);
				lblCodV.setBounds(30, 90, 100, 30);
				lblDHV.setBounds(150, 90, 100, 30);
				lblVal.setBounds(355, 90, 100, 30);
				boxMat.setBounds(430, 90, 100, 20);
				ban.conectar();
				mats = ban.pegaDado("matricula");
				for(int i =0; i < mats.length; i++){ //Inicia a ComboBox
					boxMat.addItem(mats[i]);
				}
				
				String matBox= String.valueOf(boxMat.getSelectedItem()); //Pega o dado selecionado do ComboBox
				ban.conectar();
				cods = ban.pegaCodVenOperador(ban.pegaDeFuncionario(matBox, "login"));
				lblTitulo.setText("Vendas de "+ban.pegaDeFuncionario(matBox, "nome"));
				
				int t = 120;//Define a altura das Labels
				Double som=0.0 ;
				for(int i=0;i< cods.length; i++){	
					cod[i] = new JLabel(cods[i]);
					lisVendaF.add(cod[i]);
					cod[i].setBounds(60 , t, 100, 30);
					daho[i] = new JLabel(ban.pegaDeVendas("dataVenda", cods[i]));
					lisVendaF.add(daho[i]);
					daho[i].setBounds(150 , t, 300, 30);
					val[i] = new JLabel("R$" +ban.pegaDeVendas("valor", cods[i]));
					lisVendaF.add(val[i]);
					val[i].setBounds(355 , t, 320, 30);
					daho[i].setFont(roboto15);
					val[i].setFont(roboto15);
					cod[i].setFont(roboto15);
					t=t+30;
					som += (Double.parseDouble(val[i].getText().replace("R", "").replace("$", "")));
				}
				lblTot.setText(String.valueOf(som).replace(".", ","));
				lblTOT.setBounds(280, t, 100, 40);
				lblTot.setBounds(350, t, 100, 40);
				lisVendaF.add(Fundo);
				ban.desconectar();
				
				//Ação da ComboBox
				boxMat.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if(e.getStateChange() == ItemEvent.SELECTED){
							ban.conectar();
							String matBox= String.valueOf(boxMat.getSelectedItem());
							String[] cods = ban.pegaCodVenOperador(ban.pegaDeFuncionario(matBox, "login"));
							lblTitulo.setText("Vendas de "+ban.pegaDeFuncionario(matBox, "nome"));
							//São iniciadas depois pq precisam do tamanho  de cods
							int t = 120;//Define a altura das Labels
							Double som=0.0 ;
							
							for(int i=0;i< cods.length; i++){	
								cod[i] = new JLabel(cods[i]);
								lisVendaF.add(cod[i]);
								cod[i].setBounds(60 , t, 100, 30);
								daho[i] = new JLabel(ban.pegaDeVendas("dataVenda", cods[i]));
								lisVendaF.add(daho[i]);
								daho[i].setBounds(150 , t, 300, 30);
								val[i] = new JLabel("R$" +ban.pegaDeVendas("valor", cods[i]));
								lisVendaF.add(val[i]);
								val[i].setBounds(355 , t, 320, 30);
								daho[i].setFont(roboto15);
								val[i].setFont(roboto15);
								cod[i].setFont(roboto15);
								t=t+30;
								som += (Double.parseDouble(val[i].getText().replace("R", "").replace("$", "")));
							}
							lblTot.setText(String.valueOf(som).replace(".", ","));
							lblTOT.setBounds(280, t, 100, 40);
							lblTot.setBounds(350, t, 100, 40);
							lisVendaF.add(Fundo);
							ban.desconectar();
						}}
						});
			}
		});
//=================   SAIR ====================================================================================================	
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dispose();
				TelaLogin log = new TelaLogin();
				
			}
		});
	}
}
