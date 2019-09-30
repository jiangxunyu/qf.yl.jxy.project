<!DOCTYPE html>
<html lang="zh-cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="Author" content="">
    <meta content=" " name="">
    <title></title>

    <link rel="stylesheet" type="text/css" href="css/css_whir.css"/>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="scripts/html5shiv.v3.72.min.js"></script>
    <![endif]-->
    <script src="scripts/jquery.SuperSlide.2.1.1.js"></script>
    <script type="text/javascript">
        var navID = "4";
    </script>
</head>

<body>
<!--top-->
<header class="header">
    <div class="Top">
        <div class="Fool1">
            <div id="is_login" class="txt">
                <span>您好，<b>我是VIP</b>欢迎来到海天一线交易平台</span>
                <a href="http://localhost:8091/user/login" class="Btn_login"><em class="Texthide">登录</em></a>
                <a href="http://localhost:8093/register" class="Btn_logout"><em class="Texthide">注册</em></a>
            </div>
            <nav class="topNav">
                <ul class="ul">
                    <li><a href="#">收藏夹</a></li>
                    <li><a href="#">诚信会员</a></li>
                    <li><a href="#">客服中心</a></li>
                    <li class="member"><a href="Member.html">会员中心</a></li>
                    <li class="myorder"><a href="http://localhost:8097/shopping/showOrder">购物车</a></li>
                    <!--<li class="phone">
                       <a href="javascript:void(0)">手机版</a>
                       <div class="PhoneBox">
                         <figure>
                            <img src="uploadfiles/image/temp/code.jpg" />
                            <figcaption>扫我去手机版</figcaption>
                            <div class="z">◆</div>
                            <div class="y">◆</div>
                         </figure>
                       </div>
                    </li>-->
                </ul>
                <div class="clear"></div>
            </nav>
            <div class="clear"></div>
        </div>
        <div class="Fool2">
            <div class="logo"><a href="index.html"><img src="images/logo3.png" width="284" height="73"/></a></div>
            <div class="MainSreach">
                <div class="KeyWord">
                    <a href="#">产品</a><em>|</em><a href="#">店铺</a><em>|</em><a href="Encyclopedia.html">百科</a><em>|</em><a
                        href="Supply.html">供求</a>
                </div>
                <div class="SreachBox">
                    <div class="Sreach">
                        <form action="http://localhost:8095/search/products" method="post" id="search_form">
                            <input name="keyword" type="text" class="sreach_ipu" id="key"
                                   onkeydown="entersearch()"/>
                            <span id="search_btn" class="sreach_btn"><span>搜 索</span></span>
                        </form>
                    </div>
                    <a href="#" class="Btn_Release">发布询价单</a>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <nav class="MainNav">
            <div class="CategoryNav">
                <a class="Btn" href="javascript:void(0)"><span>全部商品列表</span></a>
                <div class="CategoryList">
<#--                    <ul th:each="first_pro : ${productTypes}" th:if="${first_pro.pid==0}" class="ul">-->
<#--                        <li class="Li1"><a class="lb" href="#"><span th:text="${first_pro.name}"></span></a>-->
<#--                            <div class="nab">-->
<#--                                <span></span>-->
<#--                                <div class="SubNav">-->
<#--                                    <dl th:each="second_pro : ${productTypes}" th:if="${second_pro.pid==first_pro.id}">-->
<#--                                        <dt><a th:text="${second_pro.name}"></a></dt>-->
<#--                                    </dl>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </li>-->
<#--                    </ul>-->
                </div>
            </div>
            <ul class="ul NavList">
                <li id="nav1"><a href="http://localhost:8092/index">首 页</a></li>
                <li id="nav2"><a href="decoration.html">家装产品</a></li>
                <li id="nav3"><a href="EnterpriseCenter.html">企业中心</a></li>
                <li id="nav4"><a href="TradeCenter.html">交易中心</a></li>
            </ul>
            <div class="clear"></div>
        </nav>
    </div>
</header>
<script type="text/javascript">
    //导航选中
    $("#nav" + navID).addClass("onnav");

    //手机版
    $(".topNav li.phone").hover(function () {
        $(this).find(".PhoneBox").show();
    }, function () {
        $(this).find(".PhoneBox").hide();
    });

    //类别菜单
    if (navID == "1") {
        $(".MainNav .CategoryList").show();
    }
    $(".MainNav .CategoryNav").hover(
        function () {
            $(this).find(".CategoryList").show();
        },
        function () {
            if (navID == "1") {
                $(".MainNav .CategoryList").show();
            } else {
                $(this).find(".CategoryList").hide();
            }
        }
    );

    //登录之后隐藏 登录，注册
    var isLogin;
    if (isLogin == '1') {
        $(".Btn_login,.Btn_logout").hide();
        $(".Top .Fool1 .txt b").show();
    }
</script>

<script type="text/javascript">
    $(".CategoryList li").each(function () {
        $(this).find(".lb").hover(function () {
            var statis = $(this).parents("li").find(".nab").css("display");
            if (statis == "none") {
                $(this).parents("li").find(".nab").show();
            } else {
                $(this).parents("li").siblings().find(".nab").hide();
            }
        }, function () {
            $(".nab").hide();
        })
    })
    $(".nab").hover(function () {
        $(this).show();
    }, function () {
        $(".nab").hide();
    })
</script><!--top End-->

<section class="Contain">
    <div class="Current">
        <dl><a href="#">首页</a>><a href="#">交易中心</a>><span>求购信息</span></dl>
    </div>
    <div class="MainContain">
        <div class="TradingCenter">
            <aside class="js_AdverPic"><a href="#"><img src="uploadfiles/image/temp/js_AdverPic.jpg"/></a></aside>
            <div class="txtCon" style="border-width:1px;">
                <div class="Level1">
                    <div class="Product_bigimg">
                        <a id="abimg" class="thickbox" href="" rel="gallery-plants" title="" name="abimg">
                            <img id="bimg" style=" width:360px; height:192px;" border="0" name="bimg"
                                 src="${product.image}">
                        </a>
                    </div>
                    <div class="clear"></div>
                    <div class="Cont">

                        <dl>
                            <dt style="color:red;">${product.name}</dt>
                            <dt>
                                <span style="display: inline-block">基&nbsp;&nbsp;类:&nbsp;&nbsp;&nbsp;</span><span style="display: inline-block">${product.typeName}</span>
                            </dt>
<!--                            <dt><span>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：梦虹</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>生&nbsp;&nbsp;产&nbsp;&nbsp;商：杭州江南电缆有限公司</span>-->
<!--                            </dt>-->
<!--                            <dt><span>提货地点：浙江省 杭州市 富阳市</span></dt>-->
                            <dt><span>报&nbsp;&nbsp;价：<em class="sla">${product.price}</em>元/米</span>
                            </dt>
                            <dt><span>卖&nbsp;&nbsp;点：<em class="sla" >${product.salePoint}</em></span></dt>
                            <br/>
                            <dt>
                                <a href="http://localhost:8096/addProduct?id=${product.id}"><button type="button">加入购物车</button></a>
                            </dt>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <aside class="TradingLeft">
            <div class="SideDLZX" style="margin-top:0px;">
                <div class="tt"><span>电缆咨询</span><a href="电缆百科列表.html" class="more"></a></div>
                <div class="txtCon">
                    <ul>
                        <li>
                            <div class="item"><span>[问]</span> 铝合金电缆的安全性能，铝合...?</div>
                            <div class="answer">
                                <span>[答] </span>铝合金电缆的安全性能，铝合金电缆的
                                <time>2014-12-18</time>
                            </div>
                        </li>
                        <li>
                            <div class="item"><span>[问]</span> 铝合金电缆的安全性能，铝合...?</div>
                            <div class="answer">
                                <span>[答] </span>铝合金电缆的安全性能，铝合金电缆的
                                <time>2014-12-18</time>
                            </div>
                        </li>
                        <li>
                            <div class="item"><span>[问]</span> 铝合金电缆的安全性能，铝合...?</div>
                            <div class="answer">
                                <span>[答] </span>铝合金电缆的安全性能，铝合金电缆的
                                <time>2014-12-18</time>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="SideDLBK">
                <div class="tt"><span>电缆百科</span><a href="电缆百科.html" class="more"></a></div>
                <div class="txtCon">
                    <ul>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                        <li><a href="#">高品质8000系列铝合金杆的特性</a></li>
                    </ul>
                </div>
            </div>
        </aside>
    </div>
</section>
<script type="text/javascript">
    //推荐供应商滚动
    jQuery(".SupplierTJ .txtCon").slide({mainCell: ".bd ul", autoPage: true, effect: "top", autoPlay: true, vis: 6});

    //下拉选项
    $(".SelectBox").each(function () {    //同一个页面允许多次使用
        $(this).find(".SelectText").click(function () {  //点击展开下拉菜单
            var SelectListStatis = $(this).next(".SelectList").css("display");
            if (SelectListStatis == "none") {    //判断下拉菜单是否展开
                $(this).next(".SelectList").show();
            } else {
                $(this).next(".SelectList").hide();
            }
        });
        //下拉菜单选项点击事件
        $(this).find(".SelectList").children("dt").click(function () {
            var Value = $(this).find("a").text();  //点击获取值
            //赋值
            $(this).parents(".SelectList").prev(".SelectText").text(Value)
            $(this).parents(".SelectList").hide();
            $(this).mouseleave(function () {
                $(this).find(".SelectList").hide();
            });
        });
    });
    //选项卡切换
    $(".TradingCenter .JShd li:first").addClass("on");
    $(".TradingCenter .txtCon .JSCon:first").show();
    $(".TradingCenter .JShd li").each(function (i) {
        $(this).hover(function () {
            $(".TradingCenter .JShd li").removeClass("on");
            $(this).addClass("on");
            $(".TradingCenter .txtCon .JSCon").hide();
            $(".TradingCenter .txtCon .JSCon").eq(i).show();
        });
    });
    $(".CategoryList").hide();
</script>

<!--bottom-->
<footer class="footer">
    <div class="Fool1">
        <div class="BoxL BtNav">
            <ul class="ul">
                <li>
                    <span><img src="images/Btico_1.jpg"/><b>新手指南</b></span>
                    <dl>
                        <dd><a href="#">了解缆天</a></dd>
                        <dd><a href="#">注册新用户</a></dd>
                        <dd><a href="#">买家入门</a></dd>
                        <dd><a href="#">商家入门</a></dd>
                    </dl>
                </li>
                <li>
                    <span><img src="images/Btico_2.jpg"/><b>订单指南</b></span>
                    <dl>
                        <dd><a href="#">如何下单</a></dd>
                        <dd><a href="#">修改订单</a></dd>
                        <dd><a href="#">取消订单</a></dd>
                        <dd><a href="#">查看订单进程</a></dd>
                        <dd><a href="#">支付问题</a></dd>
                    </dl>
                </li>
                <li>
                    <span><img src="images/Btico_3.jpg"/><b>配送问题</b></span>
                    <dl>
                        <dd><a href="#">配送时间</a></dd>
                        <dd><a href="#">运费报价</a></dd>
                    </dl>
                </li>
                <li>
                    <span><img src="images/Btico_4.jpg"/><b>供应商服务</b></span>
                    <dl>
                        <dd><a href="#">供应商入驻</a></dd>
                        <dd><a href="#">资质审核</a></dd>
                        <dd><a href="#">供应商管理</a></dd>
                        <dd><a href="#">质量管理</a></dd>
                    </dl>
                </li>
                <li>
                    <span><img src="images/Btico_5.jpg"/><b>帮助中心</b></span>
                    <dl>
                        <dd><a href="#">常见问题</a></dd>
                        <dd><a href="#">电缆百科</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="BoxR">
            <p class="p">
                免费会员咨询热线：<b>400-8888-888</b>(免长途费) <br/>
                客服传真：<b>021-888888888</b><br/>
                交易安全帮助热线：<b>021-88888888</b>
            </p>
            <ul class="ul">
                <li class="weixin">
                    <figure class="CodeBox">
                        <div class="CodeImg"><img src="uploadfiles/image/temp/code_weixin.gif"/><span></span></div>
                        <figcaption>官方微信</figcaption>
                    </figure>
                </li>
                <li class="weibo">
                    <figure class="CodeBox">
                        <div class="CodeImg"><img src="uploadfiles/image/temp/code_weibo.gif"/><span></span></div>
                        <figcaption>官方微博</figcaption>
                    </figure>
                </li>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="Fool2">
        <div class="Copyright">
            Copyright © 2014 All rights reserved. ed by <br/>
            网站备案编号：02010802088888888
        </div>
        <div class="imgBox">
            <ul class="ul">
                <li><img src="uploadfiles/image/temp/Btimg_1.gif" width="90" height="30"/></li>
                <li><img src="uploadfiles/image/temp/Btimg_2.gif" width="73" height="30"/></li>
                <li><img src="uploadfiles/image/temp/Btimg_3.gif" width="70" height="30"/></li>
                <li><img src="uploadfiles/image/temp/Btimg_4.gif" width="71" height="30"/></li>
                <li><img src="uploadfiles/image/temp/Btimg_5.gif" width="71" height="30"/></li>
                <li><img src="uploadfiles/image/temp/Btimg_6.gif" width="85" height="30"/></li>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>


    <!--右边悬浮框-->
    <div class="FloatBox" id="FloatBox">
        <div class="Enter">
            <a href="http:///htdl201412167358/WEB_ZXPT/index.html">
                <img alt="" src="images/jrzx.png"/>
            </a>
        </div>
    </div>
</footer>
<script type="text/javascript" src="scripts/float.js"></script>
<script type="text/javascript">
    //返回顶部
    $(".ReturnTop").click(function () {
        $('body,html').animate({scrollTop: 0}, 1000);
        return false;
    });
    //整站无图处理
    jQuery.each(jQuery("img"), function (i, n) {
        jQuery(n).error(function () {
            n.src = 'uploadfiles/nopic.jpg';
        });
        n.src = n.src;
    });
</script>
<!--bottom End-->
</body>
</html>

<script type="text/javascript">
    $(".Scroll").find("li a:first").addClass("onpig")
    $(".Scroll li").click(function () {
        //选中样式
        $(this).children("a").addClass("onpig");
        $(this).siblings().children("a").removeClass("onpig");
        //大图切换
        var href = $(this).find("img").attr("src");
        $(".Product_bigimg").find("img").attr("src", href);
    });

    $(".col").find("li:first").addClass("chose")
    $(".col").find("li").click(function () {
        $(".col").find("li").removeClass("chose")
        $(this).addClass("chose")
    })

    //显示用户登录状态
    $.ajax({
        url:"http://localhost:8091/user/checkIsLogin",
        type:'GET',
        data:'json',
        //允许携带证书
        xhrFields: {
            withCredentials: true
        },
        //允许跨域
        crossDomain: true,
        success:function (d) {
            console.log(d),
                $("#is_login").html("欢迎您，"+d.data.username+"\n" +
                    "                |&nbsp;<a href=\"http://localhost:8091/user/logout\">注销</a>")
        }
    })

    $("#search_btn").click(function () {
        $("#search_form").submit()
    })

</script>