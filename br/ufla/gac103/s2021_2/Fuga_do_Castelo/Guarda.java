package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

/**
 * Esta classe eh parte da aplicacao "Fuga do Castelo".
 * 
 * Ela representa um Guarda o personagem inimigo, ele fica apenas no ambiente em que foi criado.
 * Além disso, o guarda pode usar itens para tentar eliminar o prisioneiro.
 * 
 * Guarda herda da classe Personagem
 * 
 * @author Rugelli Oliveira 
 * @version 2022.03.25
 */

public class Guarda extends Personagem
{
    //Definimos apenas os atributos que sao especificos do Guarda
    private static int numeroUltimoGuarda = 1;
    private int numero;
    
    /**
     * Construtor para objetos da classe Guarda
     */
    public Guarda()
    {
        super("guarda"," personagem inimigo"); 
        this.numero = numeroUltimoGuarda++;
        
    }
    
    //Método para retorna o número de uma guarda
    public int getNumeroGuarda(){
        return numero;
    }
    
    /*
     * Sobrescreve o método getDescricaoPersonagem da superclasse (Personagem), 
     * acrescentando informações especificas do personagem Guarda
    */
    @Override
    public String getDescricaoPersonagem(){
        String descricao = super.getDescricaoPersonagem();
        
       
        descricao = descricao + ", eh um " + super.getFuncao();
        
        
        return descricao;
    }
    
}
