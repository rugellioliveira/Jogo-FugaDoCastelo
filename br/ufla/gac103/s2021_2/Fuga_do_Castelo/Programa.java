package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

/**
 * Classe que contém o método main cria uma instancia da Classe Jogo e chama o metodo
 *  "jogar()" para iniciar o jogo através do Terminal.
 * 
 */
public class Programa {

    public static void main(String[] args) {
        
        Terminal terminal = new Terminal(); //Cria objeto do tipo Terminal
        
        Jogo jogo = new Jogo(terminal);
        
        jogo.jogar();
    }

}

