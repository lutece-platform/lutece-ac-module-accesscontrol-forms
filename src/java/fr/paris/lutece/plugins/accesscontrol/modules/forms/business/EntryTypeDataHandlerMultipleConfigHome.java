package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import fr.paris.lutece.plugins.accesscontrol.util.AccessControlUtils;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public final class EntryTypeDataHandlerMultipleConfigHome
{
    private static IEntryTypeDataHandlerMultipleConfigDao _dao = SpringContextService.getBean( EntryTypeDataHandlerMultipleConfigDao.BEAN_NAME );
    private static Plugin _plugin = AccessControlUtils.getPlugin( );
    
    /**
     * Private constructor - this class need not be instantiated
     */
    private EntryTypeDataHandlerMultipleConfigHome( )
    {
    }
    
    /**
     * Create an instance of the EntryTypeDataHandlerConfig class
     * 
     * @param config
     *            The instance of the EntryTypeDataHandlerConfig which contains the informations to store
     */
    public static void create( EntryTypeDataHandlerMultipleConfig config )
    {
        _dao.insert( config, _plugin );
    }

    /**
     * Update of the config which is specified in parameter
     * 
     * @param config
     *            The instance of the EntryTypeDataHandlerConfig which contains the data to store
     */
    public static void update( EntryTypeDataHandlerMultipleConfig config )
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
    public static EntryTypeDataHandlerMultipleConfig findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }
}
