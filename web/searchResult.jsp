<%--
  Created by IntelliJ IDEA.
  User: pianobean
  Date: 3/5/15
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <td id="tripTitle">${tripInfo.departCode} - ${tripInfo.arriveCode}</td>
            <c:choose>
              <c:when test="${trip=='single'}">
                <fmt:setLocale value="en_US"/>
                <td><fmt:formatDate value="${tripInfo.goDate}" pattern="MMM dd"/> </td>
              </c:when>
              <c:otherwise>
                <fmt:setLocale value="en_US"/>
                <td><fmt:formatDate value="${tripInfo.goDate}" pattern="MMM dd"/> - <fmt:formatDate value="${tripInfo.backDate}" pattern="MMM dd"/></td>
              </c:otherwise>
            </c:choose>
            <td>${tripInfo.seat}</td>
            <td>${tripInfo.passenger} Traveler</td>
            <td><input id="changeBtn" type="button" value="Change"></td>
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
              <select name="sorting" id="sortBy">
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
          </tr>="
        </table>
      </div>
    </div>
    <%--搜索结果显示--%>
    <div class="rows">
      <c:forEach var="list" items="${showInfo}">
        <c:choose>
          <c:when test="${list.flightType=='ROUND'}">
            <%@include file="/WEB-INF/jsp/RoundFlightModel.jsp"%>
          </c:when>
          <c:when test="${list.flightType=='SINGLE'}">
            <%@include file="/WEB-INF/jsp/SingleFlightModel.jsp"%>
          </c:when>
        </c:choose>
        <%--<c:choose>--%>
          <%--<c:when test="${map.key.getClass().name=='java.lang.String'}">--%>
            <%--<c:when test="${map.key=='ONE' && trip=='single'}">--%>
              <%--<%@include file="/WEB-INF/jsp/singleOneFlight.jsp"%>--%>
            <%--</c:when>--%>
            <%--&lt;%&ndash;该页全是转机路线,并且是往返航线&ndash;%&gt;--%>
            <%--<c:when test="${map.key=='ONE' && trip=='round'}">--%>
              <%--<%@include file="/WEB-INF/jsp/roundOneFlight.jsp"%>--%>
            <%--</c:when>--%>
            <%--</c:choose>--%>
          <%--</c:when>--%>
          <%--&lt;%&ndash;既有直飞又有转机&ndash;%&gt;--%>
          <%--<c:otherwise>--%>
            <%--<c:choose>--%>
              <%--&lt;%&ndash;单程航班&ndash;%&gt;--%>
              <%--<c:when test="${trip=='single'}">--%>
                <%--<%@include file="/WEB-INF/jsp/mixSingle.jsp"%>--%>
              <%--</c:when>--%>
              <%--&lt;%&ndash;往返航班&ndash;%&gt;--%>
              <%--<c:when test="${trip=='round'}">--%>
                <%--<%@include file="/WEB-INF/jsp/mixRound.jsp"%>--%>
              <%--</c:when>--%>
            <%--</c:choose>--%>
          <%--</c:otherwise>--%>
        <%--</c:choose>--%>
      </c:forEach>
    </div>
  </div>
  <div id="pageSeparator">
    <c:choose>
      <c:when test="${currentPage==1}">
        Prev
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/page?pageNumber=${currentPage-1}">Prev</a>
      </c:otherwise>
    </c:choose>
    <span>
      <c:forEach var="i" begin="1" end="${totalPages}" >
        <c:choose>
          <c:when test="${i==currentPage}">
              ${i}
          </c:when>
          <c:otherwise>
            <a href="${pageContext.request.contextPath}/page?pageNumber=${i}">
                ${i}
            </a>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </span>
    <c:choose>
      <c:when test="${currentPage==totalPages}">
        Next
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/page?pageNumber=${currentPage+1}">Next</a>
      </c:otherwise>
    </c:choose>
  </div>

${showInfo}
${trip}

</body>
</html>
