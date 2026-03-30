package org.example;

import lombok.Getter;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.spanning.KruskalMinimumSpanningTree;
import org.graph4j.spanning.MinimumSpanningTreeBase;
import org.graph4j.traversal.DFSIterator;
import org.graph4j.traversal.SearchNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class City {
    private List<Street> streets;
    private List<Intersection> intersections;
    private Graph graf;


    public City(List<Street> streets, List<Intersection> intersections) {
        this.streets = streets;
        this.intersections = intersections;
        createGraph();
    }

    public List<Street> longerThan(int lg){
        return streets.stream().filter(x -> x.getLength() > lg).collect(Collectors.toList());
    }

    private void createGraph(){
        graf = GraphBuilder.empty().estimatedNumVertices(intersections.size()).estimatedAvgDegree(intersections.size() - 1).buildGraph();

        for(int i = 0; i < intersections.size(); i++){
            graf.addLabeledVertex(i, intersections.get(i));
        }

        for(Street s : streets){
           int i = intersections.indexOf(s.getFrom());
           int j = intersections.indexOf(s.getTo());

           graf.addLabeledEdge(i,j,s);
           graf.setEdgeWeight(i,j,s.getLength());
        }
    }

    public MinimumSpanningTreeBase mst(){
        return new KruskalMinimumSpanningTree(graf);
    }

    public List<Integer> getSurvCarRoute(){
        List<Integer> route = new ArrayList<>();
        MinimumSpanningTreeBase mstEdg = new KruskalMinimumSpanningTree(graf);
        mstEdg.getEdges();

        Graph mstTree = mstEdg.getTree();

        DFSIterator iterator = new DFSIterator(mstTree, 0);
        while(iterator.hasNext()){
            SearchNode node = iterator.next();
            if(!route.contains(node.vertex())){
                route.add(node.vertex());
            }
        }

        return route;

    }

}
