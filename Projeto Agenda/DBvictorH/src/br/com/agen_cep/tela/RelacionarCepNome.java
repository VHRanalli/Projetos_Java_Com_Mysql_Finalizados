package br.com.agen_cep.tela;

import br.com.agen_cep.dal.Dal;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Projeto Crud
 * @author Victor Henrique Ranalli Barbosa
 */
public class RelacionarCepNome extends javax.swing.JFrame {
         
    //Variavel para conectar ao banco de dados no myslq.
    Connection conectar = null;
    //Esta variavel tem como funçao adicionar ou alterar (depende do metodo) no banco de dados.
    PreparedStatement pst = null;
    //Esta variavel com a funsao resultset serve para trazer dados do banco de dados ao java e depois transmitilos atravez de caixas de texto no modo grafico
    ResultSet rs = null;
    
    
    //Construtor
     
    
    /**
     * <b>RelacionarCepNome</b><b>
     * Este metodo constroe uma interface grafica e a conexão com o banco de dados deixando o usuraio inserir dados dentro deste mesmo banco de dados
     * @param conectar -> variavel instanciada que logo em seguida recebe parametros da Dal.conector(); para realizar a conexão ao banco de dados
     * @exception lblConexao e a nossa label que ira transmitir uma resposta ao usuario se o banco deu uma resposta de conectado ou se ele retornou uma resposta invalida para isso usamos o if else se o valor estiver diferente de nulo mostrara o icone de conectado se estiver com um x ele nao esta conectado ao banco de dados
     */
    public RelacionarCepNome() {
        initComponents();
        //usando a variavel conectar para se conectar a classe dal e pegar o metodo conector que ira gerar a conexão ao banco de dados
        conectar = Dal.conector();
        
        //if e else para testar se o banco de dados esta disponivel ou nao
        if (conectar != null) {
            
            lblConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/dbok.png")));
            
        } else {
            
            lblConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/dberror.png")));
            
        }
    }
    
    //este metodo limpa os campos de texto do formulario
    
    /**
     * <b>limpeza</b><b>
     * este metodo irá limpar as caixas de texto...
     * @param txtID.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtNome.setText(null); -> caixa de texto que ira ser apagada por este metodo
     * @param txtTelefone.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtEmail.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtCEP.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtLogradouro.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtNcasa.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtBairro.setText(null);  -> caixa de texto que ira ser apagada por este metodo 
     * @param txtCidade.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtEstado.setText(null);  -> caixa de texto que ira ser apagada por este metodo
     * @param txtComplemento.setText(null); -> caixa de texto que ira ser apagada por este metodo
     */
    private void limpeza(){
        
        txtID.setText(null);
        txtNome.setText(null);
        txtTelefone.setText(null);
        txtEmail.setText(null);
        txtCEP.setText(null);
        txtLogradouro.setText(null);
        txtNcasa.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);
        txtEstado.setText(null);
        txtComplemento.setText(null);
    }
    
    
    //este metodo ira pegar os dados do banco e ira transmitilos as caixas de texto
    /**
     * <b>Consultar</b><b>
     * Este metodo irá pesquisar no banco de dados, com base fornecidas nas txt, usando uma variavel string que esta usando um comando musql que ira consultar no banco de dados as informações 
     * @param consulta -> variavel string que possui o comando a ser  dado no mysql para gerar a busca
     * @param parametros -> este parametro ? sera substituido pelo valor inserido na caixa de texto id para realizar a busca
     * @exception try e catch para testar o comando que irá ser dado por esta variavel chamada consulta...
     */
    private void consultar(){
        //comando a ser dado no mysql
        String consulta = "select * from tb_relacionamento_vhrb where id=?";
        
         //tentativa e erro para ver se a alguma coisa de errado ao codigo
         //try > tentativa
         //catch > erro
         
        try {
            //se estiver tudo certo entao ele ira pegar o campo id que foi digitado na interface grafica jframe
            
            pst = conectar.prepareStatement(consulta);
            pst.setString(1, txtID.getText());
            //
            rs = pst.executeQuery();
            
            //mais um if e else para gerar a busca
            
            if (rs.next()) {
                
                txtNome.setText(rs.getString(2));
                txtTelefone.setText(rs.getString(3));
                txtEmail.setText(rs.getString(4));
                txtCEP.setText(rs.getString(5));
                txtLogradouro.setText(rs.getString(6));
                txtNcasa.setText(rs.getString(7));        
                txtBairro.setText(rs.getString(8));        
                txtCidade.setText(rs.getString(9));        
                txtEstado.setText(rs.getString(10));        
                txtComplemento.setText(rs.getString(11));  
                
                //se não houver nenhum usuario uma caixa de mensagens amigavel ira aparecer e falar dados não cadastrados
            } else {
                limpeza();
                JOptionPane.showMessageDialog(null, "Dados nao cadastrados","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
            }
            
              //se houver erro na hora de procurar os dados no banco irá aparecer uma caixa de mensaagem dizendo qual e o erro
        } catch (Exception e) {
            
           JOptionPane.showMessageDialog(null, e); 
            
        }
    }
    
    
    //este metodo irá adicionar informações ao nosso banco de dados mysql
    /**
     * <b>Adcionar</b><b>
     * Este metodo faz quase a mesma coisa so que ao invez disse disso ele pega o que foi digitado nas caixas de texto e envia atravez de um comando para o banco de dados mysql que eo banco de dados usado por este projeto
     * @param adicionar -> a string que esta com o comando mysql para inserir informações no banco de dados  
     * @param parametros -> este ? parametro ira ser substituido pelos valores das caixas de texto so que ao invez de pegar so o valor da caixa de texto id ele pega os valores das 11 caixas de texto para adcionar informações
     * @exception nesta aqui possui uma coisa diferente alem do try catch que ira fazer a checagem ele possue um if e else que diz se caso as caixas de texto estiverem vasias aparecera uma mesagem de erro amigavel dizendo para preencher corretamente a caixa de texto com os dados corretamentes
     */
    private void adicionar(){
        
        //comando de inserção a ser dado no mysql
        String inserir = "insert into tb_relacionamento_vhrb(id,nome,fone,email,cep,logradouro,ncasa,bairro,cidade,estado,complemento) values (?,?,?,?,?,?,?,?,?,?,?)";
        
        
        try {
            
            //chamando a variável inserir e aplicando ela a pst que irá pegar os dados inseridos nas caixas de texto e colocalos nos campos lá do mysql
            pst = conectar.prepareStatement(inserir);
            //essa parte do codigo irá substituir o ? pelo valor dado nas caixas de texto
            pst.setString(1, txtID.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtEmail.getText());
            pst.setString(5, txtCEP.getText());
            pst.setString(6, txtLogradouro.getText());
            pst.setString(7, txtNcasa.getText());
            pst.setString(8, txtBairro.getText());
            pst.setString(9, txtCidade.getText());
            pst.setString(10, txtEstado.getText());
            pst.setString(11, txtComplemento.getText());
            
            
            //if e else para verificar se os campos estao vazios ou não se estiver uma menssagem amigavel aparecera dizendo para preencher os dados corretamente, se estiver tudo ok entao o comando prosseguirá normalmente
            if ((txtID.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtTelefone.getText().isEmpty()) || (txtCEP.getText().isEmpty()) || (txtLogradouro.getText().isEmpty()) || (txtNcasa.getText().isEmpty()) || (txtBairro.getText().isEmpty()) || (txtCidade.getText().isEmpty()) || (txtEstado.getText().isEmpty()) || (txtComplemento.getText().isEmpty())) {
                
               JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatorios *","Atenção",JOptionPane.WARNING_MESSAGE);
               
                
            } else {
                
                int confirmacao = pst.executeUpdate();
                if (confirmacao == 1) {
                     JOptionPane.showMessageDialog(null, "usuario cadastrado com sucesso","Informação",JOptionPane.INFORMATION_MESSAGE);
                    limpeza();
                }
                
            }
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
    }
    
     /**
     * <b>Atualizar</b><b>
     * Este metodo faz quase a mesma coisa so que ao invez disse disso ele pega o que foi digitado nas caixas de texto e envia atravez de um comando para o banco de dados mysql que eo banco de dados usado por este projeto
     * @param alteracao -> a string que esta com o comando mysql para atualizar informações no banco de dados  
     * @param parametros -> este ? parametro ira ser substituido pelos valores das caixas de texto so que ao invez de pegar so o valor da caixa de texto id ele pega os valores das 11 caixas de texto para adcionar informações e ao mesmo tempo atualizar la no banco de dados as informaçoes que forao dadas pelo pripio usuario
     * @exception alem do try catch que ira fazer a checagem ele possue um if e else que diz se caso as caixas de texto estiverem vasias aparecera uma mesagem de erro amigavel dizendo para preencher corretamente a caixa de texto com os dados corretamentes
     */
    private void atualizar(){
        String alteracao = "update tb_relacionamento_vhrb set nome=?,fone=?,email=?,cep=?,logradouro=?,ncasa=?,bairro=?,cidade=?,estado=?,complemento=? where id=?";
        
        try {
            pst = conectar.prepareStatement(alteracao);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtEmail.getText());
            pst.setString(4, txtCEP.getText());
            pst.setString(5, txtLogradouro.getText());
            pst.setString(6, txtNcasa.getText());
            pst.setString(7, txtBairro.getText());
            pst.setString(8, txtCidade.getText());
            pst.setString(9, txtEstado.getText());
            pst.setString(10, txtComplemento.getText());
            pst.setString(11, txtID.getText());
            
            if ((txtID.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtTelefone.getText().isEmpty()) || (txtCEP.getText().isEmpty()) || (txtLogradouro.getText().isEmpty()) || (txtNcasa.getText().isEmpty()) || (txtBairro.getText().isEmpty()) || (txtCidade.getText().isEmpty()) || (txtEstado.getText().isEmpty()) || (txtComplemento.getText().isEmpty())) {
                
               JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatorios *","Atenção",JOptionPane.WARNING_MESSAGE);
               
                
            } else {
                
                int confirma = pst.executeUpdate();
                
                if (confirma == 1) {
                    JOptionPane.showMessageDialog(null, "Informaçoes atualizadas com sucesso","Informação", JOptionPane.INFORMATION_MESSAGE);
                    limpeza();
                    
                }
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * <b>Remover</b><b>
     * remove informaçoes do banco de dados com base no id fornecido na variavel deletar
     */
    private void remover(){
        String deletar = "delete from tb_relacionamento_vhrb where id=?";
        
        try {
            pst = conectar.prepareStatement(deletar);
            pst.setString(1, txtID.getText());
            int exclusao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar estas Informações ?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (exclusao == JOptionPane.YES_OPTION) {
                
                int confirma = pst.executeUpdate();
                
                if (confirma == 1) {
                    
                    JOptionPane.showMessageDialog(null, "Informações deletadas com sucesso","Informação",JOptionPane.INFORMATION_MESSAGE);
                    limpeza();
                    
                }
                
            }
        } catch (Exception e) {
            
             JOptionPane.showMessageDialog(null, e);
             
        }
    }
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        txtNcasa = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCEP = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnProcurar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        lblConexao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("* ID");

        jLabel2.setText("Nome");

        jLabel3.setText("Telefone");

        jLabel4.setText("Email");

        jLabel5.setText("CEP");

        jLabel6.setText("Cidade");

        jLabel7.setText("Endereços");

        jLabel8.setText("Numero");

        jLabel9.setText("Bairro");

        jLabel10.setText("Complemento");

        jLabel11.setText("Estado");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/if_archive-insert-directory_79884 - Copia.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/if_Files_folder_search_storage_1886938 (1) - Copia.png"))); // NOI18N
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/if_reload_all_tabs_79452 (1) - Copia.png"))); // NOI18N
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/if_Delete_dustbin_empty_recycle_recycling_remove_trash_1886812 - Copia.png"))); // NOI18N
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        lblConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agen_cep/icones/Instavel.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(91, 91, 91))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNcasa, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(72, 72, 72))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(115, 115, 115))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(582, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNcasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(btnProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(874, 627));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnProcurarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
        atualizar();
        
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnDeletarActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RelacionarCepNome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelacionarCepNome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelacionarCepNome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelacionarCepNome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RelacionarCepNome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblConexao;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCEP;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNcasa;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
