package de.fhws.fiw.fds.sutton.AdministrationSystem;

import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart {

    public static final String CONTEXT_PATH = "de/fhws/fiw/fds/sutton/AdministrationSystem";

    public static void main( final String[] args ) throws Exception
    {
        new Start( ).startTomcat( );
    }

    @Override protected String contextPath( )
    {
        return CONTEXT_PATH;
    }
}
