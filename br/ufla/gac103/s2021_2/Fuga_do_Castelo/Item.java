package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

import br.ufla.gac103.s2021_2.baseJogo.EntidadeGrafica;

/**
 * Esta classe eh parte da aplicacao "Fuga do Castelo".
 * 
 * Um item representa objetos ou artefatos que podem estar presentes
 * em um ou mais ambientes do jogo. Ele pode ser coletado e usado pelo
 * personagem do jogo (o prisioneiro).
 * 
 * @author Rugelli Oliveira
 * @version 2022.03.25
 */
public class Item extends EntidadeGrafica
{
    //atributos
    private String descricao;
    private String nome;
    private boolean usoUnico;

    /**
     * Construtor para objetos da classe Item
     */
    public Item(String nome,String descricao, boolean usoUnico, String caminhoImagem)
    {
        super(caminhoImagem);
        this.descricao = descricao;
        this.nome = nome;
        this.usoUnico = usoUnico;
    }
    
    //método retorna descrição de um item
    public String getDescricao(){
        return descricao;
    }
    
    //método retorna nome de um item
    public String NomeDoItem(){
        return nome;
    }
    
    //método retorna "true" ou "false" para saber se determinado item é de uso único
    public boolean ehUsoUnico(){
        return usoUnico;
    }
    
    //sobrescrita do método getNome() presente na superclasse EntidadeGrafica
    @Override
    public String getNome(){
       return nome;  
     }

}