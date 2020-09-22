/*
Anuska Kepler Rehn
 */
import java.util.Arrays;

public class Trab1 {

    public static void main(String[] args) {

        int[][] minhaMatriz = geraMatriz();

        System.out.println("----- Trabalho 1 . Anuska -----\n");
        System.out.println("Matriz do grafo:\n");

        toStringMatriz(minhaMatriz);

        System.out.println(tipoDoGrafo(minhaMatriz));
        System.out.println(arestasDoGrafo(minhaMatriz));
        System.out.println(grausDoVertice(minhaMatriz));

    }

    private static int[][] geraMatriz() {
        int[][] matrix = new int[5][5];
        for (int x = 0; x < matrix.length; x++) {
            for (int i = 0; i < matrix[x].length; i++) {
                if ((x == 0) || (x == (matrix.length) - 1)) {
                    matrix[x][i] = 0;
                } else if ((i == 0) || (i == (matrix[x].length) - 1)) {
                    matrix[x][i] = 0;
                } else {
                    matrix[x][i] = 1;
                }
            }
        }
//        for (int x = 0; x < matrix.length; x++) {
//            for (int i = 0; i < matrix[x].length; i++) {
//                System.out.print(matrix[x][i]);
//            }
//            System.out.println();
//        }
        return matrix;
    }

    private static void toStringMatriz(int[][] matriz) {

        for (int x = 0; x < matriz.length; x++) {
            for (int i = 0; i < matriz[x].length; i++) {
                System.out.print(matriz[x][i]);
            }
            System.out.println();
        }
        System.out.println("");
    }

    private static boolean ehSimples(int[][] matriz) {
        if (arestaParalela(matriz)) {
            return true;
        } else if (loop(matriz)) {
            return true;
        }
        return false;
    }

    private static boolean ehDigrafo(int[][] matriz) {
        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (indiceLinha != indiceColuna) {
                    if (matriz[indiceLinha][indiceColuna] != matriz[indiceColuna][indiceLinha]) {
                        return true;
                    }
                }
            }
        }
        // Verificar exceção
        return false;
    }

    private static boolean ehCompleto(int[][] matriz) {
        // diagonal principal zerada e o resto 1, pq todos se relacionam com todos menos com si mesmos

        if (ehSimples(matriz)) {
            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    if (matriz[indiceLinha][indiceColuna] == 1) {
                        return true;
                    }
                }
            }
        } else {

        }

        return false;
    }

    private static boolean ehRegular(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            int valor = matriz[i][i];

            if (valor != 0) {
                return true;
            }
        }

        return false;
    }

    private static boolean loop(int[][] matriz) {

        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (indiceColuna == indiceLinha) {
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        return true;
                    }
                }
            }
        }

//        for (int i = 0; i < matriz.length; i++) {
//
//            int valor = matriz[i][i];
//
//            if (valor != 0) {
//                return true;
//            }
//        }
        return false;
    }

    private static boolean arestaParalela(int[][] matriz) {
        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (matriz[indiceLinha][indiceColuna] > 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean ehNulo(int[][] matriz) {
        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (matriz[indiceLinha][indiceColuna] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean ehBipartido(int[][] matriz) {
        if (matriz.length >= 3 && ehCompleto(matriz)) {
            return false;
        }
//        for (int indiceLinha = 0; indiceLinha < array.length; indiceLinha++) {
//            for (int indiceColuna = 0; indiceColuna < array.length; indiceColuna++) {
////                if () {
//                return false;
////                }
//            }
//        }
        return true;
    }

    private static String grauDigrafo(int[][] matriz) {
        String resposta = "";
        // grau de entrada e grau de saída de cada vértice

        return resposta;
    }

    private static String grauNaoDirigido(int[][] matriz) {
        String resposta = "";
        int[] grauPelaLinha = new int[matriz.length];

        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (indiceColuna == indiceLinha) { //se for na diagonal principal, é  dobro do valor, pois loop vale 2
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        grauPelaLinha[indiceLinha] += matriz[indiceLinha][indiceColuna] * 2;
                    }
                } else {
                    grauPelaLinha[indiceLinha] += matriz[indiceLinha][indiceColuna];
                }
            }
        }

        for (int i = 0; i < grauPelaLinha.length; i++) {
            resposta += "\tVértice " + i + " - Grau: " + grauPelaLinha[i] + "\n";
        }

        int[] sequenciaGraus;
        sequenciaGraus = grauPelaLinha;

        for (int i = 0; i < sequenciaGraus.length - 1; i++) {//bubble sort para ordenar e ter a sequência
            for (int j = 0; j < sequenciaGraus.length - 1 - i; j++) {
                if (sequenciaGraus[j] > sequenciaGraus[j + 1]) {
                    int aux = sequenciaGraus[j];
                    sequenciaGraus[j] = sequenciaGraus[j + 1];
                    sequenciaGraus[j + 1] = aux;
                }
            }
        }

        resposta += "\tSequência de graus: " + Arrays.toString(sequenciaGraus);
        return resposta;
    }

    private static String arestasGrafoSimples(int[][] matriz) {
        String conjuntoArestas = "";
        int countArestas = 0;

        if (!ehNulo(matriz)) {
            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        if (matriz[indiceLinha][indiceColuna] == 1) {
                            countArestas += matriz[indiceLinha][indiceColuna];
                            conjuntoArestas += indiceColuna + indiceLinha + ", ";
                        }
                    }
                }
            }
        } else {
            return "{} (Grafo nulo, portanto, não há arestas.)";
        }

        return "\tE = {" + conjuntoArestas + "}\n\t Totalizando " + countArestas + " arestas.";
    }

    private static String arestasMultigrafo(int[][] matriz) {
        String conjuntoArestas = "";
        int countArestas = 0;

        if (!ehNulo(matriz)) {
            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        if (matriz[indiceLinha][indiceColuna] == 1) {
                            countArestas += matriz[indiceLinha][indiceColuna];
                            conjuntoArestas += indiceColuna + indiceLinha + ", ";
                        } else if (matriz[indiceLinha][indiceColuna] > 1) {
                            //podem haver arestas paralelas, então preciso printar quantas vezes for preciso "AB, AB"

                            for (int i = matriz[indiceLinha][indiceColuna]; i < matriz[indiceLinha][indiceColuna]; i--) {;
                                countArestas += matriz[indiceLinha][indiceColuna];
                                conjuntoArestas += indiceColuna + indiceLinha + ", ";
                            }

                        }
                    }
                }
            }
        } else {
            return "{} (Grafo nulo, portanto, não há arestas.)";
        }

        return "\tE = {" + conjuntoArestas + "}\n\t Totalizando " + countArestas + " arestas.";
    }

    /*
    Questões
     */
    private static String tipoDoGrafo(int[][] matriz) {
        String resposta = "a. Tipo do grafo:\n";

        //drigido ou não
        if (ehDigrafo(matriz)) {
            resposta += "\tDigrafo (grafo dirigido)\n";
        } else {
            resposta += "\tNão dirigido\n";
        }

        // simples ou multigrafo
        if (ehSimples(matriz)) {
            resposta += "\tSimples\n";
        } else {
            resposta += "\tMultigrafo\n";
        }

        // regular
        if (ehRegular(matriz)) {
            resposta += "\tRegular\n";
        }

        // completo
        if (ehCompleto(matriz)) {
            resposta += "\tCompleto\n";
        }

        //nulo
        if (ehNulo(matriz)) {
            resposta += "\tNulo\n";
        }

        //bipartido
        if (ehBipartido(matriz)) {
            resposta += "\tBipartido\n";
        }

        return resposta;
    }

    private static String arestasDoGrafo(int[][] matriz) {
        String resposta = "b. Arestas:\n";

        if (ehSimples(matriz)) {
            resposta += arestasGrafoSimples(matriz);
        } else {
            resposta += arestasMultigrafo(matriz);
        }

        return resposta += "\n";
    }

    private static String grausDoVertice(int[][] matriz) {
        String resposta = "c. Graus: \n";
        if (ehDigrafo(matriz)) {
            resposta += grauDigrafo(matriz);
        } else {
            resposta += grauNaoDirigido(matriz);
        }
        return resposta += "\n";
    }

}
