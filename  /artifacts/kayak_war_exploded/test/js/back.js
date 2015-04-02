var highlight=-1;
$(document).ready(function(){
    //获取文本框
    var depart = $("#airportA");
    //获得其偏离的数据
    var offset = depart.position();

    var showBox = $("#arAirport")
    //隐藏并固定联想框位置
    showBox.css({
        "position": "absolute",
        "background-color": "white",
        "border":"1px #73ABEF solid",
        "top": offset.top + depart.height +"px",
        "left": offset.left + "px",
        "z-index": 99999
    }).width(depart.width()+2).hide();
    //添加键盘监听事件
    depart.keyup(function(event){
        //获取按键
        var code = event.keyCode;

        if((code>=65 && code<=90) || code==8 || code==46){
            //当输入英文，退格或删除键时需要向服务器发送数据进行判断
            //1.首先需要获取文本框里的内容
            var content = depart.val();
            //2.判断文本框内容
            if(content!=""){
                //首先清空上次查询结果
                showBox.empty();
                //将内容发给服务器并显示返回内容
                $.post("/lookup",{word: content},function(data){
                    var names = data.split("|");
                    if(data.length>1 && names.length>0){
                        names.each(function(value){
                            //新建div节点，并将单词内容加入到新建节点中
                            var newNode = $("<div class='childNode'>");
                            newNode.html(value).appendTo(showBox);
                            newNode.mouseenter(function(){
                                //if(highlight!=-1){
                                //    names[highlight].css()
                                //}
                                $(this).css("background-color","#4373CC").css("color","white");
                            });
                            newNode.mouseleave(function(){
                                $(this).css("background-color","white").css("color","#574D3D");
                            });
                            newNode.click(function(){
                                var text = $(this).text();
                                showBox.hide();
                                depart.val(text);
                            });
                        });
                        showBox.show();
                    }
                },"text");
            }else{
                showBox.hide();
            }
        }else if(code==38){
            //输入内容为向上箭头时
            //1.获取显示框中所有子节点
            var allNodes = showBox.children("div");
            //2. 判断指针位置
            if(highlight!=-1){
                allNodes.eq(highlight).css("background-color","white").css("color","#574D3D");
                highlight--;
            }
            if(highlight==-1){
                highlight=allNodes.length-1;
            }
            allNodes.eq(highlight).css("background-color","#4373CC").css("color","white");
        }else if(code==40){
            //输入内容为向下箭头时
            var allNodes =showBox.children("div");

            if(highlight!=allNodes.length){
                allNodes.eq(highlight).css("background-color","white").css("color","#574D3D");
                highlight++;
            }

            if(highlight==allNodes.length){
                highlight=0;
            }

            allNodes.eq(highlight).css("background-color","#4373CC").css("color","white");

        }else if(code==13){
            //输入回车时
            if(highlight!=-1){
                //下拉框有选中内容
                var content = showBox.hide().children("div").eq(highlight).text();
                highlight = -1;
                depart.val(content);
            }
        }
    });
});
