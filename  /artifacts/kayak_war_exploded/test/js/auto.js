//定义一个被选中的全局高亮节点值
var highlight = -1;

$(document).ready(function(){
    //获取文本框并得到其偏离数据
    var inputBox = $("#word");
    var boxOffset = inputBox.offset();
   //隐藏并固定联想框位置
    $("#showBox").css({
        "border":"1px black solid",
        "top": boxOffset.top + inputBox.height() + 10 +"px",
        "left": boxOffset.left - 3 + "px",
        "position": "absolute"
    }).width(inputBox.width()+2).hide();
   //给文本框添加键盘事件，用于捕捉用户输入内容
    inputBox.keyup(function(event){
        //处理键盘事件
        //获取键盘输入值
        var keyCode = event.keyCode;

        //判断输入内容为英文字母，backspace，或delete时
        if((keyCode>=65 && keyCode<=90) || keyCode==8 || keyCode==46) {
            //1.首先获取文本框的内容
            var wordText = $("#word").val();
            //获取显示框对象
            var autoNode = $("#showBox");
            if(wordText!="") {
                autoNode.empty();
                //2.将内容发给服务器，并显示返回的数据
                $.post("/auto", {word: wordText}, function (data) {
                    //将服务器返回的dom对象转换成Jquery对象
                    var jqueryObj = $(data);
                    //找到所有word节点
                    var wordNodes = jqueryObj.find("word");

                    //遍历所有的word节点，取出内容，然后添加到弹出框中
                    wordNodes.each(function () {
                        //获取单词节点
                        var wordNode = $(this);
                        //新建div节点，并将单词内容加入到新建节点中
                        var newNode = $("<div>");
                        newNode.html(wordNode.text()).appendTo(autoNode);
                        newNode.mouseenter(function(){
                            $(this).css("background-color","blue");
                        });
                        newNode.mouseleave(function(){
                            $(this).css("background-color","white");
                        });
                        newNode.click(function(){
                            var text = $(this).text();
                            $("#showBox").hide();
                            $("#word").val(text);
                        });
                    });
                    if (wordNodes.length > 0) {
                        autoNode.show();
                    } else {
                        autoNode.hide();
                        highlight = -1;
                    }
                }, "xml");
            }else{
                autoNode.hide();
                highlight=-1;
                //alert("null");
            }
        }else if(keyCode==38){
            //输入内容为向上箭头时

            //1.获取显示框中所有子节点
            var allNodes = $("#showBox").children("div");
            //判断指针位置
            if(highlight!=-1){
                //如果指针不在起始位置，则恢复当前选中样式,并且上移指针
                allNodes.eq(highlight).css("background-color","white");
                highlight--;
            }
            if(highlight==-1){
                //如果指针在初始位置，则将指针指向最后一个元素
                highlight = allNodes.length-1;
            }

            allNodes.eq(highlight).css("background-color","blue");

        }else if(keyCode==40){
            //输入内容为向下箭头时
            var allNodes = $("#showBox").children("div");

            if(highlight!=allNodes.length){
                allNodes.eq(highlight).css("background-color","white");
                highlight++;
            }

            if(highlight==allNodes.length){
                highlight=0;
            }

            allNodes.eq(highlight).css("background-color","blue");

        }else if(keyCode==13){
            //如果是回车
            if(highlight!=-1){
            //下拉框有选中内容
                var content = $("#showBox").hide().children("div").eq(highlight).text();
                highlight = -1;
                $("#word").val(content);
            }else{
            //没有下拉框时
                alert("submited");
            }

        }
    });


    $("input[type = 'button']").click(function () {
        alert("works");
    });
});