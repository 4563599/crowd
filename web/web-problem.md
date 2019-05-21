i标签是干啥的

行高等于高度 居中

#header .mobile-link

http://www.w3school.com.cn/css/css_selector_descendant.asp
后代选择器（Ｅ Ｆ）

选择子元素
h1 > strong {color:red;}

<h1>This is <strong>very</strong> <strong>very</strong> important.</h1>
<h1>This is <em>really <strong>very</strong></em> important.</h1>

这个规则会把第一个 h1 下面的两个 strong 元素变为红色，但是第二个 h1 中的 strong 不受影响：


h1 em {color:red;}c

<h1>This is a <em>important</em> heading</h1>
<p>This is a <em>important</em> paragraph.</p>


选择相邻兄弟
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
h1 + p {margin-top:50px;}
</style>
</head>

<body>
<h1>This is a heading.</h1>
<p>This is paragraph.</p>
<p>This is paragraph.</p>
<p>This is paragraph.</p>
<p>This is paragraph.</p>
<p>This is paragraph.</p>
</body>
</html>

选择紧接在 h1 元素后出现的段落，h1 和 p 元素拥有共同的父元素”。


a标签去掉下划线

<img src="img/c_06.jpg" height="136" width="120" alt="">
alt是干嘛的

#header .mobile-link > img
>是什么选择器


位置错误
检查为什么