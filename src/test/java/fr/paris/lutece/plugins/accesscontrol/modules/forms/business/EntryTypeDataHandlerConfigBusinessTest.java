package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.test.LuteceTestCase;

public class EntryTypeDataHandlerConfigBusinessTest extends LuteceTestCase
{
    private static final int ID_CONTROLLER = 1;
    private static final String CODE_QUESTION_1 = "code1";
    private static final String CODE_QUESTION_2 = "code2";
    
    @Test
    public void testCRUD( )
    {
        EntryTypeDataHandlerConfig config = new EntryTypeDataHandlerConfig( );
        config.setIdController( ID_CONTROLLER );
        config.setCodeQuestion( CODE_QUESTION_1 );
        
        EntryTypeDataHandlerConfigHome.create( config );
        
        EntryTypeDataHandlerConfig loaded = EntryTypeDataHandlerConfigHome.findByPrimaryKey( ID_CONTROLLER );
        assertEquals( CODE_QUESTION_1, loaded.getCodeQuestion( ) );
        
        config.setCodeQuestion( CODE_QUESTION_2 );
        EntryTypeDataHandlerConfigHome.update( config );
        
        loaded = EntryTypeDataHandlerConfigHome.findByPrimaryKey( ID_CONTROLLER );
        assertEquals( CODE_QUESTION_2, loaded.getCodeQuestion( ) );
        
        EntryTypeDataHandlerConfigHome.remove( ID_CONTROLLER );
        loaded = EntryTypeDataHandlerConfigHome.findByPrimaryKey( ID_CONTROLLER );
        assertNull( loaded );
    }
}
