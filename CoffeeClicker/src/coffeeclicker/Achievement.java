/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeclicker;

/**
 *
 * @author Kynan
 */
public class Achievement {
    
    private String message;
    
    public Achievement() {
        System.out.println("\nNew achievement created!");
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
