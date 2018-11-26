/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc4380.project3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Array;
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
        this.database();
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
    
    /**
     * @param show Show_ID
     * @return 2D array of seat availability for the specified movie and showtime
     */
    public int[][] getSeats(int show) {
        String s = "";
        int count = 0;
        int[][] seats = new int[5][10];
        
        
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
        
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 10; j++) {
                seats[i][j] = Integer.parseInt(""+s.charAt(count++));
            }
        }
        
        return seats;
    }
    
    /**
     * @return Title of movie
     */
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
    
    /**
     * @return String array of all movies stored in the database
     */
    public String[] getListings() {
        String s = "";
        String[] results = {};
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM movies");
            
            while (rs.next()) {
                s += rs.getString(2)+"@";   //Creates a string of all movies and uses "@" as a delimiter
            }
            
            results = s.split("@");         //Segments the string using the delimiter and stores each segment as an element in an array

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
        
        return results;
    }
    
    /**
     * Creates ticket that contains person's name, reserved seat number and Show_ID
     * Calls setSeat after ticket is created
     * @param sid Show_ID
     * @param seat Seat being reserved
     * @param name Name of person buying ticket
     */
    public void createTicket(int sid, String seat, String name) {
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            st.executeUpdate("INSERT INTO tickets (Show_ID, Seat, Name) VALUES ('"+sid+"', '"+seat+"', '"+name+"')");
            

        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               
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
        
        setSeat(sid, seat, getSeats(sid));
    }
    
    /**
     * Sets the set availability for the specific seat number for the showtime
     * Decrements the number of Available seats by 1
     * @param sid Show_ID
     * @param seat Seat number that is being reserved
     * @param seats current seat availability for that showtime
     */
    public void setSeat(int sid, String seat, int[][] seats) {
        String s = "";
        seats[seat.charAt(0)-65][Integer.parseInt(seat.substring(1))] = 1;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 10; j++) {
                s += seats[i][j];
            }
        }
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.addBatch("UPDATE showtime SET Seats = '"+s+"' WHERE Show_ID = "+sid+"");
            st.addBatch("UPDATE showtime SET Available_Seats = Available_Seats - 1 WHERE Show_ID = "+sid+"");
            st.executeBatch();

        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               
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
    }
    
    /**
     * @param mov Movie_ID
     * @return String array of all showtimes for the specified Movie
     */
    public String[] getShowtimes(int mov) {
        String s = "";
        String[] results = {};
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            st.executeQuery("SELECT * FROM showtime WHERE Movie_ID = "+mov+"");
            
            while(rs.next()) {
                s += rs.getString(3)+"@";
            }

            results = s.split("@");
        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               
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
        
        return results;
    }
    
    /**
     * Gets details of movie
     * @param mov Movie_ID
     * @return String array containing the movie title, movie description, rating and duration
     */
    public String[] getMovieDetails(int mov) {
        String[] details = {"N/A", "N/A", "--", "0:00:00"};     //Default values
        
        try {
            System.setProperty("jdbc.drivers", jdbc_drivers);
 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_maniacs", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM movies WHERE Movie_ID = "+mov+"");
            
            if(rs.next()) {
                details[0] = rs.getString(2);
                details[1] = rs.getString(3);
                details[2] = rs.getString(4);
                details[3] = rs.getString(5);
            }
            
        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            //lgr.log(Level.SEVERE, ex.getMessage(), ex);
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
               
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
        
        return details;
    }
}
