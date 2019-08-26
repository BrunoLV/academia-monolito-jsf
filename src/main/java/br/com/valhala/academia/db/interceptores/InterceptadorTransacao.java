package br.com.valhala.academia.db.interceptores;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Transacional
@Interceptor
public class InterceptadorTransacao {

    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object executaDentroDaTransacao(InvocationContext ctx) throws Exception {

        EntityTransaction transacao = em.getTransaction();
        try {
            if (!transacao.isActive()) {
                transacao.begin();
            }
            Object resultado = ctx.proceed();
            if (transacao.isActive()) {
                transacao.commit();
            }
            return resultado;
        } catch (Exception e) {
            if (transacao.isActive()) {
                transacao.rollback();
            }
            throw e;
        }
    }

}
