package leilao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import leilao.Pessoa;
import services.LeilaoService;
import services.PessoaService;
import org.junit.Before;
import org.junit.Test;

import leilao.Lance;
import leilao.Leilao;

public class LeiloesTeste {



    @Before
    public void resetaBase(){
        new LeilaoService().resetaBase();
    }

    @Test
    public void criarLeilao(){

        List<Leilao> leiloes = new LeilaoService().consultaTodosLeiloes();
        assertEquals(0, leiloes.size());
        Leilao leilao = new Leilao("Copo do vasco");
        new LeilaoService().salva(leilao);
        leiloes = new LeilaoService().consultaTodosLeiloes();
        assertEquals(1, leiloes.size());
    }
    @Test
    public void leilaoInativo(){
       
        Leilao leilao = new Leilao("Salsicha perdigão");
        new LeilaoService().salva(leilao);
        
        assertEquals("INATIVO", leilao.getFiltroLeiloes());
    } 
    @Test
    public void abrirLeilao(){

        Leilao leilao = new Leilao("XBOX - 400");
        new LeilaoService().salva(leilao);
        new LeilaoService().initLeilao(leilao);
        
        assertEquals("ABERTO", leilao.getFiltroLeiloes());
    }
    @Test
    public void fecharLeilao(){

        Leilao leilao = new Leilao("XBOX");
        new LeilaoService().salva(leilao);
        new LeilaoService().fimLeilao(leilao);
        
        assertEquals("FINALIZADO", leilao.getFiltroLeiloes());
    }
    

    @Test
    public void criarPessoa(){
       
        List<Pessoa> pessoas = new PessoaService().consultaTodasPessoas();
        assertEquals(0, pessoas.size());
        Pessoa pessoa = new Pessoa("Jurema");
        new PessoaService().salva(pessoa);

        pessoas = new PessoaService().consultaTodasPessoas();
        assertEquals(1, pessoas.size());
    }

    @Test
    public void ProporApenasUmLance() {

        Leilao leilao = new Leilao("Cabelo real");
        new LeilaoService().salva(leilao);

        Pessoa lucas = new Pessoa("Lucas");
        new PessoaService().salva(lucas);

        List<Lance> lances = new LeilaoService().consultaTodosLances();
        assertEquals(0, lances.size());

        Lance lance = new Lance(40.0, lucas);
        new LeilaoService().propoe(leilao, lance);

        lances = new LeilaoService().consultaTodosLances();
        assertEquals(1, lances.size());
    }


    @Test
    public void RetornaLance() {

        Leilao leilao = new Leilao("XBOX");
        new LeilaoService().salva(leilao);

        Pessoa Jacira = new Pessoa("Jacira");
        new PessoaService().salva(Jacira);

        Pessoa Juca = new Pessoa("Juca");
        new PessoaService().salva(Juca);

        Pessoa Luiz = new Pessoa("Luiz");
        new PessoaService().salva(Luiz);

        Lance lanceJacira = new Lance(50.0, Jacira);
        new LeilaoService().propoe(leilao, lanceJacira);

        Lance lanceJuca = new Lance(55.0, Juca);
        new LeilaoService().propoe(leilao, lanceJuca);

        Lance lanceLuiz = new Lance(35.0, Luiz);
        new LeilaoService().propoe(leilao, lanceLuiz);

        List<Lance> listaLances = new LeilaoService().retornaLances();

        assertEquals(35.0 , listaLances.get(2).getValor(), 0.0001);
        assertEquals(50.0 , listaLances.get(1).getValor(), 0.0001);
        assertEquals(55.0 , listaLances.get(0).getValor(), 0.0001);
    }


    @Test
    public void RetornaMenorLance() {
        Leilao leilao = new Leilao("Fusca a gás");
        new LeilaoService().salva(leilao);

        Pessoa lucas = new Pessoa("Lucas");
        new PessoaService().salva(lucas);

        Pessoa joao = new Pessoa("Joao");
        new PessoaService().salva(joao);

        Pessoa kenyo = new Pessoa("Kenyo");
        new PessoaService().salva(kenyo);

        Lance lanceLucas = new Lance(40.0, lucas);
        new LeilaoService().propoe(leilao, lanceLucas);

        Lance lanceJoao = new Lance(50.0, joao);
        new LeilaoService().propoe(leilao, lanceJoao);

        Lance lanceKenyo = new Lance(30.0, kenyo);
        new LeilaoService().propoe(leilao, lanceKenyo);

        List<Lance> menorLance = new LeilaoService().retornaOMenorValor();

        assertEquals(1, menorLance.size(), 0.0001);
        assertEquals(30.0, menorLance.get(0).getValor(), 0.0001);
    }

    @Test
    public void RetornarZeroQuandoMenorIgualAZero() {
        Leilao leilao = new Leilao("copo do flamengo");

        assertEquals(0, leilao.maiorLance(), 0.0001);
        assertEquals(0, leilao.menorLance(), 0.0001);
    }

    @Test
    public void ImpedirUmNovoLanceComValorMenorQueMaiorLance() {
        Leilao leilao = new Leilao("Camaro zero");

        Pessoa Joaquim = new Pessoa("Joaquim");
        Pessoa Carlos = new Pessoa("Carlos");

        Lance lanceJoaquim = new Lance(35.0, Joaquim);
        Lance lanceCarlos = new Lance(30.0, Carlos);

        leilao.propoe(lanceJoaquim);

        try {
            leilao.propoe(lanceCarlos);
            fail();
        } catch (RuntimeException e) {

        }
    }

    @Test
    public void ImpedirUmLanceDuasVezesDaMesmaPessoa() {
        Leilao leilao = new Leilao("roupas");

        Pessoa lucas = new Pessoa("Lucas");
        Pessoa Koala = new Pessoa("Koala");

        Lance lanceLucas = new Lance(35.0, lucas);
        Lance lanceKoala = new Lance(37.0, Koala);
        Lance outroLanceLucas = new Lance(40.0, lucas);

        leilao.propoe(lanceLucas);

        try {
            leilao.propoe(outroLanceLucas);
            fail();
        } catch (RuntimeException e) {

        }
    }

}