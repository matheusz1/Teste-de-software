package services;

import leilao.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaService {

    private EntityManager em = Persistence.createEntityManagerFactory("postgres-leilao").createEntityManager();;

    public void salva(Pessoa pessoa) {
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
    }

    public List<Pessoa> consultaTodasPessoas(){
        List<Pessoa> pessoas = em.createQuery("SELECT c FROM Pessoa c").getResultList();
        return pessoas;
    }
}
