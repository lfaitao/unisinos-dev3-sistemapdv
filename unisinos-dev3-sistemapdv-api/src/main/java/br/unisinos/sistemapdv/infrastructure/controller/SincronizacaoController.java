package br.unisinos.sistemapdv.infrastructure.controller;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

@RestController
public class SincronizacaoController {


    private SessionFactory hibernateFactory;
    @Autowired
    public EntityManagerFactory factory;

    @CrossOrigin("*")
    @GetMapping("/sync")
    @ResponseBody
    public int get() {

        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
        SessionFactory sessionFactory = hibernateFactory.openSession().getSessionFactory();
        Session session = sessionFactory.openSession();

        SQLQuery query = session.createSQLQuery("show slave status");

        if (!query.getQueryReturns().isEmpty()) {
            ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
            Object[] result = results.get(0);

            //Seconds Behind Master should always be the last value in the row.
            String secondsBehindMaster = "" + result[result.length - 1];

            int r = Integer.parseInt(secondsBehindMaster);

            if (r <= 300) {
                return 0;
            }
        }

        return 1;


    }

}
