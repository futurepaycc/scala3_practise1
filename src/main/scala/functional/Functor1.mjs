// https://juejin.cn/post/6844903621507678216


/* 
示列理解:
scala中或一般情况下都是集合的map|flatMap
这里是Optional的Map|flatMap
*/


/* 
一个基本的函子，实现数据串操作
*/
class Functor {

  static of(val) {
    const res = new Functor(val)
    return res
  }

  constructor(value) {
    this.value = value
  }

  map(fn) {
    const res = Functor.of(fn(this.value))
    return res
  }

}

function test1() {
  const add5 = item => item + 5
  const double = item => item * 2
  let res1 = Functor.of(5).map(add5).map(double)
  console.log(res1)
}
test1()


/* 
一个maybe函子，体现了过滤操作的高阶函数特性
*/
class Maybe {
  static of(val) {
    return new Maybe(val)
  }

  constructor(value) {
    this.value = value;
  }
  map(fn) {
    return this.value ? Maybe.of(fn(this.value)) : Maybe.of(null);
  }
}

function test2() {
  const res = Maybe.of(null).map(item => item.toUpperCase());
  console.log(res)
}

test2()


/* 这就是monad? -- 就是flat再map*/
class Maybe2 {
  static of(val) {
    return new Maybe2(val)
  }

  constructor(value) {
    this.value = value;
  }
  map(fn) {
    return this.value ? Maybe2.of(fn(this.value)) : Maybe2.of(null);
  }
  flat() {
    return this.value
  }
}


function test3() {
  const toUpperCase = _ => _.toUpperCase()

  // .of(.of )
  let s1 = Maybe2.of( Maybe2.of("adsf") )

  let res = s1.flat().map(toUpperCase)
  console.log(res)
}

test3()