function showDetail(node){
    var detailLink = $(node).parent().parent().parent().parent().parent().next();
    //alert(detailLink);
    detailLink.toggleClass("transferDetail");
}