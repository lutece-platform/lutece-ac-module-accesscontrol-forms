package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import java.util.List;

import fr.paris.lutece.plugins.accesscontrol.util.AccessControlUtils;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public final class EntryTypeHome
{
    private static IEntryTypeDao _dao = SpringContextService.getBean( EntryTypeDao.BEAN_NAME );
    private static Plugin _plugin = AccessControlUtils.getPlugin( );
    
    /**
     * Private constructor - this class need not be instantiated
     */
    private EntryTypeHome( )
    {
    }
    
    /**
     * Create an instance of the EntryTypeDataHandlerConfig class
     * 
     * @param config
     *            The instance of the EntryTypeDataHandlerConfig which contains the informations to store
     */
    public static void create( EntryType config )
    {
        _dao.insert( config, _plugin );
    }

    /**
     * Update of the config which is specified in parameter
     * 
     * @param config
     *            The instance of the EntryTypeDataHandlerConfig which contains the data to store
     */
    public static void update( EntryType config )
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
    public static EntryType findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }
    
    public static List<EntryType> getListEntryTypeByIdMultiple(int nKey)
    {
    	return _dao.getListEntryTypeByIdMultiple(nKey, _plugin);
    }
    
    public static EntryType findByCodeQuestionAndIdMultiple( int nKey, String strCodeQuestion )
    {
        return _dao.getEntryTypeByCodeQuestionAndIdMultiple( nKey, strCodeQuestion, _plugin );
    }
}
