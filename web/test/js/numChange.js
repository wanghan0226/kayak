

function numPlus(){
    var number = $("#input_32").val();
    var after = parseInt(number)+1;
    $("#input_32").val(after)
}

function numMinus(){
    var number = $("#input_32").val();
    var after = parseInt(number)-1;
    if(after<1){
        return;
    }
    $("#input_32").val(after)
}