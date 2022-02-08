package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

public class EntryTypeDataHandlerConfig
{
    private int _nIdController;
    private String _strCodeQuestion;
    
    /**
     * @return the nIdController
     */
    public int getIdController( )
    {
        return _nIdController;
    }
    /**
     * @param nIdController the nIdController to set
     */
    public void setIdController( int nIdController )
    {
        _nIdController = nIdController;
    }
    /**
     * @return the strCodeQuestion
     */
    public String getCodeQuestion( )
    {
        return _strCodeQuestion;
    }
    /**
     * @param strCodeQuestion the strCodeQuestion to set
     */
    public void setCodeQuestion( String strCodeQuestion )
    {
        _strCodeQuestion = strCodeQuestion;
    }
}
