<%--
  Created by IntelliJ IDEA.
  User: pianobean
  Date: 3/5/15
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Flight Info</title>
    <link type="text/css" href="/test/css/result.css" rel="stylesheet">
    <script type="text/javascript" src="/test/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="/test/js/resultPage.js"></script>
</head>
<body>
  <div id="main">
    <%--顶部条件选择区域--%>
    <div id="conditions">
      <%--再次搜索选择内容--%>
      <div id="changeCon">
        <table id="originalInfo">
          <tbody>
            <tr>
              <td id="tripTitle">BOS - NYC</td>
              <td>Mar 18 - Mar 21</td>
              <td>Economy</td>
              <td>1 Traveler</td>
              <td><input id="changeBtn" type="button" value="Change"></td>
            </tr>
            <tr>
              <td></td>
            </tr>
          </tbody>
        </table>
      </div>
      <%--对结果进行分类排序--%>
      <div id="sorting">
        <table id="lookUpCondition">
          <tr>
            <td>
              Sort by:
              <select id="sortBy">
                <option>price(low to high)</option>
                <option>price(high to low)</option>
                <option>airline(A to Z)</option>
                <option>airline(Z to A)</option>
                <option>duration(shorter to longer)</option>
                <option>duration(longer to shorter)</option>
              </select>
            </td>
            <td style="text-align: left">
              <input type="checkbox" name="nonStop" value="0" checked>nonstop<br>
              <input type="checkbox" name="multiStop" value="1" checked>multi-stop<br>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <%--搜索结果显示--%>
    <div class="rows">
      <%--<c:forEach var="oneStopFlight" items="">--%>
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
              <td><img src="/test/image/0W.gif"></td>
              <td>AirlineName</td>
              <td>BOS <b>7:30AM</b></td>
              <td>JFK <b>8:20PM</b></td>
              <td class="minorInfo">2h 15m</td>
              <td class="minorInfo">1 stop(IST)</td>
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
            <hr>
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
                  <div class="floatTitle">NRT  <b>3:35 PM</b></div>
                  <div class="minorInfo">Wednesday</div>
                </td>
                <td class="minorInfo">13hr 35min</td>
              </tr>
              <tr>
                <td colspan="5"><div class="subTitle">Arrive: Beijing, China, Beijing Capital Int. (PEK) , Wed, Mar 11</div></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
      <%--</c:forEach>--%>
      <%--分页--%>
      <%--<div id="page">--%>
        <%--1.......5--%>
      <%--</div>--%>
    </div>
  </div>
${goNonStop}
</body>
</html>
