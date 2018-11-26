/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc4380.project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

/**
 *
 * @author Joana
 */
public class Controller{
    
    private View views;
    private Model models;

    Controller (Model model, View view)
    {
        views = view;
        views.addALSeats(new seatListener());
        views.addALMovie(new movieListener());
        views.addTimeAL(new timeButtonsListener());
        models = model;
        //model.getConnection();
        
    }
    
    class seatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            views.checkout();
        }      
    }
    
    class movieListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //int movieId = views.getMovie();
            String s = views.movie1.getText();
            int movieId = s.length();
            s = s.substring(movieId-1, movieId);
            movieId = Integer.parseInt(s);
            
            //System.out.println("Mov id is "+movieId);
            views.addTimes(models.getShowtimes(movieId)); 
            //how to populate this view with the data retrieved above
            views.showTimes();
        }
    }
    
    class timeButtonsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            views.showSeats();
        }
    }
}

