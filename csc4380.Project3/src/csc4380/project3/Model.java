/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc4380.project3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Model {
    private Connection con;
    private java.sql.Statement st;
    private ResultSet rs;
    private String  jdbc_drivers, url, user, password = "";
    private String current_user, current_native, current_lastConver;
    private String status;
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public Model() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public void database(){
        con = null;
        st = null;
        rs = null;
        
        jdbc_drivers = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/movie_maniacs";
        user = "root";
        password = "";
        
    }
    
    public int[][] getSeats(int show) {
        String s = "";
        int count = 0;
        int[][] seats = {
            new int[8],
            new int[8],
            new int[10],
            new int[10],
            new int[10],
            new int[10],
        };
        
        
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM showtime WHERE Movie_ID = '"+show+"'");

            if (rs.next()) {
                s = rs.getString(6);
            }

        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Exception Caught");
               
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
               // Logger lgr = Logger.getLogger(Version.class.getName());
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                //lgr.log(Level.WARNING, ex.getMessage(), ex);
                            }
        }
        
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < (i < 2? 8:10); j++) {
                seats[i][j] = Integer.parseInt(""+s.charAt(count));
            }
        }
        
        return seats;
    }
    
    public String getMovie(int mov) {
        String s = "";
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM movies WHERE Movie_ID = "+mov+"");

            if (rs.next()) {
                s = rs.getString(2);
            }

        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Exception Caught");
               
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
               // Logger lgr = Logger.getLogger(Version.class.getName());
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                //lgr.log(Level.WARNING, ex.getMessage(), ex);
                            }
        }
        
        return s;
    }
    
    public String getListings() {
        String s = "";
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM movies");
            
            while (rs.next()) {
                s += rs.getString(2)+" ";
                
            }

        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Exception Caught");
               
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
               // Logger lgr = Logger.getLogger(Version.class.getName());
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                //lgr.log(Level.WARNING, ex.getMessage(), ex);
                            }
        }
        
        
        
        return s;
    }
}
