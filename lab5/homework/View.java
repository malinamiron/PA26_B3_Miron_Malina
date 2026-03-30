package org.example.homework;

import org.example.compulsory.Resource;

public class View implements Command {
    private View(){};

    public static void view(Resource resource){
        resource.openResource();
    }
}
