import scala.collection.mutable.Stack

// 递归函数必须声明返回值，哪怕是Uint
def build_tree(seq:Stack[Any]):Unit = 
    // if 还必须有()
    if (seq.length >= 3) {
        val num1:Any = seq.pop()
        val op:Any = seq.pop()
        val num2:Any = seq.pop()

        val node = (op,num1,num2)
        seq.push(node)
        build_tree(seq)
    }

def test1 = 
    var list1 = List("1","+","2","*","3")
    // var stack1 = Stack[Any]("1","+","2","*","3")
    var stack1 = Stack[Any]()
    stack1.pushAll(list1)

    build_tree(stack1)

    val res = stack1.pop()
    println(res)

@main def Stack1 =
    test1