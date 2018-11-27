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
public class Controller {
    
    private View views;
    private Model models;
    public int movieId;

    Controller (Model model, View view)
    {
        views = view;
        views.addALSeats(new seatListener());
        views.addALMovie(new movieListener());
        //views.addTimeAL(new timeButtonsListener());

        models = model;
        for (int i = 1; i <= 4; i++) {
            views.setMovieTitle(model.getMovie(i), i);
        }
    }

    class seatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            views.checkout();
        }      
    }
    
    class movieListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //int movieId = views.getMovie();
            if (e.getSource() == views.movie1) {
                movieId = 1;
            } else if (e.getSource() == views.movie2) {
                movieId = 2;
            } else if (e.getSource() == views.movie3) {
                movieId = 3;
            } else if (e.getSource() == views.movie4) {
                movieId = 4;
            }
            System.out.println("CONTROLLER: Mov id here is "+movieId);
            views.addTimes(models.getShowtimes(movieId)); 
            views.showTimes();
        }
    }

//    class timeButtonsListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            views.genSeats();
//            views.addSeats(model.getSeats(movieId));
//            
//            views.showSeats();
//        }
//    }
}

