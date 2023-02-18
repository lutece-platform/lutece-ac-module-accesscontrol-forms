package fr.paris.lutece.plugins.accesscontrol.modules.forms.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.accesscontrol.business.AccessController;
import fr.paris.lutece.plugins.accesscontrol.modules.forms.business.EntryType;
import fr.paris.lutece.plugins.accesscontrol.modules.forms.business.EntryTypeDataHandlerMultipleConfig;
import fr.paris.lutece.plugins.accesscontrol.modules.forms.business.EntryTypeDataHandlerMultipleConfigHome;
import fr.paris.lutece.plugins.accesscontrol.modules.forms.business.EntryTypeHome;
import fr.paris.lutece.plugins.accesscontrol.service.IPersistentDataHandler;
import fr.paris.lutece.plugins.forms.business.FormQuestionResponse;
import fr.paris.lutece.plugins.forms.business.Question;
import fr.paris.lutece.plugins.forms.business.QuestionHome;
import fr.paris.lutece.plugins.forms.service.entrytype.EntryTypeCheckBox;
import fr.paris.lutece.plugins.forms.service.entrytype.EntryTypeRadioButton;
import fr.paris.lutece.plugins.forms.service.entrytype.EntryTypeSelect;
import fr.paris.lutece.plugins.forms.web.FormResponseManager;
import fr.paris.lutece.plugins.genericattributes.business.Entry;
import fr.paris.lutece.plugins.genericattributes.business.Field;
import fr.paris.lutece.plugins.genericattributes.business.FieldHome;
import fr.paris.lutece.plugins.genericattributes.business.Response;
import fr.paris.lutece.plugins.genericattributes.service.entrytype.EntryTypeServiceManager;
import fr.paris.lutece.plugins.genericattributes.service.entrytype.IEntryTypeService;
import fr.paris.lutece.portal.business.accesscontrol.AccessControlSessionData;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.ReferenceList;

public class EntryTypeDataHandlerMultiple implements IPersistentDataHandler
{
    private static final String BEAN_NAME = "accesscontrol-forms.entryTypeDataHandlerMultiple";
    private static final String HANDLER_NAME = "module.accesscontrol.forms.handler.entrytype.multiple.name";
    
    private static final String MARK_CONFIG = "config";
    private static final String MARK_LIST_CODES = "code_list";
    private static final String MARK_ENTRY_LIST = "entryTypeList";
    
    private static final String PARAMETER_LIST_QUESTIONS = "list_code_questions";
    private static final String TEMPLATE_CONFIG = "/admin/plugins/accesscontrol/modules/forms/entrytype_datahandler_multiple_config_form.html";
    
    @Override
    public String getBeanName( )
    {
        return BEAN_NAME;
    }

    @Override
    public String getHandlerName( Locale locale )
    {
        return I18nService.getLocalizedString( HANDLER_NAME, locale );
    }

    @Override
    public String getDataHandlerConfigForm( Locale locale, int idConfig )
    {
        EntryTypeDataHandlerMultipleConfig config = EntryTypeDataHandlerMultipleConfigHome.findByPrimaryKey( idConfig );
        if ( config == null )
        {
            config = new EntryTypeDataHandlerMultipleConfig( );
            config.setIdControllerMultiple( idConfig );
        }
        Map<String, Object> model = new HashMap<>( );
        model.put( MARK_CONFIG, config );
        
        List<EntryType> entryTypeList = EntryTypeHome.getListEntryTypeByIdMultiple(idConfig);
        model.put( MARK_ENTRY_LIST, entryTypeList );
        
        ReferenceList referenceList = new ReferenceList( );
        List<Question> questionList = QuestionHome.getQuestionsList( );
        
        List<String> codeList = questionList.stream( ).map( Question::getCode ).distinct( ).collect( Collectors.toList( ) );
        codeList.sort( Comparator.naturalOrder( ) );
        for ( String code : codeList )
        {
            referenceList.addItem( code, code );
        }
        model.put( MARK_LIST_CODES, referenceList );
        return AppTemplateService.getTemplate( TEMPLATE_CONFIG, locale, model ).getHtml( );
    }

    @Override
    public void doSaveConfig( HttpServletRequest request, int idConfig )
    {
        EntryTypeDataHandlerMultipleConfig config = EntryTypeDataHandlerMultipleConfigHome.findByPrimaryKey( idConfig );
        String[] listCodeQuestion = request.getParameterValues(PARAMETER_LIST_QUESTIONS);
        if ( config == null )
        {
            config = new EntryTypeDataHandlerMultipleConfig( );
            config.setIdControllerMultiple( idConfig );
            EntryTypeDataHandlerMultipleConfigHome.create( config );
        }
		List<EntryType> listEntryType = EntryTypeHome.getListEntryTypeByIdMultiple(idConfig);
		Integer i = 0;
		EntryType e = null;
        if (listCodeQuestion != null)
        {
        	for ( String str : listCodeQuestion )
            {
        		if ( listEntryType == null || listEntryType.isEmpty( ) )
        		{
        			e = new EntryType();
        			e.setCodeQuestion( str );
                	e.setIdControllerMultiple(idConfig);
                	EntryTypeHome.create(e);
        		}
        		else
        		{
        			e = listEntryType.get(i);
        			e.setCodeQuestion( str );
                	e.setIdControllerMultiple(idConfig);
                	EntryTypeHome.update(e);
                	i++;
        		}
            }
        }
        EntryTypeDataHandlerMultipleConfigHome.update( config );
    }
    
    @Override
    public void doDeleteConfig( int idConfig )
    {
    	EntryTypeDataHandlerMultipleConfigHome.remove( idConfig );
    }
    
    @Override
    public void handlePersistentData( AccessController controller, AccessControlSessionData sessionData, Serializable data, Object destination )
    {
    	EntryTypeDataHandlerMultipleConfig config = EntryTypeDataHandlerMultipleConfigHome.findByPrimaryKey( controller.getId( ) );
        if ( config == null )
        {
            return;
        }

        FormResponseManager formResponseManager = (FormResponseManager) destination;
        List<FormQuestionResponse> listResponsesTemp = new ArrayList<>( );

    	Entry entry = null;
        Question question = null;
        Response response = null;
        FormQuestionResponse formQuestionResponse = null;
        
        List<String> dataList = (List<String>) data;
        Integer i = 0;
        
        for ( String strCode : config.getListCodeQuestions() )
        {
            List<EntryType> entryTypeList = EntryTypeHome.getListEntryTypeByIdMultiple( config.getIdControllerMultiple() );
            
            for (EntryType entryType : entryTypeList)
            {
            	if ( entryType.getCodeQuestion().equals(strCode) )
            	{
                	for (Question q : QuestionHome.findByCode( entryType.getCodeQuestion( ) ) )
                	{
                		Entry e = q.getEntry( );
                		if ( e.getResourceType( ).equals( sessionData.getTypeResource( ) ) && e.getIdResource( ) == sessionData.getIdResource( ) )
                        {
                            question = q;
                            entry = e;
                            break;
                        }
                	}
                	if ( question ==null || question.getIdStep( ) != formResponseManager.getCurrentStep( ).getId( ) )
                    {
                        return;
                    }
                	response = new Response( );
                    response.setEntry( entry );
                    response.setResponseValue( dataList.get(i) );
                    response.setIterationNumber( -1 );
                    
                    IEntryTypeService entryTypeService = EntryTypeServiceManager.getEntryTypeService(entry);
                    if (entryTypeService instanceof EntryTypeSelect || entryTypeService instanceof EntryTypeRadioButton 
                    		|| entryTypeService instanceof EntryTypeCheckBox)
                    {
                    	Field field = FieldHome.findByPrimaryKey( Integer.valueOf( dataList.get(i) ) );
                    	response.setField(field);
                    }
                    formQuestionResponse = new FormQuestionResponse( );
                    formQuestionResponse.setQuestion( question );
                    formQuestionResponse.setIdStep( question.getIdStep( ) );
                    formQuestionResponse.setEntryResponse( Collections.singletonList( response ) );
                    listResponsesTemp.add( formQuestionResponse );
            	}
            }
            i++;
        }
        formResponseManager.addResponses( listResponsesTemp );
    }
}
