package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import java.util.List;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

public class EntryTypeDataHandlerMultipleConfigDao implements IEntryTypeDataHandlerMultipleConfigDao
{
    public static final String BEAN_NAME = "accesscontrol-forms.entryTypeDataHandlerMultipleConfigDao";

    private static final String SQL_QUERY_INSERT = " INSERT INTO ac_forms_controller_entrytype_datahandler_multiple_config (id_access_controller_multiple ) VALUES ( ? ) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_access_controller_multiple FROM ac_forms_controller_entrytype_datahandler_multiple_config WHERE id_access_controller_multiple = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM ac_forms_controller_entrytype_datahandler_multiple_config WHERE id_access_controller_multiple = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE ac_forms_controller_entrytype_datahandler_multiple_config SET id_access_controller_multiple = ? WHERE id_access_controller_multiple = ? ";
    
    @Override
    public void insert( EntryTypeDataHandlerMultipleConfig config, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, config.getIdControllerMultiple( ) );

            daoUtil.executeUpdate( );
        }
    }

    @Override
    public EntryTypeDataHandlerMultipleConfig load( int nId, Plugin plugin )
    {
    	EntryTypeDataHandlerMultipleConfig config = null;
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeQuery( );
            
            if ( daoUtil.next( ) )
            {
                int nIndex = 0;
                config = new EntryTypeDataHandlerMultipleConfig( );
                config.setIdControllerMultiple( daoUtil.getInt( ++nIndex ) );
            }
        }
        return config;
    }

    @Override
    public void store( EntryTypeDataHandlerMultipleConfig config, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, config.getIdControllerMultiple( ) );
            
            daoUtil.setInt( ++nIndex, config.getIdControllerMultiple( ) );
            daoUtil.executeUpdate( );
        }
    }

    @Override
    public void delete( int nId, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeUpdate( );
        }
        List<EntryType> listEntryType = EntryTypeHome.getListEntryTypeByIdMultiple( nId );
        for (EntryType e : listEntryType)
        {
        	EntryTypeHome.remove( e.getId( ) );
        }
    }
}
