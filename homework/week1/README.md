# 类说明

## Expression 类

* **属性**
  * map：用于储存对应的<指数，项>
* **接口参数**：（只能两者选一个）
  * 多项式字符串
  * 多项式的map
* **方法**
  * checkFormat方法：检查输入的字符串是否合法
  * derivative方法：多项式求导，返回类型也为多项式
  * toString方法：将多项式转化为String

## Item 类

* **属性**
  * coeffi：项的系数
  * index：项的指数
* **接口参数**
  * 项的字符串（默认正确，无非法判断）
* **方法**
  * getCoeffi方法：获取项系数
  * getIndex方法：获取项指数
  * lessThanZero方法：布尔类型，返回该项系数是否小于零
  * add方法：将两个指数相同的项进行合并，即系数相加
  * derivative方法：项求导，返回项
  * toString方法：将项转化为String

