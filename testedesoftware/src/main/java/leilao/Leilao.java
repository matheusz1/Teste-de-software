package leilao;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private String filtroLeiloes = "INATIVO";

    @OneToMany(mappedBy = "leilao", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Lance> lances = new ArrayList<Lance>();

    

	@Transient
    private double maiorLance;
    @Transient
    private double menorLance;

    @Transient
    private Pessoa maiorLancePessoa;
    @Transient
    private Pessoa menorLancePessoa;

    public Leilao(String item) {
        super();
        this.item = item;
        this.maiorLance = 0;
        this.menorLance = 0;
        this.maiorLancePessoa = new Pessoa("Sem lance");
        this.menorLancePessoa = new Pessoa("Sem lance");
    }

    public Leilao() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public String getFiltroLeiloes() {
		return filtroLeiloes;
	}

	public void setFiltroLeiloes(String filtroLeiloes) {
		this.filtroLeiloes = filtroLeiloes;
	}

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public void setMaiorLance(double maiorLance) {
        this.maiorLance = maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public void setMenorLance(double menorLance) {
        this.menorLance = menorLance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public void propoe(Lance lance) {

        int ultimaPosicao = this.lances.size()-1;

        int contadorCincoVezes = 0;
  
        

        if (this.lances.size() > 0){
            for (int i = 0; i < lances.size(); i++){
                if (contadorCincoVezes < 6){
                    if (lances.get(i).getPessoa().getNome().equals(lance.getPessoa().getNome())){
                        contadorCincoVezes++;
                    }
                } else {
                    throw new RuntimeException("A pessoa nao pode fazer mais de 5 lances");
                }
            }
        }

        if (this.lances.size() > 0 && lance.getPessoa().getNome().equals(this.lances.get(ultimaPosicao).getPessoa().getNome())) {
           throw new RuntimeException("A mesma pessoa nao pode fazer 2 lances seguidos");
        }

        if (lance.getValor() < this.maiorLance) {
            throw new RuntimeException("O lance não é permitido, por menor");
        }

        if (this.maiorLance == 0) {
            this.maiorLance = lance.getValor();
            this.maiorLancePessoa = lance.getPessoa();
        } else {
            if (lance.getValor() > this.maiorLance) {
                this.maiorLance = lance.getValor();
                this.maiorLancePessoa = lance.getPessoa();
            }
        }

        if (this.menorLance == 0) {
            this.menorLance = lance.getValor();
            this.menorLancePessoa = lance.getPessoa();
        } else {
            if (lance.getValor() < this.menorLance) {
                this.menorLance = lance.getValor();
                this.menorLancePessoa = lance.getPessoa();
            }
        }

        this.lances.add(lance);
    }

    public double maiorLance() {
        if (this.maiorLance == 0) {
            this.maiorLance = 0;
        }
        return this.maiorLance;
    }

    public double menorLance() {
        if (this.maiorLance == 0) {
            this.maiorLance = 0;
        }
        return this.menorLance;
    }

    public List<Lance> obtemTresMaioresLances() {
        Collections.sort(this.lances, new Comparator<Lance>() {
            public int compare(Lance a, Lance b) {
                if (a.getValor() < b.getValor()) {
                    return 1;
                }
                if (a.getValor() > b.getValor()) {
                    return -1;
                }
                return 0;
            }
        });
        int tamanhoDaLista = this.lances.size();

        if (tamanhoDaLista > 3) {
            tamanhoDaLista = 3;
        }
        return this.lances.subList(0, tamanhoDaLista);
    }

    public double obtemOMenorLance() {
        double lance = 0.00;

        for (int i = 0; i < (this.lances.size()) + 1; i++) {
            if (this.lances.get(i).getValor() < this.menorLance) {
                lance = this.lances.get(i).getValor();
            }
        }

        return lance;
    }

}