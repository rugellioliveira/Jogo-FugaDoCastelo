package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

import br.ufla.gac103.s2021_2.baseJogo.EntidadeGrafica;

//imporata a classe HashMap
import java.util.HashMap;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "Fuga do Castelo". 
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como baixo, cima, norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author de "Fuga do Castelo"(Rugelli Oliveira)
 * @version 2022.03.01
*/



public class Ambiente extends EntidadeGrafica
{
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private Item item;
    private String saidaBloqueada;
    private String chaveDaSaidaBloqueada;
    private Personagem personagem;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    
    //Construtores
    //Cria ambiente com descrição
    public Ambiente(String descricao, String caminhoImagem ) 
    {
        super(caminhoImagem);
        this.descricao = descricao;
        this.saidaBloqueada = " ";
        this.chaveDaSaidaBloqueada = " ";
        saidas = new HashMap<String, Ambiente>();
    }
    
    //Cria ambiente com descrição, item e personagem inimigo
    public Ambiente(String descricao, Item item, Personagem personagemInimigo, String caminhoImagem ){
        this(descricao, caminhoImagem);
        this.item = item;
        this.personagem = personagemInimigo;
    }
    
    
    //método retorna nome de um item que está no ambiente, sem nao tiver nenhum retorna String vazia
    public String getNomeItem(){
       if(item!=null){
        return item.getNome();
       }
       else{
        return "";
       }
    }
    
   
    //método remove item do ambiente
    public Item removeItem(){
        Item aux = item;
        item = null;
        return aux;
    }
    
    //método retorna ambiente de acordo com a direção recebida por parâmetro
    public Ambiente getSaida(String direcao){
        if(direcao.equals(saidaBloqueada)){
          return null;
        }
        else{     
          return saidas.get(direcao);
        }
    } 
    
    //método verifica se há algum item no ambiente
    public boolean temItem(){
        if(item!=null){
            return true;
        }
        return false;
    }
    
    //método verifica se há algum inimigo no ambiente
    public boolean temPersonagem(){
        if(personagem!=null){
            return true;
        }
        
        return false;
    }
    
    //método remove personagem do ambiente
    public void removePersonagem(){
        personagem = null;
        saidaBloqueada = "";
        
    }
    
    
    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaida(String direcao, Ambiente ambiente) 
    {
        saidas.put(direcao, ambiente);
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }
    
    //método retorna decrição do ambiente, saídas e se há ou não itens e\ou personagens no ambiente
    public String getDescricaoLonga()
    {
        String desc = "Voce esta " + descricao + "\n" + "Saida(s): " + getSaidas();
        if(temItem() && !temPersonagem()){
            desc += "\n" + "Há um(a) " + item.getNome() + " ; " + item.getDescricao();
        }
        else if(temPersonagem() && !temItem()){
            desc += "\n" + "Há um(a) " + personagem.getDescricaoPersonagem() + 
            ", ele(a) possui um(a)" + personagem.itensDoPersonagem();
        }
        else if(temItem() && temPersonagem()){
            desc += "\n" + "Há um(a) " + item.getNome() + " ; " + item.getDescricao() + "\n" 
            + "Há também um(a) " + personagem.getDescricaoPersonagem() + ", ele(a) possui um(a)" + personagem.itensDoPersonagem();
        }
        else{
            desc += "\n" + "Não há nada aqui";
        }
        return desc;
    }

    //método retorna saidas de um ambiente
    public String getSaidas()
    {
        String saidasTexto = " ";
        for (String direcao : saidas.keySet()){
            saidasTexto = saidasTexto + direcao + " ";
        }
        return saidasTexto;
    }
    
    //método para bloquear uma saida do ambiente
    public void ajustarSaidaBloqueada(String direcaoBloqueada, Ambiente ambienteSelecionado, String nomeItemDesbloqueio){
        ajustarSaida(direcaoBloqueada, ambienteSelecionado);
        saidaBloqueada = direcaoBloqueada;
        chaveDaSaidaBloqueada = nomeItemDesbloqueio;
    }
    
    //método para usar um item no ambiente atual
    public boolean usarItem(Item item){
        if(item.getNome().equals(chaveDaSaidaBloqueada)){
            saidaBloqueada = "";
            return true;
        }
        else{
            return false;
        }
    }

    //sobrescrita do método getNome() presente na superclasse EntidadeGrafica
    @Override
    public String getNome(){       
        return getDescricao();
    }



}
