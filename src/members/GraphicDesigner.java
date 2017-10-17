package members;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class GraphicDesigner extends Member{
    private String software; // favourite software

    public GraphicDesigner(int id,String firstName, String lastName, boolean male, short age, String streetNo,
            String streetName, String suburb, String state,
            String favouriteSoftware){
        super(350,id,firstName,lastName,male,age,streetNo,streetName,suburb,state,false);
    }
    public GraphicDesigner(){
        super(350);
    }
    
    public void setFavouriteSoftware(String software) {
        this.software = software;
    }
    public String getFavouriteSoftware(){
        return software;
    }
}

