/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.TablesFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kerberos
 */
public class ContextListener implements ServletContextListener {
    
    @EJB
    private TablesFacade tablesFacade;
    private static final Logger logger = Logger.getLogger(ContextListener.class.getName());

    @Override
     public void contextInitialized(ServletContextEvent sce) {
        logger.info("Context Initialized");
        System.out.println("===========Context has been initialized=============");
        tablesFacade.createTablesIfNotExist();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("====================Context Destroyed======================");
    }

}
