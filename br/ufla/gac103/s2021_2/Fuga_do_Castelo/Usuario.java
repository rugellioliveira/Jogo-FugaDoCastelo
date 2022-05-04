package br.ufla.gac103.s2021_2.Fuga_do_Castelo;


/**
 * Esta classe faz parte da aplicação "Fuga do Castelo"
 * Ela representa um Usuário do jogo.
 * 
 * @author Rugelli Oliveira 
 * @version 2022.04.10
 */
public class Usuario
{
    //atributos
    private String nome;
    private long tempo; // em segundos

    /**
     * Construtor para objetos da classe Usuario
     */
    public Usuario(String nome)
    {
        this.nome = nome;
        tempo = 0;
    }

    //Método para retornar nome do usuário
    public String getNomeUsuario(){
        return nome;
    }
    
    //Método para setar tempo de jogo do usuario de acordo com o valor recebido por parametro
    public void setTempoJogo(long tempo)
    {
        this.tempo = tempo;
    }
    
    //Método para retornar tempo de jogo do usuário
    public long getTempoJogo()
    {
        return tempo;
    }
    
}
