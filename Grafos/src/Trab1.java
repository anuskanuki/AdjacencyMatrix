
import java.util.Arrays;

/*
Anuska Kepler Rehn
 */
public class Trab1 {

    public static void main(String[] args) {

        int[][] minhaMatriz = geraMatriz();

        System.out.println("-------- Trabalho 1 . Anuska --------\n");
        System.out.println("Matriz do grafo:\n");

        toStringMatriz(minhaMatriz);

        System.out.println(tipoDoGrafo(minhaMatriz));
        System.out.println(arestasDoGrafo(minhaMatriz));
        System.out.println(grausDoVertice(minhaMatriz));
    }

    /*
    Matriz 
     */
    private static int[][] geraMatriz() {

        // Grafo não dirigido, multigrafo, graus 0,0,4,4,4
        int[][] matriz0 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};

        // Grafo simples, não dirigido, grus 2,2,2,3,3 
        int[][] matriz1 = {{0, 0, 1, 1, 1}, {0, 0, 1, 1, 1}, {1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}};

        // Grafo simples, não dirigido, graus 1,1,2,2,3,3
        int[][] matriz2 = {{0, 1, 1, 1, 0, 0}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 0}, {1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0}};

        // Grafo multigrafo, não dirigido, graus 2,4,5,5, arestas 0-0, 0-1, 0-2, 0-3, 1-2, 1-0, 2-0 3-0, 2-1
        int[][] matriz3 = {{1, 1, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 3}, {1, 0, 3, 0}};

        // Grafo dirigido, 
        int[][] matriz4 = {{0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 1}, {1, 2, 1, 0}};

        return matriz4;
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

    /*
    simples ou miltigrafo
     */
    private static boolean ehMultigrafo(int[][] matriz) {
        return arestaParalela(matriz) || loop(matriz);
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

    /*
    dirigido ou não dirigido
     */
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
        // Verificar exceção tipo a do K3? ---> não
        return false;
    }

    /*
    Grau
     */
    private static String grauDigrafo(int[][] matriz) {
        // Em um digrafo (grafo dirigido) os vértices possuem grau de entrada (coluna matriz) e grau de saída (linha matriz)

        String resposta = "";
        int[] grauLinhaSaida = new int[matriz.length];
        int[] grauColunaEntrada = new int[matriz.length];

        //saída - LINHA
        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (indiceColuna == indiceLinha) { //se for na diagonal principal, é  dobro do valor, pois loop vale 2
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        grauLinhaSaida[indiceLinha] += matriz[indiceLinha][indiceColuna] * 2;
                    }
                } else {
                    grauLinhaSaida[indiceLinha] += matriz[indiceLinha][indiceColuna];
                }
            }
        }

        //entrada - COLUNA
        for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
            for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                if (indiceColuna == indiceLinha) { //loop vale 2
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        grauColunaEntrada[indiceColuna] += matriz[indiceLinha][indiceColuna] * 2;
                    }
                } else {
                    grauColunaEntrada[indiceColuna] += matriz[indiceLinha][indiceColuna];
                }
            }
        }

        //mostrar os resultados - vértice X tem grau Y
        for (int i = 0; i < grauLinhaSaida.length; i++) {
            resposta += "\tVértice " + i + " - Grau de Saída: " + grauLinhaSaida[i] + "\n";
        }

        for (int i = 0; i < grauColunaEntrada.length; i++) {
            resposta += "\tVértice " + i + " - Grau de Entrada: " + grauColunaEntrada[i] + "\n";
        }

        //Sequência de graus:
        int[] sequenciaGrausSaida;
        sequenciaGrausSaida = grauLinhaSaida;

        int[] sequenciaGrausEntrada;
        sequenciaGrausEntrada = grauColunaEntrada;

        //achar a seguencia - ordenar os graus em ordem crescente
        for (int i = 0; i < sequenciaGrausSaida.length - 1; i++) {
            for (int j = 0; j < sequenciaGrausSaida.length - 1 - i; j++) {
                if (sequenciaGrausSaida[j] > sequenciaGrausSaida[j + 1]) {
                    int aux = sequenciaGrausSaida[j];
                    sequenciaGrausSaida[j] = sequenciaGrausSaida[j + 1];
                    sequenciaGrausSaida[j + 1] = aux;
                }
            }
        }

        for (int i = 0; i < sequenciaGrausEntrada.length - 1; i++) {
            for (int j = 0; j < sequenciaGrausEntrada.length - 1 - i; j++) {
                if (sequenciaGrausEntrada[j] > sequenciaGrausEntrada[j + 1]) {
                    int aux = sequenciaGrausEntrada[j];
                    sequenciaGrausEntrada[j] = sequenciaGrausEntrada[j + 1];
                    sequenciaGrausEntrada[j + 1] = aux;
                }
            }
        }

        resposta += "\tSequência de graus de saída: " + Arrays.toString(sequenciaGrausSaida);
        resposta += "\n\tSequência de graus de entrada: " + Arrays.toString(sequenciaGrausEntrada);
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

    /*
    Arestas
     */
    private static String arestasGrafoSimples(int[][] matriz) {
        String conjuntoArestas = "";
        int countArestas = 0;

        if (!ehNulo(matriz)) {
            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    if (matriz[indiceLinha][indiceColuna] > 0) {
                        countArestas += matriz[indiceLinha][indiceColuna];
                        conjuntoArestas += indiceColuna + "-" + indiceLinha + ", ";
                    }
                }
            }
        } else {
            return "{} (Grafo nulo, portanto, não há arestas.)";
        }
        return "\tE = {" + conjuntoArestas + "}\n\tTotalizando " + countArestas + " arestas.";
    }

    private static String arestasMultigrafo(int[][] matriz) {
        String conjuntoArestas = "";
        int countArestas = 0;

        if (!ehNulo(matriz)) {
            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    int valorMatriz = matriz[indiceLinha][indiceColuna];

                    if (valorMatriz > 0) {
                        countArestas += matriz[indiceLinha][indiceColuna];
                        if (valorMatriz == 1) {
                            conjuntoArestas += indiceColuna + "-" + indiceLinha + ", ";

                        } else if (valorMatriz > 1) { //incrementar corretamente arestas paralelas
                            for (int i = 1; i <= valorMatriz;) {
                                conjuntoArestas += indiceColuna + "-" + indiceLinha + ", ";
                                i++;
                            }
                        }
                    }
                }
            }
        } else {
            return "{} (Grafo nulo, portanto, não há arestas.)";
        }
        return "\tE = {" + conjuntoArestas + "}\n\tTotalizando " + countArestas + " arestas.";
    }

    /*
    Completo
     */
    private static boolean ehCompleto(int[][] matriz) {
        // diagonal principal zerada e o resto 1, pq todos se relacionam com todos, menos com si mesmos

        if (!loop(matriz)) {// não pode haver loop pois não deve se relacionar consigo mesmo

            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    if (matriz[indiceLinha][indiceColuna] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    Regular
     */
    private static boolean ehRegular(int[][] matriz) {
        String resposta = "";

        if (ehCompleto(matriz)) {
            return true;
        }
        if (ehNulo(matriz)) {
            return true;
        }

        if (!ehDigrafo(matriz)) {

            int[] grausPorLinha = new int[matriz.length];

            for (int indiceLinha = 0; indiceLinha < matriz.length; indiceLinha++) {
                for (int indiceColuna = 0; indiceColuna < matriz.length; indiceColuna++) {
                    if (indiceColuna == indiceLinha) { // loop => *2
                        if (matriz[indiceLinha][indiceColuna] > 0) {
                            grausPorLinha[indiceLinha] += matriz[indiceLinha][indiceColuna] * 2;
                        }
                    } else {
                        grausPorLinha[indiceLinha] += matriz[indiceLinha][indiceColuna];
                    }
                }
            }
            int primeiroElemento = grausPorLinha[0];

            for (int i = 1; i < grausPorLinha.length; i++) {
                if (grausPorLinha[i] != (primeiroElemento)) {
                    return false;
                }
            }
            return true;
        } else {
            if (ehNulo(matriz)) {
                return true;
            }
            // se for dirigido vejo de uma forma diferente pois há jeitos de graus diferentes
        }

        return false;
    }

    /*
    Nulo
     */
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

    /*
    Bipartido
     */
    private static boolean ehBipartido(int[][] matriz) {
        if (matriz.length >= 3 && ehCompleto(matriz)) {
            return false;
        }

//        
//        for (int indiceLinha = 0; indiceLinha < array.length; indiceLinha++) {
//            for (int indiceColuna = 0; indiceColuna < array.length; indiceColuna++) {
////                if () {
//                return false;
////                }
//            }
//        }
        return true;
    }

    /*
    Questões
     */
    private static String tipoDoGrafo(int[][] matriz) {
        String resposta = "a. Tipo do grafo:\n";

        //drigido ou não
        if (ehDigrafo(matriz)) {
            resposta += "\t.Digrafo (grafo dirigido)\n";
        } else {
            resposta += "\t.Não dirigido\n";
        }

        // simples ou multigrafo
        if (ehMultigrafo(matriz)) {
            resposta += "\t.Multigrafo\n";
        } else {
            resposta += "\t.Simples\n";
        }

        // regular
        if (ehRegular(matriz)) {
            resposta += "\t.Regular\n";
        }

        // completo
        if (ehCompleto(matriz)) {
            resposta += "\t.Completo\n";
        }

        //nulo
        if (ehNulo(matriz)) {
            resposta += "\t.Nulo\n";
        }

        //bipartido
        if (ehBipartido(matriz)) {
            resposta += "\t.Bipartido\n";
        }

        return resposta;
    }

    private static String arestasDoGrafo(int[][] matriz) {
        String resposta = "b. Arestas:\n\tSendo 'E' o conjunto de arestas pelos índices da matriz Xij, sendo 'i' índiceColuna e 'j' índiceLinha, iniciando do zero:\n";

        if (ehMultigrafo(matriz)) {
            resposta += arestasMultigrafo(matriz);
        } else {
            resposta += arestasGrafoSimples(matriz);
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
