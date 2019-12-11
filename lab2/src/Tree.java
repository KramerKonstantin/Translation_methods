import java.util.ArrayList;
import java.util.List;

class Tree {
    private String node;
    private List<Tree> children;

    Tree(String node) {
        this.node = node;
        this.children = new ArrayList<>();
    }

    void addChild(Tree child) {
        children.add(child);
    }

    List<Tree> getChildren() {
        return children;
    }

    String getNode() {
        return node;
    }
}
