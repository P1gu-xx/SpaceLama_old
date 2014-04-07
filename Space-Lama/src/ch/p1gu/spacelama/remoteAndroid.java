/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.p1gu.spacelama;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.RemoteInput;

/**
 *
 * @author Michael
 */
public class RemoteAndroid {

    private static Input input;
    private RemoteAndroid() {
        input=new RemoteInput();
    }
    
    public static Input getInstance(){
    if(input==null){
        input=new RemoteInput();
    }
        return input;
    }
    
}
