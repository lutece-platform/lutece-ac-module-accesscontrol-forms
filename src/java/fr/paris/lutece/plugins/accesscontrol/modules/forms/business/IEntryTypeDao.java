package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import java.util.List;

import fr.paris.lutece.portal.service.plugin.Plugin;

public interface IEntryTypeDao
{
    /**
    * Insert a new record in the table.
    * 
    * @param EntryTypeDataHandlerMultipleConfig
    *            instance of the config object to insert
    * @param plugin
    *            the Plugin
    */
    void insert( EntryType config, Plugin plugin );
    
    /**
     * Load the data from the table
     * 
     * @param nId
     *            The identifier of the config
     * @param plugin
     *            the Plugin
     * @return The instance of the config
     */
    EntryType load( int nId, Plugin plugin );
    
    /**
     * Update the record in the table
     * 
     * @param config
     *            the reference of the config
     * @param plugin
     *            the Plugin
     */
    void store( EntryType config, Plugin plugin );
    
    /**
     * Delete a record from the table
     * 
     * @param nId
     *            The identifier of the config to delete
     * @param plugin
     *            the Plugin
     */
    void delete( int nId, Plugin plugin );

	List<EntryType> getListEntryTypeByIdMultiple(int nKey, Plugin _plugin);

	EntryType getEntryTypeByCodeQuestionAndIdMultiple(int nIdMultiple, String CodeQuestion, Plugin plugin);
}
