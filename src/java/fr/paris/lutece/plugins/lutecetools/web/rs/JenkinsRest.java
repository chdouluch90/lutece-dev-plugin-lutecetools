/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.lutecetools.web.rs;

import fr.paris.lutece.plugins.lutecetools.service.JenkinsService;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Page resource
 */
@Path( RestConstants.BASE_PATH + Constants.PATH_PLUGIN + Constants.PATH_JENKINS )
public class JenkinsRest
{
    private static JenkinsService _jenkinsService;

    /**
     * Set the Jenkins service
     * 
     * @param jenkinsService
     *            The service
     */
    public void setJenkinsService( JenkinsService jenkinsService )
    {
        _jenkinsService = jenkinsService;
    }

    /**
     * 
     * @param accept
     * @param url
     * @return
     * @throws IOException 
     */
    @GET
    @Path( Constants.PATH_JENKINS_BADGE )
    @Produces( "image/svg+xml" )
    public Response getJenkinsBadge( @HeaderParam( HttpHeaders.ACCEPT ) String accept, @QueryParam( Constants.PARAMETER_URL ) String url ) throws IOException 
    {
        HttpResponse response = _jenkinsService.performsGetJenkinsUrl( url, false );
        HttpEntity entity = response.getEntity( );

        return Response.ok( entity.getContent( ), "image/svg+xml" ).build( );
    }
}
