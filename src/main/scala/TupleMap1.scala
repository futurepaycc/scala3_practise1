def test_tuple1 = 
    var t1 = ("+","3","5")
    println(t1)

    // 下标从1始
    println(t1._1) 
    println(t1._2)
    println(t1._3)

    // 支持析构
    val (op,left,right) = t1
    printf("%s,%s,%s\n",op,left,right)


def test_map1 = 
    val m1 = Map("name"->"liunix","age"->40)
    println(m1)

    // 增加key的方式
    val m2 = m1 + ("addr"->"beijing")
    println(m2)

    // 转为java map: https://stackoverflow.com/questions/16583265/how-can-i-convert-scala-map-to-java-map-with-scala-float-to-java-float-k-v-conve
    import scala.jdk.CollectionConverters._
    val mj = m2.asJava
    println(mj.getClass.getName)

@main def TupleMap1 =
    test_tuple1
    test_map1