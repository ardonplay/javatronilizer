package io.github.ardonplay.javatronilizer;

public class User {


    public class Subclass {
        public String username;
        public Subclass (String username){
            this.username = username;
        }
    }
    public Subclass subclass;
    public User(String username){
        this.subclass = new Subclass(username);
    }
}
