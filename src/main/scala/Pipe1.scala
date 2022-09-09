// https://alvinalexander.com/scala/scala-2.13-pipe-tap-chaining-operations/

/* 
1. 给AnyVal及子类定义一个隐式转换
2. 转换后的类有 |> 高阶函数 => 有点函子的味道
3. 新的高阶函数使用原函数运算
*/
implicit private class AnyEx[+T](val v: T) extends AnyVal {
    def |>[U] (f: T ⇒ U): U = f(v)
}

@main def Pipe1 = 
  // 数学与pipe的隐式转换
  36.0 |> math.sqrt |> println

  // 要对List起作用，需要加上 (), 将lamba转为函数的意思么?
  List(4.0,9.0,25.0) |> (l => l.length) |> println
  List(4.0,9.0,25.0) |> (l => l.map(math.sqrt)) |> println