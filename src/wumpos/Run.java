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

    public void agente() {

        for (int i = 0; i < tabuAgente.length; i++) {
            for (int j = 0; j < tabuAgente[i].length; j++) {

                if (i == 0 && j == 0) {
                    tabuAgente[i][j] = "Agente";
                }
            }
        }

        tabuAgente[0][0] = "Agente";
        mostrarAgente();

        while (ouro == false) {

            if ("Ouro".equals(tabu[agentePosicaoAtual[0]][agentePosicaoAtual[1]])) {
                tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "Ouro";
                acheiOuro();
            } else if ("Briza".equals(tabu[agentePosicaoAtual[0]][agentePosicaoAtual[1]])) {
                tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "P";
            } else if ("Grito".equals(tabu[agentePosicaoAtual[0]][agentePosicaoAtual[1]])) {
                tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "P";
            } else if ("Poço".equals(tabu[agentePosicaoAtual[0]][agentePosicaoAtual[1]]) || "Wumpos".equals(tabu[agentePosicaoAtual[0]][agentePosicaoAtual[1]])) {
                tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "Morreu";
                mostrarAgente();
                perdeu();
            } else {
                tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "L";
            }

            if (ouro == false) {
                escolherCaminho();
            }
            mostrarAgente();
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

        agentePosicaoAtual[1] += 1;

        if (agentePosicaoAtual[1] >= 0 && agentePosicaoAtual[1] <= 3) {
            System.out.println("Direita");
            tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "Agente";
        } else {
            agentePosicaoAtual[1] -= 1;
            escolherCaminho();
        }

    }

    public void agenteEsquerda() {

        agentePosicaoAtual[1] -= 1;

        if (agentePosicaoAtual[1] >= 0 && agentePosicaoAtual[1] <= 3) {
            System.out.println("Esquerda");
            tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "Agente";
        } else {
            agentePosicaoAtual[1] += 1;
            escolherCaminho();
        }
    }

    public void agenteCima() {

        agentePosicaoAtual[0] -= 1;

        if (agentePosicaoAtual[0] >= 0 && agentePosicaoAtual[0] <= 3) {
            System.out.println("Cima");
            tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "Agente";
        } else {
            agentePosicaoAtual[0] += 1;
            escolherCaminho();
        }
    }

    public void agenteBaixo() {
        agentePosicaoAtual[0] += 1;

        if (agentePosicaoAtual[0] >= 0 && agentePosicaoAtual[0] <= 3) {
            System.out.println("Baixo");
            tabuAgente[agentePosicaoAtual[0]][agentePosicaoAtual[1]] = "Agente";
        } else {
            agentePosicaoAtual[0] -= 1;
            escolherCaminho();
        }
    }

    //Achados
    public void perdeu() {
        JOptionPane.showMessageDialog(null, "Perdeu PlayBoy");
        System.exit(0);
    }

    public void acheiOuro() {
        ouro = true;
        JOptionPane.showMessageDialog(null, "Estou Rico");
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

        linhaBrizaSegundo = linhaSegundo;
        colunaBrizaSegundo = colunaSegundo;
    }

    public void ouro() {
        while (linhaOuro == 0 && colunaOuro == 0 || linha == linhaOuro && coluna == colunaOuro || linhaSegundo == linhaOuro && colunaSegundo == colunaOuro) {
            linhaOuro = aleatorio.nextInt(4);
            colunaOuro = aleatorio.nextInt(4);
        }
    }

    public void wumpus() {
        while (linhaWumpus == 0 && colunaWumpus == 0 || linha == linhaWumpus && coluna == colunaWumpus || linhaSegundo == linhaWumpus && colunaSegundo == colunaWumpus || linhaOuro == linhaWumpus && colunaOuro == colunaWumpus) {
            linhaWumpus = aleatorio.nextInt(4);
            colunaWumpus = aleatorio.nextInt(4);
        }
    }
}
