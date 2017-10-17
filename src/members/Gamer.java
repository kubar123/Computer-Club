package members;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub Rybicki
 */
public class Gamer extends Member{
    private String favGenre;
    private String favGame;
    
    public Gamer(){
	super(140);
    }
    public Gamer(int id, String firstName,String lastName, boolean male, short age, String streetNo,
            String streetName, String suburb, String state, int postCode,
            String favouriteGenre, String favGame){
        super(140, id,firstName,lastName,male,age,streetNo,streetName,suburb,state,true);	
    }

    public void setFavGenre(String favGenre) {
            this.favGenre = favGenre;
    }

    public void setFavGame(String favGame) {
            this.favGame = favGame;
    }
	
	
    public String getFavGenre() {
        return favGenre;
    }

    public String getFavGame() {
        return favGame;
    }
    
    

    @Override
    public String toString() {
        return super.toString()+"Gamer{" + "favGenre=" + favGenre + ", favGame=" + favGame + '}';
    }
	
}
