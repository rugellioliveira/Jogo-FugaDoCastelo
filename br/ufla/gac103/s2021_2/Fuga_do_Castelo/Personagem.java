package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

//importa a classe ArrayList
import java.util.ArrayList;

/**
 * Esta classe faz parte da aplicação "Fuga do Castelo"
 * Ela representa um personagem no jogo.
 * 
 * @author Rugelli Oliveira 
 * @version 2022.03.25
 */

public class Personagem
{
    //Definimos atributos comuns aos personagens
    private String descricaoPersonagem;
    private String funcao;
    private static int pontosDeVidaInicial = 10;
    private int pontosDeVidaAtual;
    private ArrayList<Item> itensObtidos ;
    
    //Constroi objeto Animal com base nos parâmetros passados
    public Personagem(String funcao, String descricaoPersonagem){
        this.funcao = funcao;
        this.descricaoPersonagem = descricaoPersonagem;
        this.pontosDeVidaAtual = pontosDeVidaInicial;
        this.itensObtidos = new ArrayList<Item>();
        
    }
    
    //método retorna funçao de um personagem do jogo
    public String getFuncao(){
        return funcao;
    }
    
    //método retorna pontos de vida de um personagem do jogo
    public int getPontosDeVidaAtual(){
        return pontosDeVidaAtual;
    }
    
    //método adiciona item na bolsa do prisioneiro
    public void adicionarItemNoPersonagem(Item novoItem){
        
        itensObtidos.add(novoItem);
    
    }
    
    //método remove item da bolsa do prisioneiro
    public void removeDoPersonagem(Item itemARemover){
        
        if(itensObtidos!=null)
        {
          int indice =-1;
          
          for(int i=0; i<itensObtidos.size();i++){
             if(itemARemover.getNome().equals(itensObtidos.get(i).getNome())){
               indice = i;
             }
          }
        
           if(indice!=-1){
              
              itensObtidos.remove(indice);
           }
        
    
        }  
       
    }
    
    //método retorna nome dos itens na bolsa do prisioneiro
    public String itensDoPersonagem(){
        
        String nomeDosItens = "";
        
        if(itensObtidos!=null){
         for(int i=0; i<itensObtidos.size(); i++)
         {
            nomeDosItens += itensObtidos.get(i).getNome() + ", ";
         }   
        }
        
        return nomeDosItens;
    }
    
    //método verifica se o prisioneiro tem determinado item
    public Item temOItem(String nomeItemX){
        
        Item itemSelecionado = null;
        for(int i=0; i<itensObtidos.size(); i++)
        {
            if(itensObtidos.get(i).getNome().equals(nomeItemX)){ 
                itemSelecionado = itensObtidos.get(i);
            }
        }
        
        return itemSelecionado;
    }
    
    //Método retorna String contendo informações comuns dos personagens do jogo
    public String getDescricaoPersonagem(){
        return descricaoPersonagem + ", atualmente ele tem " + pontosDeVidaAtual + " pontos de vida";
    }
    
    //Método reduz pontos de vida se personagem receber um ataque
    public void recebeuAtaque(){
      pontosDeVidaAtual = pontosDeVidaAtual - 5;
        
    }
    
}