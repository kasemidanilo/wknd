package com.adobe.aem.guides.wknd.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Credentials;
import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.qom.Selector;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service= Servlet.class,
        property={
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.resourceTypes="+ "wknd/components/content/container",
                "sling.servlet.selectors="+"selector1"
        })
public class CreateNodeWithResourceType extends SlingSafeMethodsServlet {

    private ResourceResolver resourceResolver;

    private Resource resource;

    private PrintWriter responseWriter;

    @Reference
    protected SlingRepository repository;

    @Override

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException

    {

        try

        {

            response.setContentType("text/plain");

            resourceResolver = request.getResourceResolver();

            resource = request.getResource();
            RequestPathInfo pathInfo = request.getRequestPathInfo();
            String selectorName =  pathInfo.getSelectors()[0];
            responseWriter = response.getWriter();



            write("Version 2018-Sept-07.1");

            write(resourceResolver.toString());

            write(resource.toString());
            Credentials credentials = new SimpleCredentials("admin", "admin".toCharArray());
            Session session=repository.login(credentials);
            Node node= session.getRootNode();
            Node contentNode = node.getNode("content/wknd/us/en/jcr:content/root/responsivegrid/");

            contentNode.addNode(selectorName);

            write(node.toString());
            session.save();
            session.logout();
            write("All is done");



            //resourceResolver.create(arg0, arg1, arg2)

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
            responseWriter.println("\n TestCreateNodeServlet: " + message +"\n");
        }
    }
}
