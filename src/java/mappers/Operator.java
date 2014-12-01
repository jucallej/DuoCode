package mappers;

/**
 * Representa un operador de comparación. Se utiliza en QueryCondition.
 * 
 * @author Manuel Montenegro
 *
 */
public enum Operator {
	EQ("="), LE("<"), LT("<="), GE(">="), GT(">"), NEQ("!="), LIKE("RLIKE");
	
	private String repr;
	
	private Operator(String repr){
		this.repr=repr;
	}
	
	@Override
	public String toString() {
		return this.repr;
	}
}
