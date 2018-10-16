package coffeSystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class TelaFuncionario extends JFrame{
	
	//Objetos
	private static double caix;
	BancoDeDados ban = new BancoDeDados();
	JMenuBar menu = new JMenuBar();
	ImageIcon logo = new ImageIcon(getClass().getResource("FundoLogado.jpg"));
	JMenu venda = new JMenu("venda");
	JMenu produto = new JMenu("Produto");
	JMenu sair = new JMenu("sair");
	JMenuItem vender = new JMenuItem("Vender");
	JMenuItem vendasFeitas = new JMenuItem("Vendas Feitas");
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
	JLabel titulo = new JLabel("Código | Produto                   | Descrição                      | Valor");
	JScrollPane listaProduto = new JScrollPane(lblsair);
	
	TelaFuncionario(Funcionario f){
		this.func=f;
		//Frame
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
		menu.add(venda);
		menu.add(produto);
		menu.add(sair);
		venda.add(vender);
		venda.add(vendasFeitas);
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
			ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
			JFrame lisProdutos = new JFrame();
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
			JLabel Fundo = new JLabel(fundo);
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
			Fundo.setBounds(0 , 0, 700, 500);
			
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
			lisProdutos.add(Fundo);
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

//=================   VENDAS FEITAS ====================================================================================================	
		vendasFeitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ban.conectar();		
				JFrame lisVendaF = new JFrame();
				ImageIcon fundo = new ImageIcon(getClass().getResource("FundoLista.jpg"));
				Font roboto20 = new Font("Roboto Condensed", Font.BOLD, 20);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto18 = new Font("Roboto Condensed", Font.PLAIN, 18);
				Font roboto25 = new Font("Roboto Condensed", Font.PLAIN, 20);
				String [] cods=ban.pegaCodVenOperador(func.getLogin()); //Define o tamanho dos LabelsArrays
				JLabel lblTitulo = new JLabel("Vendas de ");
				JLabel lblCodV = new JLabel("Cód. Venda");
				JLabel lblDHV = new JLabel("Data/Hora");
				JLabel lblVal = new JLabel("Valor");
				JLabel lblTOT = new JLabel("TOTAL: ");
				JLabel lblTot = new JLabel();
				JLabel cod[] = new JLabel[cods.length];
				JLabel daho[] = new JLabel[cods.length];
				JLabel val[] = new JLabel[cods.length];
				JLabel lblFundo = new JLabel(fundo);
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
				lblTitulo.setBounds(30 , 30, 300, 30);
				lblCodV.setBounds(30, 90, 100, 30);
				lblDHV.setBounds(150, 90, 100, 30);
				lblVal.setBounds(355, 90, 100, 30);
				lblFundo.setBounds(0, 0, 700, 500);

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
				lisVendaF.add(lblFundo);
				ban.desconectar();
				}
		});
//=================   VENDER ====================================================================================================			
		vender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ban.conectar();
				String [] cods=ban.pegaCods(); //Recebe os códigos
				ban.desconectar();
				JFrame jfVender = new JFrame();
				ImageIcon Fundo = new ImageIcon(getClass().getResource("FundoVenda.jpg"));
				JLabel fundo = new JLabel(Fundo);
				JLabel lblTituloCompra = new JLabel();
				JLabel lblcod = new JLabel("Código");
				JLabel lblquat = new JLabel("Quantidade");
				JLabel lblprecUni = new JLabel("Preço Unt.");
				JLabel lblPreUni = new JLabel();
				JLabel lblPretot = new JLabel();
				JLabel lblpreTot = new JLabel("Preço Total");
				JLabel lblSubTot = new JLabel("SubTotal:");
				JLabel lblSubTotVal = new JLabel();
				JTextField txtQuant = new JTextField();
				JComboBox<String> boxCod = new JComboBox();
				JButton btnCancela = new JButton("Cancelar"); 
				JButton btnConclui = new JButton("Concluir"); 
				JButton btnInicia = new JButton("Iniciar");
				JButton btnOk = new JButton("Ok");
				JTextArea atxCupom = new JTextArea();
				Font roboto35 = new Font("Roboto Condensed", Font.BOLD, 35);
				Font roboto15 = new Font("Roboto Condensed", Font.BOLD, 15);
				Font roboto12 = new Font("Roboto Condensed", Font.PLAIN, 12);
				Font roboto22 = new Font("Roboto Condensed", Font.PLAIN, 22);
				JScrollPane painel = new JScrollPane(atxCupom);
				String cupom = "                                                             CUPOM";
				String title = "QTD     |Produto                                                | Cód.            | Val Unt.              | Valor  ";
				String non = "------------------------------------------------------------------------------------------------------------------------------------------";
				double valorTot;
				jfVender.setLayout(null);  
				jfVender.setTitle("Venda");
				jfVender.setSize(700, 500);
				jfVender.setLocationRelativeTo(null);
				jfVender.setResizable(false);
				jfVender.setVisible(true);
				
				lblTituloCompra.setFont(roboto35);
				lblcod.setFont(roboto12);
				boxCod.setFont(roboto22);
				lblquat.setFont(roboto12);
				lblprecUni.setFont(roboto12);
				lblPreUni.setFont(roboto22);
				lblPretot.setFont(roboto22);
				lblpreTot.setFont(roboto12);
				lblSubTot.setFont(roboto22);
				lblSubTotVal.setFont(roboto22);
				btnCancela.setFont(roboto22);
				btnConclui.setFont(roboto22); 
				btnInicia.setFont(roboto22);
				btnOk.setFont(roboto12);
				txtQuant.setFont(roboto22);
				atxCupom.setFont(roboto12);

				
				lblTituloCompra.setBounds(245, 18, 420, 55);
				lblcod.setBounds(30, 200, 420, 20);
				lblquat.setBounds(30, 260, 420, 20);
				lblprecUni.setBounds(30, 330, 150, 20);
				lblPreUni.setBounds(30, 350, 150, 40);
				lblPretot.setBounds(30, 410, 150, 40);
				lblpreTot.setBounds(30, 390, 150, 20);
				lblSubTot.setBounds(295, 350, 300, 55);
				lblSubTotVal.setBounds(380, 350, 150, 55);
				btnCancela.setBounds(260, 420, 120, 40);
				btnConclui.setBounds(560, 420, 120, 40);
				btnInicia.setBounds(560, 420, 120, 40);
				btnOk.setBounds(150, 410, 60, 30);
				txtQuant.setBounds(30, 290, 100, 30);
				boxCod.setBounds(30, 220, 100, 30);
				atxCupom.setBounds(5, 5, 410, 210);
				painel.setBounds(245, 70, 420, 260);
				fundo.setBounds(0, 0, 700, 500);
				btnConclui.setVisible(false);
				btnOk.setVisible(false);
				btnCancela.setVisible(false);
				
				jfVender.add(lblTituloCompra);
				jfVender.add(lblcod);
				jfVender.add(boxCod);
				jfVender.add(lblquat);
				jfVender.add(lblprecUni);
				jfVender.add(lblPreUni);
				jfVender.add(lblPretot);
				jfVender.add(lblpreTot);
				jfVender.add(lblSubTot);
				jfVender.add(lblSubTotVal);
				jfVender.add(txtQuant);
				jfVender.add(btnCancela);
				jfVender.add(btnConclui);
				jfVender.add(btnInicia);
				jfVender.add(btnOk);
				jfVender.add(painel);
				jfVender.add(fundo);
				
				atxCupom.setDisabledTextColor(Color.BLACK);
				atxCupom.setText(String.format("%s\n%s\n%s", cupom, title, non));//inicia o JtextArea
				//Inciando o Combobox
				for(int i=0; cods.length>i; i++){
					boxCod.addItem(cods[i]);
				}
				//Pega o dado selecionado do ComboBox e põe no lbl
				String codBox= String.valueOf(boxCod.getSelectedItem()); 
				ban.conectar();
				lblPreUni.setText(ban.pegaProdutos("valorProduto", codBox));
				lblPretot.setText(lblPreUni.getText());
				txtQuant.setText("1");ban.conectar();
				lblTituloCompra.setText(txtQuant.getText()+" X "+ban.pegaProdutos("nomeProduto", String.valueOf(boxCod.getSelectedItem())));
				ban.desconectar();
				//Quando o combox é acionado
				boxCod.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						String codBox; //Pega o dado selecionado do ComboBox
						if(e.getStateChange() == ItemEvent.SELECTED){
							ban.conectar();
							codBox= String.valueOf(boxCod.getSelectedItem());
							lblPreUni.setText(ban.pegaProdutos("valorProduto", codBox));
							lblPretot.setText(lblPreUni.getText());
							txtQuant.setText("1");
							lblTituloCompra.setText(txtQuant.getText()+" X "+ban.pegaProdutos("nomeProduto", String.valueOf(boxCod.getSelectedItem())));
							ban.desconectar();
						}
					}
				});
				
				txtQuant.addKeyListener( new KeyListener(){
					@Override
					public void keyPressed(java.awt.event.KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyReleased(java.awt.event.KeyEvent e) {
						// TODO Auto-generated method stub
						try{
							ban.conectar();
							lblPretot.setText(String.valueOf((Double.parseDouble(txtQuant.getText())*Double.parseDouble(lblPreUni.getText()))));
							lblTituloCompra.setText(txtQuant.getText()+" X "+ban.pegaProdutos("nomeProduto", String.valueOf(boxCod.getSelectedItem())));
							ban.desconectar();
						} catch (Exception erro){
							
						}
						
					}

					@Override
					public void keyTyped(java.awt.event.KeyEvent arg0) {
						// TODO Auto-generated method stub
						
						
					}
				});
				
				
				btnInicia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						btnOk.setVisible(true);
						btnCancela.setVisible(true);
						btnConclui.setVisible(true);
						btnInicia.setVisible(false);
					}
				});
				
				btnConclui.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						ban.conectar();
						ban.inserirVenda(lblSubTotVal.getText(), func.getLogin());
						lblPreUni.setText(ban.pegaProdutos("valorProduto", codBox));
						lblPretot.setText(lblPreUni.getText());
						txtQuant.setText("1");
						atxCupom.setText(String.format("%s\n%s\n%s", cupom, title, non));
						lblTituloCompra.setText(txtQuant.getText()+" X "+ban.pegaProdutos("nomeProduto", String.valueOf(boxCod.getSelectedItem())));
						lblSubTotVal.setText("");
						btnConclui.setVisible(false);
						btnOk.setVisible(false);
						btnCancela.setVisible(false);
						btnInicia.setVisible(true);
						ban.desconectar();
					}
				});
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						ban.conectar();
						String codBox= String.valueOf(boxCod.getSelectedItem());
						String text = String.format("\n %s   | %s       |%s        |%s        |        %s", txtQuant.getText(), ban.pegaProdutos("nomeProduto", codBox), 
													boxCod.getSelectedItem(),lblPreUni.getText(), lblPretot.getText());
						atxCupom.setText(atxCupom.getText()+text);
						if(lblSubTotVal.getText().equals("")){
							lblSubTotVal.setText(lblPretot.getText());
						} else {
							lblSubTotVal.setText(String.valueOf((Double.parseDouble(lblPretot.getText())+(Double.parseDouble(lblSubTotVal.getText())))));
						}
						
						ban.desconectar();
					}
				});
				
				btnCancela.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						ban.conectar();
						lblPreUni.setText(ban.pegaProdutos("valorProduto", codBox));
						lblPretot.setText(lblPreUni.getText());
						txtQuant.setText("1");
						atxCupom.setText(String.format("%s\n%s\n%s", cupom, title, non));
						lblSubTotVal.setText("");
						btnConclui.setVisible(false);
						btnOk.setVisible(false);
						btnCancela.setVisible(false);
						btnInicia.setVisible(true);
						ban.desconectar();
					}
				});
				
			}
		});
//=================   SAIR ====================================================================================================			
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dispose();
				new TelaLogin();
				
			}
		});
	}
	
}
