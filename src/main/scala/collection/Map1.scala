package collection

private case class Person(id: Int, name: String);

val pl = List(Person(1, "liunix1"), Person(2, "liunix2"), Person(3, "liunix3"))

def test_map1 =
  var m1 = Map(1 -> Person(1, "liunix1"))
  m1 += (2 -> Person(2, "liunix2"))

  println(m1(1))
  println(m1(2))

def test_map2: Map[Int, Person] =
  var m2 = Map[Int, Person]()
  pl.zipWithIndex.foreach { case (item, idx) =>
    m2 += (idx -> item)
  }

  println(m2(0).name)
  return m2

private def find_person_byId(id: Int): Option[Person] =
  val m2 = test_map2
  val p = m2.get(id)  //map的get自动是Option,使用()则是确定性结果
  return p

def test_flatmap1 =
  // list to map
  val l1 = List(find_person_byId(0),find_person_byId(1),find_person_byId(2))
  val res1 = l1.map(item=>item.get.name) //这里使用flatmap会返回打散String->Char的串
  println(res1)

  // map
  val res2 = List(0,1,2).map(id=>find_person_byId(id))
  println(res2)

  // NOTE floatmap要点: 可以褪去Option的壳, 而且这里key没有也不会报错
  val res3 = List(0,1,2,100).flatMap(id=>find_person_byId(id))
  println(res3)

@main def Map1 = 
  test_map1
  test_map2
  test_flatmap1