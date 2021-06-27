package com.alejandro.breakingbad.model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BBCharacter implements Serializable {

    private int char_id;
    private String name;
    private String nickname;
    private String img;
    private ArrayList<String> occupation;
    private String status;
    private String portrayed;

    public BBCharacter(int char_id, String name, String nickname, String img, ArrayList<String> occupation, String status, String portrayed) {
        this.char_id = char_id;
        this.name = name;
        this.nickname = nickname;
        this.img = img;
        this.occupation = occupation;
        this.status = status;
        this.portrayed = portrayed;
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<String> getOccupation() {
        return occupation;
    }

    public void setOccupation(ArrayList<String> occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPortrayed() {
        return portrayed;
    }

    public void setPortrayed(String portrayed) {
        this.portrayed = portrayed;
    }
}
