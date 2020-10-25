/*
ANUSKA KEPLER E BRUNA SHROEDER
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Ratinho {

    static String BRANCO = "BRANCO";
    static String PRETO = "PRETO";
    static String CINZA = "CINZA";

    static List<Distancia> listaDistancias = new ArrayList<>();
    static int INFINITO = 99999;

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

    public static Distancia buscarDistancia(String chave) {
        for (Distancia d : listaDistancias) {
            if (d.pai.equals(chave)) {
                return d;
            }
        }

        return null;
    }

    public static void calcularDistancia(Grafo grafo, String vertice) {
        try {
            int numeroDeVertices = grafo.vertices.size();

            grafo.vertices.forEach((v) -> {
                listaDistancias.add(new Distancia(v.chave, INFINITO));
            });

            Queue<String> fila = new LinkedList<>();
            Distancia distanciaVertice = buscarDistancia(vertice);
            distanciaVertice.valor = 0;
            fila.add(vertice);
            while (!fila.isEmpty()) {
                String pivo = fila.remove();
                Distancia distanciaPai = buscarDistancia(pivo);
                for (int i = 0; i < numeroDeVertices; i++) {
                    String pontoB = grafo.vertices.get(i).chave;
                    Distancia distanciaPontoB = buscarDistancia(pontoB);
                    if (grafo.existeAresta(pivo, pontoB) && distanciaPontoB.valor == INFINITO) {
                        distanciaPontoB.valor = distanciaPai.valor + 1;
                        distanciaPontoB.cor = CINZA;
                        distanciaPai.filhos.add(pontoB);
                        fila.add(pontoB);
                    }
                }
                distanciaPai.cor = PRETO;
            }
        } catch (Exception e) {
            System.out.println("\n calcularDistancia. Erro: " + e.getMessage());
        }
    }

    public static Distancia distanciaEntreDois(Grafo grafo, String pontoA, String pontoB) {
        calcularDistancia(grafo, pontoA);
        return buscarDistancia(pontoB);
    }

    public static class Grafo {

        List<Vertice> vertices;

        public Grafo(List<Vertice> vertices) {
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

    public static class Vertice {

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

    public static Grafo criarGrafo1() {
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

        Grafo grafo = new Grafo(vertices);
        grafo.insereAresta("Entrada", "A");
        grafo.insereAresta("A", "F");
        grafo.insereAresta("F", "C");
        grafo.insereAresta("C", "B");
        grafo.insereAresta("B", "D");
        grafo.insereAresta("C", "D");
        grafo.insereAresta("F", "J");
        grafo.insereAresta("J", "H");
        grafo.insereAresta("H", "G");
        grafo.insereAresta("J", "G");
        grafo.insereAresta("J", "*");
        grafo.insereAresta("*", "I");
        grafo.insereAresta("I", "L");
        grafo.insereAresta("L", "M");
        grafo.insereAresta("M", "K");
        grafo.insereAresta("K", "Saida");
        grafo.insereAresta("A", "K");
        grafo.insereAresta("C", "E");
        grafo.insereAresta("E", "I");
        grafo.insereAresta("I", "M");

        return grafo;
    }

    public static Grafo criarGrafo2() {
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice("Entrada"));
        vertices.add(new Vertice("A"));
        vertices.add(new Vertice("B"));
        vertices.add(new Vertice("GT"));
        vertices.add(new Vertice("H"));
        vertices.add(new Vertice("*"));
        vertices.add(new Vertice("C"));
        vertices.add(new Vertice("I"));
        vertices.add(new Vertice("D"));
        vertices.add(new Vertice("Saida"));

        Grafo grafo = new Grafo(vertices);
        grafo.insereAresta("B", "A");
        grafo.insereAresta("A", "B");
        grafo.insereAresta("Entrada", "A");
        grafo.insereAresta("B", "GT");
        grafo.insereAresta("GT", "H");
        grafo.insereAresta("H", "*");
        grafo.insereAresta("B", "*");
        grafo.insereAresta("*", "C");
        grafo.insereAresta("C", "I");
        grafo.insereAresta("I", "D");
        grafo.insereAresta("C", "D");
        grafo.insereAresta("D", "Saida");

        return grafo;
    }

    public static Grafo criarGrafo3() {
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
        vertices.add(new Vertice("*"));
        vertices.add(new Vertice("Saida"));

        Grafo grafo = new Grafo(vertices);
        grafo.insereAresta("Entrada", "A");
        grafo.insereAresta("A", "B");
        grafo.insereAresta("B", "C");
        grafo.insereAresta("C", "D");
        grafo.insereAresta("D", "*");

        grafo.insereAresta("A", "E");
        grafo.insereAresta("E", "F");
        grafo.insereAresta("F", "G");
        grafo.insereAresta("G", "H");
        grafo.insereAresta("H", "*");
        grafo.insereAresta("*", "I");
        grafo.insereAresta("I", "Saida");

        return grafo;
    }

    public static Grafo criarGrafo4() {
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
        vertices.add(new Vertice("*"));
        vertices.add(new Vertice("Saida"));

        Grafo grafo = new Grafo(vertices);
        grafo.insereAresta("Entrada", "A");
        grafo.insereAresta("A", "*");

        grafo.insereAresta("A", "B");
        grafo.insereAresta("B", "C");
        grafo.insereAresta("C", "D");
        grafo.insereAresta("D", "*");

        grafo.insereAresta("*", "Saida");

        return grafo;
    }

    public static Grafo criarGrafo5() {
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
        vertices.add(new Vertice("*"));
        vertices.add(new Vertice("Saida"));

        Grafo grafo = new Grafo(vertices);
        grafo.insereAresta("Entrada", "A");
        grafo.insereAresta("A", "B");
        grafo.insereAresta("B", "C");
        grafo.insereAresta("C", "D");
        grafo.insereAresta("D", "*");

        grafo.insereAresta("A", "Saida");
        grafo.insereAresta("*", "Saida");

        return grafo;
    }

    public static void realizaBuscas(Grafo grafo, String verticeEntrada, String verticeSaida) {

        boolean pontosValidos = grafo.vertices.size() > 4 || grafo.vertices.size() < 4000;

        if (pontosValidos) {
            if (verticeEntrada.equals(verticeSaida)) {
                System.out.println("\n A distância é 0, pois é o mesmo ponto");
            } else {
                Distancia distanciaA = distanciaEntreDois(grafo, verticeEntrada, "*");

                if (distanciaA == null) {
                    System.out.print("\n Erro não esperado: Distância A não encontrada");
                } else {
                    if (distanciaA.valor == INFINITO) {
                        System.out.println("\n Não conseguimos pegar o queijo entre o vertice (" + verticeEntrada + ") e o vertice (" + verticeSaida + ")");
                    } else {
                        int tempoA = distanciaA.valor;
                        listaDistancias = new ArrayList<>();
                        Distancia distanciaB = distanciaEntreDois(grafo, "*", verticeSaida);
                        if (distanciaB == null) {
                            System.out.print("\n Erro não esperado: Distância B não encontrada");
                        } else {
                            if (distanciaB.valor == INFINITO) {
                                System.out.println("\n Não conseguimos pegar o queijo entre o vertice (" + verticeEntrada + ") e o vertice (" + verticeSaida + ")");
                            } else {
                                System.out.println("\n A distancia entre v(" + verticeEntrada + ") e v(" + verticeSaida + ") é: " + (tempoA + distanciaB.valor) + " (pegando o queijo no caminho).");
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("\n A quantidade de pontos (vértices) deve ser entre 4 e 4000");
        }
    }

    public static void main(String[] args) {
        Grafo grafo = criarGrafo1();
        // Grafo grafo = criarGrafo2();
        // Grafo grafo = criarGrafo3();

        Scanner scan = new Scanner(System.in);
        System.out.print("\n Vertice de entrada: ");
        String verticeEntrada = scan.next();

        while (grafo.buscarVertice(verticeEntrada) == null) {
            System.out.println("\n Vertice invalido!");
            System.out.print("\n Vertice de entrada: ");
            verticeEntrada = scan.next();
        };

        System.out.print("\n Vertice de saída: ");
        String verticeSaida = scan.next();

        while (grafo.buscarVertice(verticeSaida) == null) {
            System.out.println("\n Vertice invalido!");
            System.out.print("\n Vertice de saída: ");
            verticeSaida = scan.next();
        }

        realizaBuscas(grafo, verticeEntrada, verticeSaida);

        System.out.print("\n");
    }
}
