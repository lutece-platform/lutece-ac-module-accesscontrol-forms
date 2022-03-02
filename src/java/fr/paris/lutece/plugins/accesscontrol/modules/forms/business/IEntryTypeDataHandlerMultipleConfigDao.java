package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import fr.paris.lutece.portal.service.plugin.Plugin;

public interface IEntryTypeDataHandlerMultipleConfigDao
{
    /**
    * Insert a new record in the table.
    * 
    * @param EntryTypeDataHandlerMultipleConfig
    *            instance of the config object to insert
    * @param plugin
    *            the Plugin
    */
    void insert( EntryTypeDataHandlerMultipleConfig config, Plugin plugin );
    
    /**
     * Load the data from the table
     * 
     * @param nId
     *            The identifier of the config
     * @param plugin
     *            the Plugin
     * @return The instance of the config
     */
    EntryTypeDataHandlerMultipleConfig load( int nId, Plugin plugin );
    
    /**
     * Update the record in the table
     * 
     * @param config
     *            the reference of the config
     * @param plugin
     *            the Plugin
     */
    void store( EntryTypeDataHandlerMultipleConfig config, Plugin plugin );
    
    /**
     * Delete a record from the table
     * 
     * @param nId
     *            The identifier of the config to delete
     * @param plugin
     *            the Plugin
     */
    void delete( int nId, Plugin plugin );
}
