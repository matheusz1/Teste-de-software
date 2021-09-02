package services;

import leilao.Lance;
import leilao.Leilao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class LeilaoService {

    private EntityManager em = Persistence.createEntityManagerFactory("postgres-leilao").createEntityManager();

    public void resetaBase(){
        Query queryLance = em.createQuery("Delete from Lance ");
        Query queryLeilao = em.createQuery("Delete from Leilao");
        Query queryPessoa = em.createQuery("Delete from Pessoa ");
        em.getTransaction().begin();
        queryLance.executeUpdate();
        queryLeilao.executeUpdate();
        queryPessoa.executeUpdate();
        em.getTransaction().commit();
    }

    public List<Leilao> consultaTodosLeiloes(){
        List<Leilao> leiloes = em.createQuery("SELECT c FROM Leilao c").getResultList();
        return leiloes;
    }

    public void salva(Leilao leilao){
        em.getTransaction().begin();
        em.persist(leilao);
        em.getTransaction().commit();
    }

    public List<Lance> consultaTodosLances() {
        List<Lance> lances = em.createQuery("SELECT c FROM Lance c").getResultList();
        return lances;
    }
    public void initLeilao(Leilao leilao) {
    	leilao.setFiltroLeiloes("ABERTO");
    }
    public void fimLeilao(Leilao leilao) {
    	leilao.setFiltroLeiloes("FINALIZADO");
    }
    public void propoe(Leilao leilao, Lance lance){
        em.getTransaction().begin();
        lance.setLeilao(leilao);
        em.persist(lance);
        em.getTransaction().commit();
    }

    public List<Lance> retornaLances(){
        List<Lance> lances = em.createQuery("FROM Lance c ORDER BY c.valor DESC").getResultList();
        List<Lance> listaLances = null;

        int tamanho = lances.size();
        listaLances = lances.subList(0, tamanho);

        return listaLances;
    }

    public List<Lance> retornaOMenorValor(){
        String sql = "SELECT c FROM Lance as c WHERE c.valor = (SELECT min(cc.valor) FROM Lance cc)";
    	List<Lance> lances = em.createQuery(sql).getResultList();
        return lances;
    }
}