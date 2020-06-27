package ir.declarations

import buildPrettyString
import ir.visitors.IRElementVisitor
import ir.visitors.IRElementTransformer

class IRVar(override val name: String, val expression: IRExpression, override var parent: IRStatementContainer? = null) : IRDeclarationWithName{

    override fun <R, D> accept(visitor: IRElementVisitor<R, D>, data: D): R =
        visitor.visitVar(this, data)

    override fun <D> transformChildren(transformer: IRElementTransformer<D>, data: D) {
        expression.transform(transformer, data)
    }

    override fun toString(): String =
        buildPrettyString {
            append("var %$name = $expression")
        }

}