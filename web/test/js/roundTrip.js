//$(document).ready(function(){
//
//    //$('<li id="id_28" class="form-line jf-required allowTime" data-type="control_datetime"> <label id="label_28" class="form-label form-label-left form-label-auto">Return Date/Time <span class="form-required"> * </span> </label> <div id="cid_28" class="form-input jf-required"> <input id="calendar2" type="text" style="width: 250px" placeholder="Return" onblur="checkDate(this)"/> <img id="input_28_pick" class="showAutoCalendar" align="absmiddle" src="http://d2g9qbzl5h49rh.cloudfront.net/images/calendar.png" alt="Pick a Date"/> <span></span> </div> </li>')
//});
$(document).ready(function(){
    $(".typeText").eq(0).addClass("boldFont");
});

function oneWay(){
    $(".typeText").eq(1).removeClass("boldFont");
    $(".typeText").eq(0).addClass("boldFont");
    $("#id_28").remove();
}


function rTrip(){
    $(".typeText").eq(0).removeClass("boldFont");
    $(".typeText").eq(1).addClass("boldFont");
    $('<li id="id_28" class="form-line jf-required allowTime" data-type="control_datetime">' +
    ' <label id="label_28" class="form-label form-label-left form-label-auto">Return Date/Time' +
    ' <span class="form-required"> * </span> </label> <div id="cid_28" class="form-input jf-required"> ' +
    '<input id="calendar2" type="text" style="width: 250px" placeholder="Return" onblur="checkDate(this)"/> ' +
    '<img id="input_28_pick" class="showAutoCalendar" align="absmiddle" src="http://d2g9qbzl5h49rh.cloudfront.net/images/calendar.png" alt="Pick a Date"/>' +
    ' <span></span> </div> </li>').insertAfter("#id_15");
    $('#calendar2').glDatePicker();

}