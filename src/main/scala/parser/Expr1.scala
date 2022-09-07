/*
说明: 这个很大程度上是用来练习BNF的工具
优点: 可以debug
缺点: 对java|scala平台和语法规则有点强依赖(貌似可以通过RegexParsers来扩展)
目标: 写一个灵活点的markdow parser分离代码和普通文本(后面可以想办法转成js语言)
 * https://github.com/tristanjuricek/knockoff/blob/master/src/main/scala/com/tristanhunt/knockoff/MarkdownParsing.scala
 * https://github.com/greatcodeclub/markdown_parser (js之jison版本的markdow语法解析)
 */

/*
来源: 《scala编程》
代码:  https://booksites.artima.com/programming_in_scala_4ed/examples/html/ch33.html
参考:  https://github.com/heguangwu/doc/blob/master/Scala%20Parser%20Combinators%20%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93.md
 */

/*
书:
快学scala_2nd 20章
Scala程序设计_2nd 20章
Practical Scala DSLs 6、7章
*/
import scala.util.parsing.combinator._

/*
1. EBNF 表达式 => 对比js版本的仔细练习
expr ::= term {"+" term | "-" term}
term ::= factor {"*" factor | "/" factor}
factor ::= floatingPointNumber | "(" expr ")"

2. 代码符号说明
~ 连接词，用于连接两个token，实际上是一个case类，定义：case class ~[+a, +b](_1: a, _2: b)
| 或操作，和EBNF中的|等同
rep 用于替换上面文法中EBNF中的大括号，该函数返回Parser[List]，此外还有一个rep1，和rep的区别是：rep表示0或多个，而rep1是一或多个
^^ 转换parser的结果，即^^后面的函数处理parser解析的值，可以理解为map函数
<~ 提取器，因为 '~' 会出现在字面量中，需要进一步case匹配对应的 '~' ，为提取更快捷，提供'~>'用于提取右边的结果、'<~'提取其左边的结果，一般配对使用
 */
class Paser1 extends JavaTokenParsers {
  def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)
  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)
  def factor: Parser[Any] = floatingPointNumber | "(" ~ expr ~ ")"
}

def Expr1_test1() = {
  val parser1 = new Paser1();
  val result = parser1.parseAll(parser1.expr, "2 * (3 + 7)")
  println(result)
}

@main def Expr1 = {
  Expr1_test1() //App已包含main函数，这里不需要
}
