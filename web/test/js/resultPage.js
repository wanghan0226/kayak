function showDetail(node){
    var detailLink = $(node).parent().parent().parent().parent().parent().next();
    //alert(detailLink);
    detailLink.toggleClass("transferDetail");
}

function changeFunc(){
    var selectBox = document.getElementById("sortBy");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    //alert(selectedValue);
    if(selectedValue==="1"){
        window.location.href ="/sort?type=1";
    }else if(selectedValue==="2"){
        window.location.href ="/sort?type=2";
    }else if(selectedValue==="3"){
        window.location.href ="/sort?type=3";
    }else{
        window.location.href ="/sort?type=4";
    }
}

function isChecked(){
    var one;
    var non;
    document.getElementById("oneStop").checked ? one="checked" : one = "unchecked";
    document.getElementById("nonStop").checked ? non="checked" : non = "unchecked";

    //alert(one + non);
    //
    //alert($("#nonStop").val());
    //alert($("#oneStop").val());
    window.location.href ="/type?one="+one+"&non="+non;
}
