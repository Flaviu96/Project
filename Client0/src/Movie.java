
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class Movie implements Serializable{
    private static final long serialVersionUID=48L;
    public String name,genre,userRate;
    public int metascore,year,score;
    public List<String>comments;
    public transient Blob blob;
    public double counterVote;
    public Movie(String name,String genre,int metascore,int year,String userRate){
        this.name=name;
        this.genre=genre;
       this.metascore=metascore;
       this.year=year;
       this.userRate=userRate;
    }
    public Movie(String name,String genre,int metascore,int year,Blob blob,String userRate){
        this.name=name;
        this.genre=genre;
       this.metascore=metascore;
       this.year=year;
       this.blob=blob;
       this.userRate=userRate;
    }
    public Movie(String name,int score,String genre){
        this.name=name;
        this.score=score;
        this.genre=genre;
    }
    public void setList(List<String>comments){
        this.comments=comments;
    }
    public void setCounterVote(int counterVote){
        this.counterVote=counterVote;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public String toString(){
        return name;
    }
}
