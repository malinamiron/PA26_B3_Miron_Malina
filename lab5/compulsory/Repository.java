package org.example.compulsory;

import org.example.advance.Concepts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Repository {
    Set<Resource> repository;

    public Repository(Set<Resource> repository) {
        this.repository = repository;
    }

    public Repository() {
        this.repository = new HashSet<>();
    }

    public void addResource(Resource resource){
        repository.add(resource);
    }

    public Set<Resource> getRepository() {
        return repository;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repository=" + repository +
                '}';
    }

    public List<Resource> minimSet() {
        Set<Concepts> allNeededConcepts = new HashSet<>();
        for (Resource res : this.repository) {
            if (res.getConceptsList() != null) {
                allNeededConcepts.addAll(res.getConceptsList());
            }
        }

        Set<Concepts> uncovered = new HashSet<>(allNeededConcepts);
        List<Resource> selectedResources = new ArrayList<>();

        while (!uncovered.isEmpty()) {
            Resource bestResource = null;
            int maxNewlyCovered = 0;

            for (Resource res : this.repository) {
                int count = 0;
                if (res.getConceptsList() != null) {
                    for (Concepts concept : res.getConceptsList()) {
                        if (uncovered.contains(concept)) {
                            count++;
                        }
                    }
                }

                if (count > maxNewlyCovered) {
                    maxNewlyCovered = count;
                    bestResource = res;
                }
            }
            if (bestResource == null) {
                break;
            }

            selectedResources.add(bestResource);
            uncovered.removeAll(bestResource.getConceptsList());
        }

        return selectedResources;
    }
}
