function showDetail(node){
    var detailLink = $(node).parent().parent().parent().parent().parent().next();
    //alert(detailLink);
    detailLink.toggleClass("transferDetail");
    //
    //if (stoptype == "NON_STOP") {
    //    var detailLink = $(node).parent().parent().parent().next();
    //    detailLink.toggleClass("nonStopTransferDetail");
    //}else if(stoptype == "ONE_STOP") {
    //    var detailLink = $(node).parent().parent().parent().next().next();
    //    detailLink.toggleClass("oneStopTransferDetail");
    //}
}