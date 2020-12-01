package com.example.recycleviewpractice;
import android.app.Application;


public class GlobalVariable extends Application {
    private String Usnername,Token;

    public void setUsername(String username){
        this.Usnername=username;
    }
    public void setToken(String token){
        this.Token=token;
    }
    public String getUsername(){
        return Usnername;
    }
    public String getToken(){
        return Token;
    }
}