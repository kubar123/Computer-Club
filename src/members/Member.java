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
public class Member {

    public int memberId;
    private String firstName;
    private String lastName;
    private boolean male;
    private short age;
    private String streetNo;
    private String streetName;
    private String suburb;
    private String state;
    private boolean isValid;
    private boolean isGamer;

    @Override
    public String toString() {
        return "Member{" + "memberId=" + memberId + ", firstName=" + firstName
            + ", lastName=" + lastName + ", male=" + male + ", age=" + age
            + ", streetNo=" + streetNo + ", streetName=" + streetName
            + ", suburb=" + suburb + ", state=" + state + ", isValid="
            + isValid + ", fee=" + fee + ", gamer=" + gamer + '}';
    }

    public double fee;
    private boolean gamer;

    public Member(int fee) {
        this.fee = fee;
    }

    public Member(double fee, int id, String firstName, String lastName, boolean male, short age, String streetNo,
        String streetName, String suburb, String state, boolean isGamer) {
        //memberId++;
        this.isGamer = isGamer;
        this.memberId = id;
        this.fee = fee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.male = male;
        this.age = age;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.suburb = suburb;
        this.state = state;
    }

// ------------------------- getters -------------------------
    public boolean isGamer() {
        return gamer;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isMale() {
        return male;
    }

    public short getAge() {
        return age;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getState() {
        return state;
    }

    public double getFee() {
        return fee;
    }
// ----------------- setters ----------------
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setGamer(boolean gamer) {
        this.gamer = gamer;
    }
}
