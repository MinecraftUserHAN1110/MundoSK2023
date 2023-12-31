package com.pie.tlatoani.Miscellaneous.Tree;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Loop;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.sections.SecLoop;
import ch.njol.util.Kleenean;

import com.pie.tlatoani.Core.Skript.CustomScope;
import org.bukkit.event.Event;

/**
 * Created by Tlatoani on 5/7/16.
 */
public class ExprBranch extends SimpleExpression<String> {
    private ExprTreeOfListVariable exprTreeOfListVariable = null;

    @Override
    protected String[] get(Event event) {
        return new String[]{exprTreeOfListVariable.getBranch(event)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "branch";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (CustomScope.isNewSectionsSupported()) {
        	for (Object obj : CustomScope.getCurrentLoops()) {
        		SecLoop loop = (SecLoop) obj;
                if (loop.getLoopedExpression() instanceof ExprTreeOfListVariable) {
                    exprTreeOfListVariable = (ExprTreeOfListVariable) loop.getLoopedExpression();
                }
            }
        } else {
        	for (Object obj : CustomScope.getCurrentLoops()) {
        		Loop loop = (Loop) obj;
                if (loop.getLoopedExpression() instanceof ExprTreeOfListVariable) {
                    exprTreeOfListVariable = (ExprTreeOfListVariable) loop.getLoopedExpression();
                }
            }
        }
        if (exprTreeOfListVariable == null) {
            Skript.error("'branch' can only be used within a 'loop tree of %objects%' expression!");
            return false;
        }
        return true;
    }
}
