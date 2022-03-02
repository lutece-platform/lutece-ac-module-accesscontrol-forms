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
import fr.paris.lutece.plugins.accesscontrol.modules.forms.business.EntryTypeDataHandlerConfig;
import fr.paris.lutece.plugins.accesscontrol.modules.forms.business.EntryTypeDataHandlerConfigHome;
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
import fr.paris.lutece.util.ReferenceList;

public class EntryTypeDataHandler implements IPersistentDataHandler
{
    private static final String BEAN_NAME = "accesscontrol-forms.entryTypeDataHandler";
    private static final String HANDLER_NAME = "module.accesscontrol.forms.handler.entrytype.name";
    
    private static final String MARK_CONFIG = "config";
    private static final String MARK_LIST_CODES = "code_list";
    
    private static final String PARAMETER_CODE = "code_question";
    private static final String TEMPLATE_CONFIG = "/admin/plugins/accesscontrol/modules/forms/entrytype_datahandler_config_form.html";
    
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
        EntryTypeDataHandlerConfig config = EntryTypeDataHandlerConfigHome.findByPrimaryKey( idConfig );
        if ( config == null )
        {
            config = new EntryTypeDataHandlerConfig( );
            config.setIdController( idConfig );
        }
        Map<String, Object> model = new HashMap<>( );
        model.put( MARK_CONFIG, config );
        
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
        EntryTypeDataHandlerConfig config = EntryTypeDataHandlerConfigHome.findByPrimaryKey( idConfig );
        if ( config == null )
        {
            config = new EntryTypeDataHandlerConfig( );
            config.setIdController( idConfig );
            EntryTypeDataHandlerConfigHome.create( config );
        }
        config.setCodeQuestion( request.getParameter( PARAMETER_CODE ) );
        EntryTypeDataHandlerConfigHome.update( config );
    }
    
    @Override
    public void doDeleteConfig( int idConfig )
    {
        EntryTypeDataHandlerConfigHome.remove( idConfig );
    }
    
    @Override
    public void handlePersistentData( AccessController controller, AccessControlSessionData sessionData, Serializable data, Object destination )
    {
        EntryTypeDataHandlerConfig config = EntryTypeDataHandlerConfigHome.findByPrimaryKey( controller.getId( ) );
        if ( config == null )
        {
            return;
        }
        Entry entry = null;
        Question question = null;
        for ( Question q : QuestionHome.findByCode( config.getCodeQuestion( ) ) )
        {
            Entry e = q.getEntry( );
            if ( e.getResourceType( ).equals( sessionData.getTypeResource( ) ) && e.getIdResource( ) == sessionData.getIdResource( ) )
            {
                question = q;
                entry = e;
                break;
            }
        }
        FormResponseManager formResponseManager = (FormResponseManager) destination;
        if ( question ==null || question.getIdStep( ) != formResponseManager.getCurrentStep( ).getId( ) )
        {
            return;
        }
            
        Response response = new Response( );
        response.setEntry( entry );
        response.setResponseValue( (String) data );
        response.setIterationNumber( -1 );
        
        IEntryTypeService entryTypeService = EntryTypeServiceManager.getEntryTypeService(entry);
        if (entryTypeService instanceof EntryTypeSelect || entryTypeService instanceof EntryTypeRadioButton 
        		|| entryTypeService instanceof EntryTypeCheckBox)
        {
        	Field field = FieldHome.findByPrimaryKey(Integer.valueOf((String) data));
        	response.setField(field);
        }
        
        List<FormQuestionResponse> listResponsesTemp = new ArrayList<>( );
        FormQuestionResponse formQuestionResponse = new FormQuestionResponse( );
        formQuestionResponse.setQuestion( question );
        formQuestionResponse.setIdStep( question.getIdStep( ) );
        formQuestionResponse.setEntryResponse( Collections.singletonList( response ) );
        listResponsesTemp.add( formQuestionResponse );
        
        formResponseManager.addResponses( listResponsesTemp );
    }
}
