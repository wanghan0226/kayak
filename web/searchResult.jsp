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
              <select id="sortBy" onchange="changeFunc()">
                <option></option>
                <option value="1" ${fn:contains(selected, "priceAsc")?'selected':''}>price(low to high)</option>
                <option value="2" ${fn:contains(selected, "priceDes")?'selected':''}>price(high to low)</option>
                <option value="3" ${fn:contains(selected, "timeAsc")?'selected':''}>duration(shorter to longer)</option>
                <option value="4" ${fn:contains(selected, "timeDes")?'selected':''}>duration(longer to shorter)</option>
              </select>
            </td>
            <td style="text-align: left">
              <input id="nonStop" type="checkbox" name="stops" value="NON" onchange="isChecked()" ${fn:contains(fn:join(showType, ","), "NON")?'checked':''}>nonstop<br>
              <input id="oneStop" type="checkbox" name="stops" value="ONE" onchange="isChecked()" ${fn:contains(fn:join(showType, ","), "ONE")?'checked':''}>multi-stop<br>
            </td>
          </tr>
        </table>
      </div>
    </div>
    <%--搜索结果显示--%>
      <c:choose>
        <c:when test="${type=='oneWay'}">
          <c:forEach var="showItem" items="${showInfo}">
            <c:choose>
              <%--单程航班直飞--%>
              <c:when test="${showItem.value.getClass().name=='beans.Flight'}">
                <%@include file="/WEB-INF/jsp/singleNon.jsp"%>
              </c:when>
              <%--单程航班并且有转机--%>
              <c:otherwise>
                <%@include file="/WEB-INF/jsp/singleOne.jsp"%>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <c:forEach var="showItem" items="${showInfo}">
            <c:choose>
              <%--往返航班直飞--%>
              <c:when test="${showItem.value['0'].getClass().name=='beans.Flight'}">
                <%@include file="/WEB-INF/jsp/roundNon.jsp"%>
              </c:when>
              <%--往返航班并且有转机--%>
              <c:otherwise>
                <%@include file="/WEB-INF/jsp/roundOne.jsp"%>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
  <div id="pageSeparator">
    <%--<c:forEach var="pageBean" items="${pagebean.pageBar}" varStatus="i">--%>
      <%--<a href="${pageContext.request.contextPath}/page?pageNumber=${i}">--%>
          <%--${pageBean[i]}--%>
      <%--</a>--%>
    <%--</c:forEach>--%>
      <c:choose>
        <c:when test="${pagebean.currentPage==1}">
          Prev
        </c:when>
        <c:otherwise>
          <a href="${pageContext.request.contextPath}/perfect?pageNumber=${pagebean.prePage}">Prev</a>
        </c:otherwise>
      </c:choose>
      <c:forEach var="pageNum" items="${pagebean.pageBar}">
        <c:if test="${pageNum==pagebean.currentPage}">
          <b style="color: #cc0000">${pageNum}</b>
        </c:if>
        <c:if test="${pageNum!=pagebean.currentPage}">
          <a href="${pageContext.request.contextPath}/perfect?pageNumber=${pageNum}">${pageNum}</a>
        </c:if>
      </c:forEach>
      <c:choose>
        <c:when test="${pagebean.currentPage==pagebean.totalPage}">
          Next
        </c:when>
        <c:otherwise>
          <a href="${pageContext.request.contextPath}/perfect?pageNumber=${pagebean.nextPage}">Next</a>
        </c:otherwise>
      </c:choose>
  </div>

${showInfo}
${trip}

</body>
</html>
