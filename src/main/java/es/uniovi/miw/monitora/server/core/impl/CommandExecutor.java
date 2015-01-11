package es.uniovi.miw.monitora.server.core.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class CommandExecutor {
	public Object execute(Command command) throws BusinessException {

		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();

		Object res = null;
		try {
			res = command.execute();

			trx.commit();
		} catch (PersistenceException pex) {
			if (trx.isActive())
				trx.rollback();
			throw pex;
		} catch (BusinessException bex) {

			trx.rollback();
			throw bex;
		} finally {
			if (em.isOpen())
				em.close();
		}
		return res;
	}
}
