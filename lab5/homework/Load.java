package org.example.homework;

import org.example.compulsory.Repository;
import org.example.compulsory.Resource;

public class Load implements Command{
    private Load(){};

    public static void load(Repository repo , Resource resource){
        repo.addResource(resource);
    }
}
