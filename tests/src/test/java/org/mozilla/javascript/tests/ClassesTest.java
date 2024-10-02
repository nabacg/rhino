package org.mozilla.javascript.tests;

import org.junit.Assert;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class ClassesTest {

    @Test
    public void testSimpleClassDeclaration() {
        var classDeclaration =
                "class Rectangle {\n"
                        + "  constructor(height, width) {\n"
                        + "    this.height = height;\n"
                        + "    this.width = width;\n"
                        + "    this.id = 12;\n"
                        + "  }"
                        + "}\n"
                        + "const r = new Rectangle(20, 10);"
                        + "r.height == 20";

        Utils.runWithOptimizationLevel(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(true, actual);
                    return null;
                },
                -1);
    }

    @Test
    public void testSimpleClassDeclaration_WithFields() {
        var classDeclaration =
                "class ClassWithField {"
                        + "constructor() {}"
                        + /// turns out we HAVE to have a constructor
                        "  instanceField;\n"
                        + "  instanceFieldWithInitializer = \"instance field\";\n"
                        + "  static staticField;\n"
                        + "  static staticFieldWithInitializer = \"static field\";\n"
                        + "}\n"
                        + "const c = new ClassWithField();"
                        + "c.instanceFieldWithInitializer";

        Utils.runWithOptimizationLevel(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals("instance field", actual);
                    return null;
                },
                -1);
    }

    @Test
    public void testClassDeclarationWithMethods() {
        var classDeclaration =
                "class Point {\n"
                        + "  constructor(x, y) {\n"
                        + "    this.x = x;\n"
                        + "    this.y = y;\n"
                        + "  }\n"
                        + "\n"
                        + "distance(b) {\n"
                        + "    const dx = this.x - b.x;\n"
                        + "    const dy = this.y - b.y;\n"
                        + "\n"
                        + "    return Math.hypot(dx, dy);\n"
                        + "  }\n"
                        + "}\n"
                        + "const p = new Point(20, 10);"
                        + "15.81 < p.distance(new Point(5, 5)) < 15.82;";

        Utils.runWithOptimizationLevel(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(true, actual);
                    return null;
                },
                -1);
    }

    @Test
    public void testClassDeclarationWithStatic() {
        var classDeclaration =
                "class Point {\n"
                        + "  constructor(x, y) {\n"
                        + "    this.x = x;\n"
                        + "    this.y = y;\n"
                        + "  }\n"
                        + "\n"
                        +
                        //                                        "  static displayName =
                        // \"Point\";\n" +
                        "  static distance(a, b) {\n"
                        + "    const dx = a.x - b.x;\n"
                        + "    const dy = a.y - b.y;\n"
                        + "\n"
                        + "    return Math.hypot(dx, dy);\n"
                        + "  }\n"
                        + "}\n"
                        +
                        //                "const r = new Rectangle(20, 10);" +
                        "const p = new Point(20, 10);"
                        + "Point.distance(p, new Point(1, 1))";

        Utils.runWithOptimizationLevel(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(21.02379604162864, actual);
                    return null;
                },
                -1);
    }
}
