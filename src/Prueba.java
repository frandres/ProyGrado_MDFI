import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import query.Attribute;
import query.Query;

import conditions.*;

public class Prueba {
	
	public static void main(String[] args) {
		
		ArrayList<Attribute> atributos1;
		ArrayList<Attribute> atributos2;
		
		atributos1 = new ArrayList<Attribute>();
		atributos1.add(new Attribute("A1", "C1"));
		
		atributos2 = new ArrayList<Attribute>();
		atributos2.add(new Attribute("A1", "C1"));
		
		Formula temp1 = new AndFormula(new Atom("C1", atributos1, new ArrayList<Query>()), 
				 					   new Atom("C2", atributos2, new ArrayList<Query>()));
		
		atributos1 = new ArrayList<Attribute>();
		atributos1.add(new Attribute("A3", "C1"));
		atributos1.add(new Attribute("A4", "C1"));
		
		atributos2 = new ArrayList<Attribute>();
		
		Formula temp2 = new AndFormula(new Atom("C3", atributos1, new ArrayList<Query>()), 
										new Atom("C4", null, new ArrayList<Query>()));

		Formula tempL = new OrFormula(temp1,temp2);
		
		atributos1 = new ArrayList<Attribute>();
		atributos1.add(new Attribute("A5", "C1"));
		
		atributos2 = new ArrayList<Attribute>();
		atributos2.add(new Attribute("A6", "C1"));
		
		temp1 = new AndFormula(new Atom("C5", atributos1, new ArrayList<Query>()), new Atom("C6", atributos2, new ArrayList<Query>()));
		
		atributos1 = new ArrayList<Attribute>();
		atributos1.add(new Attribute("A7", "C1"));
		atributos1.add(new Attribute("A8", "C1"));
		
		atributos2 = new ArrayList<Attribute>();
		atributos2.add(new Attribute("A9", "C1"));
		
		temp2 = new OrFormula(new Atom("C7", atributos1, new ArrayList<Query>()),	new Atom("C8", atributos2,new ArrayList<Query>()));
		
		Formula tempR = new AndFormula(temp1,temp2);

		Formula form = new NegativeFormula(new OrFormula(tempL, tempR));
		
		atributos1= new ArrayList<Attribute>();
		atributos1.add(new Attribute("B1", "C2"));
		Query query = new Query(null,atributos1 ,form);
		
		System.out.println(query.toString());
	
		for (Iterator <Formula> iterator = form.toNCF().iterator(); iterator.hasNext();) {
			Formula formNueva = iterator.next();
			
			System.out.print(formNueva.getConditionText(true));
			
			if (iterator.hasNext()){
				System.out.print(" AND ");
			}
		}
		
	}

}
