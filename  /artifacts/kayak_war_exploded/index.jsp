<%--
  Created by IntelliJ IDEA.
  User: pianobean
  Date: 2/26/15
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
  <link title="oEmbed Form" href="https://www.jotform.com/oembed/?format=json&url=http%3A%2F%2Fwww.jotform.com%2Fform%2F50567096425157" type="application/json+oembed" rel="alternate">
  <link title="oEmbed Form" href="https://www.jotform.com/oembed/?format=xml&url=http%3A%2F%2Fwww.jotform.com%2Fform%2F50567096425157" type="text/xml+oembed" rel="alternate">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
  <meta content="true" name="HandheldFriendly">
  <title>Online Booking Form</title>
  <link type="text/css" rel="stylesheet" href="http://d2g9qbzl5h49rh.cloudfront.net/static/formCss.css?3.2.5843">
  <link href="http://d2g9qbzl5h49rh.cloudfront.net/css/styles/nova.css?3.2.5843" rel="stylesheet" type="text/css">
  <link href="http://d2g9qbzl5h49rh.cloudfront.net/css/printForm.css?3.2.5843" rel="stylesheet" media="print" type="text/css">
  <link href="/test/css/glDatePicker.default.css" rel="stylesheet" type="text/css">
  <link href="/test/css/select.css" rel="stylesheet" type="text/css"/>
  <style type="text/css">
    .invalideInput{
      border: 3px solid red;
    }
    .form-label-left{
      width:150px !important;
    }
    .form-line{
      padding-top:12px;
      padding-bottom:12px;
    }
    .form-label-right{
      width:150px !important;
    }
    body, html{
      margin:0;
      padding:0;
      /*background:false;*/
    }
    .form-all{
      margin:0px auto;
      padding-top:0px;
      width:690px;
      color:#555 !important;
      font-family:'Lucida Grande',' Lucida Sans Unicode',' Lucida Sans',' Verdana',' Tahoma',' sans-serif';
      font-size:14px;
    }
  </style>
  <script type="text/javascript" src="http://d2g9qbzl5h49rh.cloudfront.net/static/prototype.forms.js" />
  <script type="text/javascript" src="http://d2g9qbzl5h49rh.cloudfront.net/static/jotform.forms.js?3.2.5843"/>
  <script type="text/javascript">
    var jsTime = setInterval(function(){try{
      JotForm.jsForm = true;
      JotForm.init(function(){
        JotForm.calendarMonths = ["January","February","March","April","May","June","July","August","September","October","November","December"];
        JotForm.calendarDays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
        JotForm.calendarOther = {"today":"Today"};
        JotForm.setCalendar("15", false);
        JotForm.displayLocalTime("hour_15", "min_15", "ampm_15");
        JotForm.calendarMonths = ["January","February","March","April","May","June","July","August","September","October","November","December"];
        JotForm.calendarDays = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
        JotForm.calendarOther = {"today":"Today"};
        JotForm.setCalendar("28", false);
        JotForm.displayLocalTime("hour_28", "min_28", "ampm_28");
        $('input_32').spinner({ imgPath:'http://d2g9qbzl5h49rh.cloudfront.net/images/', width: '60', maxValue:'', minValue:'', allowNegative: false, addAmount: 1, value:'0' });
      });
      clearInterval(jsTime);
    }catch(e){}}, 1000);
  </script>
  <script type="text/javascript" src="/test/js/jquery-2.1.3.min.js"></script>
  <script type="text/javascript" src="/test/js/trip.js"></script>
  <script type="text/javascript" src="/test/js/back.js"></script>
  <script type="text/javascript" src="/test/js/glDatePicker.js"></script>
  <script type="text/javascript" src="/test/js/validate.js"></script>
  <script type="text/javascript" src="/test/js/roundTrip.js"></script>
  <script type="text/javascript" src="/test/js/numChange.js"></script>

  <script type="text/javascript">
    $(window).load(function()
    {
      $('#calendar1').glDatePicker();
    });
  </script>
</head>
<body>
<form id="50567096425157" class="jotform-form" method="post" action="/search" novalidate="true" onsubmit="return checkForm()">
  <div class="form-all">
    <ul class="form-section">
      <li id="cid_27" class="form-input-wide" data-type="control_head">
        <div class="form-header-group">
          <div class="header-text httal htvam">
            <%--<h1 id="header_27" class="form-header"> Online Booking Form </h1>--%>
            <img id="logo" src="/test/image/logo.jpg" width="500" height="100">
          </div>
        </div>
      </li>
      <li id="id_11" class="form-line jf-required" data-type="control_textarea">
        <label id="label_11" class="form-label form-label-left form-label-auto">
          Depart From
          <span class="form-required"> * </span>
        </label>
        <div id="cid_11" class="form-input jf-required">
          <input type="text" placeholder="Departure Airport Name" name="deCode" id="airportD" style="width: 400px" onblur="checkEmpty(this)">
          <span class="erroMessage"></span>
        </div>
        <div id="deAirport"></div>
      </li>
      <li id="id_12" class="form-line jf-required" data-type="control_textarea">
        <label id="label_12" class="form-label form-label-left form-label-auto">
          Arrive To
          <span class="form-required"> * </span>
        </label>
        <div id="cid_12" class="form-input jf-required">
          <input id="airportA" name="arCode" placeholder="Arrival Airport Name" type="text" style="width:400px" onblur="checkEmpty(this)" />
          <span class="erroMessage"></span>
        </div>
        <div id="arAirport"></div>
      </li>
      <li id="id_19" class="form-line jf-required" data-type="control_dropdown">
        <label id="label_19" class="form-label form-label-left form-label-auto" >
          Journey Type
          <span class="form-required"> * </span>
        </label>
        <div id="cid_19" class="form-input jf-required">
          <table id="tripType">
            <tbody>
            <tr>
              <td class="typeText">One-way</td><td><input class="radio" type="radio" name="type" value="oneWay" onclick="oneWay()" checked></td>
              <td class="typeText">Round-trip</td><td><input class="radio" type="radio" name="type" value="roundTrip" onclick="rTrip()" /></td>
            </tr>
            </tbody>
          </table>
        </div>
      </li>
      <li id="id_15" class="form-line jf-required allowTime" data-type="control_datetime">
        <label id="label_15" class="form-label form-label-left form-label-auto">
          Departure Date/Time
          <span class="form-required"> * </span>
        </label>
        <div id="cid_15" class="form-input jf-required">
          <input id="calendar1" type="text" style="width: 250px" name="deDate" placeholder="Depart" onblur="checkDate(this)"/>
          <img id="input_15_pick" class="showAutoCalendar" align="absmiddle" src="http://d2g9qbzl5h49rh.cloudfront.net/images/calendar.png" alt="Pick a Date">
          <span></span>
        </div>
      </li>
      <li id="id_33" class="form-line jf-required" data-type="control_dropdown">
        <label id="label_33" class="form-label form-label-left form-label-auto" for="input_33">
          Seat
          <span class="form-required"> * </span>
        </label>
        <div id="cid_33" class="form-input jf-required">
          <select id="input_33" class="form-dropdown validate[required]" name="seat" style="width:150px">
            <option value="First"> First </option>
            <option value="Coach"> Coach </option>
          </select>
        </div>
      </li>
      <li id="id_32" class="form-line jf-required" data-type="control_spinner">
        <label id="label_32" class="form-label form-label-left form-label-auto" for="input_32">
          Number of Passengers
          <span class="form-required"> * </span>
        </label>
        <div id="cid_32" class="form-input jf-required">
            <input type="button" class="numButton" id="plus" width="50" value="-" onclick="numMinus()">
            <input id="input_32" name="passenger" type="text" style="width: 50px;" value="1" onblur="checkNum(this)"/>
            <input type="button" class="numButton" id="minus" width="50" value="+" onclick="numPlus()">
            <span></span>
        </div>
      </li>
      <li id="id_1" class="form-line" data-type="control_button">
        <div id="cid_1" class="form-input-wide">
          <div class="form-buttons-wrapper" style="margin-left:156px">
            <input class="form-submit-button" style="background: #e46c15; color: #E4DFDF; width: 150px; font-family: 'Arial', sans-serif; border: none" type="submit" value="Search"/>
          </div>
        </div>
      </li>
    </ul>
  </div>
</form>
</body>
</html>