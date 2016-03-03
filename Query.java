package modelo;
	import java.beans.Statement;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import javax.swing.DefaultListModel;
	import javax.swing.JFrame;
	import javax.swing.JList;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import controller.Faraday;
	import vista.Gui;
	
	/**Sirve para ejecutar las Query que debemos implementar*/
	public class Query {
			String nameDataBase="TonyK6.db";
			private static BaseDeDatos base=new BaseDeDatos();
			static ArrayList<String>nombres= new ArrayList<String>();
			static ArrayList<Double>precios= new ArrayList<Double>();
			static ArrayList<String>platos= new ArrayList<String>();
			DefaultListModel modeloNombres,modeloPrecios;
			Connection c;
			java.sql.Statement stmt;
			java.sql.ResultSet rs;
			public Query(){
					c=null;
					stmt=null;
					rs=null;
					//getModeloNombres();	
					modeloNombres= new DefaultListModel();
					modeloPrecios= new DefaultListModel();
				}
			/*
			public static void main(String[] args) {
					JFrame ventana= new JFrame("Lida");
					Query app= new Query();
					app.editarInsumo("Papaya", 490.0);
					app.showTablaPrecios();
					app.showTablaIngredientes();
					//ventana.add(lista);
					
					ventana.setSize(290,110);
					ventana.setVisible(true);
					//lista= new JList();
					ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				*/
			public void crearTablaPrecios(){
					try{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							String sql = "CREATE TABLE Precios " +
							"(Id INTEGER PRIMARY KEY  ," +
							" Nombre           TEXT    NOT NULL, " + 
							" Precio         REAL)"; 
							stmt.executeUpdate(sql);
							stmt.close();
							c.close();	
						}
					catch(SQLException e){
							e.printStackTrace();
						}
				}
			public DefaultListModel   getModeloNombres(){
					try	{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							rs = stmt.executeQuery( "SELECT * FROM Precios;" );
							String nombre;
							double prec=0;
							while(rs.next())
								{
									nombre=null;
									nombre = rs.getString("Nombre");
									modeloNombres.addElement(nombre);
								}
							stmt.close();
							c.close();
						}
					catch(SQLException e){
							e.printStackTrace();
						}
					return modeloNombres;
				}
			public static ArrayList<String> getNombres() {
				return nombres;
			}
			public  DefaultListModel getModeloPrecios(){
					try	{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							rs = stmt.executeQuery( "SELECT * FROM Precios;" );
							while(rs.next())
								{
									Double precio=rs.getDouble("Precio");
									modeloPrecios.addElement(precio);
									
								}
							stmt.close();
							c.close();
						}
					catch(SQLException e){
							e.printStackTrace();
						}
					return modeloPrecios;
				}
			public  void addInsumo(String nombre, double precio)
				{
					boolean flag=true;
				
					try{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();	
							rs = stmt.executeQuery( "SELECT Nombre FROM Precios;" );
							
							while(rs.next())
							{	
								String  nombrep = rs.getString("Nombre");
								if(nombrep.equals(nombre))
									{
										System.out.println("Este insumo ya fue ingresado");
										flag=false;break;
									}
							}//Fin del While
							if(flag)
								{
								String sql22 = "INSERT INTO Precios " +
										"VALUES (null,'" +nombre+  "',"  +precio+ ");"; 
										stmt.executeUpdate(sql22);
										System.out.println("Esta ingresando al else");
								
								}
							stmt.close();
							c.close();
							
						}//Fin del Try
					catch(SQLException g){
							g.printStackTrace();
						}
		
				}//Fin del metodo addInsumo
			public  void showTablaPrecios()
				{
					try{	c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							rs = stmt.executeQuery( "SELECT * FROM Precios;" );
							while(rs.next())
								{
										String  nombre = rs.getString("Nombre");
										int id= rs.getInt("Id");
										double precio= rs.getFloat("Precio");
										System.out.print(id+"\t"+nombre+"\t"+precio+"\n");
								}//Fin del While
							stmt.close();
							c.close();
					
					}//Fin del Try
					catch(SQLException g){g.printStackTrace();}//Fin del catch
				
				}//Fin del metodo showTablaPrecios
			public  void cleanTablaPrecios()
			/**Este metodo borra todos los registros de la tabla de precios*/
			{
				try{	c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						stmt.executeQuery( "Delete  FROM Precios;" );
						System.out.println("La tabla de Precios fue borrada");
						stmt.close();
						c.close();
				}//Fin del Try
				catch(SQLException g){g.printStackTrace();}//Fin del catch
			
			}//Fin del metodo showTablaPrecios
			public  void editarInsumo(String nombre, double precio){
				try{
						c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						rs = stmt.executeQuery( "SELECT * FROM Precios;" );
						while(rs.next()){
								String  nombrex = rs.getString("Nombre");
								if(nombrex.equals(nombre)){
										String query2="UPDATE Precios SET Precio="+precio +" WHERE Nombre='"+nombre+"'";
										stmt.executeUpdate(query2);
										System.out.println("esta hecho");
									}
							}
						stmt.close();
						c.close();
					}
				catch(SQLException g){
						g.printStackTrace();
					}
			}
			/**Este metodo crea una tabla para almacenar los ingredientes que haran parte de un plato
			 * posee 4 atributos, el id, la cantidad de gramos, la unidad gramos, el nombre
			 * y el total que es el resultado de multiplicar la cantidad por el precio actual del nombre
			 * que esta en la tabla de precios
			 * */
			
			public  void crearTablaIngredientes(){
				try{
					c=base.conectarA(nameDataBase);
					stmt = c.createStatement();
					String sql = "CREATE TABLE Ingredients " +
					"(Id INTEGER PRIMARY KEY  ," +
					" Cantidad           REAL, " +
					" Unidad           TEXT  , " +
					" Nombre           TEXT, " +
					" Total         REAL)"; 
					stmt.executeUpdate(sql);
					stmt.close();
					c.close();	
				}
				catch(SQLException e){
						e.printStackTrace();
					}
			}
			
			
			
			public  void crearTablaPlatos(){
				try{
					c=base.conectarA(nameDataBase);
					stmt = c.createStatement();
					String sql = "CREATE TABLE Platos " +
					"(Id INTEGER PRIMARY KEY  ," +
					" Servicios           INTEGER, " +
					//" Unidad           INTEGER  , " +
					" Nombre           TEXT, " +
					" Total         REAL)"; 
					stmt.executeUpdate(sql);
					stmt.close();
					c.close();	
				}
				catch(SQLException e){
						e.printStackTrace();
					}
			}
			
			public  void addPlato(double servicios,String nombre){
				double total=0;
				String unidad="gramos";
				try{
						c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						String sql2 = "INSERT INTO Platos " +
						"VALUES (null," +servicios+  ",'"+nombre+"',"+total+");"; 
						stmt.executeUpdate(sql2);
						stmt.close();
						c.close();
					}
				catch(SQLException g){
						g.printStackTrace();
					}
	
			}
			
			
			
			public  void crearTablaPlatoIngredientes(){
				try{
					c=base.conectarA(nameDataBase);
					stmt = c.createStatement();
					String sql = "CREATE TABLE PlatoIngredientes " +
					"(Id INTEGER PRIMARY KEY  ," +
					" Plato           INTEGER, " +
					//" Unidad           TEXT  , " +
					//" Nombre           TEXT, " +
					" Ingrediente         INTEGER)"; 
					stmt.executeUpdate(sql);
					stmt.close();
					c.close();	
				}
				catch(SQLException e){
						e.printStackTrace();
					}
			}
			
			
			
			public void addRelacionPlatoIngredientes(int plato,int ingredientes)
				{
					try
						{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							String sql2 = "INSERT INTO  PlatoIngredientes " +
									"VALUES (null," +plato+   ","+ingredientes+");";
							stmt.executeUpdate(sql2);
							stmt.close();
							c.close();
							
						}
					catch(SQLException g){g.printStackTrace();}
				}
			
			
			
			
			
			
			public  void addIngrediente(double cantidad,String insumo){
				double total=cantidad*getPrecio(insumo);
				String unidad="gramos";
				try{
						c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						String sql2 = "INSERT INTO Ingredients " +
						"VALUES (null," +cantidad+  ",'"  +unidad+ "','"+insumo+"',"+total+");"; 
						stmt.executeUpdate(sql2);
						//modeloNombres.addElement(nombre);
						//modeloPrecios.addElement(precio);
						stmt.close();
						c.close();
					}
				catch(SQLException g){
						g.printStackTrace();
					}
			}
				
				public void showRelaciones()
					{
						try
							{
								c=base.conectarA(nameDataBase);
								stmt = c.createStatement();
								rs = stmt.executeQuery( "SELECT * FROM  PlatoIngredientes;" );
								while(rs.next()){
									int id= rs.getInt("Id");
									int pla=rs.getInt("Plato");
									int ingre=rs.getInt("Ingrediente");
									
									System.out.print(id+"\t"+pla+"\t"+ingre+"\n");
							
							}
							stmt.close();
							c.close();
							
							}
						catch(SQLException g){g.printStackTrace();}
					}
	
			
			/**Este metodo recibe como parameto en nombre de un insumo y returna su precio*/
			private double getPrecio(String insumo) 
				{	double salida=0;
					try
						{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							rs = stmt.executeQuery( "SELECT * FROM Precios;" );
							while(rs.next())
								{
									String name=rs.getString("Nombre");
									if(name.equals(insumo))
										{
											salida=rs.getDouble("Precio");
											break;
										}
									//else{System.out.println("Intenta  obtener el precio de un producto que no esta en BD");}
								}
							stmt.close();
							c.close();
						}
					catch(SQLException g){g.printStackTrace();}
					return salida;
				}
			public  void showTablaIngredients(){
				try{	c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						rs = stmt.executeQuery( "SELECT * FROM Ingredients;" );
								while(rs.next()){
										//String  nombre = rs.getString("Nombre");
										int id= rs.getInt("Id");
										double cantidad= rs.getFloat("Cantidad");
										String unidad=rs.getString("Unidad");
										String insumo=rs.getString("Nombre");
										//int alimento= rs.getInt("Alimento");
										double total=rs.getDouble("Total");
										System.out.print(id+"\t"+cantidad+"\t"+unidad+"\t"+insumo
										+"\t"+total+"\n");
								
								}
								stmt.close();
								c.close();
					
				}
				catch(SQLException g){
						g.printStackTrace();
					}
				
			}
			public static ArrayList<Double> getPrecios() {
				// TODO Auto-generated method stub
				return precios;
			}
			public static ArrayList<String> getPlatos() {
				// TODO Auto-generated method stub
				return null;
			}
			public static void main(String[] args) {
				//JFrame ventana= new JFrame("Lida");
				Query app= new Query();
				//app.editarInsumo("Papaya", 490.0);
				//app.cleanTablaPrecios();
				//app.addInsumo("Savila", 75);
				//app.addInsumo("Melocotones", 75);
				//app.editarInsumo("Zanahoria", 49);
				//app.showTablaPrecios();
				//app.crearTablaPlatos();
				//app.addPlato(1810, "Sopa de Ajiaco");
				//app.crearTablaPlatoIngredientes();
				app.showPlatos();
				app.showTablaIngredients();
				//app.addRelacionPlatoIngredientes(1, 12);
				app.showRelaciones();
				System.out.println("***********************************************");
				
				
				/*app.addPlato(1810, "Sopa de Avena");
				app.addPlato(1810, "Sopa de Maiz Mote");
				app.addPlato(1810, "Sopa de Ajiaco");
				app.addPlato(1810, "Crema de Apio");
				app.addPlato(1810, "Sopa de Verduras");
				app.addPlato(1810, "Sopa de Platano Verde Picado");
				app.showPlatos();*/
				
				//app.cleanTablaPrecios();
				//app.showTablaIngredients();
				//app.addIngrediente(13000, "Apio");
				//app.showTablaIngredients();
				
				//app.crearTablaIngredientes();
				//app.addIngrediente(12000, "Sandia");
				//app.showTablaIngredients();
				//app.borrarIngrediente("remolacha");
				//app.showTablaIngredients();
				//app.showTablaIngredientes();
				//ventana.add(lista);
				
				//ventana.setSize(290,110);
				//ventana.setVisible(true);
				//lista= new JList();
				//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			private void showPlatos() {
				// TODO Auto-generated method stub
				try{	c=base.conectarA(nameDataBase);
				stmt = c.createStatement();
				rs = stmt.executeQuery( "SELECT * FROM Platos;" );
				while(rs.next())
					{
							String  nombre = rs.getString("Nombre");
							int id= rs.getInt("Id");
							int servi= rs.getInt("Servicios");
							double total= rs.getFloat("Total");
							System.out.print(id+"\t"+servi+"\t"+nombre+"\t"+total+"\n");
					}//Fin del While
				stmt.close();
				c.close();
		
		}//Fin del Try
		catch(SQLException g){g.printStackTrace();}//Fin del catch
				
			}
			public void borrarInsumo(String selected) 
				{
					try{	c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							String qap="Delete  FROM Precios WHERE Nombre='"+selected+"';";
							stmt.executeQuery( qap);
							System.out.println("La tabla de Precios fue borrada");
							stmt.close();
							c.close();
						}//Fin del Try
					catch(SQLException g){g.printStackTrace();}//Fin del catch
				
				}
			public void borrarIngrediente(String selected) 
				{
					
					try
						{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							String qaz="Delete  FROM Ingredients WHERE Nombre='"+selected+"';";
							//stmt.executeQuery( qap);
							stmt.execute(qaz);
							stmt.close();
							c.close();
							System.out.println("Un ingrediente ha sido borrado");
						
						}
					catch(SQLException g){System.out.println("Erron en borrar Ingrediente");g.printStackTrace();}
				}
			
			/**Este metodo consulta la tabla ingredientes y devuelve todos los elementos de la columna
			 * cantidad ,Names y totals y los devuelve en un arreglo de DefaultListModel*/
			public DefaultListModel[] getModelosIngredientes() 
				{ 	
					DefaultListModel[] salida = new DefaultListModel[3];
					DefaultListModel canti= new DefaultListModel();
					DefaultListModel nombre= new DefaultListModel();
					DefaultListModel total= new DefaultListModel();
					try
						{
							c=base.conectarA(nameDataBase);
							stmt = c.createStatement();
							rs = stmt.executeQuery( "SELECT * FROM Ingredients;" );
							while(rs.next())
								{
									double cantidad= rs.getFloat("Cantidad");
									String unidad=rs.getString("Unidad");
									String insumo=rs.getString("Nombre");
									double totl=rs.getDouble("Total");
									canti.addElement(cantidad);
									nombre.addElement(insumo);
									total.addElement(totl);
								}
							stmt.close();
							c.close();
						}
					catch(SQLException g){g.printStackTrace();}//Fin del catch
				
					salida[0]=canti;
					salida[1]=nombre;
					salida[2]=total;
					return salida;
				}
			public DefaultListModel[] getModeloPlatos() 
				{
				
				DefaultListModel[] salida = new DefaultListModel[2];
				DefaultListModel servi= new DefaultListModel();
				DefaultListModel platos= new DefaultListModel();
				//DefaultListModel ingre= new DefaultListModel();
				try
					{
						c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						rs = stmt.executeQuery( "SELECT * FROM Platos;" );
						while(rs.next())
							{
								String  nombre = rs.getString("Nombre");
								int seri= rs.getInt("Servicios");
								double total= rs.getFloat("Total");
								servi.addElement(seri);
								platos.addElement(nombre);
							}
						stmt.close();
						c.close();
					}
				catch(SQLException g){g.printStackTrace();}//Fin del catch
			
				salida[0]=servi;
				salida[1]=platos;
				//salida[2]=total;
				return salida;
				}
			public DefaultListModel[] getModelos(int indexPlatoSelected) {
				int aux=indexPlatoSelected+1;
				ArrayList<Integer>idIngredientes= new ArrayList<Integer>();
				DefaultListModel[] salida= new DefaultListModel[3];
				DefaultListModel cant= new DefaultListModel();
				try
					{
						c=base.conectarA(nameDataBase);
						stmt = c.createStatement();
						rs = stmt.executeQuery( "SELECT * FROM PlatoIngredientes WHERE Id='"+aux+"';" );
						while(rs.next())
							{
								int ser= rs.getInt("Ingrediente");
								idIngredientes.add(ser);//Almaceno todos los ingredientes de un plato
							}
					    for(int x=0;x<idIngredientes.size();x++) 
					    	{
					        	System.out.println(idIngredientes.get(x));
					        	double cantida=getCantidad(idIngredientes.get(x));
					        	String nombre=;
					        	double total=;
					    	}
					stmt.close();
					c.close();
						
					}
				catch(){}
				
				
				salida[0]=;
				salida[1]=;
				salida[2]=;
				return salida;
			}
			private double getCantidad(Integer integer) {
				// TODO Auto-generated method stub
				double salida=0;
				try
				{
					c=base.conectarA(nameDataBase);
					stmt = c.createStatement();
					rs = stmt.executeQuery( "SELECT * FROM Ingredientes WHERE Id='"+integer+"';" );
					while(rs.next())
						{
							
						}
				stmt.close();
				c.close();
					
				}
			catch(){}
				
				
				return salida;
			}//Fin del metodo getCantidad
			
			
	
		}
			
