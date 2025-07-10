package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import fr.paris.lutece.plugins.accesscontrol.util.AccessControlUtils;
import fr.paris.lutece.portal.service.plugin.Plugin;
import jakarta.enterprise.inject.spi.CDI;

public final class EntryTypeDataHandlerConfigHome
{
    private static IEntryTypeDataHandlerConfigDao _dao = CDI.current( ).select( IEntryTypeDataHandlerConfigDao.class ).get( );
    private static Plugin _plugin = AccessControlUtils.getPlugin( );
    
    /**
     * Private constructor - this class need not be instantiated
     */
    private EntryTypeDataHandlerConfigHome( )
    {
    }
    
    /**
     * Create an instance of the EntryTypeDataHandlerConfig class
     * 
     * @param config
     *            The instance of the EntryTypeDataHandlerConfig which contains the informations to store
     */
    public static void create( EntryTypeDataHandlerConfig config )
    {
        _dao.insert( config, _plugin );
    }

    /**
     * Update of the config which is specified in parameter
     * 
     * @param config
     *            The instance of the EntryTypeDataHandlerConfig which contains the data to store
     */
    public static void update( EntryTypeDataHandlerConfig config )
    {
        _dao.store( config, _plugin );
    }

    /**
     * Remove the config whose identifier is specified in parameter
     * 
     * @param nKey
     *            The config Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of a config whose identifier is specified in parameter
     * 
     * @param nKey
     *            The config primary key
     * @return an instance of EntryTypeDataHandlerConfig
     */
    public static EntryTypeDataHandlerConfig findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }
}
