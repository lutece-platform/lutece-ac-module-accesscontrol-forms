package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

public class EntryTypeDataHandlerConfigDao implements IEntryTypeDataHandlerConfigDao
{
    public static final String BEAN_NAME = "accesscontrol-forms.entryTypeDataHandlerConfigDao";

    private static final String SQL_QUERY_INSERT = " INSERT INTO accesscontrol_forms_controller_entrytype_datahandler_config (id_access_controller, code_question ) VALUES ( ?, ? ) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_access_controller, code_question FROM accesscontrol_forms_controller_entrytype_datahandler_config WHERE id_access_controller = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM accesscontrol_forms_controller_entrytype_datahandler_config WHERE id_access_controller = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE accesscontrol_forms_controller_entrytype_datahandler_config SET code_question = ? WHERE id_access_controller = ? ";
    
    @Override
    public void insert( EntryTypeDataHandlerConfig config, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, config.getIdController( ) );
            daoUtil.setString( ++nIndex, config.getCodeQuestion( ) );

            daoUtil.executeUpdate( );
        }
    }

    @Override
    public EntryTypeDataHandlerConfig load( int nId, Plugin plugin )
    {
        EntryTypeDataHandlerConfig config = null;
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeQuery( );
            
            if ( daoUtil.next( ) )
            {
                int nIndex = 0;
                config = new EntryTypeDataHandlerConfig( );
                config.setIdController( daoUtil.getInt( ++nIndex ) );
                config.setCodeQuestion( daoUtil.getString( ++nIndex ) );
            }
        }
        return config;
    }

    @Override
    public void store( EntryTypeDataHandlerConfig config, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setString( ++nIndex, config.getCodeQuestion( ) );
            
            daoUtil.setInt( ++nIndex, config.getIdController( ) );
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
    }

}
