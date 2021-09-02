package leilao;

import leilao.Leilao;

import java.util.ArrayList;
import java.util.List;

public class InicializadorDeLeiloes {
    private static List<Leilao> leiloes = new ArrayList<Leilao>();

    public static List<Leilao> obtemLeiloes() {
        leiloes.add(new Leilao("console"));
        leiloes.add(new Leilao("carro"));
        leiloes.add(new Leilao("casa"));
        leiloes.add(new Leilao("smartphone"));
        leiloes.add(new Leilao("tv"));
        leiloes.add(new Leilao("som"));
        leiloes.add(new Leilao("caminhonete"));
        leiloes.add(new Leilao("geladeira"));
        leiloes.add(new Leilao("consultorio"));
        return leiloes;
    }
}
