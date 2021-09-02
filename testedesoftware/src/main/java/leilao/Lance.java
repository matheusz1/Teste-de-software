package leilao;

import javax.persistence.*;

@Entity
public class Lance{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private double valor;

    @ManyToOne
    private Pessoa pessoa;

    @ManyToOne
    private Leilao leilao;


    public Lance(double valor, Pessoa pessoa){
        super();
        this.valor = valor;
        this.pessoa = pessoa;
    }

    public Lance() {
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }

}
