/* 
scala3最有用的好处: 全局函数，可以像py一样写脚本了
* https://juejin.cn/post/7061227510884909063 (中文上手)
*/
import java.util.ArrayList

// 单行函数
def sum(a:Int,b:Int):Int = a + b

// 多行函数，有点像python了
def mean(in:List[Int]):Int = 
  // NOTE v2中不能在无{}块函数中定义变量, v3看来是可以了
  var res = 0
  // for循环不能有后面的:, 不是python
  for(item<-in)
    res += item
  res/in.length

@main def hello =
    // 使用java的类一般还得new, var|val同义
    var l1 = new ArrayList[Any]()
    l1.add("hello")
    println(l1)

    // val 不可修改，var可以
    var res = sum(3,5)
    printf("sum res:%d\n",res)
    
    var l2 = (0 to 9).toList
    res = mean(l2)
    printf("sum res:%d\n",res)

    new Thread(()=>{
        println("run in thread")
    }).start

    Thread.sleep(100)
