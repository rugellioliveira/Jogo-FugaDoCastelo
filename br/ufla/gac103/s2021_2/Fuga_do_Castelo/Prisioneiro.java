package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

/**
 * Esta classe eh parte da aplicacao "Fuga do Castelo".
 * Ela representa o personagem Prisioneiro, ele pode se mover 
 * pelos ambientes. Além disso, o prisioneiro também pode pegar e usar itens
 * nos ambientes.
 * 
 * Prisioneiro herda da classe Personagem
 * 
 * @author Rugelli Oliveira 
 * @version 2022.03.25
 */


public class Prisioneiro extends Personagem
{
    //Definimos apenas os atributos que sao especificos do Prisioneiro
    private String nome;
    private static Prisioneiro instanciaUnica;

    /**
     * Construtor para objetos da classe Prisioneiro
     */
    public Prisioneiro(String nome)
    {
        super("prisioneiro","O prisioneiro tem experiência em combate");
        this.nome = nome;
        
    }
    
    /**
     * O método cria a instância apenas na primeira vez que é chamado. 
     *  A partir daí retorna a instância que havia sido criada antes.
     */
    public static Prisioneiro getInstancePrisioneiro(){
        
        if(instanciaUnica == null){
            instanciaUnica = new Prisioneiro("Bjorn");
        }
        
        return instanciaUnica;
    }

    //método retorna nome do personagem (o prisioneiro)
    public String getNomePrisioneiro(){
        return nome;
    }
    

}