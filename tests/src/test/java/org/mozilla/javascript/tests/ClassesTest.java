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

        Utils.runWithAllOptimizationLevels(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(true, actual);
                    return null;
                });
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
                        + "c";

        Utils.runWithAllOptimizationLevels(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(20, actual);
                    return null;
                });
    }

    @Test
    public void testSimpleClassDeclaration_ReturnCtor() {
        var classDeclaration =
                "class Rectangle {\n"
                        + "  constructor(height, width) {\n"
                        + "    this.height = height;\n"
                        + "    this.width = width;\n"
                        + "    this.id = 12;\n"
                        + "  }"
                        + "}\n"
                        + "Rectangle.test ";

        Utils.runWithAllOptimizationLevels(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(20, actual);
                    return null;
                });
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
                        + "p.distance(new Point(5, 5));";

        Utils.runWithAllOptimizationLevels(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(20, actual);
                    return null;
                });
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

        Utils.runWithAllOptimizationLevels(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Object actual =
                            cx.evaluateString(scope, classDeclaration, "declareClass", 0, null);
                    Assert.assertEquals(21.02379604162864, actual);
                    return null;
                });
    }

    @Test
    public void testFunctionCreation() {
        //        var functionDefinition =
        //                "  function fakeConstructor(height, width) {\n"
        //                        + "    this.height = height;\n"
        //                        + "    this.width = width;\n"
        //                        + "  }\n";

        var functionDefinition = " var clazz = {" + "  aField: 32," + "  bField: 33," + "  };";

        Utils.runWithAllOptimizationLevels(
                cx -> {
                    cx.setLanguageVersion(Context.VERSION_ES6);
                    Scriptable scope = cx.initStandardObjects();
                    Assert.assertEquals(
                            20,
                            cx.evaluateString(scope, functionDefinition, "declareClass", 0, null));
                    return null;
                });
    }
}
