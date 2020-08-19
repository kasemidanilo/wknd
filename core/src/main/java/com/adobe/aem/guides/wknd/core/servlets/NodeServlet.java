package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "= NodeServlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                   "sling.servlet.paths="+ "/bin/addWKNDnode"
           })
@ServiceDescription("Servlet to add a new node")

public class NodeServlet extends SlingAllMethodsServlet {

    private ResourceResolver resourceResolver;
    private Resource resource;
    private PrintWriter responseWriter;

    @Reference
    protected SlingRepository repository;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException{
        try{
            response.setContentType("text/plain");
            resourceResolver = request.getResourceResolver();
            resource = request.getResource();
            responseWriter = response.getWriter();

            write("Version 2018-Sept-07.1");
            write(resourceResolver.toString());
            write(resource.toString());

            SimpleCredentials credential = new SimpleCredentials("admin", "admin".toCharArray());
            Session  session = repository.login(credential);

            Node node= session.getRootNode();
            Node contentNode = node.getNode("content/wknd/us/en/jcr:content/root/responsivegrid/");

            contentNode.addNode("ENEDAtest");

            write(node.toString());
            session.save();
            session.logout();
          }

        catch (final Exception e)
        {
            write("ERROR: " + e.getMessage() + " , " + e.getClass());
        }
    }
    private void write(final String message)
    {
        if (responseWriter != null)
        {
            responseWriter.println("TestCreateNodeServlet: " + message);
        }
    }
}

