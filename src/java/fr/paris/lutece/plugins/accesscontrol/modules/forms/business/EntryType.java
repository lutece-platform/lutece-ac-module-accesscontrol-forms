package fr.paris.lutece.plugins.accesscontrol.modules.forms.business;

import java.util.List;

public class EntryType 
{
	private int _nId;
    private String _strCodeQuestion;
    private int _nIdControllerMultiple;
	
    public int getId() {
		return _nId;
	}
    
	public void setId(int _nId) {
		this._nId = _nId;
	}

	public String getCodeQuestion() {
		return _strCodeQuestion;
	}

	public void setCodeQuestion(String _strCodeQuestion) {
		this._strCodeQuestion = _strCodeQuestion;
	}

	public int getIdControllerMultiple() {
		return _nIdControllerMultiple;
	}

	public void setIdControllerMultiple(int _nIdControllerMultiple) {
		this._nIdControllerMultiple = _nIdControllerMultiple;
	}
}
