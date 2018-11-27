/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc4380.project3;

import java.io.IOException;

/**
 *
 * @author Michael
 */
public class Csc4380Project3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller (model, view);
        view.setVisible(true);
    }
    
}
