package leilao;

import leilao.Lance;
import leilao.Leilao;
import leilao.Pessoa;

public class Principal {
    public static void main(String[] args) {
        propoeApenasUmLance();
        propooeVariosLances();
    }

    private static void propooeVariosLances() {
        Leilao leilao = new Leilao("roupas");
        Pessoa lucas = new Pessoa("Lucas");
        Pessoa joao = new Pessoa("Joao");
        Pessoa jonathan = new Pessoa("Jonathan");
        Pessoa kenyo = new Pessoa("Kenyo");

        Lance lanceLucas = new Lance(30.0, lucas);
        Lance lanceJoao = new Lance(35.0, joao);
        Lance lanceJonathan = new Lance(40.0, jonathan);
        Lance lanceKenyo = new Lance(42.0, kenyo);

        if(leilao.getLances().size() == 0){
            leilao.propoe(lanceLucas);
            leilao.propoe(lanceJoao);
            leilao.propoe(lanceJonathan);
            leilao.propoe(lanceKenyo);
            if (leilao.getLances().size() == 4){
                System.out.println("Funcionou Propoe Varios Lances");
                System.out.println("O Maior Lance e: " + leilao.getMaiorLance() + "\n");
            }
        }
    }

    private static void propoeApenasUmLance() {
        Leilao leilao = new Leilao("roupas");
        Pessoa lucas = new Pessoa("Lucas");
        Lance lance = new Lance(30.0, lucas);
        if(leilao.getLances().size() == 0){
            leilao.propoe(lance);
            if (leilao.getLances().size() >0){
                System.out.println("Funcionou Propoe Apenas Um Lance");
                System.out.println("Maior Lance e: " + leilao.getMaiorLance() + "\n");
            }
        }
    }
}