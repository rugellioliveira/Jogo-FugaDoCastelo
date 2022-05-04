package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

//importa a classe ArrayList
import java.util.ArrayList;

import br.ufla.gac103.s2021_2.baseJogo.InterfaceUsuario;

/**
 *  Essa eh a classe principal da aplicacao "Fuga do Castelo".
 *  "Fuga do Castelo" eh um jogo de aventura bem divertido, baseado em texto.
 *  foi adaptado a partir da aplicação "World of Zuul".
 *  Usuarios controlam um personagem que pode caminhar em um cenario, pegar itens e usá-los, para desbloquear passagens
 *  e eliminar inimigos. 
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, os personagens, cria o analisador e comeca o jogo. Ela tambem avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author de  "World of Zuul" Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves) 
 * @version 2011.07.31 (2016.02.01)
 * 
 * @author de "Fuga do Castelo"(Rugelli Oliveira)
 * @version 2022.03.25
 * 
 */

public class Jogo 
{
    //Atributos da classe
    private Analisador analisador;
    private Ambiente ambienteAtual, ambienteFinal;
    private ArrayList<Personagem> personagens;
    private Prisioneiro prisioneiro;
    private Ranking ranking;
    private Usuario usuario;
    private InterfaceUsuario interfaceUsuario;
    
    /**
     * Construtor.
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo(InterfaceUsuario interfaceUsuario) 
    {
        this.interfaceUsuario = interfaceUsuario;
        personagens = new ArrayList <Personagem>();
        criarAmbientes();
        analisador = Analisador.getInstanceAnalisador(interfaceUsuario);
        prisioneiro = Prisioneiro.getInstancePrisioneiro();
        listarPersonagem(prisioneiro);
        ranking = Ranking.getInstanceRanking();
        
        String nomeUsuario = interfaceUsuario.obterInformacao("Escolha um nome de usuário: ");

        usuario = new Usuario(nomeUsuario); 
        
    }

    /**
     * Cria todos os ambientes com ou sem itens e/ou personagens e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente sala, banheiro, cozinha, quartoEmpregados, quartoRei, calabouco, torreLeste, torreOeste, ponte, fora;
      
        // cria os ambientes
        sala = new Ambiente("na sala principal do castelo","imagens/salaPrincipal.jpg");
        banheiro = new Ambiente("no banheiro","imagens/banheiro.jpg");
        cozinha = new Ambiente("na cozinha","imagens/cozinha.jpg");
        quartoEmpregados = new Ambiente("no quarto dos empregados", new Item("chave","chave para abrir alguma porta",true,"imagens/chave.jpg"),null,"imagens/quartoEmpregados.jpg");
        quartoRei = new Ambiente("no quarto do Rei", new Item("espada","espada para ser usada em combate",false,"imagens/espada.jpg"),null,"imagens/quartoRei.jpg");
        calabouco = new Ambiente("no calabouço","imagens/calabouco.jpg");
        torreLeste = new Ambiente("na torre leste",  new Item("corda","corda velha",true,"imagens/corda.jpg"),null,"imagens/torreLeste.jpg");
        torreOeste = new Ambiente("na torre oeste","imagens/torreOeste.jpg");
        ponte = new Ambiente("na ponte para entrada/saída do castelo",null,criarPersonagensInimigos(),"imagens/ponte.jpg");
        fora = new Ambiente("fora do castelo","imagens/fora.jpg");
        
        // inicializa as saidas dos ambientes
        
        //saídas do calabouço
        calabouco.ajustarSaidaBloqueada("cima",sala,"corda");
        calabouco.ajustarSaida("oeste",torreOeste);
        calabouco.ajustarSaida("leste",torreLeste);
        
        //saída da torre norte
        torreOeste.ajustarSaida("leste",calabouco);
        
        //saída da torre sul
        torreLeste.ajustarSaida("oeste",calabouco);
        
        //saídas da sala
        sala.ajustarSaida("baixo",calabouco);
        sala.ajustarSaida("leste",banheiro);
        sala.ajustarSaida("oeste",quartoEmpregados);
        sala.ajustarSaida("sul", cozinha);
        sala.ajustarSaidaBloqueada("norte",ponte,"chave");
        
        //saída do banheiro
        banheiro.ajustarSaida("oeste", sala);
        
        //saídas do quarto dos empregados
        quartoEmpregados.ajustarSaida("leste",sala);
        quartoEmpregados.ajustarSaida("norte",quartoRei);
        
        //saídas do quarto do Rei
        quartoRei.ajustarSaida("sul", quartoEmpregados);
        
        //saída da cozinha
        cozinha.ajustarSaida("norte", sala);
        
        //saídas da ponte
        ponte.ajustarSaidaBloqueada("norte",fora,"espada");
        ponte.ajustarSaida("sul",sala);
        
        ambienteAtual = calabouco;  // o jogo comeca no calabouço do castelo
        
        interfaceUsuario.ambienteAtualMudou(ambienteAtual);
        
        ambienteFinal = fora; //o jogo termina do lado de fora do castelo
    }

    
    /**
     * Cria os personagens inimigos no jogo
     */
    private Personagem criarPersonagensInimigos(){
        
            Personagem personagem = new Guarda();
            personagem.adicionarItemNoPersonagem(new Item("espada","espada para lutar",false,"imagens/espada.jpg"));
            listarPersonagem(personagem); 
        
            return personagem;
    }
    
    /**
     * Inserir personagem na lista de personagens do jogo
     */
    private void listarPersonagem(Personagem personagem){
        personagens.add(personagem);   
    }
    
    
    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();
        ranking.iniciarContagem();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        interfaceUsuario.continuarMensagem("Obrigado por jogar. Ate mais!");
        
        exibirRanking();
    }
    
    /**
     * Método para exibir ranking de usuarios que conseguiram vencer o jogo.
     */
    private void exibirRanking(){
        
        ArrayList<Usuario> aux;
        
        aux = ranking.carregarRanking();
        interfaceUsuario.continuarMensagem("Ranking de usuários que conseguiram vencer o jogo");
        interfaceUsuario.continuarMensagem("Usuário  -  Tempo de jogo (segundos)");
        interfaceUsuario.continuarMensagem("");
        
        for(int i = 0; i<aux.size(); i++)
        {
            interfaceUsuario.continuarMensagem(((i+1) + "º " + aux.get(i).getNomeUsuario() + " - " + aux.get(i).getTempoJogo()));
        }
        
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        
        interfaceUsuario.exibirMensagem("");
        
        interfaceUsuario.continuarMensagem("Olá " + usuario.getNomeUsuario() + ", bem-vindo(a) ao Fuga do Castelo!");
        
        interfaceUsuario.continuarMensagem("Fuga do Castelo é um novo jogo de aventura, o personagem principal é um soldado que foi levado para um castelo"
        + " como prisioneiro de guerra \nagora ele precisa fugir para reencontrar sua família.\n");
        
        interfaceUsuario.continuarMensagem("O nome do prisioneiro é: " + prisioneiro.getNomePrisioneiro());
        
        interfaceUsuario.continuarMensagem(prisioneiro.getDescricaoPersonagem());
        
        interfaceUsuario.continuarMensagem("Digite 'ajuda' se voce precisar de ajuda.");
        
        interfaceUsuario.continuarMensagem("");
        interfaceUsuario.continuarMensagem("Início...");
        
        
        exibirLocalizacaoAtual();
    }
    
    /**
     * Exibe a localização atual do personagem principal do jogo
     * e també as saídas possíveis.
     */
    private void exibirLocalizacaoAtual(){
        interfaceUsuario.continuarMensagem("Voce esta " + ambienteAtual.getDescricao());
    
        if(ambienteAtual!=ambienteFinal){
            interfaceUsuario.continuarMensagem("Saidas: " + ambienteAtual.getSaidas());
        }
        
        interfaceUsuario.continuarMensagem("");
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;
        boolean aux = false;

        if(comando.ehDesconhecido()) {
            interfaceUsuario.exibirMensagem("Eu nao entendi o que voce disse...");
            
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            aux = irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if (palavraDeComando.equals("observar")) {
            observar(comando);
        }
        else if (palavraDeComando.equals("pegar")) {
            pegar(comando);
        }
        else if (palavraDeComando.equals("usar")) {
            usar(comando);
        }
       
        
        //condição para sair do jogo de acordo com retorno do método irParaAmbiente()
        if(aux==true){
            querSair = true;
        }
        
        

        return querSair;
    }
    
    /**
     * Método para apresentar informações do ambiente e do personagem (o prisioneiro)
     * quando o comando recebido for "observar".
     */
    private void observar(Comando comando){
        interfaceUsuario.exibirMensagem(ambienteAtual.getDescricaoLonga());
        
         if(prisioneiro.itensDoPersonagem()==""){
           interfaceUsuario.continuarMensagem(prisioneiro.getNomePrisioneiro() + ", atualmente está com " + prisioneiro.getPontosDeVidaAtual() + " pontos de vida, e não está com nenhum item!" ); 
                  
         }
         else{
           interfaceUsuario.continuarMensagem(prisioneiro.getNomePrisioneiro() + ", atualmente está com " + prisioneiro.getPontosDeVidaAtual() + " pontos de vida, e está com os seguintes itens: ");
                 
           interfaceUsuario.continuarMensagem(prisioneiro.itensDoPersonagem());
         }
          
    }

    
    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando.
     */
    private void imprimirAjuda() 
    {
        interfaceUsuario.exibirMensagem("Voce é prisioneiro. Voce esta sozinho. Voce caminha pelo Castelo.");
        interfaceUsuario.continuarMensagem("");
        interfaceUsuario.continuarMensagem("Suas palavras de comando sao:");
        interfaceUsuario.continuarMensagem("   " + analisador.getComandos());
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private boolean irParaAmbiente(Comando comando) 
    {
      
       boolean jogoTerminou = false;
       Item item = null;
       String direcao = "";
       
       if(!comando.temSegundaPalavra()) {
         // se nao ha segunda palavra, nao sabemos pra onde ir...
         interfaceUsuario.exibirMensagem("Ir pra onde?");
         
       }
       
       else{
           
         direcao = comando.getSegundaPalavra();

          // Tenta sair do ambiente atual
         Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);
         
          if (proximoAmbiente == null) {
              interfaceUsuario.exibirMensagem("Nao ha passagem!");  
              
              interfaceUsuario.continuarMensagem("Dica: No entanto, se a direção escolhida aparece nas saídas possíveis, a passagem pode estar bloqueada,");
              
              interfaceUsuario.continuarMensagem("(ex: não há como subir, há alguma porta trancada, há algum personagem inimigo impedindo a passagem,...,etc)");
              
          }
        
          else{
              interfaceUsuario.ambienteAtualMudou(proximoAmbiente);
        
              //Se personagem chegou no ambiente final, o jogo termina.
             if(proximoAmbiente==ambienteFinal){
               interfaceUsuario.continuarMensagem("Voce esta " + ambienteFinal.getDescricao());
               
               interfaceUsuario.continuarMensagem("");
               
               interfaceUsuario.continuarMensagem("Parabéns você conseguiu fugir do castelo!\n");
               
               jogoTerminou=true; //encerra o jogo
               ranking.tempoDeJogo();
               ranking.incluirNoRanking(usuario);
            
             }
             
            else{
                
                  if (proximoAmbiente.temPersonagem()){
                      
                    item = prisioneiro.temOItem("espada");
                    
                       if(item!=null){//se prisioneiro tem item para lutar ele se defende automaticamente, não perde pontos de vida
                         ambienteAtual = proximoAmbiente;
                         interfaceUsuario.ambienteAtualMudou(ambienteAtual);
                         exibirLocalizacaoAtual();
                      }
                      else{//se prisioneironão não tem item para lutar perde pontos de vida
                         ambienteAtual = proximoAmbiente;
                         interfaceUsuario.ambienteAtualMudou(ambienteAtual);
                         exibirLocalizacaoAtual();
                         prisioneiro.recebeuAtaque();
                         interfaceUsuario.exibirMensagem("Você foi atacado e perdeu alguns pontos de vida!" + "\n" + "Atualmente você tem: " + prisioneiro.getPontosDeVidaAtual() + " pontos de vida.");

                          
                             if(prisioneiro.getPontosDeVidaAtual()==0){
                                  interfaceUsuario.continuarMensagem("");
                                  
                                  interfaceUsuario.continuarMensagem("Você foi morto pelo inimigo!");
                                  
                                  interfaceUsuario.continuarMensagem("");
                                  
                                  jogoTerminou=true; //encerra o jogo
                              }
                  
                      }
                  }
          
                  else{
                      ambienteAtual = proximoAmbiente;
                      interfaceUsuario.ambienteAtualMudou(ambienteAtual);
                      exibirLocalizacaoAtual();
                  }
            
             }
         
          }
       }
        return jogoTerminou;
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            interfaceUsuario.exibirMensagem("Sair o que?");
            
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
    
    /** 
     * "Pegar" foi digitado. Se existe um item pega-o, caso contrario imprime mensagem de erro.
     */
    private void pegar(Comando comando){
        Item item = null;
        
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos o que pegar...
            interfaceUsuario.exibirMensagem("Pegar o que?");
            
            return;
        }
        
        String nomeItem = comando.getSegundaPalavra();
        if(nomeItem.equals(ambienteAtual.getNomeItem())){
                   
          item = ambienteAtual.removeItem();
              
          prisioneiro.adicionarItemNoPersonagem(item);
              
          interfaceUsuario.jogadorPegouItem(item);
              
          interfaceUsuario.exibirMensagem("O item foi coletado!");
        }
        
        else{
            
          interfaceUsuario.exibirMensagem("Não há esse item no ambiente.");
        }
    }
            
    
    /** 
     * "Usar" foi digitado. Se tem um determinado item na lista de itens do personagem principal usa-o, 
     * caso contrario imprime mensagem de erro.
     * Itens podem ser usados para desbloquear passagens e/ou eliminar inimigos.
     */
    private void usar(Comando comando){
      
      //Item item = null;
      Item item = null;
        
      if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos o que usar...
            interfaceUsuario.exibirMensagem("Usar o que?");
            return;
        }
        
      String nomeItem = comando.getSegundaPalavra();
             
      item = prisioneiro.temOItem(nomeItem);
                
      if(item!=null){//verifica se prisioneiro tem determinado item
                 
         if(!item.ehUsoUnico()){ //se item não é de uso único, ele serve para eliminar personagens inimigos 
                    
            if(ambienteAtual.temPersonagem()){
                       
              eliminarInimigo();
            }
                     
            else{
              interfaceUsuario.exibirMensagem("Não há inimigos neste ambiente.");
                        
            }
                    
         }
                 
         else{//se item é de uso único, ele serve para desbloquear passagens
             
             if(ambienteAtual.usarItem(item)){
                        
                prisioneiro.removeDoPersonagem(item);
                interfaceUsuario.jogadorDescartouItem(item);
                interfaceUsuario.exibirMensagem("A passagem foi desbloqueada.");
                     
             }
                    
             else{
                interfaceUsuario.exibirMensagem("Este não é o item certo para desbloquear a passagem.");
                        
             }
         }
            
      }
          
      else{
        interfaceUsuario.exibirMensagem("O prisioneiro não possui esse item.");
               
      }
           
   }
    
    
    private void eliminarInimigo(){
         
         for(Personagem p : personagens){
             if(p.getFuncao().equals("guarda")){
                p.recebeuAtaque();
                interfaceUsuario.exibirMensagem("Você atacou o guarda, ele está com: " + p.getPontosDeVidaAtual() + " pontos de vida.");
                
                if(p.getPontosDeVidaAtual()==0){
                 
                 ambienteAtual.removePersonagem();
                 interfaceUsuario.continuarMensagem("O guarda foi eliminado!");
                 interfaceUsuario.continuarMensagem("");
                 interfaceUsuario.continuarMensagem("O caminho está liberado.");
                 
                 
                 
               }
            }
        }
    
   }
}