function checkEmpty(input){
    var flag;
    var value = $(input).val();
    var spanNode = $(input).parent().children("span");
    if(value==""){
        $(input).addClass("invalideInput");
        spanNode.text("Required field").css("color", "red");
        flag = false;
    }else{
        $(input).removeClass("invalideInput");
        spanNode.text("");
        flag = true;
    }
    return flag;
}

function checkDate(input){
    var flag;
    if(!checkEmpty(input)){
        flag = false;
        return flag;
    }
    var reg = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
    var value = $(input).val();
    var spanNode = $(input).parent().children("span");
    if(!reg.test(value)){
        $(input).addClass("invalideInput");
        spanNode.text("Invalid date format").css("color", "red");
        flag = false;
    }else{
        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth();
        var year = date.getFullYear();
        var now = new Date(year,month,day).getTime();
        var depart = Date.parse(value);
        //alert(now+" "+depart);
        if(depart<now){
            $(input).addClass("invalideInput");
            spanNode.text("Invalid date").css("color", "red");
            flag = false;
        }else {
            $(input).removeClass("invalideInput");
            spanNode.text("");
            flag = true;
        }
    }
    return flag;
}

function checkNum(input){
    var flag;
    var value = $(input).val();
    var spanNode = $(input).parent().children("span");
    if(!checkEmpty(input)){
        flag = false;
        return flag;
    }
    var reg = /^[0-9]*$/
    if(!reg.test(value) || value<1){
        $(input).addClass("invalideInput");
        spanNode.text("Valid Integer Only").css("color", "red");
        flag = false;
    }else{
        $(input).removeClass("invalideInput");
        spanNode.text("");
        flag = true;
    }
    return flag;
}

function checkForm(){
    var deId = $("#airportD");
    var arId = $("#airportA");
    var calId1 = $("#calendar1");
    var calId2 = $("#calendar2");
    var num = $("#input_32");
    //alert(checkEmpty(deId)+" "+checkEmpty(arId)+" "+checkDate(calId1)+" "+calId2.val()+" "+checkNum(num));
    if(calId2.val()==null && checkEmpty(deId) && checkEmpty(arId) && checkDate(calId1) && checkNum(num)){
        return true;
    }
    //alert(checkEmpty(deId)+" "+checkEmpty(arId)+" "+checkDate(calId1)+" "+checkDate(calId2)+" "+checkNum(num));
    if(checkEmpty(deId) && checkEmpty(arId) && checkDate(calId1) && checkDate(calId2) && checkNum(num)){
        return true;
    }
    return false;
}