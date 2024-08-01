/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.Cookie;

/**
 *
 * @author kerberos
 */
public abstract class Constants {
    public static final String URL = "jdbc:mysql://localhost:3306/jobhunter";
    public static final String USER = "root";
    public static final String PASSWORD = "@Meltdown6057";
    
    public static Cookie setCookie(String name, String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        return cookie;
    }
}
