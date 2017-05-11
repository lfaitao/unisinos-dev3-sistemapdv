package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.CredencialRepository;
import br.unisinos.sistemapdv.application.repository.UsuarioRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SincronizacaoController {


    @CrossOrigin("*")
    @GetMapping("/sync")
    @ResponseBody
    public int get() {

        /*Session session = sessionFactory.getCurrentSession();

        SQLQuery query = session.createSQLQuery("show slave status");
        ArrayList<Object[]> results = (ArrayList<Object[]>)query.list();
        Object [] result =  results.get(0);

        //Seconds Behind Master should always be the last value in the row.
        String secondsBehindMaster = ""+result[result.length-1];

        int r = Integer.parseInt(secondsBehindMaster);

        if(r <= 300)
        {
            return 0;
        }
        else{
            return 1;
        }*/

        return 0;
    }

}
