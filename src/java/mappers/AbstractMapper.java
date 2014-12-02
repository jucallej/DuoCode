package mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;


public abstract class AbstractMapper<T,K> {

	protected DataSource ds;

	/**
	 * Devuelve el nombre de la tabla asociada al mapper concreto. Esta
	 * tabla será la utilizada en todas las consultas SQL.
	 * 
	 * @return Cadena con el nombre de la tabla
	 */
	protected abstract String getTableName();

	/**
	 * Devuelve los nombres de las columnas de la tabla asociada al mapper
	 * concreto.
	 * 
	 * @return Array con los nombres de columnas de la tabla.
	 */
	protected abstract String[] getColumnNames();

	/**
	 * Divide un objeto dado en sus componentes. Las componentes del array
	 * devuelto deben estar en el orden correspondiente al dado por las
	 * columnas devueltas por getColumnNames() 
	 * 
	 * @param object Objeto a dividir
	 * @return Componentes del objeto dividido
	 */
	protected abstract Object[] serializeObject(T object);

	/**
	 * Devuelve los nombres de las columnas que forman la clave primaria de
	 * la tabla del mapper concreto.
	 * 
	 * @return Array con nombres de columnas clave
	 */
	protected abstract String[] getKeyColumnNames();
	
	/**
	 * Divide una clave primaria en sus componentes. Las componentes del array
	 * devuelto deben estar en el orden correspondiente al dado por las
	 * columnas devueltas por getKeyColumnNames() 
	 * 
	 * @param key Clave a dividir
	 * @return Componentes de la clave pasada como parámetro
	 */
	protected abstract Object[] serializeKey(K key);
	
	/**
	 * Construye un objeto a partir del resultado de una consulta.
	 * 
	 * @param rs ResultSet con el resultado actual de la consulta.
	 * @return Objeto (de tipo T) representado por la fila contenida en rs
	 * @throws SQLException
	 */
	protected abstract T buildObject(ResultSet rs) throws SQLException;

	/**
	 * Obtiene la clave primaria del objeto pasado como parámetro. 
	 * 
	 * @param object Objeto
	 * @return Clave primera del objeto pasado como parámetro.
	 */
	protected abstract K getKey(T object);

	
	public AbstractMapper(DataSource ds) {
		this.ds = ds;
	}
	
	
	/**
	 * Devuelve la lista de objetos que satisfacen todas las condiciones
	 * del array pasado como parámetro
	 * 
	 * @param conditions Objetos de la clase QueryCondition que especifican las condiciones
	 *                   de los objetos a buscar
	 * @return Lista de objetos de la tabla que cumplen las condiciones dadas. 
	 *         Si ninguno de ellos las cumple, se devuelve una lista vacía.
	 */
	protected List<T> findByConditions(QueryCondition[] conditions) {
		/*
		 * Este método debería hacer uso de los métodos abstractos:
		 * 
		 * getTableName()
		 * getColumnNames()
		 * buildObject()
		 * 
		 * Éste método se declara como protegido, para evitar hacer uso de nombres
		 * de columnas explícitos (contenidos dentro de las QueryCondition) desde
		 * el resto del programa.
		 */
		Connection con                   = null;
		PreparedStatement pst            = null;
		ResultSet rs                     = null;
		List<T> result                   = new LinkedList<T>();
		try {
			con = ds.getConnection();
			String[] columNames = getColumnNames();
			String columnNamesWithCommas = StringUtils.join(columNames, ",");
			String[] conditionsStr = new String[conditions.length];
			
			for (int i = 0; i < conditions.length; i++) {
				//conditionsStr[i] = columNames[i] + " = ?";
				conditionsStr[i] = conditions[i].getColumnName() + " "+conditions[i].getOperator() + " ?";
				
			}
			String whereCondition = StringUtils.join(conditionsStr, " AND ");
                        if (conditions.length >0)
                            pst = con.prepareStatement(
                                            "SELECT " + columnNamesWithCommas + " FROM " + getTableName() +
                                    " WHERE " + whereCondition
                                            );
                        else
                            pst = con.prepareStatement(
                                            "SELECT " + columnNamesWithCommas + " FROM " + getTableName());

			for (int i =0; i < conditions.length; i++) {
				pst.setObject(i+1, conditions[i].getValue());
			}
			
			rs = pst.executeQuery();
			while(rs.next()){
				result.add(buildObject(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
		
		
		return result;
	}

	public T findById(K key) {
		/*
		 * Este método debería utilizar findByConditions y, además, los siguientes
		 * métodos abstractos:
		 * 
		 * getKeyColumnNames()
		 * serializeKey()
		 * 
		 */
		//String[] keyColumnNames = getKeyColumnNames();
		QueryCondition[] conditions = getConditionFromKey(key);
				/**
				new QueryCondition[keyColumnNames.length];
		Object[] columnValues = serializeKey(key);
		for (int i = 0; i< conditions.length; i++) {
			conditions[i] = new QueryCondition(keyColumnNames[i],
					Operator.EQ, columnValues[i]);
		}
		**/
		List<T> results = findByConditions(conditions);
		if (results.isEmpty()) {
		   return null;
		} else {
			return results.get(0);
		}
	}
	
	private String getWhereCondition(QueryCondition[] condition){
		String[] aux = new String[condition.length];
		for (int i=0; i<condition.length; i++)
			aux[i]=condition[i].getColumnName()+" = ?";
		return StringUtils.join(aux, " and ");
	}

	private QueryCondition[] getConditionFromKey(K id){
		String[] keyColumnNames = getKeyColumnNames();
		QueryCondition[] conditions = new QueryCondition[keyColumnNames.length];
		Object[] columnValues = serializeKey(id);
		for (int i= 0; i< conditions.length; i++) {
			conditions[i] = new QueryCondition(keyColumnNames[i],
					Operator.EQ, columnValues[i]);
		}
		return conditions;
		
	}
	
	public void update(T object) {
		/*
		 * Éste método debería utilizar los siguientes métodos abstractos:
		 * 
		 * getTableName()
		 * getColumnNames()
		 * serializeObject()
		 * getKeyColumnNames()
		 * serializeKey()
		 * getKey()
		 *
		 * Este método comparte bastantes cosas en común con findByConditions
		 * y findById (para generar la cláusula WHERE). Deberían extraerse
		 * estas partes comunes en métodos aislados. Si se hace así, las llamadas
		 * a serializeKey y getKeyColumnNames no deberían ser necesarias. 
		 */
		String[] columNames = getColumnNames();
		String[] assigments = new String[columNames.length];
		QueryCondition[] conditions = getConditionFromKey(getKey(object));
		
		for (int i=0; i<assigments.length; i++){
			assigments[i]=columNames[i]+" = ?";
		}
		
		String sql = ("UPDATE "+getTableName()+
				" SET "+StringUtils.join(assigments, ",")+
				" WHERE "+getWhereCondition(conditions));
		
		Connection con = null;
		PreparedStatement stm = null;
		try {
			con = ds.getConnection();
			stm = con.prepareStatement(sql);
			Object[] objectFields = serializeObject(object);
			int j=1;
			for (int i=0; i<objectFields.length;i++){
				stm.setObject(j, objectFields[i]);
				j++;
			}
			for (int i=0; i<conditions.length;i++){
				stm.setObject(j, conditions[i].getValue());
				j++;
			}
			
			stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (stm != null)
					stm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
	}
	
        /**
         * @param object a insertar
         * @return -1 si no tiene id autoincrement, y un int con el id, si tiene id Autoincrement
         */
	public int insert(T object){
		String[] columNames = getColumnNames();
		String[] assigments = new String[columNames.length];
                int autoincrement = -1;
		
		for (int i=0; i<assigments.length; i++){
			assigments[i]="?";
		}
		
		String sql = ("INSERT INTO "+getTableName()+
				" ( "+StringUtils.join(columNames, ",")+
				") VALUES ("+StringUtils.join(assigments, ",")+")");
		
		Connection con = null;
		PreparedStatement stm = null;
                ResultSet rs = null;
		try {
			con = ds.getConnection();
			stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			Object[] objectFields = serializeObject(object);
			int j=1;
			for (int i=0; i<objectFields.length;i++){
				stm.setObject(j, objectFields[i]);
				j++;
			}
			stm.execute();
                        rs = stm.getGeneratedKeys();

                        if (rs.next()) {
                            autoincrement = rs.getInt(1);
                        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (stm != null)
					stm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}
                return autoincrement;
	}
	
	public void delete(T object){
		String[] columNames = getColumnNames();
		String[] assigments = new String[columNames.length];
		QueryCondition[] conditions = getConditionFromKey(getKey(object));
		
		for (int i=0; i<assigments.length; i++){
			assigments[i]=columNames[i]+" = ?";
		}
		
		String sql = ("DELETE FROM "+getTableName()+
				" WHERE "+getWhereCondition(conditions));
		
		Connection con = null;
		PreparedStatement stm = null;
		try {
			con = ds.getConnection();
			stm = con.prepareStatement(sql);
			Object[] objectFields = serializeKey(getKey(object));
			int j=1;
			for (int i=0; i<objectFields.length;i++){
				stm.setObject(j, objectFields[i]);
				j++;
			}
			stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if (stm != null)
					stm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {}
		}		
	}
        
        public List<T> findAll(){
            return this.findByConditions(new QueryCondition[0]);
        }
}