<%--
  Created by IntelliJ IDEA.
  User: pianobean
  Date: 3/14/15
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%--单元格格式--%>
<div class="cell">
  <%--左半部分--%>
  <div class="left">
    <div class="price">
      $277
    </div>
    <div class="select">
      <input type="button" value="Select"/>
    </div>
  </div>
  <%--右半部分--%>
  <div class="right">
    <table class="flightDetail">
      <tr>
        <td><img src="/test/image/0W.gif"></td>
        <td>AirlineName</td>
        <td>BOS <b>7:30AM</b></td>
        <td>JFK <b>8:20PM</b></td>
        <td class="minorInfo">2h 15m</td>
        <td class="minorInfo">1 stop(IST)</td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td><b></b></td>
        <td><b></b></td>
        <td class="minorInfo"></td>
        <td class="minorInfo"></td>
      </tr>
      <tr>
        <td colspan="4"><a href="#" onclick="showDetail(this)" class="detailLink">Show Detail</a></td>
      </tr>
    </table>
  </div>
  <%--飞行转机细节--%>
  <div class="transferDetail">
    <%--出发航程--%>
    <div class="departDetail">
      <table>
        <tr>
          <td colspan="5"><div class="subTitle">Depart: Boston, MA, Logan Intl (BOS) , Tue, Mar 10</div></td>
        </tr>
        <tr>
          <td><img src="/test/image/0W.gif"></td>
          <td>
            <div class="floatTitle">American Airlines</div>
            <div class="minorInfo">Flight 8475</div>
          </td>
          <td>
            <div class="floatTitle">BOS <b>1:00 PM</b></div>
            <div class="minorInfo">Tuesday</div>
          </td>
          <td>
            <div class="floatTitle">NRT <b>3:35 PM</b></div>
            <div class="minorInfo">Wednesday</div>
          </td>
          <td class="minorInfo">13hr 35min</td>
        </tr>
        <tr>
          <td colspan="5"><div class="subTitle">Connection in Tokyo, Japan, Narita Intl (NRT) for 2hr 30min</div></td>
        </tr>
        <tr>
          <td><img src="/test/image/0W.gif"></td>
          <td>
            <div class="floatTitle">American Airlines</div>
            <div class="minorInfo">Flight 8475</div>
          </td>
          <td>
            <div class="floatTitle">BOS <b>1:00 PM</b></div>
            <div class="minorInfo">Tuesday</div>
          </td>
          <td>
            <div class="floatTitle">NRT <b>3:35 PM</b></div>
            <div class="minorInfo">Wednesday</div>
          </td>
          <td class="minorInfo">13hr 35min</td>
        </tr>
        <tr>
          <td colspan="5"><div class="subTitle">Arrive: Beijing, China, Beijing Capital Int. (PEK) , Wed, Mar 11</div></td>
        </tr>
      </table>
    </div>
    <%--返回航程--%>
    <div class="departDetail">
      <table>
        <tr>
          <td colspan="5"><div class="subTitle"></div></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <div class="floatTitle"></div>
            <div class="minorInfo"></div>
          </td>
          <td>
            <div class="floatTitle"> <b></b></div>
            <div class="minorInfo"></div>
          </td>
          <td>
            <div class="floatTitle"> <b></b></div>
            <div class="minorInfo"></div>
          </td>
          <td class="minorInfo"></td>
        </tr>
        <tr>
          <td colspan="5"><div class="subTitle"></div></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <div class="floatTitle"></div>
            <div class="minorInfo"></div>
          </td>
          <td>
            <div class="floatTitle"><b></b></div>
            <div class="minorInfo"></div>
          </td>
          <td>
            <div class="floatTitle"> <b></b></div>
            <div class="minorInfo"></div>
          </td>
          <td class="minorInfo"></td>
        </tr>
        <tr>
          <td colspan="5"><div class="subTitle"></div></td>
        </tr>
      </table>
    </div>
  </div>
</div>
</body>
</html>
