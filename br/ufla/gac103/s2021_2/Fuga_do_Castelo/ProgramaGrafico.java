package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

import br.ufla.gac103.s2021_2.baseJogo.Tela;

/**
 *  Classe que contém o método main cria uma instancia da Classe Jogo e chama o metodo
 *  "jogar()" para iniciar o jogo através da Tela (interface gráfica).
 * 
 * @author Rugelli Oliveira 
 * @version 2022.04.20
 */
 
public class ProgramaGrafico 
{
    
    public static void main(String[] args) {
            
        Tela tela = new Tela("Fuga do Castelo"); //cria objeto do tipo Tela e passa nome do jogo como parâmetro
        
        Jogo jogo = new Jogo(tela); 
        
        jogo.jogar();
    }
   

}
