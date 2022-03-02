package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import java.util.ArrayList;
import java.util.List;

public class EntryTypeDataHandlerMultipleConfig
{
    private int _nIdControllerMultiple;
    
    /**
     * @return the nIdController
     */
    public int getIdControllerMultiple( )
    {
        return _nIdControllerMultiple;
    }
    /**
     * @param nIdController the nIdController to set
     */
    public void setIdControllerMultiple( int nIdControllerMultiple )
    {
    	_nIdControllerMultiple = nIdControllerMultiple;
    }
    /**
     * @return the list of strCodeQuestion
     */
    public List<String> getListCodeQuestions( )
    {
        List<String> list = new ArrayList<>();
        List<EntryType> entryTypeList = EntryTypeHome.getListEntryTypeByIdMultiple(_nIdControllerMultiple);
        
        for (EntryType e : entryTypeList)
        {
        	list.add(e.getCodeQuestion());
        }
        
        return list;
    }
}
