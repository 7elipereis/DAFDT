package dafdt.utils;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import java.io.File;
import java.io.IOException;

public class GraphVizHelper {
    static Graphviz gv;
    public static void print2PNG(String graph, String label, String filePath) {
        graph = graph.replace("}", "label=\""+label+"\";labelloc=top;labeljust=center;}");
        gv = Graphviz.fromString(graph);
        try {
            gv.width(1800).render(Format.PNG).toFile(new File(filePath));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            gv = null;
        }
    }


}