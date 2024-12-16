package models;

public class UserData {
    private int age;
    private String gender;
    private double cpt;
    private double rbp;
    private int chol;
    private boolean fbs;
    private int ecg;
    private int thalach;
    private boolean exang;
    private int oldpeak;

    public UserData(int age, String gender, double cpt, double rbp, int chol,
                    boolean fbs, int ecg, int thalach,
                    boolean exang, int oldpeak) {
        this.age = age;
        this.gender = gender;
        this.cpt = cpt;
        this.rbp = rbp;
        this.chol = chol;
        this.fbs = fbs;
        this.ecg = ecg;
        this.thalach = thalach;
        this.exang = exang;
        this.oldpeak = oldpeak;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getCpt() {
        return cpt;
    }

    public void setCpt(double cpt) {
        this.cpt = cpt;
    }

    public double getRbp() {
        return rbp;
    }

    public void setRbp(double rbp) {
        this.rbp = rbp;
    }

    public int getChol() {
        return chol;
    }

    public void setChol(int chol) {
        this.chol = chol;
    }

    public boolean fbs() {
        return fbs;
    }

    public void setFbs(boolean fbs) {
        this.fbs = fbs;
    }

    public int ecg() {
        return ecg;
   }

    public int getThalach() {
        return thalach;
    }

    public void setThalach(int thalach) {
        this.thalach = thalach;
    }

    public boolean getExang() {
        return exang;
    }

    public void setExang(boolean exang) {
        this.exang = exang;
    }

    public int getOldpeak() {
        return oldpeak;
    }

    public void setOldpeak(int oldpeak) {
        this.oldpeak = oldpeak;
    }

}