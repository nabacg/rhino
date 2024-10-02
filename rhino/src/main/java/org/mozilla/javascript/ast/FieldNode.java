package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;

public class FieldNode {
    private boolean isStatic = false;
    private Name name;
    private Node initExpr;

    public FieldNode(Name fieldName, AstNode initExpr) {
        this.name = fieldName;
        this.initExpr = initExpr;
    }

    public FieldNode(Name fieldName, AstNode initExpr, boolean isStatic) {
        this(fieldName, initExpr);
        this.isStatic = isStatic;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Node getInitExpr() {
        return initExpr;
    }

    public void setInitExpr(Node initExpr) {
        this.initExpr = initExpr;
    }
}
