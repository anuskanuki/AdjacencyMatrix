/*
ANUSKA KEPLER E BRUNA SHROEDER
 */

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Ratinho {

    static String BRANCO = "BRANCO";
    static String PRETO = "PRETO";
    static String CINZA = "CINZA";

    public static class Distancia {

        int valor;
        String pai;
        List<String> filhos;
        String cor;

        public Distancia(int valor) {
            cor = BRANCO;
            this.valor = valor;
            this.filhos = new ArrayList<>();
        }

        public Distancia(String pai, int valor) {
            cor = BRANCO;
            this.pai = pai;
            this.valor = valor;
            this.filhos = new ArrayList<>();
        }
    }

    static List<String> listaPontos;
    static List<Distancia> listaDistancias;
    static int INFINITO = 99999;
    static int distancias[];

    /**
     * Metodo que utiliza a busca em largura (bfs) para calcular a distancia de
     * um vertice v para todos os outros vertices no grafo e armazena as
     * distancias encontradas no vetor 'distancias', ou seja, a distancia do
     * vertice vi para o vertice vj e igual a distancias[j].
     *
     * @param grafo
     * @param vertice
     */
//    public static void calculaDistanciaUsandoBuscaEmLargura(Grafo grafo, Vertice vertice) {
//        int numeroVertices = grafo.getNumeroVertices();
//
//        listaDistancias = new ArrayList<>(numeroVertices);
//
//        distancias = new int[numeroVertices];
//
//        for (int i = 0; i < numeroVertices; i++) {
////            distancias[i] = INFINITO;
//            listaDistancias.add(new Distancia(INFINITO));
//        }
//
//        Queue<Vertice> fila = new LinkedList<>();
//
//        listaDistancias.add(new Distancia(vertice, 0));
//
//        distancias[vertice.getChave()] = 0;
//        fila.add(vertice);
//        while (!fila.isEmpty()) {
//            Vertice pivo = fila.remove();
//            for (int k = 0; k < numeroVertices; k++) {
//                if (grafo.existeAresta(pivo.getChave(), k) && distancias[k] == INFINITO) {
//                    distancias[k] = distancias[pivo.getChave()] + 1;
//                    fila.add(grafo.getVertices().get(k));
//                }
//            }
//        }
//    }

    public static Distancia buscarDistancia(String chave) {
        for (Distancia d : listaDistancias) {
            if (d.pai.equals(chave)) {
                return d;
            }
        }

        return null;
    }

    public static void calcularDistancia(GrafoTeste grafo, String vertice) {
        int numeroDeVertices = grafo.vertices.size();

        grafo.vertices.forEach((v) -> {
            listaDistancias.add(new Distancia(v.chave, INFINITO));
        });

        Queue<String> fila = new LinkedList<>();
        fila.add(vertice);
        while (!fila.isEmpty()) {
            String pivo = fila.remove();
            for (int i = 0; i <= numeroDeVertices; i++) {
                String pontoB = grafo.vertices.get(i).chave;
                if (!pivo.equals(pontoB)) {
                    Distancia distanciaPontoB = buscarDistancia(pontoB);
                    if (grafo.existeAresta(pivo, pontoB) && distanciaPontoB.valor == INFINITO) {
                        Distancia distanciaPivo = buscarDistancia(pivo);
                        distanciaPontoB.valor = distanciaPivo.valor + 1;
                        distanciaPivo.filhos.add(pontoB);
                        fila.add(pontoB);
                    }
                }
            }

        }
    }

    /**
     * Metodo usado para calcular a distancia entre dois vertices no grafo.
     *
     * @param g
     * @param i
     * @param j
     */
    public static void distanciaEntreDoisVertcies(Grafo g, int i, int j) {
        calculaDistanciaUsandoBuscaEmLargura(g, g.getVertices().get(i));
        if (distancias[j] == INFINITO) {
            System.out.println("\n Nao existe um caminho entre o vertice (" + i + ") e o vertice (" + j + ")");
        } else {
            System.out.println("\n A distancia entre v(" + i + ") e v(" + j + ") e': " + distancias[j]);
        }
        System.out.print("\n");
    }

    public static void distanciaEntreDois(String pontoA, String pontoB) {

    }

    /**
     * Classe que implementa o Grafo 'G'
     *
     */
    public static class Grafo {

        List<Vertice> vertices;
        int numeroVertices;

        public Grafo(int numeroVertices) {
            this.numeroVertices = numeroVertices; // PONTOS
            this.vertices = new ArrayList<Vertice>(0);

//            for (int i = 0; i < numeroVertices; i++) {
//                this.vertices.add(new Vertice(i));
//            }
        }

        public void insereVertice(String nomeVertice) {
            this.vertices.add(new Vertice(nomeVertice));
        }

        public void insereAresta(String pontoA, String pontoB) {
//            Vertice v1 = this.vertices.get(i);
//            Vertice v2 = this.vertices.get(j);
//            v1.listaAdj.add(v2);
            //v2.listaAdj.add(v1); //Para não orientado

            Vertice vertice1 = buscarVertice(pontoA);
            Vertice vertice2 = buscarVertice(pontoB);
            vertice1.listaAdj.add(vertice2);
        }

        private Vertice buscarVertice(String nomeVertice) {
            for (Vertice v : this.vertices) {
                if (v.chave == nomeVertice) {
                    return v;
                }
            }
            return null;
        }

        public boolean existeAresta(String i, String j) {
            for (Vertice v : this.vertices) {
                if (v.chave == i) {
                    return v.existeAresta(j);
                }
            }
            return false;
        }

        public int getNumeroVertices() {
            return this.numeroVertices;
        }

        public List<Vertice> getVertices() {
            return this.vertices;
        }
    }

    public static class GrafoTeste {

        List<Vertice> vertices;

        public GrafoTeste(List<Vertice> vertices) {
            this.vertices = vertices;
        }

        public void insereAresta(String pontoA, String pontoB) {
            Vertice vertice1 = buscarVertice(pontoA);
            Vertice vertice2 = buscarVertice(pontoB);
            vertice1.listaAdj.add(vertice2);
        }

        private Vertice buscarVertice(String nomeVertice) {
            for (Vertice v : this.vertices) {
                if (v.chave.equals(nomeVertice)) {
                    return v;
                }
            }
            return null;
        }

        public boolean existeAresta(String pontoA, String pontoB) {
            for (Vertice v : this.vertices) {
                if (v.chave.equals(pontoA)) {
                    return v.existeAresta(pontoB);
                }
            }
            return false;
        }
    }

    /**
     * Classe que implementa um vertice do grafo.
     *
     */
    public static class Vertice {

        // int marca;
        String chave;
        List<Vertice> listaAdj;

        public Vertice(String chave) {
            this.chave = chave;
            this.listaAdj = new ArrayList<>();
        }

        public boolean existeAresta(String pontoB) {
            for (Vertice v : listaAdj) {
                if (v.chave.equals(pontoB)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return chave + "";
        }

        public String getChave() {
            return this.chave;
        }

        public List<Vertice> getListaAdj() {
            return this.listaAdj;
        }
    }

    public static void main(String[] args) {

//        listaPontos.add("Entrada");
//        listaPontos.add("A");
//        listaPontos.add("B");
//        listaPontos.add("C");
//        listaPontos.add("D");
//        listaPontos.add("E");
//        listaPontos.add("F");
//        listaPontos.add("G");
//        listaPontos.add("H");
//        listaPontos.add("I");
//        listaPontos.add("J");
//        listaPontos.add("K");
//        listaPontos.add("L");
//        listaPontos.add("M");
//        listaPontos.add("*");
//        listaPontos.add("Saida");
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice("Entrada"));
        vertices.add(new Vertice("A"));
        vertices.add(new Vertice("B"));
        vertices.add(new Vertice("C"));
        vertices.add(new Vertice("D"));
        vertices.add(new Vertice("E"));
        vertices.add(new Vertice("F"));
        vertices.add(new Vertice("G"));
        vertices.add(new Vertice("H"));
        vertices.add(new Vertice("I"));
        vertices.add(new Vertice("J"));
        vertices.add(new Vertice("K"));
        vertices.add(new Vertice("L"));
        vertices.add(new Vertice("M"));
        vertices.add(new Vertice("*"));
        vertices.add(new Vertice("Saida"));

        GrafoTeste teste = new GrafoTeste(vertices);
        teste.insereAresta("Entrada", "A");
        teste.insereAresta("A", "F");
        teste.insereAresta("F", "C");
        teste.insereAresta("C", "B");
        teste.insereAresta("B", "D");
        teste.insereAresta("C", "D");
        teste.insereAresta("F", "J");
        teste.insereAresta("J", "H");
        teste.insereAresta("H", "G");
        teste.insereAresta("J", "G");
        teste.insereAresta("J", "*");
        teste.insereAresta("*", "I");
        teste.insereAresta("I", "L");
        teste.insereAresta("L", "M");
        teste.insereAresta("M", "K");
        teste.insereAresta("K", "Saida");
        teste.insereAresta("A", "K");
        teste.insereAresta("C", "E");
        teste.insereAresta("E", "I");
        teste.insereAresta("I", "M");

//        Grafo digrafo = new Grafo(10);
        Scanner scan = new Scanner(System.in);

//        digrafo.insereAresta("Entrada", "A");
//        digrafo.insereAresta("A", "F");
//        digrafo.insereAresta("F", "C");
//        digrafo.insereAresta("C", "B");
//        digrafo.insereAresta("B", "D");
//        digrafo.insereAresta("C", "D");
//        digrafo.insereAresta("F", "J");
//        digrafo.insereAresta("J", "H");
//        digrafo.insereAresta("H", "G");
//        digrafo.insereAresta("J", "G");
//        digrafo.insereAresta("J", "*");
//        digrafo.insereAresta("*", "I");
//        digrafo.insereAresta("I", "L");
//        digrafo.insereAresta("L", "M");
//        digrafo.insereAresta("M", "K");
//        digrafo.insereAresta("K", "Saida");
//        digrafo.insereAresta("A", "K");
//        digrafo.insereAresta("C", "E");
//        digrafo.insereAresta("E", "I");
//        digrafo.insereAresta("I", "M");
        System.out.print("\n Qual o primeiro vertice? - VÉRTICE DA ENTRADA ");
        String verticeEntrada = scan.next();

        while (!listaPontos.contains(verticeEntrada)) {
            System.out.println("\n Vertice invalido!");
            System.out.print("\n Qual o primeiro vertice? - VÉRTICE DA ENTRADA ");
            verticeEntrada = scan.next();
        };

        System.out.print(" Qual o segundo vertice? - VÉRTICE DA SAÍDA");
        String verticeSaida = scan.next();

        while (!listaPontos.contains(verticeSaida)) {
            System.out.println("\n Vertice invalido!");
            System.out.print(" Qual o segundo vertice? - VÉRTICE DA SAÍDA");
            verticeSaida = scan.next();
        }
        distanciaEntreDoisVertcies(digrafo, verticeEntrada, verticeSaida);
    }

}

/*
        POSSO FAZER DO NÚMERO 8 O ASTERISCO, PRA QUE QUANDO DIGITAR ASTERISCO 
         NA ENTRADA ELE RECONHECER COMO O QUEIJO
 */
//        System.out.print("\n Insira um inteiro entre 4 e 4000 para serem os pontos: ");
//        int pontos = scan.nextInt();
//
//        while (pontos < 4 || pontos > 4000) {
//            System.out.println("\n Número inválido, a representação dos PONTOS deve ser um inteiro entre 4 e 4000!");
//            System.out.print("\n Insira um inteiro entre 4 e 4000 para serem os pontos: ");
//            pontos = scan.nextInt();
//        }
//
//        System.out.print("\n Insira um inteiro entre 4 e 5000 para serem as ligações: ");
//        int ligacoes = scan.nextInt();
//
//        while (ligacoes < 4 || ligacoes > 5000) {
//            System.out.println("\n Número inválido, a representação das LIGAÇÕES deve ser um inteiro entre 4 e 5000!");
//            System.out.print("\n Insira um inteiro entre 4 e 4000 para serem os pontos: ");
//            ligacoes = scan.nextInt();
//        }
//
//        //Criando o Grafo 'G'
//        System.out.print("\n ARESTA 1 - Insira vértice 1:");
//        int aresta1v1 = scan.nextInt();
//
//        System.out.print("\n ARESTA 1 - Insira vértice 2:");
//        int aresta1v2 = scan.nextInt();
//
//        digrafo.insereAresta(aresta1v1, aresta1v2);
//
//        System.out.print("\n ARESTA 2 - Insira vértice 1:");
//        int aresta2v1 = scan.nextInt();
//
//        System.out.print("\n ARESTA 2 - Insira vértice 2:");
//        int aresta2v2 = scan.nextInt();
//
//        digrafo.insereAresta(aresta2v1, aresta2v2);
        //addArestas(1, 4, 8);
