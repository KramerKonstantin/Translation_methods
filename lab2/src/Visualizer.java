import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Visualizer {
    private static BufferedWriter w;

    static void visualizer(Tree tree, String test) throws IOException {
        try {
            w = new BufferedWriter(new FileWriter(new File("tree.dot")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        w.write("digraph {" + System.lineSeparator());
        buildGraph(tree);
        w.write("}");
        w.flush();
        MutableGraph g = guru.nidi.graphviz.parse.Parser.read(new File("tree.dot"));
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("result/" + test + ".png"));
    }

    private static long buildGraph(Tree tree) throws IOException {
        w.write(tree.hashCode() + " [label=\"" + tree.getNode() + "\"");
        if (tree.getChildren().isEmpty()) w.write(", color=\"red\"");
        w.write("];" + System.lineSeparator());
        for (Tree t: tree.getChildren()) w.write(tree.hashCode() + " -> " + buildGraph(t) + System.lineSeparator());
        return tree.hashCode();
    }
}
