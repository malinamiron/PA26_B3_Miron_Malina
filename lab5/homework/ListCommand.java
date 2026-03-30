package org.example.homework;

import org.example.compulsory.Repository;

public class ListCommand implements Command{
    private ListCommand(){};

    public static void list(Repository repo){
        System.out.println(repo.getRepository().stream().toList());
    }
}
