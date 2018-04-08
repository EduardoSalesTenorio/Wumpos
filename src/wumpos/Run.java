package wumpos;

import static java.lang.Math.random;
import java.lang.reflect.Array;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Sales
 */
public class Run {

    Random aleatorio = new Random();

    String tabu[][] = new String[4][4];
    String tabuAgente[][] = new String[4][4];

    boolean ouro = false;
    int linha = 0;
    int coluna = 0;
    int linhaBriza = 0;
    int colunaBriza = 0;

    int linhaSegundo = 0;
    int colunaSegundo = 0;
    int linhaBrizaSegundo = 0;
    int colunaBrizaSegundo = 0;

    int linhaOuro = 0;
    int colunaOuro = 0;

    int linhaWumpus = 0;
    int colunaWumpus = 0;
    int linhaGrito = 0;
    int colunaGrito = 0;

    int[] agentePosicaoAtual = {0, 0};
    int[] agentePosicaoAnterior = {0, 0};

    public static void main(String[] args) {
        new Run().tabuleiro();
    }

    //Tabuleiros
    public void mostrarTabuleiro() {

        for (int i = 0; i < tabu.length; i++) {
            System.out.println("");
            for (int j = 0; j < tabu[i].length; j++) {
                System.out.print(tabu[i][j] + " | ");
            }
        }
    }

    public void mostrarAgente() {
        for (int i = 0; i < tabuAgente.length; i++) {
            System.out.println("");
            for (int j = 0; j < tabuAgente[i].length; j++) {
                System.out.print(tabuAgente[i][j] + " | ");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    //Criar tabuleiro
    public void tabuleiro() {

        poco();
        pocoSegundo();
        ouro();
        wumpus();

        for (int i = 0; i < tabu.length; i++) {
            for (int j = 0; j < tabu[i].length; j++) {

                if (i == linha && j == coluna || i == linhaSegundo && j == colunaSegundo) {
                    tabu[i][j] = "Poço";
                } else if (i == linhaOuro && j == colunaOuro) {
                    tabu[i][j] = "Ouro";
                } else if (i == linhaWumpus && j == colunaWumpus) {
                    tabu[i][j] = "Wumpus";
                } else {
                    tabu[i][j] = "    ";
                }

                if (i == linhaBriza + 1 && j == colunaBriza || i == linhaBrizaSegundo + 1 && j == colunaBrizaSegundo) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j]) || "Wumpus".equals(tabu[i][j])) {

                    } else {
                        tabu[i][j] = "Briza";
                    }
                }
                if (i == linhaBriza - 1 && j == colunaBriza || i == linhaBrizaSegundo - 1 && j == colunaBrizaSegundo) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j]) || "Wumpus".equals(tabu[i][j])) {

                    } else {
                        tabu[i][j] = "Briza";
                    }

                }
                if (i == linhaBriza && j == colunaBriza + 1 || i == linhaBrizaSegundo && j == colunaBrizaSegundo + 1) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j]) || "Wumpus".equals(tabu[i][j])) {

                    } else {
                        tabu[i][j] = "Briza";
                    }

                }
                if (i == linhaBriza && j == colunaBriza - 1 || i == linhaBrizaSegundo && j == colunaBrizaSegundo - 1) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j]) || "Wumpus".equals(tabu[i][j])) {

                    } else {
                        tabu[i][j] = "Briza";
                    }

                }

                //Wumpus
                if (i == linhaWumpus + 1 && j == colunaWumpus) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j])) {

                    } else if ("Briza".equals(tabu[i][j])) {
                        tabu[i][j] = "BG";
                    } else {
                        tabu[i][j] = "Grito";
                    }

                }

                if (i == linhaWumpus - 1 && j == colunaWumpus) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j])) {

                    } else if ("Briza".equals(tabu[i][j])) {
                        tabu[i][j] = "BG";
                    } else {
                        tabu[i][j] = "Grito";
                    }

                }

                // comentario
                // Detectar comentario
                if (i == linhaWumpus && j == colunaWumpus + 1) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j])) {

                    } else if ("Briza".equals(tabu[i][j])) {
                        tabu[i][j] = "BG";
                    } else {
                        tabu[i][j] = "Grito";
                    }

                }

                if (i == linhaWumpus && j == colunaWumpus - 1) {

                    if ("Poço".equals(tabu[i][j]) || "Ouro".equals(tabu[i][j])) {

                    } else if ("Briza".equals(tabu[i][j])) {
                        tabu[i][j] = "BG";
                    } else {
                        tabu[i][j] = "Grito";
                    }

                }
            }
        }

        //Metodo para mostrar tabuleiro
        mostrarTabuleiro();
        System.out.println("");
        agente();
    }

    //Manipulação e criação do Agente
    public void atualizarPosicao(int linha, int coluna) {

        agentePosicaoAnterior[0] = agentePosicaoAtual[0];
        agentePosicaoAnterior[1] = agentePosicaoAtual[1];

        tabuAgente[agentePosicaoAnterior[0]][agentePosicaoAnterior[1]] = "L";

        agentePosicaoAtual[0] = linha;
        agentePosicaoAtual[1] = coluna;
        tabuAgente[linha][coluna] = "Agente";
    }

    public void agente() {

        for (int i = 0; i < tabuAgente.length; i++) {
            for (int j = 0; j < tabuAgente[i].length; j++) {

                if (i == 0 && j == 0) {
                    tabuAgente[i][j] = "Agente";
                }

            }
        }

        while (ouro == false) {
            escolherCaminho();

        }
    }

    //Caminhos do agente
    public void escolherCaminho() {

        int a = aleatorio.nextInt(4);

        if (a == 0) {
            agenteDireita();
        }
        if (a == 1) {
            agenteEsquerda();
        }
        if (a == 2) {
            agenteCima();
        }
        if (a == 3) {
            agenteBaixo();
        }
    }

    public void agenteDireita() {
        int linha = 0;
        int coluna = 0;

        linha = agentePosicaoAtual[0];
        coluna = agentePosicaoAtual[1];

        coluna = coluna + 1;

        if (coluna > 0 && coluna < 4) {

            if ("Grito".equals(tabu[linha][coluna]) || "Briza".equals(tabu[linha][coluna]) || "BG".equals(tabu[linha][coluna])) {
                tabuAgente[linha][coluna] = "P";
            } else if ("Ouro".equals(tabu[linha][coluna])) {
                ouro = true;
                JOptionPane.showMessageDialog(null, "Achei o Ouro");
                atualizarPosicao(linha, coluna);
            } else if ("Poço".equals(tabu[linha][coluna]) || "Wumpus".equals(tabu[linha][coluna])) {
                atualizarPosicao(linha, coluna);
                perdeu();
            } else {
                atualizarPosicao(linha, coluna);
            }
            mostrarAgente();
        }
    }

    public void agenteEsquerda() {
        int linha = 0;
        int coluna = 0;

        linha = agentePosicaoAtual[0];
        coluna = agentePosicaoAtual[1];

        coluna = coluna - 1;

        if (coluna > 0 && coluna < 4) {

            if ("Grito".equals(tabu[linha][coluna]) || "Briza".equals(tabu[linha][coluna]) || "BG".equals(tabu[linha][coluna])) {
                tabuAgente[linha][coluna] = "P";
            } else if ("Ouro".equals(tabu[linha][coluna])) {
                ouro = true;
                JOptionPane.showMessageDialog(null, "Achei o Ouro");
                atualizarPosicao(linha, coluna);
            } else if ("Poço".equals(tabu[linha][coluna]) || "Wumpus".equals(tabu[linha][coluna])) {
                atualizarPosicao(linha, coluna);
                perdeu();
            } else {
                atualizarPosicao(linha, coluna);
            }
            mostrarAgente();
        }

    }

    public void agenteCima() {
        int linha = 0;
        int coluna = 0;

        linha = agentePosicaoAtual[0];
        coluna = agentePosicaoAtual[1];

        linha = linha - 1;

        if (linha > 0 && linha < 4) {

            if ("Grito".equals(tabu[linha][coluna]) || "Briza".equals(tabu[linha][coluna]) || "BG".equals(tabu[linha][coluna])) {
                tabuAgente[linha][coluna] = "P";
            } else if ("Ouro".equals(tabu[linha][coluna])) {
                ouro = true;
                JOptionPane.showMessageDialog(null, "Achei o Ouro");
                atualizarPosicao(linha, coluna);
            } else if ("Poço".equals(tabu[linha][coluna]) || "Wumpus".equals(tabu[linha][coluna])) {
                atualizarPosicao(linha, coluna);
                perdeu();
            } else {
                atualizarPosicao(linha, coluna);
            }
            mostrarAgente();
        }
    }

    public void agenteBaixo() {
        int linha = 0;
        int coluna = 0;

        linha = agentePosicaoAtual[0];
        coluna = agentePosicaoAtual[1];

        linha = linha + 1;

        if (linha > 0 && linha < 4) {

            if ("Grito".equals(tabu[linha][coluna]) || "Briza".equals(tabu[linha][coluna]) || "BG".equals(tabu[linha][coluna])) {
                tabuAgente[linha][coluna] = "P";
            } else if ("Ouro".equals(tabu[linha][coluna])) {
                ouro = true;
                JOptionPane.showMessageDialog(null, "Achei o Ouro");
                atualizarPosicao(linha, coluna);
            } else if ("Poço".equals(tabu[linha][coluna]) || "Wumpus".equals(tabu[linha][coluna])) {
                atualizarPosicao(linha, coluna);
                perdeu();
            } else {
                atualizarPosicao(linha, coluna);
            }
            mostrarAgente();
        }
    }

    //Caso Perder o Jogo
    public void perdeu() {
        JOptionPane.showMessageDialog(null, "Perdeu PlayBoy");
        System.exit(0);
    }

    //Objetos do tabuleiro
    public void poco() {

        while (linha == 0 && coluna == 0) {
            linha = aleatorio.nextInt(4);
            coluna = aleatorio.nextInt(4);
        }

        linhaBriza = linha;
        colunaBriza = coluna;

    }

    public void pocoSegundo() {

        while (linhaSegundo == 0 && colunaSegundo == 0 || linhaSegundo == linha && colunaSegundo == coluna) {
            linhaSegundo = aleatorio.nextInt(4);
            colunaSegundo = aleatorio.nextInt(4);
        }

//        while (linhaSegundo == linha && colunaSegundo == coluna) {
//            linhaSegundo = aleatorio.nextInt(4);
//            colunaSegundo = aleatorio.nextInt(4);
//        }
        linhaBrizaSegundo = linhaSegundo;
        colunaBrizaSegundo = colunaSegundo;

    }

    public void ouro() {

        while (linhaOuro == 0 && colunaOuro == 0 || linha == linhaOuro && coluna == colunaOuro || linhaSegundo == linhaOuro && colunaSegundo == colunaOuro) {
            linhaOuro = aleatorio.nextInt(4);
            colunaOuro = aleatorio.nextInt(4);
        }

//        while (linha == linhaOuro && coluna == colunaOuro || linhaSegundo == linhaOuro && colunaSegundo == colunaOuro && linhaOuro == 0 && colunaOuro == 0) {
//            linhaOuro = aleatorio.nextInt(4);
//            colunaOuro = aleatorio.nextInt(4);
//        }
    }

    public void wumpus() {
        while (linhaWumpus == 0 && colunaWumpus == 0 || linha == linhaWumpus && coluna == colunaWumpus || linhaSegundo == linhaWumpus && colunaSegundo == colunaWumpus || linhaOuro == linhaWumpus && colunaOuro == colunaWumpus) {
            linhaWumpus = aleatorio.nextInt(4);
            colunaWumpus = aleatorio.nextInt(4);
        }

//        while (linha == linhaWumpus && coluna == colunaWumpus || linhaSegundo == linhaWumpus && colunaSegundo == colunaWumpus && linhaOuro == linhaWumpus && colunaOuro == colunaWumpus && linhaWumpus == 0 && colunaWumpus == 0) {
//            linhaWumpus = aleatorio.nextInt(4);
//            colunaWumpus = aleatorio.nextInt(4);
//        }
    }

}
