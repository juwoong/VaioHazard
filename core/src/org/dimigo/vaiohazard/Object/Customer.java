
package org.dimigo.vaiohazard.Object;

/**
 * Created by YuTack on 2015-11-11.
 */

public class Customer extends VaioActor {
    private String name;

    public Customer(String name, String image, int cols, int rows){
        this.name = name;
        setAnimation(image, cols, rows);
    }

    public Customer(String name) {
        this(name, "default.png", 1, 1);
    }


}