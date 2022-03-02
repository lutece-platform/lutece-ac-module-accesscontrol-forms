package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import java.util.ArrayList;
import java.util.List;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.sql.DAOUtil;

public class EntryTypeDao implements IEntryTypeDao
{
    public static final String BEAN_NAME = "accesscontrol-forms.entryTypeDao";

    private static final String SQL_QUERY_INSERT = " INSERT INTO accesscontrol_forms_controller_entrytype (code_question, id_access_controller_multiple) VALUES ( ?, ? ) ";
    private static final String SQL_QUERY_SELECT = " SELECT id_entry_type, code_question, id_access_controller_multiple FROM accesscontrol_forms_controller_entrytype WHERE id_entry_type = ? ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM accesscontrol_forms_controller_entrytype WHERE id_entry_type = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE accesscontrol_forms_controller_entrytype SET code_question = ?, id_access_controller_multiple = ? WHERE id_entry_type = ? ";
    private static final String SQL_QUERY_SELECT_BY_ID_MULTIPLE = " SELECT id_entry_type, code_question, id_access_controller_multiple FROM accesscontrol_forms_controller_entrytype WHERE id_access_controller_multiple = ? ";
    private static final String SQL_QUERY_SELECT_BY_CODE_QUESTION = " SELECT id_entry_type, code_question, id_access_controller_multiple FROM accesscontrol_forms_controller_entrytype WHERE id_access_controller_multiple = ? AND code_question = ? ";
    
    
    @Override
    public void insert( EntryType config, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setString( ++nIndex, config.getCodeQuestion( ) );
            daoUtil.setInt(++nIndex, config.getIdControllerMultiple( ));

            daoUtil.executeUpdate( );
        }
    }

    @Override
    public EntryType load( int nId, Plugin plugin )
    {
    	EntryType config = null;
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeQuery( );
            
            if ( daoUtil.next( ) )
            {
            	config = new EntryType( );
            	config.setId( daoUtil.getInt( "id_entry_type" ) );
            	config.setCodeQuestion(daoUtil.getString( "code_question" ) );
            	config.setIdControllerMultiple( daoUtil.getInt( "id_access_controller_multiple" ) );
            }
        }
        return config;
    }

    @Override
    public void store( EntryType config, Plugin plugin )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin ) )
        {
            int nIndex = 0;
            daoUtil.setString( ++nIndex, config.getCodeQuestion( ) );
            daoUtil.setInt( ++nIndex, config.getIdControllerMultiple( ) );
            
            daoUtil.setInt( ++nIndex, config.getId( ) );
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

	@Override
	public List<EntryType> getListEntryTypeByIdMultiple(int nId, Plugin plugin) {
		List<EntryType> configList = new ArrayList<>();
		EntryType config = null;
		try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ID_MULTIPLE, plugin ) )
        {
            daoUtil.setInt( 1, nId );
            daoUtil.executeQuery( );
            
            while ( daoUtil.next( ) )
            {
                config = new EntryType( );
                config.setId( daoUtil.getInt( "id_entry_type" ) );
                config.setCodeQuestion(daoUtil.getString( "code_question" ) );
                config.setIdControllerMultiple( daoUtil.getInt( "id_access_controller_multiple" ) );
                configList.add(config);
            }
        }
		return configList;
	}

	@Override
    public EntryType getEntryTypeByCodeQuestionAndIdMultiple( int nIdMultiple, String strCodeQuestion, Plugin plugin )
    {
    	EntryType config = null;
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CODE_QUESTION, plugin ) )
        {
            daoUtil.setInt( 1, nIdMultiple );
            daoUtil.setString( 2, strCodeQuestion );
            daoUtil.executeQuery( );
            
            if ( daoUtil.next( ) )
            {
                config = new EntryType( );
                config.setId( daoUtil.getInt( "id_entry_type" ) );
                config.setCodeQuestion(daoUtil.getString( "code_question" ) );
                config.setIdControllerMultiple( daoUtil.getInt( "id_access_controller_multiple" ) );
            }
        }
        return config;
    }
}
