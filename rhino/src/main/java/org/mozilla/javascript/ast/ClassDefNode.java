package org.mozilla.javascript.ast;

import java.util.List;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

public class ClassDefNode extends ScriptNode {
    private Name className;
    private FunctionNode constructor;
    private List<MethodNode> methods;
    private List<FieldNode> fields;
    private Node transformedConstructor;
    private List<Node> transformedMethods;

    // TODO fields and properties
    public ClassDefNode(
            int pos,
            Name name,
            FunctionNode constructor,
            List<MethodNode> methods,
            List<FieldNode> fields) {
        super(pos);
        this.className = name;
        this.constructor = constructor;
        this.methods = methods;
        this.fields = fields;
    }

    {
        type = Token.CLASS;
    }

    public List<MethodNode> getMethods() {
        return methods;
    }

    public FunctionNode getConstructor() {
        return constructor;
    }

    public Name getClassName() {
        return className;
    }

    public void setConstructorIR(Node ctorIR) {
        this.transformedConstructor = ctorIR;
    }

    public Node getTransformedConstructor() {
        return transformedConstructor;
    }

    public List<FieldNode> getFields() {
        return fields;
    }

    public void setFields(List<FieldNode> fields) {
        this.fields = fields;
    }
}
