package conditions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import query.Attribute;
import query.Query;

public class Atom extends Formula {

	
	private String conditionString;
	
	/* 
	 * Una lista de los atributos presentes en condiciones a este nivel y en
	 * niveles inferiores del arbol. 
	 */
	private List<Attribute> attributes;

	/* Una lista de las consultas anidadas a este nivel y en
	 * niveles inferiores del arbol. 
	 */
	private List<Query> nestedQueries;  
	
	public Atom(String conditionString, List<Attribute> attributes, List<Query> nestedQueries) {
		super();
		this.conditionString = conditionString;
		
		if (attributes==null){
			attributes = new ArrayList<Attribute>();
		}
		
		this.attributes=attributes;
		this.nestedQueries = nestedQueries;
		
	}

	@Override
	public List<Formula> toNCF() {
		
		ArrayList<Formula> formulas = new ArrayList<Formula>();
		formulas.add(this.clone());
		
		return formulas;
	}

	@Override
	public String getConditionText(boolean isAndSeq) {
		return conditionString;
	}

	@Override
	public List<Attribute> getAttributes() {

		ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
		
		for (Iterator<Attribute> iterator = attributes.iterator(); iterator.hasNext();) {
			Attribute at = (Attribute) iterator.next();
			attributeList.add(at.clone());
		}
		
		return attributeList;
	}
	
	public List<Query> getNestedQueries() {
		
		ArrayList<Query> nQueries = new ArrayList<Query>();
		
		for (Iterator<Query> iterator = nestedQueries.iterator(); iterator.hasNext();) {
			Query query = (Query) iterator.next();
			nQueries.add(query.clone());
		}
		
		return nQueries;
	}

	@Override
	public Formula clone() {
		return new Atom(getConditionText(true), getAttributes(),getNestedQueries());
	}

	/*
	@Override
	public Formula filterAttribute(Attribute at) {

		if (hasAttribute(at)){
			return null;
		}
		
		List<Query> nestedQueries = getNestedQueries();
		
		for (Iterator <Query> iterator = nestedQueries.iterator(); iterator.hasNext();) {
			Query qry = iterator.next();
			
			if (qry.filterAttribute(at) == null){
				
			}
		}
		return this;
	}*/
}
