package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;

public class MethodNode {

    private FunctionNode functionNode;
    private boolean isStatic = false;
    private Node transformedFunction;

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public MethodNode(FunctionNode f) {
        this.functionNode = f;
    }

    public MethodNode(FunctionNode f, boolean isStatic) {
        this(f);
        this.isStatic = isStatic;
    }

    public FunctionNode getFunction() {
        return functionNode;
    }

    public Node getMethodIR() {
        return transformedFunction;
    }

    public void setMethodIR(Node ir) {
        this.transformedFunction = ir;
    }
}
