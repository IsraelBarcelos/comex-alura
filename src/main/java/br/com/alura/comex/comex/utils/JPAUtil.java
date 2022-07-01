package br.com.alura.comex.comex.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

  private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(
      "heroku_8f2ba8c4f9f4d07");

  public static EntityManager getEntityManager() {
    return FACTORY.createEntityManager();
  }
}
